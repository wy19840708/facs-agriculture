package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.UserRoleQuery;
import com.facs.agriculture.support.model.po.UserRole;
import java.util.List;

public interface UserRoleMapper {
	
	List<UserRole> loadPage(BoPageRequest<UserRoleQuery> condition);

	Long total(UserRoleQuery condition);

	UserRole load(Long id);

	int insert(UserRole object);

	int update(UserRole object);
}