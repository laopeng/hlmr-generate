package ltd.hlmr.generate.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("返回结果")
@Data
public class  Result {
	@ApiModelProperty("状态码")
	protected Integer code;
	@ApiModelProperty("消息")
	protected String msg;
}
