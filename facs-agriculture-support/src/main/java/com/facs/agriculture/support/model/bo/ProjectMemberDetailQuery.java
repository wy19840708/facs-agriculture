package com.facs.agriculture.support.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 项目成员工时明细
 */
@Data
public class ProjectMemberDetailQuery implements Serializable {

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
	 * 结束日期
	 */
	private Date endDate;
	/**
	 * 工时
	 */
	private Long manHour;
	/**
	 * 项目成员ID。关联project_member表ID
	 */
	private Long memberId;
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
	 * 开始日期
	 */
	private Date startDate;
	/**
	 * 周数
	 */
	private Integer week;
	/**
	 * 年份
	 */
	private Integer year;

	private Integer projectId;

}
