package com.facs.agriculture.support.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 项目管理。

 */
@Data
public class ProjectQuery implements Serializable {

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
	 * 编码
	 */
	private String code;
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
	 * 负责人。关联user表ID
	 */
	private Long leader;
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
	 * 优先级
	 */
	private String priority;
	/**
	 * 开始日期
	 */
	private Date startDate;

}
