package ltd.hlmr.generate.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("layui表格返回结果")
@Data
@EqualsAndHashCode(callSuper = false)
public class LayuiTableResult extends Result {
	@ApiModelProperty("总数")
	private Long count;
	@ApiModelProperty("列表数据")
	private Object data;
}
