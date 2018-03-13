package com.facs.agriculture.service.impl;

import com.facs.agriculture.dao.DeptResourceMapper;
import com.facs.agriculture.iservice.IDeptResourceService;
import com.facs.agriculture.support.model.bo.DeptResourceQuery;
import com.facs.agriculture.support.model.dto.DeptResourceQueryRequest;
import com.facs.agriculture.support.model.dto.DeptResourceRequest;
import com.facs.agriculture.support.model.dto.DeptResourceResponse;
import com.facs.agriculture.support.model.po.DeptResource;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptResourceService")
public class DeptResourceServiceImpl implements IDeptResourceService {

	@Autowired
	private DeptResourceMapper deptResourceMapper;

	@Override
	public MutiResponse<DeptResourceResponse> loadPage(PageRequest<DeptResourceQueryRequest> paramData) {
		//转换查询条件的格式
    	BoPageRequest<DeptResourceQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), DeptResourceQuery.class));

		//获取数据
		List<DeptResource> list = deptResourceMapper.loadPage(daoRequest);
		Long total = deptResourceMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<DeptResourceResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, DeptResourceResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public DeptResourceResponse load(DeptResourceRequest paramData) {
		DeptResource object = deptResourceMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,DeptResourceResponse.class);
	}

	@Override
	public DeptResourceResponse create(DeptResourceRequest paramData) {
		DeptResource newObj = FacsBeanUtils.copy(paramData, DeptResource.class);
		deptResourceMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,DeptResourceResponse.class);
	}

	@Override
	public DeptResourceResponse update(DeptResourceRequest paramData) {
		DeptResource updateObj = FacsBeanUtils.copy(paramData, DeptResource.class);
		deptResourceMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,DeptResourceResponse.class);
	}
}
