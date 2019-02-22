package ltd.hlmr.generate.repository;

import java.util.List;

import ltd.hlmr.generate.pojo.mysql.TableInfo;

public interface TableInfoRepository {
	/**
	 * 查询当前数据库所有的表信息
	 * 
	 * @return
	 */
	List<TableInfo> findAll();
}
