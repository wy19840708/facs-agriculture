package com.facs.agriculture.http;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IRoleService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="role")
public class RoleController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping(value="index")
    public String index(){
		return "index";
	}

	@RequestMapping(value="/roles")
	public @ResponseBody DataResult<MutiResponse<RoleResponse>> page(@RequestBody PageRequest<RoleQueryRequest> paramData) {
		MutiResponse<RoleResponse> paging = roleService.loadPage(paramData);
		return DataResult.ok(paging);
	}



	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<RoleResponse> load(RoleRequest request) {
		RoleResponse object = roleService.load(request);
		return DataResult.ok(object);
	}




	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody RoleRequest request) {
		RoleResponse object = roleService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody RoleRequest request) {
		RoleResponse object = roleService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
}