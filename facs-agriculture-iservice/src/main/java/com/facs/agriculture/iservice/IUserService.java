package com.facs.agriculture.iservice;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IUserService {

    MutiResponse<UserResponse> loadPage(PageRequest<UserQueryRequest> paramData);

    UserResponse load(UserRequest paramData);

    UserResponse create(UserRequest object);

    UserResponse update(UserRequest object);

}