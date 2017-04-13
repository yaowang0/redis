package Redis;

/**
 * Created by wangyao.wang on 2017/4/10.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 使用场景：Java程序连接单个redis时
 */
public class JedisPoolDemo {
    public static final Logger logger = LoggerFactory.getLogger(JedisPoolDemo.class);

    //JedisPool
    JedisCommands jedisCommands;
    JedisPool jedisPool;
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    String ip = "10.100.2.92";
    int port = 6379;
    int timeout = 2000;

    /**
     * 初始化jedis
     */
    public JedisPoolDemo() {
        //设置配置
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(100);
        jedisPoolConfig.setTestOnBorrow(false);
        jedisPoolConfig.setTestOnReturn(true);

        //初始化JedisPool
        jedisPool = new JedisPool(jedisPoolConfig, ip, port, timeout);

        Jedis jedis = jedisPool.getResource();
        jedisCommands = jedis;
    }

    public void setValue(String key, String value) {
        this.jedisCommands.set(key, value);
    }

    public String getValue(String key) {
        return this.jedisCommands.get(key);
    }

    public static void main(String[] args) {
        JedisPoolDemo jedisPoolDemo = new JedisPoolDemo();
        jedisPoolDemo.setValue("DemoJedisKey", "DemoJedisValue");
        logger.info("get value from redis:{}", jedisPoolDemo.getValue("DemoJedisKey"));
    }
}
