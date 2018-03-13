package com.facs.agriculture.iservice;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IUserRoleService {

    MutiResponse<UserRoleResponse> loadPage(PageRequest<UserRoleQueryRequest> paramData);

    UserRoleResponse load(UserRoleRequest paramData);

    UserRoleResponse create(UserRoleRequest object);

    UserRoleResponse update(UserRoleRequest object);

}