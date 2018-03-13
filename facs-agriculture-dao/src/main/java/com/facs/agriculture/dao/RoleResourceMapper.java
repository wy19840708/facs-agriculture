package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.RoleResourceQuery;
import com.facs.agriculture.support.model.po.RoleResource;
import java.util.List;

public interface RoleResourceMapper {
	
	List<RoleResource> loadPage(BoPageRequest<RoleResourceQuery> condition);

	Long total(RoleResourceQuery condition);

	RoleResource load(Long id);

	int insert(RoleResource object);

	int update(RoleResource object);
}