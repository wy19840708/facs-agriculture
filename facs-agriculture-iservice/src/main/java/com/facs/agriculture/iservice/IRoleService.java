package com.facs.agriculture.iservice;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IRoleService {

    MutiResponse<RoleResponse> loadPage(PageRequest<RoleQueryRequest> paramData);

    RoleResponse load(RoleRequest paramData);

    RoleResponse create(RoleRequest object);

    RoleResponse update(RoleRequest object);

}