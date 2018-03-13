package com.facs.agriculture.service.impl;

import com.facs.agriculture.dao.ProjectMapper;
import com.facs.agriculture.iservice.IProjectService;
import com.facs.agriculture.support.model.bo.ProjectQuery;
import com.facs.agriculture.support.model.dto.ProjectQueryRequest;
import com.facs.agriculture.support.model.dto.ProjectRequest;
import com.facs.agriculture.support.model.dto.ProjectResponse;
import com.facs.agriculture.support.model.po.Project;
import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public MutiResponse<ProjectResponse> loadPage(PageRequest<ProjectQueryRequest> paramData) {

		paramData.getParam().setCode(StringUtils.trimToNull(paramData.getParam().getCode()));
		paramData.getParam().setName(StringUtils.trimToNull(paramData.getParam().getName()));
		//转换查询条件的格式
    	BoPageRequest<ProjectQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), ProjectQuery.class));

		//获取数据
		List<Project> list = projectMapper.loadPage(daoRequest);
		Long total = projectMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<ProjectResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, ProjectResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public ProjectResponse load(ProjectRequest paramData) {
		Project object = projectMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,ProjectResponse.class);
	}



	@Override
	public  List<Project> loadAll() {
		List<Project> list = projectMapper.loadAll();
		return list;
	}

	@Override
	public List<Project> loadPageByPorjectid(Long projectId) {
		return null;
	}


	@Override
	public ProjectResponse create(ProjectRequest paramData) {
		Project newObj = FacsBeanUtils.copy(paramData, Project.class);
		newObj.setDataStatus(1);
	/*	newObj.setBusinessStatus("1");*/
		newObj.setCreateTime(new Date());
		newObj.setModifyTime(new Date());
		projectMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,ProjectResponse.class);
	}

	@Override
	public ProjectResponse update(ProjectRequest paramData) {
		Project updateObj = FacsBeanUtils.copy(paramData, Project.class);
		projectMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,ProjectResponse.class);
	}

	@Override
	public void delete(ProjectRequest paramData) {
		projectMapper.delete(paramData.getId());
	}
}
