package com.facs.agriculture.iservice;

import com.facs.agriculture.support.model.po.Resource;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.facs.agriculture.support.model.dto.*;

import java.util.List;

public interface IResourceService {

    MutiResponse<ResourceResponse> loadPage(PageRequest<ResourceQueryRequest> paramData);

    ResourceResponse load(ResourceRequest paramData);

    List<Resource> loadAll();

    boolean existsByPath(String path);

    ResourceResponse create(ResourceRequest object);

    ResourceResponse update(ResourceRequest object);

}