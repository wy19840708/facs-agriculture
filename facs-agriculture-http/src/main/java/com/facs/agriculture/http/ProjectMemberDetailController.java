package com.facs.agriculture.http;

import com.facs.agriculture.iservice.IProjectMemberService;
import com.facs.agriculture.iservice.IProjectService;
import com.facs.agriculture.service.impl.ProjectMemberDetailServiceImpl;
import com.facs.agriculture.service.impl.ProjectMemberServiceImpl;
import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.agriculture.support.model.po.ProjectMemberDetail;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IProjectMemberDetailService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value="projectmemberdetail")
public class ProjectMemberDetailController {

	@Autowired
	private IProjectMemberDetailService projectMemberDetailService;


	@Autowired
	private IProjectMemberService projectmemberService;


	@Autowired
	private ProjectMemberDetailServiceImpl projectmemberdetailMapper;

	@RequestMapping(value="index")
	public String index(){
		return "UserManage/index";
	}

	@RequestMapping(value="add")

	public String add(Long id, HttpServletRequest requeste){
		ModelAndView mv = new ModelAndView();
		ProjectMemberRequest paramData = new ProjectMemberRequest();
		paramData.setId(id);
		ProjectMemberResponse object = projectmemberService.load(paramData);
		List<ProjectMember> list=projectmemberService.loadAll();
		requeste.setAttribute("id",id);
		requeste.setAttribute("list",list);
		return "UserManage/add";
	}

	@RequestMapping(value="edit")
	public String edit(Long id,HttpServletRequest request){
		ProjectMemberDetailRequest requestObj = new ProjectMemberDetailRequest();
		requestObj.setId(id);
		ProjectMemberDetailResponse object = projectMemberDetailService.load(requestObj);
		request.setAttribute("id",id);
		request.setAttribute("obj",object);
		return "UserManage/edit";
	}


	@RequestMapping(value="/memberdetails")
	public @ResponseBody DataResult<MutiResponse<ProjectMemberDetailResponse>> page(@RequestBody PageRequest<ProjectMemberDetailQueryRequest> paramData) {
		MutiResponse<ProjectMemberDetailResponse> paging = projectMemberDetailService.loadPage(paramData);
		return DataResult.ok(paging);
	}

	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<ProjectMemberDetailResponse> load(ProjectMemberDetailRequest request) {
		ProjectMemberDetailResponse object = projectMemberDetailService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody ProjectMemberDetailRequest request) {
		ProjectMemberDetailResponse object = projectMemberDetailService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody ProjectMemberDetailRequest request) {
		ProjectMemberDetailResponse object = projectMemberDetailService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody DataResult<SingleResponse<Long>> delete(@RequestBody ProjectMemberDetailRequest request) {
		projectMemberDetailService.delete(request);
		return DataResult.ok(new SingleResponse<>(request.getId()));
	}


}