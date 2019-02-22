package ltd.hlmr.generate.pojo.mysql;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel("表信息")
@Data
@AllArgsConstructor
public class TableInfo {

	@ApiModelProperty("表名")
	private String tableName;
	
	@ApiModelProperty("表说明")
	private String tableComment;

	@ApiModelProperty("创建时间")
	private Date createTime;

	@ApiModelProperty("修改时间")
	private Date updateTime;
}
