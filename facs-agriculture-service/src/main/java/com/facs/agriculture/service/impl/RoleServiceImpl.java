package com.facs.agriculture.service.impl;

import com.facs.agriculture.dao.RoleMapper;
import com.facs.agriculture.iservice.IRoleService;
import com.facs.agriculture.support.model.bo.RoleQuery;
import com.facs.agriculture.support.model.dto.RoleQueryRequest;
import com.facs.agriculture.support.model.dto.RoleRequest;
import com.facs.agriculture.support.model.dto.RoleResponse;
import com.facs.agriculture.support.model.po.Role;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public MutiResponse<RoleResponse> loadPage(PageRequest<RoleQueryRequest> paramData) {
		//转换查询条件的格式
    	BoPageRequest<RoleQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), RoleQuery.class));

		//获取数据
		List<Role> list = roleMapper.loadPage(daoRequest);
		Long total = roleMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<RoleResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, RoleResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public RoleResponse load(RoleRequest paramData) {
		Role object = roleMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,RoleResponse.class);
	}

	@Override
	public RoleResponse create(RoleRequest paramData) {
		Role newObj = FacsBeanUtils.copy(paramData, Role.class);
		roleMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,RoleResponse.class);
	}

	@Override
	public RoleResponse update(RoleRequest paramData) {
		Role updateObj = FacsBeanUtils.copy(paramData, Role.class);
		roleMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,RoleResponse.class);
	}
}
