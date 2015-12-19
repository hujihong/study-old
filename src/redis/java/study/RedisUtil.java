package study;
import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    static {
        initPool();
    }
    private static volatile JedisPool jedisPool;
    private static ResourceBundle resourceBundle;
    public static Jedis getResource() {
        return jedisPool.getResource();
    }
    public static void returnResource(Jedis jedis) {
        jedisPool.returnResource(jedis);
    }
    public static void initPool() {
            if(jedisPool != null){
                return;
            }
            loadProperties();
            String host = resourceBundle.getString("redis.host");
            String passwd= resourceBundle.getString("redis.passwd");
            int port = Integer.parseInt(resourceBundle.getString("redis.port"));
            JedisPoolConfig config = config();
            jedisPool = new JedisPool(config,host,port,60,passwd);
    }
    private static void loadProperties() {
        resourceBundle = ResourceBundle.getBundle("config/redis-config");
    }
    private static JedisPoolConfig config() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }
    public static void main(String[]args){
       Jedis jedis= RedisUtil.getResource();
       RedisUtil.returnResource(jedis);
    }
}