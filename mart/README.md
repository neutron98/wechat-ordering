
# Wechart ordering system

A food ording Wechat app, which allows users to order items and merchant to manage the products. 


1. [Overview](#overview)
    + [Environment](#environment)
    + [Software Architecture](#software-architecture)
2. [API Description](#api-description)
    + [Product](#product)
    + [Order](#order)
    + [Wechat OAth 2.0 and Payment](#wechat-oath-20-and-payment)
3. [Status code description](#status-code-description)


## Overview

### Environment
* Spring Boot 1.5.3.RELEASE
* JDK 1.8
* MySQL 5.7
* Maven 4.0.0
* Nginx 1.11.7
* CentOS 7.3.1611
* Vue.js


### Software Architecture
Software architecture description

## API Description
API for user-facing operations

### Product

#### Product list

```
GET /sell/buyer/product/list
```

Response Body

```
{ 
   "code":0,
   "msg":"success",
   "data":[ 
      { 
         "name":"popular",
         "type":2,
         "foods":[ 
            { 
               "id":"1423113435324",
               "name":"pumpkin pie",
               "price":8.00,
               "description":"Home-made pumpkin pie",
               "icon":"https://food.fnr.sndimg.com/content/dam/images/food/fullset/2013/11/26/0/FN_Pumpkin-Pie_s4x3.jpg.rend.hgtvcom.826.620.suffix/1386172256183.jpeg"
            },
            { 
               "id":"123457",
               "name":"ham sandwitch",
               "price":4.00,
               "description":"Healthy sandwitch",
               "icon":"https://indianakitchen.com/wp-content/uploads/2015/03/Ham-Sandwich.jpg"
            }
         ]
      }
   ]
}
```

### Order

#### Create order

```
POST /sell/buyer/order/create
```

Request Body

```
name: "Jerry"
phone: "1234567890"
address: "123 Centre Ave"
openid: "ew3euwhd7sjw9diwkq" //User's Wechat openid
items: [{
    productId: "1423113435324",
    productQuantity: 2 //Purchase Quantity
}]

```
Response Body

```
{
  "code": 0,
  "msg": "Success",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

#### Get Order list

```
GET /sell/buyer/order/list
```

Parameters

```
openid: 18eu2jwk2kse3r42e2e
page: 0 //Start from page 0
size: 10
```

Response Body

```
{
  "code": 0,
  "msg": "Success",
  "data": [
    {
      "orderId": "161873371171128075",
      "buyerName": "Mike",
      "buyerPhone": "4121112365",
      "buyerAddress": "321 Fifth Ave",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    },
    {
      "orderId": "161873371171128076",
      "buyerName": "Bob",
      "buyerPhone": "4125846985",
      "buyerAddress": "888 Main Street",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    }]
}
```

#### View order status

```
GET /sell/buyer/order/detail
```

Parameters

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

Return

```
{
    "code": 0,
    "msg": "Success",
    "data": {
          "orderId": "161899085773669363",
          "buyerName": "Jerry",
          "buyerPhone": "1234567890",
          "buyerAddress": "123 Centre Ave",
          "buyerOpenid": "18eu2jwk2kse3r42e2e",
          "orderAmount": 12.00,
          "orderStatus": 0,
          "payStatus": 0,
          "createTime": 1490177352,
          "updateTime": 1490177352,
          "orderDetailList": [
            {
                "detailId": "161899085974995851",
                "orderId": "161899085773669363",
                "productId": "1423113435324",
                "productName": "Pumpkin Pie",
                "productPrice": 6.00,
                "productQuantity": 2,
                "productIcon": "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2013/11/26/0/FN_Pumpkin-Pie_s4x3.jpg.rend.hgtvcom.826.620.suffix/1386172256183.jpeg"
            }
        ]
    }
}
```

#### Cancel Order

```
POST /sell/buyer/order/cancel
```

Parameters

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```
Response Body

```
{
    "code": 0,
    "msg": "Success",
    "data": null
}
```
### Wechat OAth 2.0 and Payment

#### Get OpenId

```
Redirect: /sell/wechat/authorize
```

Parameters

```
returnUrl: http://xxx.com/abc  //[Required]
```

Return

```
http://xxx.com/abc?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg
```

#### Pay an order
```
Redirect: /sell/pay/create
```

Parameters

```
orderId: 161899085773669363
returnUrl: http://xxx.com/abc/order/161899085773669363
```

Return

```
http://xxx.com/abc/order/161899085773669363
```


## Status code description

Status code and messages in response body and exceptions. 


#### Success (Example)
```
{
  "code": 0,
  "msg": "Success"
}
```


#### Errors (Example)
```
{
  "code": 10001,
  "msg": "Product does not exist"
}
```

#### Full Reference Table

| Type    | Code | Message                                              |
| ------- | ---- | ---------------------------------------------------- |
| Common  | 0    | Success                                              |
| Common  | 1    | Invalid parameters                                   |
| Product | 1001 | Product does not exist                               |
| Product | 1002 | Invalid stock quantity                               |
| Product | 1003 | Invalid product status                               |
| Order   | 1101 | Order does not exist                                 |
| Order   | 1102 | Order detail does not exist                          |
| Order   | 1103 | Invalid order status                                 |
| Order   | 1104 | Order update failed                                  |
| Order   | 1105 | Order detail is empty                                |
| Order   | 1106 | You have successfully canceled the order             |
| Order   | 1107 | You have successfully finished the order             |
| Payment | 1201 | Invalid payment status                               |
| Payment | 1202 | Cart can not be empty                                |
| Payment | 1203 | The order does not belong to current user            |
| Payment | 1204 | Error from WeChat Public Account                     |
| Payment | 1205 | Wrong amount in WeChat pay asynchronous notification |
| Account | 2001 | Login failed: wrong login message                    |
| Account | 2002 | You have successfully logged out                     |


