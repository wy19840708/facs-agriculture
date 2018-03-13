package com.facs.agriculture.config;

import com.facs.agriculture.iservice.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description 访问权限控制
 * @Author luke
 * @Date 2018-02-02
 */
public class RequestIntercept implements HandlerInterceptor {

    @Autowired
    private IResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if("/login".equals(uri)||"/logout".equals(uri)||"/".equals(uri)||"/p404".equals(uri)){
            return true;
        }

        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if(obj==null){
            request.getRequestDispatcher("/").forward(request,response);
            return false;
        }
        if(resourceService.existsByPath(uri)){
            List<String> list = SystemConstants.getUserResource(obj.toString());
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("====postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("====afterCompletion");
    }
}
