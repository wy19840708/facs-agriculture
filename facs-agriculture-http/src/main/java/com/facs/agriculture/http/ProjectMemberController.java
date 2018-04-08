package com.facs.agriculture.http;

import com.facs.agriculture.iservice.*;
import com.facs.agriculture.support.model.po.*;
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
@RequestMapping(value="projectmember")
public class ProjectMemberController {

	@Autowired
	private IProjectMemberService projectMemberService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IProjectMemberDetailService projectMemberDetailService;


	@RequestMapping(value="index")
	public String index(Long id,Long idm, HttpServletRequest requeste){
		ModelAndView mv = new ModelAndView();
		ProjectRequest paramData = new ProjectRequest();
		paramData.setId(id);
		ProjectResponse object = projectService.load(paramData);
		List<Project> list=projectService.loadAll();
		requeste.setAttribute("id",id);
		requeste.setAttribute("list",list);

		ModelAndView mvm = new ModelAndView();
		ProjectMemberRequest paramDatam = new ProjectMemberRequest();
		paramDatam.setId(idm);
		ProjectMemberResponse objectm = projectMemberService.loadm(paramDatam);
		List<ProjectMember> listm=projectMemberService.loadAllm();
		requeste.setAttribute("idm",idm);
		requeste.setAttribute("listm",listm);


		return "Sum/index";
	}

	@RequestMapping(value="show")
	public ModelAndView show(Long id,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		ProjectMemberRequest paramData = new ProjectMemberRequest();
		paramData.setId(id);
		ProjectMemberResponse objecta = projectMemberService.load(paramData);
		List<ProjectMemberDetail> list=projectMemberDetailService.loadPageByMemberid(id);
		mv.addObject("object",objecta);
		mv.addObject("list",list);
		mv.setViewName("Sum/show");
		return mv;}

	@RequestMapping(value="show2")
	public ModelAndView show2(Long id, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		RoleRequest paramData = new RoleRequest();
		paramData.setId(id);
		RoleResponse object= roleService.load(paramData);
		List<Role> list=roleService.loadPageByRoleid(id);
		mv.addObject("object",object);
		mv.addObject("list",list);
		mv.setViewName("Sum/show2");
		return mv;
	}

	@RequestMapping(value="add")
	public String add(Long id,Long idp, HttpServletRequest requeste){

		ModelAndView mvp = new ModelAndView();
		ProjectRequest paramDatap = new ProjectRequest();
		paramDatap.setId(idp);
		ProjectResponse objectp = projectService.load(paramDatap);
		List<Project> listp=projectService.loadAll();
		requeste.setAttribute("idp",idp);
		requeste.setAttribute("listp",listp);
        //"listp",listp----"list"用于前端页面，listp命名projectService.loadAll();


		ModelAndView mv = new ModelAndView();
		UserRequest paramData = new UserRequest();
		paramData.setId(id);
		UserResponse object = userService.load(paramData);
		List<User> list=userService.loadAll();
		requeste.setAttribute("id",id);
		requeste.setAttribute("list",list);

		return "Sum/add";
	}

	@RequestMapping(value="edit")
	public String edit(Long id,HttpServletRequest request){
		ProjectMemberRequest requestObj = new ProjectMemberRequest();
		requestObj.setId(id);
		ProjectMemberResponse object = projectMemberService.load(requestObj);
		request.setAttribute("id",id);
		request.setAttribute("obj",object);
		return "Sum/edit";
	}

	@RequestMapping(value="/projects")
	public @ResponseBody DataResult<MutiResponse<ProjectMemberResponse>> page(@RequestBody PageRequest<ProjectMemberQueryRequest> paramData) {
		MutiResponse<ProjectMemberResponse> paging = projectMemberService.loadPage(paramData);
		return DataResult.ok(paging);
	}

	@RequestMapping(value="/{id}")
	public @ResponseBody DataResult<ProjectMemberResponse> load(ProjectMemberRequest request) {
		ProjectMemberResponse object = projectMemberService.load(request);
		return DataResult.ok(object);
	}

	@RequestMapping(value = "/create")
	public @ResponseBody DataResult<SingleResponse<Long>> create(@RequestBody ProjectMemberRequest request) {
		ProjectMemberResponse object = projectMemberService.create(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}

	@RequestMapping(value = "/modify")
	public @ResponseBody DataResult<SingleResponse<Long>> modify(@RequestBody ProjectMemberRequest request) {
		ProjectMemberResponse object = projectMemberService.update(request);
		return DataResult.ok(new SingleResponse<Long>(object.getId()));
	}
	@RequestMapping(value = "/delete")
	public @ResponseBody DataResult<SingleResponse<Long>> delete(@RequestBody ProjectMemberRequest request) {
		projectMemberService.delete(request);
		return DataResult.ok(new SingleResponse<>(request.getId()));
	}

}