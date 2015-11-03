/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import z.z.w.server.IServiceLoader;
import z.z.w.util.SpringContextUtil;
import z.z.w.util.comm.PropertiesUtils;

import java.net.ServerSocket;
import java.util.Properties;
import java.util.ServiceLoader;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.MainRunner
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-03 10:59
 *   LastChange: 2015-11-03 10:59
 *      History:
 * </pre>
 *********************************************************************************************/
public class MainRunner
{
	final static         String       RELATIVE_PATH = "conf/config.properties";
	private static final Logger       logger        = LoggerFactory.getLogger( MainRunner.class );
	private static       ServerSocket serverSocket  = null;

	static
	{
		try
		{
			Properties props = PropertiesUtils.INSTANCE.getProperties( RELATIVE_PATH );
			if ( !props.isEmpty() )
			{
				String port = props.getProperty( "SINGLE.PROCESS.PORT" );
				logger.info( "Single process listen port : {}.", port );
				if ( StringUtils.isNotBlank( port ) ) serverSocket = new ServerSocket( Integer.parseInt( port ) );
				logger.debug( "serverSocket=>{}.", serverSocket );
			}
		}
		catch ( Exception e )
		{
			logger.error( "Single mainRunner processor error : {}.", e.getMessage() );
			System.exit( 1 );
		}

	}

	public static void main( String[] args )
	{
		final ServiceLoader<IServiceLoader> loader = ServiceLoader.load( IServiceLoader.class );

		Runtime.getRuntime().addShutdownHook( new Thread()
		{
			@Override public void run()
			{
				logger.debug( "Service will be stopping..." );
				for ( IServiceLoader service : loader )
					service.destroy();
				logger.debug( "Service is stopped!" );
			}

			;
		} );

		logger.debug( "Service will be starting..." );
		for ( IServiceLoader service : loader )
			service.loadService();
		logger.debug( "Service is started!" );
		logger.debug( "@@{}", SpringContextUtil.getApplicationContext() );

	}
}
