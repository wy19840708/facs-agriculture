package com.facs.agriculture.iservice;

import com.facs.agriculture.support.model.bo.UserQuery;
import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.agriculture.support.model.po.User;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IUserService {

    MutiResponse<UserResponse> loadPage(PageRequest<UserQueryRequest> paramData);

    UserResponse load(UserRequest paramData);

    UserResponse loadu(UserRequest paramDatau);

    List<User> loadPageByUserid(Long Id);

    List<User> loadAll();

    UserResponse create(UserRequest object);

    UserResponse update(UserRequest paramData);


}