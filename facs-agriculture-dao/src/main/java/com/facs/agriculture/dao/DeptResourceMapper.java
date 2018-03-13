package com.facs.agriculture.dao;

import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.agriculture.support.model.bo.DeptResourceQuery;
import com.facs.agriculture.support.model.po.DeptResource;
import java.util.List;

public interface DeptResourceMapper {
	
	List<DeptResource> loadPage(BoPageRequest<DeptResourceQuery> condition);

	Long total(DeptResourceQuery condition);

	DeptResource load(Long id);

	int insert(DeptResource object);

	int update(DeptResource object);
}