package com.facs.agriculture.dao;

import com.facs.agriculture.support.model.dto.ProjectMemberRequest;
import com.facs.agriculture.support.model.dto.ProjectMemberResponse;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.ProjectMemberQuery;
import com.facs.agriculture.support.model.po.ProjectMember;
import java.util.List;

public interface ProjectMemberMapper {
	
	List<ProjectMember> loadPage(BoPageRequest<ProjectMemberQuery> condition);

	Long total(ProjectMemberQuery condition);

	ProjectMember load(Long id);

	List<ProjectMember> loadPageByPorjectid(Long projectId);

	int insert(ProjectMember object);

	int update(ProjectMember object);

	int delete(Long id);

    List<ProjectMember> loadAll();

	ProjectMemberResponse load(ProjectMemberRequest paramData);
}