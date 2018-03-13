package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.ProjectMemberDetailQuery;
import com.facs.agriculture.support.model.po.ProjectMemberDetail;
import java.util.List;

public interface ProjectMemberDetailMapper {
	
	List<ProjectMemberDetail> loadPage(BoPageRequest<ProjectMemberDetailQuery> condition);

	Long total(ProjectMemberDetailQuery condition);

	ProjectMemberDetail load(Long id);

	int insert(ProjectMemberDetail object);

	int update(ProjectMemberDetail object);

	int delete(Long id);

    List<ProjectMemberDetail> loadPageByMemberid(Long memberId);
}