package com.facs.agriculture;

import com.facs.basic.framework.core.FacsApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，系统入口
 *
 * @author luke
 * @create 2017-09-28 9:42
 **/
@MapperScan(basePackages={"com.facs.agriculture.dao"})
@SpringBootApplication(scanBasePackages = {"com.facs.agriculture","com.facs.basic.framework","com.facs.framework.plugin"})
public class Application {
    public static void main(String[] args){
        FacsApplication.start(Application.class,args);
    }
}
