#!/usr/bin/env bash
# vide https://stackoverflow.com/a/77118022/11868833
# In case of different localisations of parametrized servers
# appropriate GV (Global Variables) must be individually implemented in each environment.
# Restart OS after setup to make GV available for IDE context

declare -A dflts=(
  [CONFIG_SERVER_USER]='client'
  [CONFIG_SERVER_PASS]='s3cr3t'
  [CONFIG_SERVER_PORT]='887'
  [CONFIG_SERVER_LABEL]='dev-03'
  [DISCOVERY_SERVER_PORT]='888'
  [DISCOVERY_REGISTRY_INTERVAL]='5'
  [ACTIVE_PROFILE]='development'
  [KAFKA_SERVER_PORT]='8901'
  [ZOOKEEPER_SERVER_PORT]='8911'
)

for name in "${!dflts[@]}"; do
  if [[ -v $name ]]; then
    printf 'Variable "%s" already set to "%s"\n' \
      "$name" "${!name}"
  else
    printf 'Assigning variable "%s" to "%s"\n' \
      "$name" "${!name:=${dflts[$name]}}"
    printf "%s='%s'\n" "$name" "${!name}" >>/etc/environment
    export name
  fi
done
echo "If any problem with Global Variables type in CLI: 'cat /etc/environment' to read or 'sudo nano /etc/environment' to edit."
