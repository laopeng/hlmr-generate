package ltd.hlmr.generate.repository.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import ltd.hlmr.generate.pojo.mysql.TableInfo;
import ltd.hlmr.generate.repository.TableInfoRepository;

@Repository
@Slf4j
public class TableInfoMysqlRepository implements TableInfoRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<TableInfo> findAll() {
		String sqlString = "select table_name, table_comment, create_time, update_time  from information_schema.tables "
				+ "where table_schema = (select database()) order by table_name";
		log.debug(sqlString);
		List<TableInfo> tableInfos;
		try {
			Query query = entityManager.createNativeQuery(sqlString);
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
	};
}
