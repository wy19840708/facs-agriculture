package com.facs.agriculture.iservice;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IDepartmentService {

    MutiResponse<DepartmentResponse> loadPage(PageRequest<DepartmentQueryRequest> paramData);

    DepartmentResponse load(DepartmentRequest paramData);

    DepartmentResponse create(DepartmentRequest object);

    DepartmentResponse update(DepartmentRequest object);

}