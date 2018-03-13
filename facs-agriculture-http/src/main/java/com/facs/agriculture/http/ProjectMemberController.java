package com.facs.agriculture.http;

import com.facs.agriculture.dao.ProjectMapper;
import com.facs.agriculture.iservice.IProjectMemberDetailService;
import com.facs.agriculture.iservice.IProjectService;
import com.facs.agriculture.support.model.po.Project;
import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.agriculture.support.model.po.ProjectMemberDetail;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IProjectMemberService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.management.PersistentMBean;
import javax.management.remote.rmi.RMIConnection;
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
	private IProjectMemberDetailService projectMemberDetailService;

	@RequestMapping(value="index")
	public String index(){
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
		return mv;
	}

	@RequestMapping(value="add")
	public String add(Long id, HttpServletRequest requeste){
		ModelAndView mv = new ModelAndView();
		ProjectRequest paramData = new ProjectRequest();
		paramData.setId(id);
		ProjectResponse object = projectService.load(paramData);
		List<Project> list=projectService.loadAll();
		requeste.setAttribute("id",id);
		requeste.setAttribute("list",list);
		return "Sum/add";
		//1、获取所有可用project信息。参考获取project详情中的成员信息的方法
		//2、request.setAttribute("list",object);
		//3、页面上用for标签便利数据
		//4、select元素的name设置为projectId
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