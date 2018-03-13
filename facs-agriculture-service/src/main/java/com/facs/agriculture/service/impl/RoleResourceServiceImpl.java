package com.facs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.dao.RoleResourceMapper;
import com.facs.agriculture.iservice.IRoleResourceService;
import com.facs.agriculture.support.model.bo.*;
import com.facs.agriculture.support.model.dto.*;
import com.facs.agriculture.support.model.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service("roleResourceService")
public class RoleResourceServiceImpl implements IRoleResourceService {

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public MutiResponse<RoleResourceResponse> loadPage(PageRequest<RoleResourceQueryRequest> paramData) {
		//转换查询条件的格式
    	BoPageRequest<RoleResourceQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), RoleResourceQuery.class));

		//获取数据
		List<RoleResource> list = roleResourceMapper.loadPage(daoRequest);
		Long total = roleResourceMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<RoleResourceResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, RoleResourceResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public RoleResourceResponse load(RoleResourceRequest paramData) {
		RoleResource object = roleResourceMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,RoleResourceResponse.class);
	}

	@Override
	public RoleResourceResponse create(RoleResourceRequest paramData) {
		RoleResource newObj = FacsBeanUtils.copy(paramData, RoleResource.class);
		roleResourceMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,RoleResourceResponse.class);
	}

	@Override
	public RoleResourceResponse update(RoleResourceRequest paramData) {
		RoleResource updateObj = FacsBeanUtils.copy(paramData, RoleResource.class);
		roleResourceMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,RoleResourceResponse.class);
	}
}
