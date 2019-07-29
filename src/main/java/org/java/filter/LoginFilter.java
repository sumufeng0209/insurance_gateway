package org.java.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;


@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        //表示过滤类型为：前过滤器
        return PRE_TYPE;
    }

    /**
     * 指定过滤器执行顺序：值越小，越优先
     * @return
     */
    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }

    /**
     * 该方法用于判断是否进入过滤器主体
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //获得请求上下文
        RequestContext context = RequestContext.getCurrentContext();
        
        //通过请求上下文获得请求
        HttpServletRequest request = context.getRequest();

        //获得请求地址
        String uri = request.getRequestURI();

        //判断哪些请求不拦截直接放行
        if(
                uri.startsWith("/gateway/employee/assets") ||
                uri.startsWith("/gateway/employee/login") ||
                uri.startsWith("/gateway/employee/forwardLogin") ||
                uri.endsWith(".js") ||
                uri.endsWith(".css")
        ){
            return false;
        }

        return true;
    }

    /**
     * 过滤器主体
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获得上下文
        RequestContext context = RequestContext.getCurrentContext();
        
        //获得请求
        HttpServletRequest request = context.getRequest();

        //获得响应
        HttpServletResponse response = context.getResponse();

        //获得会话
        HttpSession session = request.getSession();

        //判断用户是否登陆
        Map<String,Object> emp = (Map<String, Object>) session.getAttribute("emp");
        if(emp==null){
            //跳转到登陆页面
            try {
                response.sendRedirect("http://localhost:9000/gateway/employee/forwardLogin");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            return null;
        }
    }
}
