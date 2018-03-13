package com.facs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.dao.UserMapper;
import com.facs.agriculture.iservice.IUserService;
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

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public MutiResponse<UserResponse> loadPage(PageRequest<UserQueryRequest> paramData) {
		//转换查询条件的格式
    	BoPageRequest<UserQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), UserQuery.class));

		//获取数据
		List<User> list = userMapper.loadPage(daoRequest);
		Long total = userMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<UserResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, UserResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public UserResponse load(UserRequest paramData) {
		User object = userMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,UserResponse.class);
	}

	@Override
	public UserResponse create(UserRequest paramData) {
		User newObj = FacsBeanUtils.copy(paramData, User.class);
		userMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,UserResponse.class);
	}

	@Override
	public UserResponse update(UserRequest paramData) {
		User updateObj = FacsBeanUtils.copy(paramData, User.class);
		userMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,UserResponse.class);
	}
}
