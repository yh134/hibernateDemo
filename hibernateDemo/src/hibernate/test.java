package hibernate;

import java.sql.Connection;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u=new User();
		u.setGender("��");
		u.setName("����");
		u.setLoginName("zhangsan");
		u.setAge(18);
		EntityORMUtil util=new ENTITYORMUtilImpl();
		Connection conn=DBUtil.getCon();
		//�����ݲ��뵽���ݿ�
			try {
				util.insertEntity(conn, u);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

}
