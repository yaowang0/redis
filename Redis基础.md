# Redis
Redis是一个key-value存储系统。
C语言编写、遵守BSD协议、支持网络、可基于内存亦可持久化的日志型、key-value数据库、并提供多种语言的API。
它通常被称为数据结构服务器，因为值(value)可以是字符串(String)，哈希(Map)，列表(list)，集合(sets)和有序集合(sorted sets)等类型。
## Redis简介
一个key-value数据库。
- 支持数据的持久化
- 支持多种数据结构存储
- 支持master-slave模式的数据备份
### 优势
- 性能极高，读的速度都是110000次/s，写的速度为81000次/s。
- 数据类型丰富
- 原子操作
- 支持publish/subscribe，通知，key过期等特性。（注释：什么鬼？）

## Redis安装
### Window下安装
**下载地址：**[https://github.com/MSOpenTech/redis/releases](https://github.com/MSOpenTech/redis/releases)
在redis的存放路径下(也可以将redis的路径添加到系统的环境变量里面)，运行**redis-server.exe redis.windows.conf**，其中redis.windows.conf可以省略，会启用默认的。
此时，另启用一个cmd窗口，原来的不要关闭，否则就无法访问服务器了。
运行**redis-cli -h 127.0.0.1 -p 6379**
设置键值对 **set myKey abc**
取出键值对 **get myKey**
### Linux下安装
**下载地址：**[http://redis.io/download](http://redis.io/download)
以2.8.17版本为例，下载并安装：
```
$ wget htpp://download.redis.io/releases/redis-2.8.17.tar.gz
$ tar xzf redis-2.8.17.tar.gz
$ cd redis-2.8.17
$ make
```
make完后redis-2.8.17目录下会出现编译后的redis服务程序redis-server，还有用于测试的客户端程序redis-cli，两个程序位于安装目录src目录下：
下面启动redis服务。
```
$ cd src
$ ./redis-server
```
注意这种方式启动redis使用的是默认的配置。也可以通过启动参数告诉redis使用指定的配置文件，使用下面的命令启动。
```
$ cd src
$ ./redis-server redis.conf
```
redis.conf是一个默认的配置文件。我们可以根据需要使用自己的配置文件。
启动redis服务进程后，就可以使用测试客户端程序redis-cli和redis服务交互了。比如：
```
$ cd src
$ ./redis-cli
redis> set foo bar
OK
redis> get foo
"bar"
```

## Redis配置
Redis的配置文件位于Redis安装目录下，文件名为redis.conf。
可以使用CONFIG命令查看或设置配置项。

**语法**

**Redis CONFIG命令格式如下：**
```
redis 127.0.0.1:6379> CONFIG GET CONFIG_SETTING_NAME
``` 
**实例**
```
redis 127.0.0.1:6379> CONFIG GET loglevel
``` 
使用*号获取所有配置项：
```
redis 127.0.0.1:6379> CONFIG GET *
``` 
### 编辑配置
可以通过修改redis.conf文件或者使用CONFIG set 命令来修改配置。

**语法**
CONFIG SET命令基本语法：
```
redis 127.0.0.1:6379> CONFIG SET CONFIG_SETTING_NAME NEW_CONFIG_VALUE
```
**实例**
```
redis 127.0.0.1:6379> CONFIG SET loglevel "notice"
OK
redis 127.0.0.1:6379> CONFIG GET loglevel

1)"loglevel"
2)"notice"
``` 

## Redis数据类型
Redis支持五种数据类型：String,hash,list,set,zset(sorted set)。
### String
String是redis最基本的类型，一个key对应一个value。
String类型是二进制安全的。意思是redis的String可以包含任何数据。比如jpg图片或者序列化的对象。
String类型是Redis最基本的数据类型，一个键最大能存储512MB。

**实例**
```
redis 127.0.0.1:6379> SET name "runoob"
OK
redis 127.0.0.1:6379> GET name
"runoob"
```
### Hash
Redis hash是一个键值对集合。
Redis hash是一个String类型的field和value的映射表，hash特别适合用于存储对象。

**实例**
```
redis 127.0.0.1:6379> HMSET user:1 username runoob password runoob points 200
OK
redis 127.0.0.1:6379> HGETALL user:1
1) "username"
2) "runoob"
3) "password"
4) "runoob"
5) "points"
6) "200"
```
以上实例中，hash数据类型存储了包含用户脚本信息的用户对象。实例中使用了Redis HMSET，HGETALL命令，user:1为键值。
每个hash可以存储2^(32) - 1键值对(40多亿)。
### List
Redis列表是简单的字符串列表，按照插入顺序排序。可以添加一个元素到列表的头部(左边)或者尾部(右边)。

**实例**
```
redis 127.0.0.1:6379> lpush runoob redis
(integer) 1
redis 127.0.0.1:6379> lpush runoob mongodb
(integer) 2
redis 127.0.0.1:6379> lrange runoob 0 10
1) "mongodb"
2) "redis"
```
列表最多可以存储2^(32) - 1元素(每个列表可存储40多亿)。
### Set
Redis的Set是String类型的无序集合。
集合是通过哈希表实现的，所以添加，删除，查找的复杂度都是O(1)。
**sadd命令**

添加一个String元素，key对应的set集合中，成功返回1，如果元素已经在集合中返回0，key对应的set不存在返回错误。
```
sadd key member
```
**实例**
```
redis 127.0.0.1:6379> sadd runoob redis
(integer) 1
redis 127.0.0.1:6379> sadd runoob mongodb
(integer) 1
redis 127.0.0.1:6379> sadd runoob rabitmq
(integer) 1
redis 127.0.0.1:6379> sadd runoob rabitmq
(integer) 0
redis 127.0.0.1:6379> smembers runooob
1) "rabitmq"
2) "mongodb"
3) "redis"
```
**注意：**以上实例中rabitmq添加了两次，但根据集合内元素的唯一性，第二次插入的元素将被忽略。
集合中最大的成员数为2^(32) - 1元素(每个集合可存储40多亿)。
### zset
Redis zset和Set一样也是String类型元素的集合，且不允许重复的成员。
不同的是每个元素都会关联一个double类型的分数。redis正是通过分数来为集合中的成员进行从小到大的排序。
zset的成员的唯一的，但分数(score)却可以重复。

**zadd命令**

添加一个String元素，key对应的set集合中，成功返回1，如果元素已经在集合中返回0，key对应的set不存在返回错误。
```
sadd key member
```
**实例**
```
redis 127.0.0.1:6379> sadd runoob 0 redis
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 mongodb
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 rabitmq
(integer) 1
redis 127.0.0.1:6379> zadd runoob 0 rabitmq
(integer) 0
redis 127.0.0.1:6379> ZRANGEBYSCORE runooob 0 1000

1) "rabitmq"
2) "mongodb"
3) "rabitmq"
```
