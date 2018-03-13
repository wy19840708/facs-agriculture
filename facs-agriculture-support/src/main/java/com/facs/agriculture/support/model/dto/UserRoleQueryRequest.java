package com.facs.agriculture.support.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.facs.basic.framework.model.dto.BaseRequest;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户角色关联表
 */
@Data
public class UserRoleQueryRequest extends BaseRequest {

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
	@ApiModelProperty(value = "修改人。关联user表ID")
	private Long modifier;
	@ApiModelProperty(value = "修改时间")
	private Date modifyTime;
	@ApiModelProperty(value = "角色id。关联role表ID")
	private Long roleId;
	@ApiModelProperty(value = "用户id。关联user表ID")
	private Long userId;

}
