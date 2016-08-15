package com.carvendy.db.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestTwo {

	//Redis服务器IP
    private static String ADDR = "192.168.20.211";
    
    //Redis的端口号
    private static int PORT = 6379;
    
    private static int MAX_ACTIVE = 5000;
    private static int MAX_IDLE = 5;
    private static int MAX_WAIT = 5000;
    
    private static JedisPool jedisPool = null;
    
    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            
           // config.setMaxActive(MAX_ACTIVE);
             config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
           // config.setMaxWait(MAX_WAIT);
            config.setMaxWaitMillis(MAX_WAIT);
          //  config.setTestOnBorrow(TEST_ON_BORROW);
          // jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
           jedisPool = new JedisPool(config, ADDR, PORT);
          //  System.out.println("===="+jedisPool+"====");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    
    private static byte[] serialize(Serializable value) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(value);
			oos.flush();
			oos.close();
			/*if (log.isDebugEnabled() ) {
				log.debug("数据序列化后大小 bytes>>>>>>>>>>>>>" + bos.toByteArray().length);
			}*/
			return bos.toByteArray();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error serializing object.  Cause: " + e, e);
		}
	}

	private static Serializable deserialize(byte[] value) {
		if (value == null) return null;
		Serializable result;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(value);
			ObjectInputStream ois = new ObjectInputStream(bis);
			result = (Serializable) ois.readObject();
			ois.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Error deserializing object.  Cause: " + e, e);
		}
		return result;
	}
	
	public Object getObject(Object key) {
		Jedis jedis = null;  
        try {  
            jedis = jedisPool.getResource();  
            //value = jedis.get(key);
            return deserialize( jedis.get(serialize((Serializable)key)) );
        } finally {  
            //返还到连接池  
        	jedisPool.returnResourceObject(jedis);
        }
    }
	
	 public void putObject(Object key, Object value) {
			Jedis jedis = null;  
	        try {  
	            jedis = jedisPool.getResource();  
	            //value = jedis.get(key);
	            jedis.set(serialize((Serializable)key), serialize((Serializable) value));
	        } finally {  
	            //返还到连接池  
	        	jedisPool.returnResourceObject(jedis);
	        }
	    }
    
	public static void main(String[] args) {
		
		TestTwo test = new TestTwo();
		String key = "key";
		test.putObject(serialize(key) , serialize("value1"));
		test.putObject(serialize("haha") , serialize("value2"));
		
		 Jedis jedis = jedisPool.getResource();
		 System.out.println(jedis.get(serialize(key) ));
		// System.out.println(deserialize( jedis.get(serialize((Serializable)key)) ));
	}
}
