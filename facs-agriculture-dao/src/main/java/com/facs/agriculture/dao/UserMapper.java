package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.UserQuery;
import com.facs.agriculture.support.model.po.User;
import java.util.List;

public interface UserMapper {
	
	List<User> loadPage(BoPageRequest<UserQuery> condition);

	Long total(UserQuery condition);

	User load(Long id);

	User loadByCode(String code);

	int insert(User object);

	int update(User object);
}