package com.facs.agriculture.http;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IDeptResourceService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="deptresource")
public class DeptResourceController {

	@Autowired
	private IDeptResourceService deptResourceService;

	@RequestMapping(value="index")
    public String index(){
		return "index";
	}

	@RequestMapping(value="/resources")
	public @ResponseBody DataResult<MutiResponse<DeptResourceResponse>> page(@RequestBody PageRequest<DeptResourceQueryRequest> paramData) {
		MutiResponse<DeptResourceResponse> paging = deptResourceService.loadPage(paramData);
		return DataResult.ok(paging);
	}

	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<DeptResourceResponse> load(DeptResourceRequest request) {
		DeptResourceResponse object = deptResourceService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody DeptResourceRequest request) {
		DeptResourceResponse object = deptResourceService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody DeptResourceRequest request) {
		DeptResourceResponse object = deptResourceService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
}