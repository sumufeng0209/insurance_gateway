package org.java.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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
        return true;//默认所有请求都进入过滤器
    }

    /**
     * 过滤器主体
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        return null;
    }
}
