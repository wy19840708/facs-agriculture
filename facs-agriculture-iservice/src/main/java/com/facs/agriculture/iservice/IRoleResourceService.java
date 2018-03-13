package com.facs.agriculture.iservice;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IRoleResourceService {

    MutiResponse<RoleResourceResponse> loadPage(PageRequest<RoleResourceQueryRequest> paramData);

    RoleResourceResponse load(RoleResourceRequest paramData);

    RoleResourceResponse create(RoleResourceRequest object);

    RoleResourceResponse update(RoleResourceRequest object);

}