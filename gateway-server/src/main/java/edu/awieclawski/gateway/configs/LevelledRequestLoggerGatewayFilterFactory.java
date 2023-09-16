package edu.awieclawski.gateway.configs;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
@Log4j2
public class LevelledRequestLoggerGatewayFilterFactory
        extends AbstractGatewayFilterFactory<LevelledRequestLoggerGatewayFilterFactory.Config> {

    public LevelledRequestLoggerGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.log(config.level, String.format("Gateway Service handles the new request for path: %s", exchange.getRequest().getPath()));
            ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
            return chain.filter(exchange.mutate().request(builder.build()).build());
        };
    }

    /**
     * Method helps to map the number and order of arguments to the filter.
     * The string in argument matches to the setter and field label in the Config class.
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return singletonList(getFirstConfigField());
    }

    private String getFirstConfigField() {
        var found = Arrays.stream(Config.class.getDeclaredFields()).findFirst();
        return found.map(Field::getName).orElse("");
    }

    @NoArgsConstructor
    public static class Config {

        public Config(String level) {
            this.setLevel(level);
        }

        private Level level;

        public void setLevel(String level) {
            Level found = Level.getLevel(level);
            this.level = found != null ? found : Level.OFF;
        }
    }

}

