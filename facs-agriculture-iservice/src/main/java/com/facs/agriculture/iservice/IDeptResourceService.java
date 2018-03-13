package com.facs.agriculture.iservice;

import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IDeptResourceService {

    MutiResponse<DeptResourceResponse> loadPage(PageRequest<DeptResourceQueryRequest> paramData);

    DeptResourceResponse load(DeptResourceRequest paramData);

    DeptResourceResponse create(DeptResourceRequest object);

    DeptResourceResponse update(DeptResourceRequest object);

}