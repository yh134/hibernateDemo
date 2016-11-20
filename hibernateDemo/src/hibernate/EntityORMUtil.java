package hibernate;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface EntityORMUtil {
	/**
	 * 添加
	 * @param conn 数据库连接对象
	 * @param entity 要添加的实体
	 * @return 操作的记录的条数
	 * @throws Exception
	 */
	public int insertEntity(Connection conn,Object entity) throws Exception;
	/**
	 * 更新
	 * @param conn 数据库连接对象
	 * @param entity 要添加的实体
	 * @return 操作的记录的条数
	 * @throws Exception
	 */
	public int updateEntity(Connection conn,int id,Object entity) throws Exception;
	/**
	 * 更新
	 * @param entity 查询的实体
	 * @param ifs 查询的条件map集合
	 * @param conn 数据库的连接独享
	 * @return 返回的是所有实体集合
	 * @throws Exception
	 */
	public List<Object> getObjList(Object entity,Map<String,Object> ifs,Connection conn)
		throws Exception;
	/**
	 * 删除操作
	 * @param conn 数据库连接对象
	 * @param id 要删除的对象id
	 * @param obj 要删除的对象
	 * @return 返回操作的记录数
	 * @throws Exception
	 */
	public int delete(Connection conn,int id,Object obj) throws Exception;
}
