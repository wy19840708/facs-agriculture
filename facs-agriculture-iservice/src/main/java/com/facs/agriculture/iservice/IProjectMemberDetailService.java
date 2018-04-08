package com.facs.agriculture.iservice;

import com.facs.agriculture.support.model.po.ProjectMemberDetail;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IProjectMemberDetailService {

    MutiResponse<ProjectMemberDetailResponse> loadPage(PageRequest<ProjectMemberDetailQueryRequest> paramData);

    ProjectMemberDetailResponse load(ProjectMemberDetailRequest paramData);

    List<ProjectMemberDetail> loadPageByMemberid(Long memberId);

    ProjectMemberDetailResponse create(ProjectMemberDetailRequest object);

    ProjectMemberDetailResponse update(ProjectMemberDetailRequest object);

    public void delete(ProjectMemberDetailRequest paramData);

    List<ProjectMemberDetail> loadPageByProjectid(Long id);

}
