# **RandL**
### *(register and login api)*
## 集成登录注册API系统

集成常用的登录注册等相关api。 

目的是为了分离app主要业务和登录注册业务等相关操作，只需要安装本系统，用户只需调用相应的接口就可以实现: 

 普通用户常规注册、手机号码快速注册、邮箱快速注册、用户注册、用户登录、qq用户一键登录、获取图片验证码、发送手机或者邮箱验证码、用户上传/更换头像等业务。

---
# 运行环境

```
JDK 1.8+ 
MySQL 5.3+
```

---
# **安装** 
- ##### 一：数据库安装
##### 1.获取sql脚本文件

```
脚本文件位置：  /RandL/project/db/...
```
##### 2.创建数据库randl_db
##### 3.进入ranl_db数据库
##### 4.执行那些sql脚本文件
##### 5.打开configuration后缀的表并修改系统设置 

- ##### 二：jar

##### 1.下载jar
```
jar文件位置：   /RandL/target/RandL-1.0.jar
```
##### 2.运行jar

```
$ java -jar target/RandL-1.0.jar
```



---

# 接口文档：

```
文档位置：   /RandL/project/doc/document.doc
```
