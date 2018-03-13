package com.facs.agriculture.http;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IDepartmentService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="department")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	@RequestMapping(value="index")
    public String index(){
		return "index";
	}

	@RequestMapping(value="/departments")
	public @ResponseBody DataResult<MutiResponse<DepartmentResponse>> page(@RequestBody PageRequest<DepartmentQueryRequest> paramData) {
		MutiResponse<DepartmentResponse> paging = departmentService.loadPage(paramData);
		return DataResult.ok(paging);
	}

	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<DepartmentResponse> load(DepartmentRequest request) {
		DepartmentResponse object = departmentService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody DepartmentRequest request) {
		DepartmentResponse object = departmentService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody DepartmentRequest request) {
		DepartmentResponse object = departmentService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
}