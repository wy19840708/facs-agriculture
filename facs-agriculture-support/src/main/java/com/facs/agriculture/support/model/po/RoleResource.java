package com.facs.agriculture.support.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色资源管理
 */
@Data
public class RoleResource implements Serializable {

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
	@ApiModelProperty(value = "资源id。关联resource表ID")
	private Long resourceId;
	@ApiModelProperty(value = "角色id。关联role表ID")
	private Long roleId;

}
