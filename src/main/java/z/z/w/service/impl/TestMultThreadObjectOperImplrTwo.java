/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import z.z.w.service.IService;
import z.z.w.service.biz.MultThreadObjectServerTow;
import z.z.w.service.biz.vo.User;

import javax.annotation.Resource;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.service.impl.TestMultThreadObjectOperImpl
 *         Desc: 這個類用於測試多線程下，異步調用某個方法，方法參數為Object情況下，是否存在臟數據
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-03 17:20
 *   LastChange: 2015-11-03 17:20
 *      History:
 * </pre>
 *********************************************************************************************/
@Service
public class TestMultThreadObjectOperImplrTwo implements IService
{
	private static final Logger logger = LoggerFactory.getLogger( TestMultThreadObjectOperImplrTwo.class );
	private MultThreadObjectServerTow multThreadObjectServerTow;

	@Override
	public void execute()
	{
		try
		{
			for ( int i = 44 ; i < 130 ; i++ )
			{
				User user = new User();
				user.setAge( i );
				user.setName( "username_" + i );
				multThreadObjectServerTow.bizOperator( user );
			}
		}
		catch ( Exception e )
		{
			logger.error( "---exception--{}", e.getMessage(), e );
		}
		finally
		{
		}
	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run()
	{
		execute();
	}

	public MultThreadObjectServerTow getMultThreadObjectServerTow()
	{
		return multThreadObjectServerTow;
	}

	@Resource
	public void setMultThreadObjectServerTow( MultThreadObjectServerTow multThreadObjectServerTow )
	{
		this.multThreadObjectServerTow = multThreadObjectServerTow;
	}
}
