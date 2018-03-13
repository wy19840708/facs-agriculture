package com.facs.agriculture.support.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.facs.basic.framework.model.dto.BaseRequest;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统资源管理
 */
@Data
public class ResourceQueryRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;
	@ApiModelProperty(value = "业务状态")
	private String businessStatus;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "创建人。关联user表ID")
	private Long creator;
	@ApiModelProperty(value = "数据状态")
	private Integer dataStatus;
	@ApiModelProperty(value = "描述信息")
	private String description;
	@ApiModelProperty(value = "修改人。关联user表ID")
	private Long modifier;
	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "父节点")
	private Long pId;
	@ApiModelProperty(value = "资源路径")
	private String pathInfo;

}
