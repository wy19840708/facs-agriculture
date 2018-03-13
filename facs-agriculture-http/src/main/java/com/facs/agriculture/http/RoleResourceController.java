package com.facs.agriculture.http;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IRoleResourceService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="roleresource")
public class RoleResourceController {

	@Autowired
	private IRoleResourceService roleResourceService;

	@RequestMapping(value="index")
    public String index(){
		return "index";
	}

	@RequestMapping(value="/resources")
	public @ResponseBody DataResult<MutiResponse<RoleResourceResponse>> page(@RequestBody PageRequest<RoleResourceQueryRequest> paramData) {
		MutiResponse<RoleResourceResponse> paging = roleResourceService.loadPage(paramData);
		return DataResult.ok(paging);
	}

	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<RoleResourceResponse> load(RoleResourceRequest request) {
		RoleResourceResponse object = roleResourceService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody RoleResourceRequest request) {
		RoleResourceResponse object = roleResourceService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody RoleResourceRequest request) {
		RoleResourceResponse object = roleResourceService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
}