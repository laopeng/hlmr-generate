package ltd.hlmr.generate.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import ltd.hlmr.generate.pojo.mysql.TableInfo;
import ltd.hlmr.generate.repository.impl.TableInfoMysqlRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TableInfoRepositoryTests {

	@Resource
	private TableInfoMysqlRepository tableInfoMysqlRepository;

	@Test
	public void findAll() {
		List<TableInfo> tableInfos = tableInfoMysqlRepository.findAll();
		tableInfos.forEach(e -> {
			log.info(e.toString());
		});
	}

}
