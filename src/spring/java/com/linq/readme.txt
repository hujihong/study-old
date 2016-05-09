spring-jedis-cache
Redis Cluster Cache Support for Spring Cache Abstraction With Jedis2

usage:

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JedisCluster jedisCluster(Environment env) {
        String hosts = env.getProperty(ConfigConstants.REDIS_HOSTS);
        Set<HostAndPort> nodes = new HashSet<>();
        for (String host : hosts.split(",")) {
            String[] hs = host.split(":");
            nodes.add(new HostAndPort(hs[0], toInt(hs[1])));
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(toInt(env.getProperty(ConfigConstants.REDIS_POOL_MAX_WAIT), -1));
        config.setMinIdle(toInt(env.getProperty(ConfigConstants.REDIS_POOL_MIN_IDLE), 0));
        config.setMaxIdle(toInt(env.getProperty(ConfigConstants.REDIS_POOL_MAX_IDLE), 8));
        return new JedisCluster(nodes, config);
    }

    @Bean
    public CacheManager cacheManager(JedisCluster jedisCluster) {
        RedisCacheManager cacheManager = new RedisCacheManager(jedisCluster);
        cacheManager.setDefaultExpiration(5 * 60);
        return cacheManager;
    }
}
