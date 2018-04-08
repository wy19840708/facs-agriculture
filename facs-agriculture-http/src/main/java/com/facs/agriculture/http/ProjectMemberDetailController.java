package com.facs.agriculture.http;

import com.facs.agriculture.iservice.*;
import com.facs.agriculture.service.impl.ProjectMemberDetailServiceImpl;
import com.facs.agriculture.support.model.po.Project;
import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.agriculture.support.model.po.Resource;
import com.facs.agriculture.support.model.po.User;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
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
	
	@Autowired
	private IProjectService projectService;

	@Autowired
	private IResourceService resourceService;

	@Autowired
	private IUserService userService;


	@RequestMapping(value="index")
	public String index(Long id, Long idm,Long idp,HttpServletRequest requeste){

		ModelAndView mvp = new ModelAndView();
		ProjectRequest paramDatap = new ProjectRequest();
		paramDatap.setId(idp);
		ProjectResponse objectp = projectService.load(paramDatap);
		List<Project> listp=projectService.loadAll();
		requeste.setAttribute("idp",idp);
		requeste.setAttribute("listp",listp);
		//"listp",listp----"list"用于前端页面，listp命名projectService.loadAll();

		ModelAndView mvm = new ModelAndView();
		ResourceRequest paramDatam = new ResourceRequest();
		paramDatap.setId(idm);
		ResourceResponse objectm = resourceService.load(paramDatam);
		List<Resource> listm=resourceService.loadAll();
		requeste.setAttribute("idm",idm);
		requeste.setAttribute("listm",listm);


		ModelAndView mv = new ModelAndView();
		UserRequest paramData = new UserRequest();
		paramData.setId(id);
		UserResponse object = userService.load(paramData);
		List<User> list=userService.loadAll();
		requeste.setAttribute("id",id);
		requeste.setAttribute("list",list);

		return "UserManage/index";
	}

	@RequestMapping(value="add")

	public String add(Long id,Long idm,Long idp, HttpServletRequest requeste){

		ModelAndView mvp = new ModelAndView();
		ProjectRequest paramDatap = new ProjectRequest();
		paramDatap.setId(idp);
		ProjectResponse objectp = projectService.load(paramDatap);
		List<Project> listp=projectService.loadAll();
		requeste.setAttribute("idp",idp);
		requeste.setAttribute("listp",listp);
		//"listp",listp----"list"用于前端页面，listp命名projectService.loadAll();

		ModelAndView mvm = new ModelAndView();
		ResourceRequest paramDatam = new ResourceRequest();
		paramDatap.setId(idm);
		ResourceResponse objectm = resourceService.load(paramDatam);
		List<Resource> listm=resourceService.loadAll();
		requeste.setAttribute("idm",idm);
		requeste.setAttribute("listm",listm);
		//"listp",listp----"list"用于前端页面，listp命名projectService.loadAll();


		ModelAndView mv = new ModelAndView();
		UserRequest paramData = new UserRequest();
		paramData.setId(id);
		UserResponse object = userService.load(paramData);
		List<User> list=userService.loadAll();
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