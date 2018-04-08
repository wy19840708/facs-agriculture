package com.facs.agriculture.service.impl;

import com.facs.agriculture.dao.ProjectMapper;
import com.facs.agriculture.dao.ProjectMemberDetailMapper;
import com.facs.agriculture.iservice.IProjectService;
import com.facs.agriculture.support.model.bo.ProjectQuery;
import com.facs.agriculture.support.model.dto.ProjectQueryRequest;
import com.facs.agriculture.support.model.dto.ProjectRequest;
import com.facs.agriculture.support.model.dto.ProjectResponse;
import com.facs.agriculture.support.model.po.Project;
import com.facs.agriculture.support.model.po.ProjectMemberDetail;
import com.facs.basic.framework.common.util.FacsBeanUtils;
import com.facs.basic.framework.model.bo.BoPageRequest;
import com.facs.basic.framework.model.dto.PageRequest;
import com.facs.basic.framework.model.rest.MutiResponse;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Resource;
import net.sf.mpxj.ResourceAssignment;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpp.MPPReader;
import net.sf.mpxj.mpx.MPXReader;
import net.sf.mpxj.mspdi.MSPDIReader;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("projectService")
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectMemberDetailMapper projectMemberDetailMapper;

    @Override
    public MutiResponse<ProjectResponse> loadPage(PageRequest<ProjectQueryRequest> paramData) {

        paramData.getParam().setCode(StringUtils.trimToNull(paramData.getParam().getCode()));
        paramData.getParam().setName(StringUtils.trimToNull(paramData.getParam().getName()));
        //转换查询条件的格式
        BoPageRequest<ProjectQuery> daoRequest = new BoPageRequest<>();
        daoRequest.setPageNum(paramData.getPageNo());
        daoRequest.setPageSize(paramData.getLimit());
        daoRequest.setStart((paramData.getPageNo() - 1) * paramData.getLimit());
        daoRequest.setParamData(FacsBeanUtils.copy(paramData.getParam(), ProjectQuery.class));

        //获取数据
        List<Project> list = projectMapper.loadPage(daoRequest);
        Long total = projectMapper.total(daoRequest.getParamData());

        //转换回传数据的格式
        MutiResponse<ProjectResponse> pageResponse = new MutiResponse<>();
        pageResponse.setItems(FacsBeanUtils.copyList(list, ProjectResponse.class));
        pageResponse.setTotal(total);
        pageResponse.setLimit(paramData.getLimit());
        pageResponse.setPageNo(paramData.getPageNo());
        return pageResponse;
    }

    @Override
    public ProjectResponse load(ProjectRequest paramDatap) {
        Project object = projectMapper.load(paramDatap.getId());
        return FacsBeanUtils.copy(object, ProjectResponse.class);
    }

    @Override
    public List<Project> loadAll() {
        List<Project> listp = projectMapper.loadAll();
        return listp;
    }

    @Override
    public List<Project> loadPageByPorjectid(Long projectId) {
        return null;
    }

    @Override
    public ProjectResponse create(ProjectRequest paramData) {
        Project newObj = FacsBeanUtils.copy(paramData, Project.class);
        newObj.setDataStatus(1);
        newObj.setCreateTime(new Date());
        newObj.setModifyTime(new Date());
        projectMapper.insert(newObj);
        return FacsBeanUtils.copy(newObj, ProjectResponse.class);
    }

    @Override
    public ProjectResponse update(ProjectRequest paramData) {
        Project updateObj = FacsBeanUtils.copy(paramData, Project.class);
        projectMapper.update(updateObj);
        return FacsBeanUtils.copy(updateObj, ProjectResponse.class);
    }

    @Override
    public void delete(ProjectRequest paramData) {
        projectMapper.delete(paramData.getId());
    }


    @Override
    public String batchImport(MultipartFile file) {
        final String mmp = "mmp";
        boolean projectfile = false;
        String filename = file.getOriginalFilename();
        String lastname = filename.substring(filename.lastIndexOf("."));
        if (lastname.equalsIgnoreCase(".mmp")) {
            projectfile = true;
        }
        String fileName = file.getOriginalFilename();
        try {
            InputStream is = file.getInputStream();

            MPPReader reader = new MPPReader();
            ProjectFile pf = reader.read(is);
            List<ProjectMemberDetail> list = readProjectValue(pf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mmp;
    }

    private List<ProjectMemberDetail> readProjectValue(ProjectFile wb) {
        List<Task> list = wb.getAllTasks();
        for (int i = 1; i < list.size(); i++) {
            ProjectMemberDetail pdetail = new ProjectMemberDetail();
            Task task = list.get(i);
            task.getUniqueID();
            task.getName();
            task.getStart();
            task.getFinish();
            task.getPercentageComplete();
            task.getOutlineLevel();
            task.getOutlineNumber();

            //save object

            parseTask(task,pdetail);
        }


        return null;

    }

    private void parseTask(Task pTask,ProjectMemberDetail pdetail) {
        List<Task> list = pTask.getChildTasks();
        if(list==null||list.size()==0){
            //解析当前任务明细
            //保存

            //判断几人任务

            parseSubDetail(pdetail);

            return;
        }
        for (int j = 0; j < list.size(); j++) {
            Task task = list.get(j);
            task.getUniqueID();
            task.getName();
            task.getStart();
            task.getFinish();
            task.getPercentageComplete();
            task.getOutlineLevel();
            task.getOutlineNumber();

            ProjectMemberDetail detail = new ProjectMemberDetail();
            detail.setPId(pdetail.getPId());
            //赋值
            //保存

            parseTask(task,detail);
        }
    }

    private void parseSubDetail(ProjectMemberDetail pdetail) {

    }
}





/* @Override
    public void batchImport(MultipartFile file) {
     //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
     //1、通过名称判读类型，2013还是以上版本
     final String xls = "xls";
     final String xlsx = "xlsx";

     boolean isExcel2003 = false;
     String filename = file.getOriginalFilename();
     String lastname = filename.substring(filename.lastIndexOf("."));
     if (lastname.equalsIgnoreCase(".xlsx")) {
         //lastname.equalsIgnoreCase忽略大小写
         isExcel2003 = true;
     }

     String fileName = file.getOriginalFilename();
     //获取excel文件的io流
     try {
         InputStream is = file.getInputStream();
     } catch (IOException e) {
         e.printStackTrace();
     }
     //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
     //根据文件名判断文件是2003版本还是2007版本

     try {
         Workbook wb = null;
         if (fileName.endsWith(xls)) {
             wb = new HSSFWorkbook(file.getInputStream());
         } else {
             wb = new XSSFWorkbook(file.getInputStream());
         }
         //读取Excel里面客户的信息

         List<ProjectMemberDetail> list = readExcelValue(wb);

     } catch (IOException e) {
         e.printStackTrace();
        }
       }
     }
        private List<ProjectMemberDetail> readExcelValue(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        //获取列
        int totalRows = sheet.getPhysicalNumberOfRows();
        //得到Excel的列数(前提是有行数)
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<ProjectMemberDetail> rowList = new ArrayList<>();
        //循环Excel行数,从第二行开始。标题不入库
        long projectId = 0;
        int year = 0;

        int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        for (int r = 0; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null)
                continue;
            int weeknow = 0;
            if (r == 0) {
                String value = row.getCell(1).getStringCellValue();
                //查询项目信息
                projectId = 1;
                continue;
            } else if (r == 1) {
                //转换日期格式，获取年月日
                Date date = row.getCell(1).getDateCellValue();
                Calendar now = Calendar.getInstance();
                now.setTime(date);
                year = now.get(Calendar.YEAR);
                int month = now.get(Calendar.MONTH);
                int day = now.get(Calendar.DAY_OF_MONTH);
                weeknow = now.get(Calendar.WEEK_OF_YEAR);

                //解析工时时间，设置startDate，endDate
                continue;
            } else if (r == 2) {
                continue;
            }

            ProjectMemberDetail obj = new ProjectMemberDetail();
            obj.setYear(year);
            obj.setDataStatus(1);

            //处理单元格中值得类型
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null == cell) {
                    continue;
                }
                String value = "";
                switch (cell.getCellTypeEnum()) {
                    case NUMERIC:
                        value = cell.getNumericCellValue() + "";
                        break;
                    default:
                        value = cell.getStringCellValue();
                        break;
                }
                if (StringUtils.isBlank(value)) {
                    continue;
                }
                //找到项目成员信息

                if (c == 0) {
                    String days = new String(value);
                    String[] splitAddress = days.split("d");
                    days = splitAddress[0];
                    obj.setBusinessStatus(days);
                }
                //获取第二列信息
                else if (c == 1) {
                    String name = new String(value);
                    String[] splitAddress = name.split("\\[");
                    name = splitAddress[0];
                    obj.setName(name);
                } else if (c == 2) {
                    String days = new String(value);
                    String[] splitAddress = days.split("h");
                    days = splitAddress[0];
                    obj.setManHour(new Double(days).longValue());
                } else if (c == 3) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date start_date = sdf.parse(value);
                        obj.setStartDate(start_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (c == 4) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date end_date = sdf.parse(value);
                        obj.setEndDate(end_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            caculateDate(obj);
            rowList.add(obj);
        }
        return rowList;
    }

    private void caculateDate(ProjectMemberDetail obj) {
        //insert obj
    *//*  projectMemberDetailMapper.insert(obj);*//*
        try {
            //计算开始周及此周工作日天数
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //获取开始时间
            Date start_date = obj.getStartDate();
            //将日期转换成字符串形式
            String strDateStart = sdf.format(start_date);
            //将日期转换成日期形式
            Date date_start = sdf.parse(strDateStart);
            //使用目前的时区和语言环境的方法得到一个日历
            Calendar d1 = Calendar.getInstance();
            d1.setTime(start_date);
            //获取日期所在周数
            int sweek = d1.get(Calendar.WEEK_OF_YEAR);
            int syear = d1.get(Calendar.YEAR);
            int sweek_day=d1.get(Calendar.DAY_OF_WEEK);

            //计算结束周及此周工作日天数
            Date end_date = obj.getEndDate();
            String strDateEnd = sdf.format(end_date);
            Date date_end = sdf.parse(strDateEnd);
            Calendar d2 = Calendar.getInstance();
            d2.setTime(end_date);
            int eweek = d2.get(Calendar.WEEK_OF_YEAR);
            int eyear = d1.get(Calendar.YEAR);
            int eweek_day=d2.get(Calendar.DAY_OF_WEEK);

            //间隔周数
            int getWeeksBetween = eweek - sweek;
            //工作日为(两天间隔天数)
            int workingDay = getWorkingDay(d1, d2);
            int time = getHolidays(d1, d2);
            Long totalhour = obj.getManHour();
            Double everytime = Double.valueOf(totalhour/workingDay);

            for (int i = 0; i <= getWeeksBetween; i++) {
                if (i == 0) {
                    //计算开始周信息
                    ProjectMemberDetail subObj = new ProjectMemberDetail();
                    subObj.setPId(obj.getId());
                    subObj.setDataStatus(1);
                   //获取开始周周末时间
                    int stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
                    // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
                    d1.add(Calendar.DATE, d1.getFirstDayOfWeek() +stmp);
                    String imptimeEnd = sdf.format(d1.getTime());

                    Date date_weekend = sdf.parse(imptimeEnd);
                    Double stime = Double.valueOf(everytime * stmp);

                    subObj.setPId(obj.getId());
                    subObj.setName(obj.getName());
                    subObj.setManHour(new Double(stime).longValue());
                    subObj.setStartDate(start_date);
                    subObj.setEndDate(date_weekend);
                    subObj.setYear(syear);
                    subObj.setWeek(sweek);
                    subObj.setWeekId(new Double(sweek).longValue());
                    subObj.setCreator(new Double(workingDay).longValue());
                    switch(obj.getName()) {
                        case "李甜":
                            subObj.setMemberId(new Double(1).longValue());
                            break;
                        case "陆磊":
                            subObj.setMemberId(new Double(2).longValue());
                            break;
                        case "王媛":
                            subObj.setMemberId(new Double(3).longValue());
                            break;
                        case "贾敏":
                            subObj.setMemberId(new Double(4).longValue());
                            break;
                        case "卢鑫":
                            subObj.setMemberId(new Double(5).longValue());
                            break;
                        case "彭蓓":
                            subObj.setMemberId(new Double(6).longValue());
                            break;
                        case "易勇":
                            subObj.setMemberId(new Double(7).longValue());
                            break;
                        default:
                            break;

                    }
                    projectMemberDetailMapper.insert(subObj);
                    continue;
                }
                else if(i!=0&&i!=getWeeksBetween) {

                   for (int b = 1; b < getWeeksBetween; b++) {
                        ProjectMemberDetail subObj = new ProjectMemberDetail();
                        Double mtime = Double.valueOf(everytime * 5);
                        String day_start = getStartDayOfWeekNo(syear, sweek+b);
                        Date datestart= sdf.parse(day_start);
                        String day_end=getEndDayOfWeekNo(syear, sweek+b);
                        Date dateend= sdf.parse(day_end);

                       subObj.setPId(obj.getId());
                       subObj.setName(obj.getName());
                       subObj.setManHour(new Double(mtime).longValue());
                       subObj.setYear(syear);
                       subObj.setStartDate(datestart);
                       subObj.setEndDate(dateend);
                       subObj.setDataStatus(1);
                       subObj.setWeek(sweek+b);
                       subObj.setWeekId(new Double(sweek+b).longValue());
                       subObj.setCreator(new Double(workingDay).longValue());

                       switch(obj.getName()) {
                           case "李甜":
                               subObj.setMemberId(new Double(1).longValue());
                               break;
                           case "陆磊":
                               subObj.setMemberId(new Double(2).longValue());
                               break;
                           case "王媛":
                               subObj.setMemberId(new Double(3).longValue());
                               break;
                           case "贾敏":
                               subObj.setMemberId(new Double(4).longValue());
                               break;
                           case "卢鑫":
                               subObj.setMemberId(new Double(5).longValue());
                               break;
                           case "彭蓓":
                               subObj.setMemberId(new Double(6).longValue());
                               break;
                           case "易勇":
                               subObj.setMemberId(new Double(7).longValue());
                               break;
                           default:
                               break;

                       }
                        projectMemberDetailMapper.insert(subObj);
                        continue;
                }
                    continue;
                }
                else if (i == getWeeksBetween) {
                    ProjectMemberDetail subObj = new ProjectMemberDetail();
                    subObj.setPId(obj.getId());
                    subObj.setDataStatus(1);

                    int etmp =d2.get(Calendar.DAY_OF_WEEK)-1;
                    d2.add(Calendar.DATE, d2.getFirstDayOfWeek()-etmp);
                    String imptimeBegin = sdf.format(d2.getTime());
                    Date date_weekstart = sdf.parse(imptimeBegin);
                    Double etime = Double.valueOf(everytime * etmp);

                    subObj.setPId(obj.getId());
                    subObj.setName(obj.getName());
                    subObj.setManHour(new Double(etime).longValue());
                    subObj.setStartDate(date_weekstart);
                    subObj.setEndDate(end_date);
                    subObj.setYear(eyear);
                    subObj.setWeek(eweek);
                    subObj.setWeekId(new Double(eweek).longValue());
                    subObj.setCreator(new Double(workingDay).longValue());
                    switch(obj.getName()) {
                        case "李甜":
                            subObj.setMemberId(new Double(1).longValue());
                            break;
                        case "陆磊":
                            subObj.setMemberId(new Double(2).longValue());
                            break;
                        case "王媛":
                            subObj.setMemberId(new Double(3).longValue());
                            break;
                        case "贾敏":
                            subObj.setMemberId(new Double(4).longValue());
                            break;
                        case "卢鑫":
                            subObj.setMemberId(new Double(5).longValue());
                            break;
                        case "彭蓓":
                            subObj.setMemberId(new Double(6).longValue());
                            break;
                        case "易勇":
                            subObj.setMemberId(new Double(7).longValue());
                            break;
                        default:
                            break;
                    }
                    projectMemberDetailMapper.insert(subObj);
                    continue;
                }
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }

        int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
                - d1.get(java.util.Calendar.DAY_OF_YEAR);
        int y2 = d2.get(java.util.Calendar.YEAR);
        if (d1.get(java.util.Calendar.YEAR) != y2) {
            d1 = (java.util.Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
                d1.add(java.util.Calendar.YEAR, 1);
            }
            while (d1.get(java.util.Calendar.YEAR) != y2);
        }
        return days;
    }

    public int getWorkingDay(java.util.Calendar d1, java.util.Calendar d2) {
        int result =-1;
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            java.util.Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int betweendays = getDaysBetween(d1, d2);
        int charge_date = 0;
        int charge_start_date = 0;//开始日期的日期偏移量
        int charge_end_date = 0;//结束日期的日期偏移量
        // 日期不在同一个日期内
        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);

        if (stmp != 0 && stmp != 6) {// 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {// 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
        result = (getDaysBetween(this.getNextMonday(d1), this.getNextMonday(d2)) / 7)
                * 5 + charge_start_date - charge_end_date;


      *//*  String date1 = sdf.format(d1.getTime());
        String date2 = sdf.format(d2.getTime());

        //工作日
        int workDay=0;
        int holiday=7;
        try {
            Date sd = sdf.parse(date1);
            Date ed = sdf.parse(date2);
            gc.setTime(sd);

            long time2 = ed.getTime() - sd.getTime();
            long day = time2/3600000/24 + 1;
            for(int i=0;i<day;i++){
                if (gc.get(GregorianCalendar.DAY_OF_WEEK)!= GregorianCalendar.SATURDAY &&
                        gc.get(GregorianCalendar.DAY_OF_WEEK)!= GregorianCalendar.SUNDAY) {
                    if(!holidayList(sdf.format(gc.getTime())) *//**//*&& !holidayOfCN(sdf.format(gc.getTime()))*//**//*)
                        workDay++;

                }
                //天数加1
                gc.add(gc.DATE,1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        result =workDay;*//*
        return result;
    }

    //春节放假三天，定义到2020年
    public boolean holidayOfCN(String year){
        List ls = new ArrayList();
        ls.add("2017-01-28");ls.add("2017-01-29");ls.add("2017-01-30");
        ls.add("2018-02-16");ls.add("2018-02-17");ls.add("2018-02-18");
        ls.add("2019-02-05");ls.add("2019-02-06");ls.add("2019-02-07");
        ls.add("2020-01-25");ls.add("2020-01-26");ls.add("2020-01-27");
        if(ls.contains(year))
            return true;
        return false;
    }
    //法定假日，五一和国庆
    public boolean holidayList(String findDate){
        List ls = new ArrayList();
        ls.add("2018-05-01");
        ls.add("2018-05-02");
        ls.add("2018-05-03");
        ls.add("2018-10-01");
        ls.add("2018-10-02");
        ls.add("2018-10-03");
        if(ls.contains(findDate))
            return true;
        return false;
    }

    public Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        }
        while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }


    private Calendar getCalendarFormYear(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }


    public String getStartDayOfWeekNo(int year,int weekNo){

        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }

    public String getEndDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
                cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getHolidays(java.util.Calendar d1, java.util.Calendar d2)
    {
        return this.getDaysBetween(d1, d2) - this.getWorkingDay(d1, d2);
    }

}*/

