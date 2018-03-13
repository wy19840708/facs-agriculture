package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.ProjectQuery;
import com.facs.agriculture.support.model.po.Project;
import java.util.List;

public interface ProjectMapper {
	
	List<Project> loadPage(BoPageRequest<ProjectQuery> condition);

	Long total(ProjectQuery condition);

	Project load(Long id);

	int insert(Project object);

	int update(Project object);

	int delete(Long id);

    List<Project> loadPageByPorjectid(Long projectId);

	List<Project>loadAll();
}