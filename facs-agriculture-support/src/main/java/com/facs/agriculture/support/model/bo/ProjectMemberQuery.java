package com.facs.agriculture.support.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 项目成员管理。
 */
@Data
public class ProjectMemberQuery implements Serializable {

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
	 * 参与项目结束日期
	 */
	private Date endDate;
	/**
	 * 修改人。关联user表ID
	 */
	private Long modifier;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 计划工时
	 */
	private Integer planHour;
	/**
	 * 参与项目开始日期
	 */
	private Date startDate;
	/**
	 * 人员ID。关联user表ID
	 */
	private Long userId;
	/**
	 * 项目ID。关联project表ID
	 */
	private Long projectId;


}
