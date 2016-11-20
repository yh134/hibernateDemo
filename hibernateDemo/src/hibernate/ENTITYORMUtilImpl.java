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
		//��ȡ����
		String tableName=Dom4jUtil.getTableNameByEntity(entity.getClass());
		//��ȡ���е�����Field
		Field[] fs=entity.getClass().getDeclaredFields();
		//����������������������Ե�ֵ
		//��������������һһ��Ӧ������ֵ-�ֶ�-��������
		Object[] fieldValues=new Object[fs.length];
		//��������������Զ�Ӧ���ֶ�
		String[] columnNames=new String[fs.length];
		//����������Ե�����
		String[] columnTypes=new String[fs.length];
		//����StringBuffer��̬����SQL���
		StringBuffer sb=new StringBuffer();
		//����ƴ�ӳɲ�����﷨ insert into
		sb.append("insert into ");
		//�����ϱ���
		sb.append(tableName);
		//������"("
		sb.append("(");
		//����ѭ�����ж�entity��Щ����ֵ��Ϊ�գ�����Ҫ����ֵ
		//��˵����Щ�ֶ�����Ҫ����ֵ��������SQL���ʱ����Ҫƴ�����ֶ�
		for(int i=0;i<fs.length;i++)
		{
			fs[i].setAccessible(true);
			//�����Ե�ֵ�Ž���Ӧ��������
			fieldValues[i]=fs[i].get(entity);
			//���ֵ��Ϊ��
			if(fieldValues[i]!=null)
			{
				//��ȡ���Զ�Ӧ���ֶ���������ŵ���Ӧ�������Ӧ��λ��
				columnNames[i]=Dom4jUtil.getColumnNameByFiled(entity.getClass(), fs[i]);
				//��ȡ���Զ�Ӧ�����ͣ�����ŵ���Ӧ�������Ӧ��λ��
				columnTypes[i]=Dom4jUtil.getColumnTypeByFiled(entity.getClass(),fs[i]);
				
				//SQL�����ƴ�Ӹ��ֶ�
				sb.append(columnNames[i]+",");
			}
		}
		//������ִ�е���һ�����ɵ�SQL���Ӧ����
		//insert into user(name,age,gender,
		//������Ҫ�����һ������ȥ��
		sb.deleteCharAt(sb.length()-1);
		//����׷��") values("
		sb.append(") values(");
		//ѭ���ж���Щ���Բ�Ϊ�գ�������Ҫƴ����һ��"?"
		for(int i=0;i<columnNames.length;i++)
		{
			if(columnNames[i]!=null)
			{
				sb.append("?,");
			}
		}
		//����SQL�������Ϊ��
		//insert into user(name,age,gender) values(?,?,?,
		//ȥ�����һ������
		sb.deleteCharAt(sb.length()-1);
		//ƴ�������һ������,sql����������
		sb.append(")");
		//��ӡ���������̨
		System.out.println(sb.toString());
		PreparedStatement pstm=null;
		pstm=conn.prepareStatement(sb.toString(),Statement.RETURN_GENERATED_KEYS);
		int k=1;
		//��ִ��SQL���֮ǰ��Ҫ��SQL����еģ���ֵ
		for(int i=0;i<columnNames.length;i++){
			//����ֵ��Ҫ�ж϶�Ӧ������
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
			//ִ��SQL���
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
