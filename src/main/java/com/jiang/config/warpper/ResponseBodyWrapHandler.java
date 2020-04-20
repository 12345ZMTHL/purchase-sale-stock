package com.jiang.config.warpper;

import com.jiang.config.BaseResult;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;


public class ResponseBodyWrapHandler implements HandlerMethodReturnValueHandler {
    private final HandlerMethodReturnValueHandler delegate;

    public ResponseBodyWrapHandler(HandlerMethodReturnValueHandler delegate) {
        this.delegate = delegate;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return this.delegate.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (!(returnValue instanceof BaseResult)) {
            returnValue = BaseResult.success(returnValue);
        }

        this.delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
    }
}
