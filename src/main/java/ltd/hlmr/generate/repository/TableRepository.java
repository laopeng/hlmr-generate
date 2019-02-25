package ltd.hlmr.generate.repository;

import java.util.List;

import ltd.hlmr.generate.pojo.mysql.TableColumn;
import ltd.hlmr.generate.pojo.mysql.TableInfo;

public interface TableRepository {
	/**
	 * 查询当前数据库所有的表信息
	 * 
	 * @return
	 */
	List<TableInfo> findAll();

	/**
	 * 根据表名查询字段信息
	 * 
	 * @return
	 */
	List<TableColumn> findByName(String name);
}
