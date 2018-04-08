package com.facs.agriculture.service.impl;

import com.facs.agriculture.dao.ResourceMapper;
import com.facs.agriculture.iservice.IResourceService;
import com.facs.agriculture.support.model.bo.ResourceQuery;
import com.facs.agriculture.support.model.dto.*;
import com.facs.agriculture.support.model.po.Resource;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public MutiResponse<ResourceResponse> loadPage(PageRequest<ResourceQueryRequest> paramData) {
		paramData.getParam().setBusinessStatus(StringUtils.trimToNull(paramData.getParam().getBusinessStatus()));
		//转换查询条件的格式
    	BoPageRequest<ResourceQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), ResourceQuery.class));

		//获取数据
		List<Resource> list = resourceMapper.loadPage(daoRequest);
		Long total = resourceMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<ResourceResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, ResourceResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public ResourceResponse load(ResourceRequest paramDatam) {
		Resource objectm = resourceMapper.load(paramDatam.getId());
		return FacsBeanUtils.copy(objectm,ResourceResponse.class);
	}


	@Override
	public List<Resource> loadAll() {
		List<Resource> listp = resourceMapper.loadAll();
		return listp;
	}


	@Override
	public boolean existsByPath(String path) {
		Long num = resourceMapper.existsByPath(path);
		return num==0?false:true;
	}

	@Override
	public ResourceResponse create(ResourceRequest paramData) {
		Resource newObj = FacsBeanUtils.copy(paramData, Resource.class);

		newObj.setDataStatus(1);
		newObj.setBusinessStatus("1");
		newObj.setCreateTime(new Date());
		newObj.setModifyTime(new Date());

		resourceMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,ResourceResponse.class);
	}

	@Override
	public ResourceResponse update(ResourceRequest paramData) {
		Resource updateObj = FacsBeanUtils.copy(paramData, Resource.class);
		resourceMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,ResourceResponse.class);
	}
}
