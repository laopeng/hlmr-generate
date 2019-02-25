package ltd.hlmr.generate.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import ltd.hlmr.generate.pojo.mysql.TableInfo;
import ltd.hlmr.generate.repository.impl.TableMysqlRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TableRepositoryTests {

	@Resource
	private TableMysqlRepository tableMysqlRepository;

	@Test
	public void findAll() {
		List<TableInfo> tableInfos = tableMysqlRepository.findAll();
		tableInfos.forEach(e -> {
			log.info(e.toString());
		});
	}

}
