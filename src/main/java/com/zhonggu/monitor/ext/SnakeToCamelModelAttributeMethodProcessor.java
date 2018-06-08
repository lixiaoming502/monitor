package com.zhonggu.monitor.ext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;

/**
 *
 * 对于表单数据，controller数据绑定从module_name转换到moduleName
 * 参考：https://blog.csdn.net/zgzczzw/article/details/53912966
 */
public class SnakeToCamelModelAttributeMethodProcessor extends ServletModelAttributeMethodProcessor implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public SnakeToCamelModelAttributeMethodProcessor(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        SnakeToCamelRequestDataBinder camelBinder = new SnakeToCamelRequestDataBinder(binder.getTarget(), binder.getObjectName());
        RequestMappingHandlerAdapter requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
        requestMappingHandlerAdapter.getWebBindingInitializer().initBinder(camelBinder, request);
        camelBinder.bind(request.getNativeRequest(ServletRequest.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}