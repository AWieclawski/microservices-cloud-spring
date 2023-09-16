# HTTP request Examples 
# incl. CLI commands for use in Terminal

# GET http://client:s3cr3t@localhost:8871/dev-02/discovery-server-development.yml
# more info: https://reqbin.com/req/c-1n4ljxb9/curl-get-request-example
```
curl http://client:s3cr3t@localhost:8871/dev-02/discovery-server-development.yml \
   -H "Accept: application/json"
```

# GET http://localhost:20001/test/discovery_instances
```
curl http://localhost:20001/test/discovery_instances \
   -H "Accept: application/json"
```

# GET http://localhost:20001/test/request

# GET http://localhost:10001/test/discovery_instances
```
curl http://localhost:10001/test/discovery_instances \
   -H "Accept: application/json"
```

# GET http://localhost:10001/test/request

# GET http://localhost:20001/shop/1/orders/delivery_date
```
curl http://localhost:20001/shop/1/orders/delivery_date \
   -H "Accept: application/json"
```
# GET http://localhost:20001/shop/2/orders
```
curl http://localhost:20001/shop/1/orders \
   -H "Accept: application/json"
```

# GET http://localhost:20001/shop/sales?ids=1,2
```
curl http://localhost:20001/shop/sales?ids=1,2 \
   -H "Accept: application/json"
```
# via gateway-service: 
# GET http://localhost:8080/shop/1/orders/delivery_date
```
curl http://localhost:8080/shop/1/orders/delivery_date \
   -H "Accept: application/json"
```

# GET http://localhost:11001/payment/3
```
curl http://localhost:11001/payment/3 \
   -H "Accept: application/json"
```

# POST http://localhost:1101/payment 
```
curl –X POST 'http://localhost:1101/payment' \
-H 'Content-Type: application/json' \
-data '{"id": "123", "businessEntity": "Company Inc", "subject": "Abxd"}'
```



# POST vide: https://reqbin.com/req/c-dwjszac0/curl-post-json-example
```
curl –X POST 'http://localhost:10000/payments' \
-H 'Content-Type: application/json' \
-data '{"value": "200 PLN"}'
```

# shop-service test 
```
curl `http://localhost:8080/shop-service/shop/test`
```

# shop-service instances registered in discover services 
```
curl `http://localhost:8080/shop-service/shop/discover_instances`
```

# curl http://localhost:20001/shop/1/asd
