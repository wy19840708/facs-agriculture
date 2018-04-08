package com.facs.agriculture.support.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.facs.basic.framework.model.dto.BaseRequest;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目成员工时明细
 */
@Data
public class ProjectMemberDetailRequest extends BaseRequest {

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
	@ApiModelProperty(value = "结束日期")
	private Date endDate;
	@ApiModelProperty(value = "工时")
	private Long manHour;
	@ApiModelProperty(value = "项目成员ID。关联project_member表ID")
	private Long memberId;
	@ApiModelProperty(value = "修改人。关联user表ID")
	private Long modifier;
	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "开始日期")
	private Date startDate;
	@ApiModelProperty(value = "周数")
	private Integer week;
	@ApiModelProperty(value = "年份")
	private Integer year;
	@ApiModelProperty(value = "项目号")
	private Long codeId;
	@ApiModelProperty(value = "人员号")
	private Integer weekId;
}
