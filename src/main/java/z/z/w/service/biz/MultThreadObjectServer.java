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
import z.z.w.service.biz.vo.User;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.service.biz.MultThreadObjectServer
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-03 17:22
 *   LastChange: 2015-11-03 17:22
 *      History:
 * </pre>
 *********************************************************************************************/
@Service
public class MultThreadObjectServer
{
	private static final Logger logger = LoggerFactory.getLogger( MultThreadObjectServer.class );

	@Async/*( value = "testExecutor" )*/ public void bizOperator( User user )
	{
		logger.info( "{}.", user.toString() );
		try
		{
			Thread.sleep( user.getAge() / 200 );
		}
		catch ( InterruptedException e )
		{
		}
		logger.info( "====>{}.", user.toString() );

	}

}
