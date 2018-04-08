package com.facs.agriculture.http;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IResourceService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="resource")
public class ResourceController {

	@Autowired
	private IResourceService resourceService;

	@RequestMapping(value="index")
	public String index(){
		return "Person/index";
	}

	@RequestMapping(value = "add")
	public String add() {
		return "Person/add";
	}

	@RequestMapping(value="/resources")
	public @ResponseBody DataResult<MutiResponse<ResourceResponse>> page(@RequestBody PageRequest<ResourceQueryRequest> paramData) {
		MutiResponse<ResourceResponse> paging = resourceService.loadPage(paramData);
		return DataResult.ok(paging);
	}


	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<ResourceResponse> load(ResourceRequest request) {
		ResourceResponse object = resourceService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody ResourceRequest request) {
		ResourceResponse object = resourceService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody ResourceRequest request) {
		ResourceResponse object = resourceService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
}