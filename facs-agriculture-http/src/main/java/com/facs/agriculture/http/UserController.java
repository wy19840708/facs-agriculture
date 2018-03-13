package com.facs.agriculture.http;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IUserService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="user")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value="index")
    public String index(){
		return "index";
	}

	@RequestMapping(value="/users")
	public @ResponseBody DataResult<MutiResponse<UserResponse>> page(@RequestBody PageRequest<UserQueryRequest> paramData) {
		MutiResponse<UserResponse> paging = userService.loadPage(paramData);
		return DataResult.ok(paging);
	}

	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<UserResponse> load(UserRequest request) {
		UserResponse object = userService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody UserRequest request) {
		UserResponse object = userService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody UserRequest request) {
		UserResponse object = userService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
}