package hibernate;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ENTITYORMUtilImpl implements EntityORMUtil {

	/**
	 * @param conn
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@Override
	public int insertEntity(Connection conn, Object entity) throws Exception {
		// TODO Auto-generated method stub
		if(conn==null)
		{
			return -1;
		}
		if(entity==null)
		{
			return -1;
		}
		//获取表名
		String tableName=Dom4jUtil.getTableNameByEntity(entity.getClass());
		//获取所有的属性Field
		Field[] fs=entity.getClass().getDeclaredFields();
		//定义数组用来存放所有属性的值
		//下面三个数组是一一对应，属性值-字段-属性类型
		Object[] fieldValues=new Object[fs.length];
		//用来存放所有属性对应的字段
		String[] columnNames=new String[fs.length];
		//用来存放属性的类型
		String[] columnTypes=new String[fs.length];
		//定义StringBuffer动态生成SQL语句
		StringBuffer sb=new StringBuffer();
		//首先拼接成插入的语法 insert into
		sb.append("insert into ");
		//连接上表名
		sb.append(tableName);
		//连接上"("
		sb.append("(");
		//下面循环是判断entity哪些属性值不为空，即是要插入值
		//就说明哪些字段是需要插入值，在生成SQL语句时就需要拼接上字段
		for(int i=0;i<fs.length;i++)
		{
			fs[i].setAccessible(true);
			//将属性的值放进对应的数组中
			fieldValues[i]=fs[i].get(entity);
			//如果值不为空
			if(fieldValues[i]!=null)
			{
				//获取属性对应的字段名，并存放到对应的数组对应的位置
				columnNames[i]=Dom4jUtil.getColumnNameByFiled(entity.getClass(), fs[i]);
				//获取属性对应的类型，并存放到对应的数组对应的位置
				columnTypes[i]=Dom4jUtil.getColumnTypeByFiled(entity.getClass(),fs[i]);
				
				//SQL语句上拼接该字段
				sb.append(columnNames[i]+",");
			}
		}
		//当程序执行到这一步生成的SQL语句应该是
		//insert into user(name,age,gender,
		//所以需要把最后一个逗号去掉
		sb.deleteCharAt(sb.length()-1);
		//继续追加") values("
		sb.append(") values(");
		//循环判断哪些属性不为空，就是需要拼接上一个"?"
		for(int i=0;i<columnNames.length;i++)
		{
			if(columnNames[i]!=null)
			{
				sb.append("?,");
			}
		}
		//到此SQL语句生成为：
		//insert into user(name,age,gender) values(?,?,?,
		//去掉最后一个逗号
		sb.deleteCharAt(sb.length()-1);
		//拼接上最后一个括号,sql语句生成完毕
		sb.append(")");
		//打印输出到控制台
		System.out.println(sb.toString());
		PreparedStatement pstm=null;
		pstm=conn.prepareStatement(sb.toString(),Statement.RETURN_GENERATED_KEYS);
		int k=1;
		//在执行SQL语句之前需要将SQL语句中的？赋值
		for(int i=0;i<columnNames.length;i++){
			//设置值需要判断对应的类型
				if(columnNames[i]!=null)
				{
					if(columnTypes[i].equals("int"))
					{
						pstm.setInt(k, (Integer)fieldValues[i]);
					}
					else if(columnTypes[i].equals("String"))
					{
						pstm.setString(k, (String)fieldValues[i]);
					}else if(columnTypes[i].equals("Date"))
					{
						java.sql.Date date=transferDate((java.util.Date) fieldValues[i]);
							pstm.setDate(k, date);
					}
					k++;
				}
			}
			//执行SQL语句
			pstm.executeUpdate();
			ResultSet rs=pstm.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
	}

	@Override
	public int updateEntity(Connection conn, int id, Object entity) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Object> getObjList(Object entity, Map<String, Object> ifs, Connection conn) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Connection conn, int id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	private java.sql.Date transferDate(java.util.Date date)
	{
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String formatDate=format.format(date);
		return java.sql.Date.valueOf(formatDate);
	}
}
