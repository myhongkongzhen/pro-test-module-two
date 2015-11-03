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
 *     FileName: z.z.w.service.impl.TestMultThreadObjectOperImplTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-03 17:27
 *   LastChange: 2015-11-03 17:27
 *      History:
 * </pre>
 *********************************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class ) @ContextConfiguration( locations = { "classpath:spring/spring.xml" } ) public class TestMultThreadObjectOperImplTest
{

	private TestMultThreadObjectOperImpl testMultThreadObjectOperImpl;

	@Test public void testExecute() throws Exception
	{
		try
		{
			testMultThreadObjectOperImpl.execute();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

	public TestMultThreadObjectOperImpl getTestMultThreadObjectOperImpl()
	{
		return testMultThreadObjectOperImpl;
	}

	@Resource public void setTestMultThreadObjectOperImpl( TestMultThreadObjectOperImpl testMultThreadObjectOperImpl )
	{
		this.testMultThreadObjectOperImpl = testMultThreadObjectOperImpl;
	}
}

