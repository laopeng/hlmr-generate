package ltd.hlmr.generate.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import ltd.hlmr.generate.pojo.mysql.TableColumn;
import ltd.hlmr.generate.pojo.mysql.TableInfo;
import ltd.hlmr.generate.repository.TableRepository;

@Repository
@Slf4j
public class TableMysqlRepository implements TableRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<TableInfo> findAll() {
		String sqlString = "select table_name, table_comment, create_time, update_time  from information_schema.tables "
				+ "where table_schema = (select database()) order by table_name";
		log.debug(sqlString);
		List<TableInfo> tableInfos;
		try {
			Query query = entityManager.createNativeQuery(sqlString);
			@SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();
			tableInfos = new ArrayList<>();
			result.forEach(e -> {
				tableInfos.add(new TableInfo((String) e[0], (String) e[1], (Date) e[2], (Date) e[3]));
			});
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return tableInfos;
	}

	@Override
	public List<TableColumn> findByName(String name) {
		String sqlString = "select column_name, is_nullable, column_type, column_comment, column_key  from information_schema.columns "
				+ "where table_schema = (select database()) and table_name = ? order by ordinal_position";
		log.debug(sqlString + "\r\n" + "name: " + name);
		List<TableColumn> tableColumns;
		try {
			Query query = entityManager.createNativeQuery(sqlString);
			query.setParameter(1, name);
			@SuppressWarnings("unchecked")
			List<Object[]> result = query.getResultList();
			tableColumns = new ArrayList<>();
			result.forEach(e -> {
				tableColumns.add(new TableColumn((String) e[0], (String) e[1], (String) e[2], (String) e[3],
						(String) e[4], null, null));
			});
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return tableColumns;
	};
}
