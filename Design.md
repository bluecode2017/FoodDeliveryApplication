# FoodDeliveryApplication

## 微服务整体架构：

提供4个微服务：
* Restaurant & menu service   简称RMS
* User service                简称US （以后扩展）
* Order service               简称OS
* Payment Service             简称PS
* Delivery Service            简称DS

### 微服务之间的调用关系：
* 1.客户向浏览器发送请求查看Restaurantmenu，RMS调用接口根据name获取restaurant同时获取对应的menu ，返回菜单内容到前端
* 2.客户选择菜品和数量，发送请求给OS，传递 fooditem，quantity，ordertime，note，deliveryaddress
* 3.OS获得后，计算总价，发送请求给PS，传递总价和userid，
* 4.PS接收userid和总价，调用pay接口，完成后，返回成功标记给OS，OS调用接口修改order status和delivery time
* 5.OS返回修改后的订单信息（包含deliverytime）给UI

### 存储
* UI微服务没有数据存储，其余3个微服务有自己独立的数据存储表，
* Restaurant & menu service 里
使用relational model设计（有表restaurant，menu，food 呈现 hierarchy关系。 其中restaurant 和 menu 是1:1 以后可以扩展为1：n， menu 和food 是1：n关系。）
这里假设1个restaurant只有1份menu（不分中午menu和晚餐menu）,

* User service里
使用relational model设计，有user表

* Order Service里
使用relational model设计，有order表，payment表 （order和payment是1：1关系，以后扩展为1：n关系处理order 不同user split 分开付款的情况）

## 设计Rest API endpoint
Resource 包含 restaurants, menuitem, foods, users,orders. 

### resource 是 restaurants
```
Get /fooddelivery.com/restaurants  
获取restaurant列表

Get /fooddelivery.com/restaurants/{restaurantname}  
搜名字查看某个具体的restaurant

Post  /fooddelivery.com/restaurants  
新建一个restaurant

Put /fooddelivery.com/restaurants/{restaurantname}  
更新一个restaurant

Delete /fooddelivery.com/restaurants/deleteByRestaurantname/{restaurantname} 
删除一个restaurant
```

### resource 是menu
```
 
Get   fooddelivery.com/restaurants/{restaurantname}/menu  
获取指定restaurant的menu
response 是：
      foodItem
      price

Post  /fooddelivery.com/restaurants/{restaurantname}/menu  
新建指定restaurant的menu

Delete /fooddelivery.com/restaurants/{restaurantname}/deleteMenu 
删除指定restaurant的menu

Get /fooddelivery.com/restaurants/{restaurantname}/menu/foods  
获取一个restaurant的menu里的foodItem

Put /fooddelivery.com/restaurants/{restaurantname}/menu/foods/{id}  
更新一个restaurant的menu里的foodItem
```

### resource是users
```
Post /fooddelivery.com/users
创建user

Get /fooddelivery.com/users/{userid}
根据userid查找user

```


### resource是Orders
```
Post /fooddelivery.com/restaurants/{restaurantname}/users/{userid}/orders   
创建一个id为XXX的user的一个订单

request内容是：
      menuItem
      quantity
      note
      deliveryAddress

response 是：
      OrderId（自动创建）
      foodItem
      quantity
      price
      orderTime
      orderStatus（默认 incomplete）
      deliveryTime （创建时默认是0，当payment完成后修改）
``` 
```
Get /fooddelivery.com/restaurants/{restaurantname}/users/{userid}/orders/{orderid}   
获取一个id为XXX的user的一个id为XXX的订单

Put /fooddelivery.com/restaurants/{restaurantname}/users/{userid}/orders/{orderid}   
修改一个id为XXX的user的一个id为XXX的订单，主要用于payment完成后，修改orderStatus和deliveryTime

不提供订单删除功能，所以没有删除的API
```

```
Post   /XXX.com/restaurants/{restaurantname}/users/{userid}/Orders/{orderID}/pay     
创建一个user的一个订单的付款信息

request内容：
      creditCardNumber
      ExpirationDate
      SecurityCode

response内容：
      paymentID（自动创建）
      timestamp
      restaurantName
      userId
      orderId
      orderstatus（自动更新为completed）
      estimatedDeliveyTime （系统随机创建）

不提供payment修改和删除功能，所以没有对应的API
```

