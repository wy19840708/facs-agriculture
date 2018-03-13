package com.facs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.dao.UserRoleMapper;
import com.facs.agriculture.iservice.IUserRoleService;
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

@Service("userRoleService")
public class UserRoleServiceImpl implements IUserRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public MutiResponse<UserRoleResponse> loadPage(PageRequest<UserRoleQueryRequest> paramData) {
		//转换查询条件的格式
    	BoPageRequest<UserRoleQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), UserRoleQuery.class));

		//获取数据
		List<UserRole> list = userRoleMapper.loadPage(daoRequest);
		Long total = userRoleMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<UserRoleResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, UserRoleResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public UserRoleResponse load(UserRoleRequest paramData) {
		UserRole object = userRoleMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,UserRoleResponse.class);
	}

	@Override
	public UserRoleResponse create(UserRoleRequest paramData) {
		UserRole newObj = FacsBeanUtils.copy(paramData, UserRole.class);
		userRoleMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,UserRoleResponse.class);
	}

	@Override
	public UserRoleResponse update(UserRoleRequest paramData) {
		UserRole updateObj = FacsBeanUtils.copy(paramData, UserRole.class);
		userRoleMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,UserRoleResponse.class);
	}
}
