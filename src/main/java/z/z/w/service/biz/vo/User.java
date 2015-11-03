/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.service.biz.vo;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.service.biz.vo.User
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-11-03 17:23
 *   LastChange: 2015-11-03 17:23
 *      History:
 * </pre>
 *********************************************************************************************/
public class User
{
	private String name;
	private int    age;

	@Override public String toString()
	{
		return "User{" +
			   "age=" + age +
			   ", name='" + name + '\'' +
			   '}';
	}

	@Override public boolean equals( Object o )
	{
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;

		User user = ( User ) o;

		if ( age != user.age ) return false;
		return !( name != null ? !name.equals( user.name ) : user.name != null );

	}

	@Override public int hashCode()
	{
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + age;
		return result;
	}

	public int getAge()
	{

		return age;
	}

	public void setAge( int age )
	{
		this.age = age;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public User()
	{

	}
}
