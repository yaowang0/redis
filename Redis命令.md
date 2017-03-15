## Redis命令
Redis命令用于在redis服务上执行操作。
要在redis服务上执行命令需要一个redis客户端。Redis客户端在之前的下载的redis的安装包中。

**语法**

Redis客户端的基本语法为：
```
$ redis-cli
```

**实例**

启动redis客户端，打开终端并输入命令redis-cli，该命令会连接本地的redis服务。
```
redis 127.0.0.1:6379> PING
PONG
redis 127.0.0.1:6379>
```
该实例中连接到本地的redis服务并执行PING命令，该命令用于检测redis服务是否启动。
### 在远程服务上执行命令
如果需要在远程redis服务上执行命令，同样我们使用的是redis-cli命令。

**语法**
```
$ redis-cli -h host -p port -a password
```

**实例**

连接到主机为127.0.0.1，端口为6379，密码为mypass的redis服务上。
```
$ redis-cli -h 127.0.0.1 -p 6379 -a "mypass"
redis 127.0.0.1:6379>
redis 127.0.0.1:6379> PING
PONG
```
