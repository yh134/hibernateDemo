package hibernate;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface EntityORMUtil {
	/**
	 * ���
	 * @param conn ���ݿ����Ӷ���
	 * @param entity Ҫ��ӵ�ʵ��
	 * @return �����ļ�¼������
	 * @throws Exception
	 */
	public int insertEntity(Connection conn,Object entity) throws Exception;
	/**
	 * ����
	 * @param conn ���ݿ����Ӷ���
	 * @param entity Ҫ��ӵ�ʵ��
	 * @return �����ļ�¼������
	 * @throws Exception
	 */
	public int updateEntity(Connection conn,int id,Object entity) throws Exception;
	/**
	 * ����
	 * @param entity ��ѯ��ʵ��
	 * @param ifs ��ѯ������map����
	 * @param conn ���ݿ�����Ӷ���
	 * @return ���ص�������ʵ�弯��
	 * @throws Exception
	 */
	public List<Object> getObjList(Object entity,Map<String,Object> ifs,Connection conn)
		throws Exception;
	/**
	 * ɾ������
	 * @param conn ���ݿ����Ӷ���
	 * @param id Ҫɾ���Ķ���id
	 * @param obj Ҫɾ���Ķ���
	 * @return ���ز����ļ�¼��
	 * @throws Exception
	 */
	public int delete(Connection conn,int id,Object obj) throws Exception;
}
