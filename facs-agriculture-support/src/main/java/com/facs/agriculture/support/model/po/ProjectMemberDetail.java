package com.facs.agriculture.support.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

/**
 * 项目成员工时明细
 */
@Data
public class ProjectMemberDetail implements Serializable {

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
	private Integer projectId;

	public ProjectMemberDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public String getBusinessStatus() {
		return this.businessStatus;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public Long getCreator() {
		return this.creator;
	}

	public Integer getDataStatus() {
		return this.dataStatus;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public Long getManHour() {
		return this.manHour;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public Long getModifier() {
		return this.modifier;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public String getName() {
		return this.name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Integer getWeek() {
		return this.week;
	}

	public Integer getYear() {
		return this.year;
	}

	public Integer getProjectId() {
		return this.projectId;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setManHour(Long manHour) {
		this.manHour = manHour;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}


	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof ProjectMemberDetail)) {
			return false;
		} else {
			ProjectMemberDetail other = (ProjectMemberDetail)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$id = this.getId();
				Object other$id = other.getId();
				if (this$id == null) {
					if (other$id != null) {
						return false;
					}
				} else if (!this$id.equals(other$id)) {
					return false;
				}

				Object this$businessStatus = this.getBusinessStatus();
				Object other$businessStatus = other.getBusinessStatus();
				if (this$businessStatus == null) {
					if (other$businessStatus != null) {
						return false;
					}
				} else if (!this$businessStatus.equals(other$businessStatus)) {
					return false;
				}

				Object this$createTime = this.getCreateTime();
				Object other$createTime = other.getCreateTime();
				if (this$createTime == null) {
					if (other$createTime != null) {
						return false;
					}
				} else if (!this$createTime.equals(other$createTime)) {
					return false;
				}

				label206: {
					Object this$creator = this.getCreator();
					Object other$creator = other.getCreator();
					if (this$creator == null) {
						if (other$creator == null) {
							break label206;
						}
					} else if (this$creator.equals(other$creator)) {
						break label206;
					}

					return false;
				}

				label199: {
					Object this$dataStatus = this.getDataStatus();
					Object other$dataStatus = other.getDataStatus();
					if (this$dataStatus == null) {
						if (other$dataStatus == null) {
							break label199;
						}
					} else if (this$dataStatus.equals(other$dataStatus)) {
						break label199;
					}

					return false;
				}

				Object this$endDate = this.getEndDate();
				Object other$endDate = other.getEndDate();
				if (this$endDate == null) {
					if (other$endDate != null) {
						return false;
					}
				} else if (!this$endDate.equals(other$endDate)) {
					return false;
				}

				label185: {
					Object this$manHour = this.getManHour();
					Object other$manHour = other.getManHour();
					if (this$manHour == null) {
						if (other$manHour == null) {
							break label185;
						}
					} else if (this$manHour.equals(other$manHour)) {
						break label185;
					}

					return false;
				}

				label178: {
					Object this$memberId = this.getMemberId();
					Object other$memberId = other.getMemberId();
					if (this$memberId == null) {
						if (other$memberId == null) {
							break label178;
						}
					} else if (this$memberId.equals(other$memberId)) {
						break label178;
					}

					return false;
				}

				Object this$modifier = this.getModifier();
				Object other$modifier = other.getModifier();
				if (this$modifier == null) {
					if (other$modifier != null) {
						return false;
					}
				} else if (!this$modifier.equals(other$modifier)) {
					return false;
				}

				Object this$modifyTime = this.getModifyTime();
				Object other$modifyTime = other.getModifyTime();
				if (this$modifyTime == null) {
					if (other$modifyTime != null) {
						return false;
					}
				} else if (!this$modifyTime.equals(other$modifyTime)) {
					return false;
				}

				label157: {
					Object this$name = this.getName();
					Object other$name = other.getName();
					if (this$name == null) {
						if (other$name == null) {
							break label157;
						}
					} else if (this$name.equals(other$name)) {
						break label157;
					}

					return false;
				}

				label150: {
					Object this$startDate = this.getStartDate();
					Object other$startDate = other.getStartDate();
					if (this$startDate == null) {
						if (other$startDate == null) {
							break label150;
						}
					} else if (this$startDate.equals(other$startDate)) {
						break label150;
					}

					return false;
				}

				Object this$week = this.getWeek();
				Object other$week = other.getWeek();
				if (this$week == null) {
					if (other$week != null) {
						return false;
					}
				} else if (!this$week.equals(other$week)) {
					return false;
				}

				label136: {
					Object this$projectId = this.getProjectId();
					Object other$projectId  = other.getProjectId();
					if (this$projectId == null) {
						if (other$projectId  == null) {
							break label136;
						}
					} else if (this$projectId .equals(other$projectId )) {
						break label136;
					}

					return false;
				}
				label136: {
					Object this$year = this.getYear();
					Object other$year = other.getYear();
					if (this$year == null) {
						if (other$year == null) {
							break label136;
						}
					} else if (this$year.equals(other$year)) {
						break label136;
					}

					return false;
				}

				return true;
			}
		}
	}
	protected boolean canEqual(Object other) {
		return other instanceof ProjectMemberDetail;
	}

	public int hashCode() {
		boolean PRIME = true;
		int result = 1;
		Object $id = this.getId();
		result = result * 59 + ($id == null ? 43 : $id.hashCode());
		Object $businessStatus = this.getBusinessStatus();
		result = result * 59 + ($businessStatus == null ? 43 : $businessStatus.hashCode());
		Object $createTime = this.getCreateTime();
		result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
		Object $creator = this.getCreator();
		result = result * 59 + ($creator == null ? 43 : $creator.hashCode());
		Object $dataStatus = this.getDataStatus();
		result = result * 59 + ($dataStatus == null ? 43 : $dataStatus.hashCode());
		Object $endDate = this.getEndDate();
		result = result * 59 + ($endDate == null ? 43 : $endDate.hashCode());
		Object $manHour = this.getManHour();
		result = result * 59 + ($manHour == null ? 43 : $manHour.hashCode());
		Object $memberId = this.getMemberId();
		result = result * 59 + ($memberId == null ? 43 : $memberId.hashCode());
		Object $modifier = this.getModifier();
		result = result * 59 + ($modifier == null ? 43 : $modifier.hashCode());
		Object $modifyTime = this.getModifyTime();
		result = result * 59 + ($modifyTime == null ? 43 : $modifyTime.hashCode());
		Object $name = this.getName();
		result = result * 59 + ($name == null ? 43 : $name.hashCode());
		Object $startDate = this.getStartDate();
		result = result * 59 + ($startDate == null ? 43 : $startDate.hashCode());
		Object $week = this.getWeek();
		result = result * 59 + ($week == null ? 43 : $week.hashCode());
		Object $year = this.getYear();
		result = result * 59 + ($year == null ? 43 : $year.hashCode());
		Object $projectId = this.getProjectId();
		result = result * 59 + ($projectId == null ? 43 : $projectId.hashCode());
		return result;
	}

	public String toString() {
		return "ProjectMemberDetail(id=" + this.getId() + ", businessStatus=" + this.getBusinessStatus() + ", createTime=" + this.getCreateTime() + ", creator=" + this.getCreator() + ", dataStatus=" + this.getDataStatus() + ", endDate=" + this.getEndDate() + ", manHour=" + this.getManHour() + ", memberId=" + this.getMemberId() + ", modifier=" + this.getModifier() + ", modifyTime=" + this.getModifyTime() + ", name=" + this.getName() + ", startDate=" + this.getStartDate() + ", week=" + this.getWeek() + ", year=" + this.getYear() + ", projectId=" + this.getProjectId() + ")";
	}
}
