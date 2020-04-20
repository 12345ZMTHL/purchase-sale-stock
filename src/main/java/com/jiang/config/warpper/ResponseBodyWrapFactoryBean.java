package com.jiang.config.warpper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ResponseBodyWrapFactoryBean implements InitializingBean {
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public ResponseBodyWrapFactoryBean() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = this.requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        this.decorateHandlers(handlers);
        this.requestMappingHandlerAdapter.setReturnValueHandlers(Collections.unmodifiableList(handlers));
    }

    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        Iterator var2 = handlers.iterator();

        while(var2.hasNext()) {
            HandlerMethodReturnValueHandler handler = (HandlerMethodReturnValueHandler)var2.next();
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                int index = handlers.indexOf(handler);
                handlers.set(index, new ResponseBodyWrapHandler(handler));
                break;
            }
        }

    }
}
