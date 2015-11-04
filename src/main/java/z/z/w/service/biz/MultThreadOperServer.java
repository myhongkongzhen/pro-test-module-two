/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.service.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import z.z.w.util.redis.ShardRedisOperator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.service.biz.MultThreadOperServer
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-03 15:11
 *   LastChange: 2015-11-03 15:11
 *      History:
 * </pre>
 *********************************************************************************************/
@Service
public class MultThreadOperServer
{
	private static final Logger logger = LoggerFactory.getLogger( MultThreadOperServer.class );

	private ShardRedisOperator shardRedisOperator;

	@Async
	public void setBathdata( Map<String, String> map )
	{
		long startTime = System.currentTimeMillis();
		try
		{
			String set = shardRedisOperator.set( "ZZWTest", map );

			logger.info( "更新REDIS緩存信息結果：{}.", set );
		}
		catch ( Exception e )
		{
			logger.error( "更新緩存信息出現異常：{}.", e.getMessage(), e );
		}
		finally
		{
			logger.info( "完成更新緩存操作，用時：{} ms.", ( System.currentTimeMillis() - startTime ) );
		}
	}

	@Async
	public void setSingledata( String key, String value )
	{
		long startTime = System.currentTimeMillis();
		try
		{
			Long hset = shardRedisOperator.hset( "zzwTest", key, value );
			logger.info( "存入緩存數據：{}-{}，結果：{}.", key, value, hset );
		}
		catch ( Exception e )
		{
			logger.error( "單個數據存入緩存出現異常：{}.", e.getMessage(), e );
		}
		finally
		{
			logger.info( "單個數據存入緩存完成，用時：{} ms.", ( System.currentTimeMillis() - startTime ) );
		}
	}

	public ShardRedisOperator getShardRedisOperator()
	{
		return shardRedisOperator;
	}

	@Resource
	public void setShardRedisOperator( ShardRedisOperator shardRedisOperator )
	{
		this.shardRedisOperator = shardRedisOperator;
	}

	public long getLen( String key )
	{
		return shardRedisOperator.hlen( key );
	}

	@Async
	public List<Object> setSetSingle( String key, String value )
	{
		long startTime = System.currentTimeMillis();
		try
		{
			ShardedJedis redisClient = null;
			try
			{
				redisClient = shardRedisOperator.getRedisClient();
				if ( redisClient == null ) return null;
				ShardedJedisPipeline pipelined = redisClient.pipelined();

				pipelined.sadd( key, value );

				return pipelined.syncAndReturnAll();

			}
			catch ( Exception e )
			{
				logger.error( "更新redis緩存出現錯誤：{}", e.getMessage(), e );
				shardRedisOperator.returnBrokenResource( redisClient );
			}
			finally
			{
				shardRedisOperator.returnResource( redisClient );
			}
		}
		catch ( Exception e )
		{
			logger.error( "單個數據存入緩存出現異常：{}.", e.getMessage(), e );
		}
		finally
		{
			logger.info( "單個數據存入緩存完成，用時：{} ms.", ( System.currentTimeMillis() - startTime ) );
		}

		return null;
	}

	@Async
	public Long setSetSingleNoPipe( String key, String value )
	{
		long startTime = System.currentTimeMillis();
		try
		{
			ShardedJedis redisClient = null;
			try
			{
				redisClient = shardRedisOperator.getRedisClient();
				if ( redisClient == null ) return -2l;

				Long sadd = redisClient.sadd( key, value );
				logger.debug( "更新數據：{}--{}，結果：{}.", key, value, sadd.intValue() );
				logger.info( "{}===={}", this, shardRedisOperator );
			}
			catch ( Exception e )
			{
				logger.error( "更新redis緩存出現錯誤：{}", e.getMessage(), e );
				shardRedisOperator.returnBrokenResource( redisClient );
			}
			finally
			{
				shardRedisOperator.returnResource( redisClient );
			}
		}
		catch ( Exception e )
		{
			logger.error( "單個數據存入緩存出現異常：{}.", e.getMessage(), e );
		}
		finally
		{
			logger.info( "單個數據存入緩存完成，用時：{} ms.", ( System.currentTimeMillis() - startTime ) );
		}

		return -1l;
	}

	@Async
	public void setListData( String key, String value )
	{
		long startTime = System.currentTimeMillis();
		try
		{
			ShardedJedis redisClient = null;
			try
			{
				redisClient = shardRedisOperator.getRedisClient();
				if ( redisClient == null ) return;

				Long sadd = redisClient.lpush( key, value );
				logger.info( "更新數據：{}--{}，結果：{}.", key, value, sadd.intValue() );
			}
			catch ( Exception e )
			{
				logger.error( "更新redis緩存出現錯誤：{}", e.getMessage(), e );
				shardRedisOperator.returnBrokenResource( redisClient );
			}
			finally
			{
				shardRedisOperator.returnResource( redisClient );
			}
		}
		catch ( Exception e )
		{
			logger.error( "單個數據存入緩存出現異常：{}.", e.getMessage(), e );
		}
		finally
		{
			logger.info( "單個數據存入緩存完成，用時：{} ms.", ( System.currentTimeMillis() - startTime ) );
		}

		return;
	}

	public void getLLen( String key )
	{
		long startTime = System.currentTimeMillis();
		try
		{
			ShardedJedis redisClient = null;
			try
			{
				redisClient = shardRedisOperator.getRedisClient();
				if ( redisClient == null ) return;

				logger.info( "List長度：{}.", redisClient.llen( key ) );
			}
			catch ( Exception e )
			{
				logger.error( "更新redis緩存出現錯誤：{}", e.getMessage(), e );
				shardRedisOperator.returnBrokenResource( redisClient );
			}
			finally
			{
				shardRedisOperator.returnResource( redisClient );
			}
		}
		catch ( Exception e )
		{
			logger.error( "單個數據存入緩存出現異常：{}.", e.getMessage(), e );
		}
		finally
		{
			logger.info( "單個數據存入緩存完成，用時：{} ms.", ( System.currentTimeMillis() - startTime ) );
		}

		return;
	}

}

