package com.facs.agriculture.service.impl;

import com.facs.agriculture.dao.ProjectMemberMapper;
import com.facs.agriculture.iservice.IProjectMemberService;
import com.facs.agriculture.support.model.bo.ProjectMemberQuery;
import com.facs.agriculture.support.model.dto.ProjectMemberQueryRequest;
import com.facs.agriculture.support.model.dto.ProjectMemberRequest;
import com.facs.agriculture.support.model.dto.ProjectMemberResponse;
import com.facs.agriculture.support.model.po.ProjectMember;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("projectMemberService")
public class ProjectMemberServiceImpl implements IProjectMemberService {

    @Autowired
    private ProjectMemberMapper projectMemberMapper;

    @Override
    public MutiResponse<ProjectMemberResponse> loadPage(PageRequest<ProjectMemberQueryRequest> paramData) {

        paramData.getParam().setName(StringUtils.trimToNull(paramData.getParam().getName()));

        //转换查询条件的格式
        BoPageRequest<ProjectMemberQuery> daoRequest = new BoPageRequest<>();
        daoRequest.setPageNum(paramData.getPageNo());
        daoRequest.setPageSize(paramData.getLimit());
        daoRequest.setStart((paramData.getPageNo() - 1) * paramData.getLimit());
        daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), ProjectMemberQuery.class));

        //获取数据
        List<ProjectMember> list = projectMemberMapper.loadPage(daoRequest);
        Long total = projectMemberMapper.total(daoRequest.getParamData());

        //转换回传数据的格式
        MutiResponse<ProjectMemberResponse> pageResponse = new MutiResponse<>();
        pageResponse.setItems(FacsBeanUtils.copyList(list, ProjectMemberResponse.class));
        pageResponse.setTotal(total);
        pageResponse.setLimit(paramData.getLimit());
        pageResponse.setPageNo(paramData.getPageNo());
        return pageResponse;
    }

    @Override
    public ProjectMemberResponse load(ProjectMemberRequest paramData) {
        ProjectMember object = projectMemberMapper.load(paramData.getId());
        return FacsBeanUtils.copy(object, ProjectMemberResponse.class);
    }


    @Override
    public List<ProjectMember> loadPageByPorjectid(Long projectId) {
        List<ProjectMember> list = projectMemberMapper.loadPageByPorjectid(projectId);
        return list;
    }

    @Override
    public ProjectMemberResponse create(ProjectMemberRequest paramData) {
        ProjectMember newObj = FacsBeanUtils.copy(paramData, ProjectMember.class);
        newObj.setDataStatus(1);
        /*newObj.setBusinessStatus("1");*/
        newObj.setCreateTime(new Date());
        newObj.setModifyTime(new Date());
        projectMemberMapper.insert(newObj);
        return FacsBeanUtils.copy(newObj, ProjectMemberResponse.class);
    }


    @Override
    public ProjectMemberResponse update(ProjectMemberRequest paramData) {
        ProjectMember updateObj = FacsBeanUtils.copy(paramData, ProjectMember.class);
        projectMemberMapper.update(updateObj);
        return FacsBeanUtils.copy(updateObj, ProjectMemberResponse.class);
    }

    @Override
    public void delete(ProjectMemberRequest paramData) {
        projectMemberMapper.delete(paramData.getId());
    }
}
