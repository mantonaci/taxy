[![license](https://img.shields.io/github/license/mashape/apistatus.svg?maxAge=2592000?style=flat-square)](https://opensource.org/licenses/MIT)
[![Build Status](https://travis-ci.org/mantonaci/taxy.svg?branch=master)](https://travis-ci.org/mantonaci/taxy)
[![Coverage Status](https://coveralls.io/repos/github/mantonaci/taxy/badge.svg?branch=master)](https://coveralls.io/github/mantonaci/taxy?branch=master)

# Taxy

- [Overview](#Overview)
- [Requirements](#Requirements)
- [Documentation](#Documentation)
  - [Test](#Test)
  - [Rest API](#rest-api)

## <a name='Overview'>Overview<a/>

RESTFul Web APIs to calculate invoices, with tax (basic sales tax and import sales tax), for any kind of products.

## <a name='Requirements'>Requirements<a/>

Taxy requires JDK 7 or higher.

- [Apache Maven 3+](https://maven.apache.org/)
- [WildFly 10+](http://wildfly.org/downloads/) o [JBoss 7+](http://developers.redhat.com/products/eap/download/)

## <a name='Documentation'>Documentation<a/>

You can verify [Sales taxes problem](https://github.com/xpeppers/sales-taxes-problem) by [Test](#Test) or [Rest API](#rest-api).

Before start you should execute ```mvn clean install``` in root project.

### <a name='Test'>Test<a/>

Execute ```mvn test``` and check logs to verify test cases in [Sales taxes problem](https://github.com/xpeppers/sales-taxes-problem), you can search **SHIPPING BASKET n** (**n** is **TEST CASE** number) keyword.

### <a name='rest-api'>Rest API<a/>

Alternative you can verify the test cases via REST API. Go into taxy-api module and execute this command ```mvn wildfly:run``` to start application server by **wildfly-maven-plugin** or compile project with ```mvn package``` and install ```taxy.war``` in existing WildFly server.

```maven
<plugins>
  <plugin>
    <groupId>org.wildfly.plugins</groupId>
    <artifactId>wildfly-maven-plugin</artifactId>
  </plugin>
</plugins>
```
Use [Rest Client](https://www.getpostman.com) or ```curl``` to call APIs. Below, **TEST CASES** verification with ```curl```:

**TEST CASE 1**
```
curl -H "Content-Type: application/json" -X POST -d '[{"title":"Crypto","price":12.49,"category":"BOOK","imported": false},{"title": "Mumford & Sons CD","price":14.99,"category":"MUSIC","imported":false},{"title":"Ferrero chocolate","price": 0.85,"category":"FOOD", "imported":false}]' http://localhost:8080/taxy/api/invoice
```
```
OUTPUT

{
  "products": [
    {
      "title": "Crypto",
      "price": 12.49,
      "taxedPrice": 12.49,
      "category": "BOOK",
      "imported": false
    },
    {
      "title": "Mumford & Sons CD",
      "price": 14.99,
      "taxedPrice": 16.49,
      "category": "MUSIC",
      "imported": false
    },
    {
      "title": "Ferrero chocolate",
      "price": 0.85,
      "taxedPrice": 0.85,
      "category": "FOOD",
      "imported": false
    }
  ],
  "salesTax": 1.5,
  "totalPrice": 29.83
}
```

**TEST CASE 2**
```
curl -H "Content-Type: application/json" -X POST -d '[{"title":"Perugina box chocolates","price":10,"category":"FOOD","imported":true},{"title":"One million","price":47.5,"category":"PERFUME","imported":true}]' http://localhost:8080/taxy/api/invoice
```
```
OUTPUT

{
  "products": [
    {
      "title": "Perugina box chocolates",
      "price": 10,
      "taxedPrice": 10.5,
      "category": "FOOD",
      "imported": true
    },
    {
      "title": "One million",
      "price": 47.5,
      "taxedPrice": 54.65,
      "category": "PERFUME",
      "imported": true
    }
  ],
  "salesTax": 7.65,
  "totalPrice": 65.15
}
```

**TEST CASE 3**
```
curl -H "Content-Type: application/json" -X POST -d '[{"title":"D&G","price":27.99,"category":"PERFUME","imported":true},{"title":"Ugo Boss","price":18.99,"category":"PERFUME","imported":false},{"title":"Aulin","price":9.75,"category":"MEDICAL","imported":false},{"title":"Novi chocolate","price":11.25,"category":"FOOD","imported":true}]' http://localhost:8080/taxy/api/invoice
```
```
OUTPUT

{
  "products": [
    {
      "title": "D&G",
      "price": 27.99,
      "taxedPrice": 32.19,
      "category": "PERFUME",
      "imported": true
    },
    {
      "title": "Ugo Boss",
      "price": 18.99,
      "taxedPrice": 20.89,
      "category": "PERFUME",
      "imported": false
    },
    {
      "title": "Aulin",
      "price": 9.75,
      "taxedPrice": 9.75,
      "category": "MEDICAL",
      "imported": false
    },
    {
      "title": "Novi chocolate",
      "price": 11.25,
      "taxedPrice": 11.85,
      "category": "FOOD",
      "imported": true
    }
  ],
  "salesTax": 6.7,
  "totalPrice": 74.68
}
```
Enjoy!


 
