package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.DepartmentQuery;
import com.facs.agriculture.support.model.po.Department;
import java.util.List;

public interface DepartmentMapper {
	
	List<Department> loadPage(BoPageRequest<DepartmentQuery> condition);

	Long total(DepartmentQuery condition);

	Department load(Long id);

	int insert(Department object);

	int update(Department object);
}