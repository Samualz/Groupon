package com.tortuousroad.framework.cache;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSON;
import com.tortuousroad.framework.base.entity.BaseEntity;

/**
 * CacheOperator
 */
public abstract class CacheOperator {
	
	private static final Logger logger = LoggerFactory.getLogger(CacheOperator.class);

	private Jedis jedis;

	private Jedis getJedis() {
		if (null == jedis) {
			try {
				ResourceBundle bundle = ResourceBundle.getBundle("cache");
				JedisPoolConfig config = new JedisPoolConfig();
				JedisPool pool = new JedisPool(config, bundle.getString("ip"), Integer.parseInt(bundle.getString("port")), 1000);
				jedis = pool.getResource();
			} catch (Exception e) {
				//logger.error(e.getMessage(), e);
				return null;
			}
		}

		return jedis;
	}

	/**
	 * 向缓存中增加实体
	 * @param key
	 * @param entity
	 */
	protected <T extends BaseEntity> void putEntity(String key, T entity) {
		if (null != this.getJedis()) {
			this.getJedis().set(key.getBytes(), JSON.toJSONBytes(entity));
		} else {
			logger.info("缓存服务连接失败，不能放入数据(" + entity.toString() + ")");
		}
	}

	/**
	 * 向缓存中增加实体集合
	 * @param key
	 * @param entities
	 */
	protected <T extends BaseEntity> void putEntities(String key, List<T> entities) {
		if (null != this.getJedis()) {
			this.getJedis().set(key.getBytes(), JSON.toJSONBytes(entities));
		}
	}
	
	/**
	 * 向缓存中增加缓存对象
	 * @param key
	 * @param cacheObject
	 */
	protected <T extends CacheObject> void putCacheObject(String key, T cacheObject) {
		if (null != this.getJedis()) {
			this.getJedis().set(key.getBytes(), JSON.toJSONBytes(cacheObject));
		}
	}
	
	/**
	 * 从缓存中获取实体
	 * @param key
	 * @return
	 */
	protected <T extends BaseEntity> T getEntity(String key, Class<T> clazz) {
		if (null != this.getJedis()) {
			byte[] bytes = this.getJedis().get(key.getBytes());
			if (null == bytes) {
				return null;
			}
			return JSON.parseObject(bytes, clazz);
		}
		return null;
	}

	/**
	 * 从缓存中获取缓存对象
	 * @param key
	 * @param clazz
	 * @return
	 */
	protected <T extends CacheObject> T getCacheObject(String key, Class<T> clazz) {
		if (null != this.getJedis()) {
			byte[] bytes = this.getJedis().get(key.getBytes());
			if (null == bytes) {
				return null;
			}
			return JSON.parseObject(bytes, clazz);
		}
		return null;
	}
	
	/**
	 * 从缓存中获取实体集合
	 * @param key
	 * @param clazz
	 * @return
	 */
	protected <T extends BaseEntity> List<T> getEntities(String key, Class<T> clazz) {
		try {
			if (null != this.getJedis()) {
				byte[] bytes = this.getJedis().get(key.getBytes());
				if (null == bytes) {
					return null;
				}
				return JSON.parseArray(new String(bytes, "UTF-8"), clazz);
			}
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 从缓存中删除实体/实体集合，缓存对象
	 * @param key
	 */
	protected void delete(String key) {
		if (null != this.getJedis()) {
			this.getJedis().del(key.getBytes());
		}
	}

	/**
	 * 根据Key前缀获取对应的所有实体
	 * @param keyPrefix
	 * @param clazz
	 * @return
	 */
	protected <T extends BaseEntity> List<T> getEntitiesByKeyPrefix(String keyPrefix, Class<T> clazz) {
		if (null != this.getJedis()) {
			Set<byte[]> keySet = this.getJedis().keys((keyPrefix + "*").getBytes());
			if (keySet.size() == 0) {
				return null;
			}
			T t;
			List<T> result = new ArrayList<>();
			List<byte[]> list = this.getJedis().mget(keySet.toArray(new byte[1][keySet.size()]));
			for (byte[] bytes : list) {
				t = JSON.parseObject(bytes, clazz);
				result.add(t);
			}
			return result;
		}
		return null;
	}

	/**
	 * 根据Key前缀获取对应的所有缓存对象
	 * @param keyPrefix
	 * @param clazz
	 * @return
	 */
	protected <T extends CacheObject> List<T> getCacheObjectsByKeyPrefix(String keyPrefix, Class<T> clazz) {
		if (null != this.getJedis()) {
			Set<byte[]> keySet = this.getJedis().keys(
					(keyPrefix + "*").getBytes());
			List<byte[]> list = this.getJedis().mget(
					keySet.toArray(new byte[1][keySet.size()]));
			List<T> result = new ArrayList<T>();
			T t;
			for (byte[] bytes : list) {
				t = JSON.parseObject(bytes, clazz);
				result.add(t);
			}
			return result;
		}
		return null;
	}
	
}