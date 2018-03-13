package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.ResourceQuery;
import com.facs.agriculture.support.model.po.Resource;
import java.util.List;

public interface ResourceMapper {
	
	List<Resource> loadPage(BoPageRequest<ResourceQuery> condition);

	Long total(ResourceQuery condition);

	Resource load(Long id);

	Long existsByPath(String path);

	int insert(Resource object);

	int update(Resource object);
}