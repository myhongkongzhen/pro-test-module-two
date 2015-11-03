/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.service.impl.TestMultThreadOperRedisImplTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-03 16:16
 *   LastChange: 2015-11-03 16:16
 *      History:
 * </pre>
 *********************************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class ) @ContextConfiguration( locations = { "classpath:spring/spring.xml" } ) public class TestMultThreadOperRedisImplTest
{

	private TestMultThreadOperRedisImpl testMultThreadOperRedisImpl;

	@Test public void testExecute() throws Exception
	{
		try
		{
//			testMultThreadOperRedisImpl.execute();
//			testMultThreadOperRedisImpl.getLen( "ZZWTest" );
//			testMultThreadOperRedisImpl.setSingleData();
//			testMultThreadOperRedisImpl.getLen( "zzwTest" );
//			testMultThreadOperRedisImpl.setSetDataSingle();
//			testMultThreadOperRedisImpl.setListDataSingle();
			testMultThreadOperRedisImpl.getLLen( "key_list_lpush" );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			Thread.sleep( 30000 );
		}
	}

	public TestMultThreadOperRedisImpl getTestMultThreadOperRedisImpl()
	{
		return testMultThreadOperRedisImpl;
	}

	@Resource public void setTestMultThreadOperRedisImpl( TestMultThreadOperRedisImpl testMultThreadOperRedisImpl )
	{
		this.testMultThreadOperRedisImpl = testMultThreadOperRedisImpl;
	}
}
