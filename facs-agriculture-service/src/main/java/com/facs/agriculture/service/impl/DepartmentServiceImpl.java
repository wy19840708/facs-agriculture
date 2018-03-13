package com.facs.agriculture.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.dao.DepartmentMapper;
import com.facs.agriculture.iservice.IDepartmentService;
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

@Service("departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public MutiResponse<DepartmentResponse> loadPage(PageRequest<DepartmentQueryRequest> paramData) {
		//转换查询条件的格式
    	BoPageRequest<DepartmentQuery> daoRequest = new BoPageRequest<>();
		daoRequest.setPageNum(paramData.getPageNo());
		daoRequest.setPageSize(paramData.getLimit());
		daoRequest.setStart((paramData.getPageNo()-1)*paramData.getLimit());
		daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), DepartmentQuery.class));

		//获取数据
		List<Department> list = departmentMapper.loadPage(daoRequest);
		Long total = departmentMapper.total(daoRequest.getParamData());

		//转换回传数据的格式
		MutiResponse<DepartmentResponse> pageResponse = new MutiResponse<>();
		pageResponse.setItems(FacsBeanUtils.copyList(list, DepartmentResponse.class));
		pageResponse.setTotal(total);
		pageResponse.setLimit(paramData.getLimit());
		pageResponse.setPageNo(paramData.getPageNo());
		return  pageResponse;
	}

    @Override
	public DepartmentResponse load(DepartmentRequest paramData) {
		Department object = departmentMapper.load(paramData.getId());
		return FacsBeanUtils.copy(object,DepartmentResponse.class);
	}

	@Override
	public DepartmentResponse create(DepartmentRequest paramData) {
		Department newObj = FacsBeanUtils.copy(paramData, Department.class);
		departmentMapper.insert(newObj);
		return FacsBeanUtils.copy(newObj,DepartmentResponse.class);
	}

	@Override
	public DepartmentResponse update(DepartmentRequest paramData) {
		Department updateObj = FacsBeanUtils.copy(paramData, Department.class);
		departmentMapper.update(updateObj);
		return FacsBeanUtils.copy(updateObj,DepartmentResponse.class);
	}
}
