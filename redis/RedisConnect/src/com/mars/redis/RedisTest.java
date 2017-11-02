package com.mars.redis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {
	public static JedisPoolConfig conf = new JedisPoolConfig();
	public static JedisPool pool = new JedisPool(conf, "192.168.9.101", 6379,
			100000);
	public static Jedis jedis = pool.getResource();

	/**
	 * 我直接存入到了Redis数据库，要更改哈timeout的时间,不然就会报read time out 的错误
	 * 我存的名字是存入到key里面的，然后它们的value都是name
	 */
	public static void add() {
		// JedisPoolConfig conf=new JedisPoolConfig();
		// JedisPool pool=new JedisPool(conf,"localhost",6379,100000);
		// Jedis jedis=pool.getResource();
		String url = "d:/file.txt";
		try {
			FileReader read = new FileReader(new File(url));
			BufferedReader br = new BufferedReader(read);
			String line = null;
			long a = System.currentTimeMillis();
			while ((line = br.readLine()) != null) {
				jedis.set(line, "name");
			}
			long b = System.currentTimeMillis();
			System.out.println("所用时间：" + (b - a) + "毫秒");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// for(int i=0;i<list.size();i++){
		// jedis.set(list.get(i), "name");
		// }
		System.out.println("OK");
	}

	/**
	 * 这个是查询的代码
	 */
	public static void query() {
		int i = 0;
		long a = System.currentTimeMillis();
		Set<String> name = jedis.keys("*");
		Iterator iter = name.iterator();

		while (iter.hasNext() && (++i) < 25) {
			System.out.println(iter.next().toString());
		}
		long b = System.currentTimeMillis();
		System.out.println("所用时间：" + (b - a) + "毫秒");
	}

	
	public static void delete(){
		jedis.flushAll();
	}
	
	public static void main(String[] args) {
		//add();
		//query(); 
		//jedis.flushAll();
		/*jedis.set("date", "20171022");
		jedis.expire("date", 100);
		query(); 
		System.out.println(jedis.get("date"));
		System.out.println(jedis.ttl("date"));*/
		/*jedis.persist("date");*/
		long a = System.currentTimeMillis();
		/*for(int i=10000000;i>100001;i--){
			jedis.lpush("top10", i+"");
		} */
		jedis.lpush("top10", "89500");
		List<String> list=jedis.sort("top10");
		long b = System.currentTimeMillis();
		System.out.println(list.size());
		System.out.println("所用时间：" + (b - a) + "毫秒");
	}
}
