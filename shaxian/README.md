### 项目结构
```
├─shaxian
│  │  
│  ├─eureka-server------------注册服务中心
│  │ 
│  ├─api-gateway--------------网关负载中心
│  │ 
│  ├─admin--------------------管理员服务
│  │    ├─admin-feign---------管理员内部服务治理
│  │    └─admin-server--------管理员内部数据服务
│  │ 
│  ├─store--------------------商城服务
│  │    ├─store-feign---------商城内部服务治理
│  │    ├─store-api-----------商城页面接口服务
│  │    ├─store-cms-----------商城CMS接口服务
│  │    └─store-server--------商城内部数据服务
│  │
│  ├─home--------------------主页服务
│  │    ├─home-feign---------主页内部服务治理
│  │    └─home-server--------主页内部数据服务
│  │
│  ├─user-------------------用户中心
│  │    ├─user-feign---------用户内部服务治理
│  │    ├─user-server--------用户内部数据服务
│  │    ├─user-api-----------用户对外接口服务
│  │    └─user-auth----------用户授权接口服务(停用)
│  │
│  ├─common-------------------通用模版
│  │    ├─common-feign--------通用内部服务治理
│  │    ├─common-server-------通用内部数据服务
│  │    ├─common-model--------通用模型模块
│  │    └─common-util---------通用工具模块
│  │
│  ├─generator----------------代码生成器
│
```
###端口定义
```
·网关服务
api-gateway：9999

·服务注册与发现服务
eureka-server：8000

·超级管理员后台
admin-cms：9991

·首页服务
home-server：9981

·用户服务
user-server：9971
user-api：9972
user-auth：9973

·商城服务
store-server：9961
store-cms：9962
store-api：9963

·通用服务
common-server：9997

```
###util
```
jar 包依赖解决方式，向本地仓库注入包
mvn install:install-file  -DgroupId=com.aliyun -DartifactId=aliyun-java-sdk-core -Dversion=3.3.1 -Dpackaging=jar -Dfile= aliyun-java-sdk-core-3.3.1.jar
mvn install:install-file  -DgroupId=com.aliyun -DartifactId=aliyun-java-sdk-dysmsapi -Dversion=1.0.0 -Dpackaging=jar -Dfile= aliyun-java-sdk-dysmsapi-1.0.0.jar
```

###主要涉及以下技术栈
```
JDK1.8
Spring cloud 基础框架
Mybatis 持久层
Druid 数据链接池
Mybatis-Plus Mybatis插件 官网http://mp.baomidou.com
MySql 数据库
Redis NO数据库
```