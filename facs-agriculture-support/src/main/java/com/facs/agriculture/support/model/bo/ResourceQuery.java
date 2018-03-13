package com.facs.agriculture.support.model.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 系统资源管理
 */
@Data
public class ResourceQuery implements Serializable {

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
	 * 描述信息
	 */
	private String description;
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
	 * 父节点
	 */
	private Long pId;
	/**
	 * 资源路径
	 */
	private String pathInfo;

}
