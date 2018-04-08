package com.facs.agriculture.http;

import com.facs.agriculture.iservice.IProjectMemberService;
import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.DataResult;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.basic.framework.model.rest.SingleResponse;
import com.facs.agriculture.iservice.IProjectService;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IProjectMemberService projectMemberService;



    @RequestMapping(value = "index")
    public String index() {
        return "Project/index";
    }

    @RequestMapping(value = "add")
    public String add() {
        return "Project/add";
    }

    @RequestMapping(value = "show")
    public ModelAndView show(Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        ProjectRequest paramData = new ProjectRequest();
        paramData.setId(id);
        ProjectResponse object = projectService.load(paramData);
        List<ProjectMember> list = projectMemberService.loadPageByPorjectid(id);

        mv.addObject("object", object);
        mv.addObject("list", list);
        mv.setViewName("Project/show");
        return mv;
    }

    @RequestMapping(value = "edit")
    public String edit(Long id, HttpServletRequest request) {
        ProjectRequest requestObj = new ProjectRequest();
        requestObj.setId(id);
        ProjectResponse object = projectService.load(requestObj);
        request.setAttribute("id", id);
        request.setAttribute("obj", object);
        return "Project/edit";
    }


    @RequestMapping(value = "/projects")
    public @ResponseBody
    DataResult<MutiResponse<ProjectResponse>> page(@RequestBody PageRequest<ProjectQueryRequest> paramData) {
        MutiResponse<ProjectResponse> paging = projectService.loadPage(paramData);
        return DataResult.ok(paging);
    }

    @RequestMapping(value = "/{id}")
    public @ResponseBody
    DataResult<ProjectResponse> load(ProjectRequest request) {
        ProjectResponse object = projectService.load(request);
        return DataResult.ok(object);
    }


    @RequestMapping(value = "/create")
    public @ResponseBody
    DataResult<SingleResponse<Long>> create(@RequestBody ProjectRequest request) {
        ProjectResponse object = projectService.create(request);
        return DataResult.ok(new SingleResponse<>(object.getId()));
    }

    @RequestMapping(value = "/modify")
    public @ResponseBody
    DataResult<SingleResponse<Long>> modify(@RequestBody ProjectRequest request) {
        ProjectResponse object = projectService.update(request);
        return DataResult.ok(new SingleResponse<>(object.getId()));
    }

    @RequestMapping(value = "/delete")
    public @ResponseBody
    DataResult<SingleResponse<Long>> delete(@RequestBody ProjectRequest request) {
        projectService.delete(request);
        return DataResult.ok(new SingleResponse<>(request.getId()));
    }

     @PostMapping(value = "/batchimport")
     public String batchimport(@RequestParam(value="filename") MultipartFile file,
                               HttpServletRequest request){
       /*  boolean judgement = false;
         String Msg =null;
         if(judgement){*/
          projectService.batchImport(file);
      /*       Msg ="批量导入EXCEL成功！";
         }else{
             Msg ="批量导入EXCEL失败！";
         }
         String res = "{ msg:'" + Msg + "'}";*/

         return "Project/index";

        }


    }

