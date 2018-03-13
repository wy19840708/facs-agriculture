package com.facs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.dao.ProjectMemberDetailMapper;
import com.facs.agriculture.iservice.IProjectMemberDetailService;
import com.facs.agriculture.support.model.bo.*;
import com.facs.agriculture.support.model.dto.*;
import com.facs.agriculture.support.model.po.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service("projectMemberDetailService")
public class ProjectMemberDetailServiceImpl implements IProjectMemberDetailService {

	@Autowired
	private ProjectMemberDetailMapper projectMemberDetailMapper;

	@Override
	public MutiResponse<ProjectMemberDetailResponse> loadPage(PageRequest<ProjectMemberDetailQueryRequest> paramData) {

	paramData.getParam().setName(StringUtils.trimToNull(paramData.getParam().getName()));

	//转换查询条件的格式
    	BoPageRequest<ProjectMemberDetailQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), ProjectMemberDetailQuery.class));

		//获取数据
		List<ProjectMemberDetail> list = projectMemberDetailMapper.loadPage(daoRequest);
		Long total = projectMemberDetailMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<ProjectMemberDetailResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, ProjectMemberDetailResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public ProjectMemberDetailResponse load(ProjectMemberDetailRequest paramData) {
		ProjectMemberDetail object = projectMemberDetailMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,ProjectMemberDetailResponse.class);
	}

	@Override
	public List<ProjectMemberDetail> loadPageByMemberid(Long memberId) {
		List<ProjectMemberDetail> list = projectMemberDetailMapper.loadPageByMemberid(memberId);
		return list;
	}


	@Override
	public ProjectMemberDetailResponse create(ProjectMemberDetailRequest paramData) {
		ProjectMemberDetail newObj = FacsBeanUtils.copy(paramData, ProjectMemberDetail.class);

		newObj.setDataStatus(1);
		newObj.setBusinessStatus("1");
		newObj.setCreateTime(new Date());
		newObj.setModifyTime(new Date());

		projectMemberDetailMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,ProjectMemberDetailResponse.class);
	}

	@Override
	public ProjectMemberDetailResponse update(ProjectMemberDetailRequest paramData) {
		ProjectMemberDetail updateObj = FacsBeanUtils.copy(paramData, ProjectMemberDetail.class);
		projectMemberDetailMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,ProjectMemberDetailResponse.class);
	}

	@Override
	public void delete(ProjectMemberDetailRequest paramData) {
		projectMemberDetailMapper.delete(paramData.getId());
	}

	@Override
	public List<ProjectMemberDetail> loadPageByPorjectid(Long id) {
		return null;
	}



}

