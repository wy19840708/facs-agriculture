package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.RoleQuery;
import com.facs.agriculture.support.model.po.Role;
import java.util.List;

public interface RoleMapper {
	
	List<Role> loadPage(BoPageRequest<RoleQuery> condition);

	Long total(RoleQuery condition);

	Role load(Long id);

	int insert(Role object);

	int update(Role object);

    List<Role> loadPageByRoleid(Long id);
}