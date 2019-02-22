package ltd.hlmr.generate.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.hlmr.generate.pojo.mysql.TableInfo;
import ltd.hlmr.generate.repository.impl.TableInfoMysqlRepository;

@Api(tags = "表信息")
@RestController
@RequestMapping("/table/infos")
public class TableInfoController {
	@Resource
	private TableInfoMysqlRepository tableInfoMysqlRepository;

	@ApiOperation(value = "查询当前数据库的表信息", response = TableInfo.class)
	@GetMapping
	public Object findAll() {
		List<TableInfo> tableInfos = tableInfoMysqlRepository.findAll();
		return tableInfos;
	}
}
