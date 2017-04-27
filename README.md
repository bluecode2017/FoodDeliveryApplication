# FoodDeliveryApplication


## 设计Rest API
Resource 包含 restaurants, menu, foods, users,orders. 这里假设1个restaurant只有1份menu（不分中午menu和晚餐menu）,1个user有n个order ， 1个user 有1个 payment info


### resource 是 restaurant
```
Get /XXX.com/restaurants  获取restaurant列表

Get /XXX.com/restaurants/{restaurantname}  搜名字查看某个具体的restaurant

Post  /XXX.com/restaurants  新建一个restaurant

Put /XXX.com/restaurants/{restaurantname}  更新一个restaurant

Delete /XXX.com/restaurants/deleteByRestaurantname/{restaurantname} 删除一个restaurant
```

### resource 是menu
```
 
Get   XXX.com/restaurants/{restaurantname}/menu  获取指定restaurant的menu
response 是：
      foodItem
      price

Post  /XXX.com/restaurants/{restaurantname}/menu  新建指定restaurant的menu

Delete /XXX.com/restaurants/{restaurantname}/deleteMenu/ 删除指定restaurant的menu

Get /XXX.com/restaurants/{restaurantname}/menu/foods  获取一个restaurant的menu里的foodItem

Put /XXX.com/restaurants/{restaurantname}/menu/foods/{id}  更新一个restaurant的menu里的foodItem
```

### resource是user
```
Post /XXX.com/restaurants/{restaurantname}/users/{userid}   创建一个id为XXX的user的一个订单

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

Get /XXX.com/restaurants/{restaurantname}/users/{userid}/orders/{orderid}   获取一个id为XXX的user的一个id为XXX的订单

Put /XXX.com/restaurants/{restaurantname}/users/{userid}/orders/{orderid}   修改一个id为XXX的user的一个id为XXX的订单，主要用于payment完成后，修改orderStatus和deliveryTime

不提供订单删除功能，所以没有删除的API
```


### resource是Order
```
Post   /XXX.com/restaurants/{restaurantname}/users/{userid}/Oders/{orderID}/pay     创建一个user的一个订单的付款信息

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

