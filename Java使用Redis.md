## Java 使用Redis
### 安装
开始在Java中使用Redis前，需要确保已经安装了redis服务及Java redis驱动，且你的机器上能正常使用Java。
-首先需要下载驱动包，[下载jedis.jar](http://repo1.maven.org/maven2/redis/clients/jedis/2.1.0/jedis-2.1.0-sources.jar),确保下载最新的驱动包。
-在calsspath中包含该驱动包。
### 连接到redis服务
```
import redis.clients.jedis.Jedis;
public class RedisJava {
   public static void main(String[] args){
      //连接到本地的Redis服务
      Jedis jedis = new Jedis("localhost");
      System.out.println("Connection to server successfully");
      //查看服务是否运行
      System.out.println("Server is running: " + jedis.ping());
   }
}
```
编译以上Java程序，确保驱动包的路径是正确的。
```
$ javac RedisJava.java
$ java RedisJava
Connection to server sucessfully
Serveri is running: PONG
```
