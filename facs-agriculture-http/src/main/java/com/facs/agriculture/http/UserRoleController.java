package com.facs.agriculture.http;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IUserRoleService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="userrole")
public class UserRoleController {

	@Autowired
	private IUserRoleService userRoleService;

	@RequestMapping(value="index")
    public String index(){
		return "index";
	}

	@RequestMapping(value="/roles")
	public @ResponseBody DataResult<MutiResponse<UserRoleResponse>> page(@RequestBody PageRequest<UserRoleQueryRequest> paramData) {
		MutiResponse<UserRoleResponse> paging = userRoleService.loadPage(paramData);
		return DataResult.ok(paging);
	}

	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<UserRoleResponse> load(UserRoleRequest request) {
		UserRoleResponse object = userRoleService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody UserRoleRequest request) {
		UserRoleResponse object = userRoleService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody UserRoleRequest request) {
		UserRoleResponse object = userRoleService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
}