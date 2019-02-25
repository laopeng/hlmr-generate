package ltd.hlmr.generate.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ltd.hlmr.generate.pojo.mysql.TableColumn;
import ltd.hlmr.generate.pojo.mysql.TableInfo;
import ltd.hlmr.generate.repository.impl.TableMysqlRepository;

@Api(tags = "表信息")
@RestController
@RequestMapping("/table")
@Validated
public class TableController {
	@Resource
	private TableMysqlRepository tableMysqlRepository;

	@ApiOperation(value = "查询当前数据库的表信息", response = TableInfo.class)
	@GetMapping("/infos")
	public Object findAll() {
		List<TableInfo> tableInfos = tableMysqlRepository.findAll();
		return tableInfos;
	}

	@ApiOperation(value = "根据表名查询列信息", response = TableColumn.class)
	@GetMapping("/columns")
	public Object findByTable(@RequestParam @NotBlank String tableName) {
		List<TableColumn> tableColumn = tableMysqlRepository.findByName(tableName);
		return tableColumn;
	}
}
