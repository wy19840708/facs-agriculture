package com.facs.agriculture.support.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 用户角色关联表
 */
@Data
public class UserRoleQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 业务状态
	 */
	private String businessStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人。关联user表ID
	 */
	private Long creator;
	/**
	 * 数据状态
	 */
	private Integer dataStatus;
	/**
	 * 修改人。关联user表ID
	 */
	private Long modifier;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 角色id。关联role表ID
	 */
	private Long roleId;
	/**
	 * 用户id。关联user表ID
	 */
	private Long userId;

}
