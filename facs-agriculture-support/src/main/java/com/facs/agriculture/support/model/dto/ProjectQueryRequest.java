package com.facs.agriculture.support.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.facs.basic.framework.model.dto.BaseRequest;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目管理。

 */
@Data
public class ProjectQueryRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;
	@ApiModelProperty(value = "业务状态")
	private String businessStatus;
	@ApiModelProperty(value = "编码")
	private String code;
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	@ApiModelProperty(value = "创建人。关联user表ID")
	private String creator;
	@ApiModelProperty(value = "数据状态")
	private Integer dataStatus;
	@ApiModelProperty(value = "结束日期")
	private Date endDate;
	@ApiModelProperty(value = "负责人。关联user表ID")
	private String leader;
	@ApiModelProperty(value = "修改人。关联user表ID")
	private String modifier;
	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "优先级")
	private String priority;
	@ApiModelProperty(value = "开始日期")
	private Date startDate;

}
