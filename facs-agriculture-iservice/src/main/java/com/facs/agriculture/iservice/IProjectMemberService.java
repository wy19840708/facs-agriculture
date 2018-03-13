package com.facs.agriculture.iservice;

import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IProjectMemberService {

    MutiResponse<ProjectMemberResponse> loadPage(PageRequest<ProjectMemberQueryRequest> paramData);

    ProjectMemberResponse load(ProjectMemberRequest paramData);

    List<ProjectMember> loadPageByPorjectid(Long projectId);

    List<ProjectMember> loadAll();

    ProjectMemberResponse create(ProjectMemberRequest object);

    ProjectMemberResponse update(ProjectMemberRequest object);

    public void delete(ProjectMemberRequest paramData);

   }

