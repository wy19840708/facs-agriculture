package com.facs.agriculture.iservice;

import com.facs.agriculture.support.model.po.Project;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProjectService {

    MutiResponse<ProjectResponse> loadPage(PageRequest<ProjectQueryRequest> paramData);

    ProjectResponse load(ProjectRequest paramData);

    List<Project> loadAll();

    List<Project> loadPageByPorjectid(Long projectId);

    ProjectResponse create(ProjectRequest object);

    ProjectResponse update(ProjectRequest object);

    void delete(ProjectRequest object);

    String batchImport(MultipartFile file);

}