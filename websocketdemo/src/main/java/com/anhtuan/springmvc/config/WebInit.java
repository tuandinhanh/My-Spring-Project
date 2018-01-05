package com.anhtuan.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

@Configuration
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*@Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("dispatchOptionsRequest", "true");
        registration.setAsyncSupported(true);
    }*/

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppWebSocketConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
