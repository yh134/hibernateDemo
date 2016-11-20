package hibernate;

import java.sql.Connection;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setGender("男");
		u.setName("张三");
		u.setLoginName("zhangsan");
		u.setAge(18);
		EntityORMUtil util=new ENTITYORMUtilImpl();
		Connection conn=DBUtil.getCon();
		//将数据插入到数据库
			try {
				util.insertEntity(conn, u);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

}
