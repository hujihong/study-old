package study;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import redis.clients.jedis.Jedis;

public class BitSetTest {

	// 1 统计系统中某天用户登录的情况：
	// 以当天日期做为key ,比如 ‘20150410’ ，对应的 bitMap 的 index 用userId来标示，UserId这里用 long 型表示，如果id不是以0开头，可以加上相应的偏移量就OK了；如果该天用户登录，
	// 调用activeUser方法，来更改bitMap相应index上的标示；
	public void activeUser(long userId, String dateKey) {
		Jedis jedis = RedisUtil.getResource();
		try {
			jedis.setbit(dateKey, userId, true);
		} finally {
			RedisUtil.returnResource(jedis);
		}
	}

	// 如果我们想统计该天用户登录的数量，及登录的用户id,可以通过如下方法实现
	// 该天用户总数
	public long totalCount(String dateKey) {
		Jedis jedis = RedisUtil.getResource();
		try {
			return jedis.bitcount(dateKey);
		} finally {
			RedisUtil.returnResource(jedis);
		}
	}

	// 该天登录所有的用户id
	public List<Long> activeUserIds(String key) {
		Jedis jedis = RedisUtil.getResource();
		try {

			if (jedis.get(key) == null) {
				return null;
			}
			BitSet set = BitSetUtils.byteArray2BitSet(jedis.get(key).getBytes());

			List<Long> list = new ArrayList<Long>();
			for (long i = 0; i < set.size(); i++) {
				if (set.get((int)i)) { // (int)
					list.add(i);
				}
			}
			return list;
		} finally {
			RedisUtil.returnResource(jedis);
		}
	}

	// 如果我们想统计n天，连续登录的用户数，及UserId:
	public List<Long> continueActiveUserCount(String... dateKeys) {
		Jedis jedis = RedisUtil.getResource();
		try {
			BitSet all = null;
			for (String key : dateKeys) {
				if (jedis.get(key) == null) {
					continue;
				}
				BitSet set = BitSetUtils.byteArray2BitSet(jedis.get(key).getBytes());
				if (all == null) {
					all = set;
				}
				System.out.println(set.size());
				all.and(set);
			}
			List<Long> list = new ArrayList<Long>();
			for (long i = 0; i < all.size(); i++) {
				if (all.get((int)i)) {  // (int)
					list.add(i);
				}
			}
			return list;
		} finally {
			RedisUtil.returnResource(jedis);
		}
	}
}
