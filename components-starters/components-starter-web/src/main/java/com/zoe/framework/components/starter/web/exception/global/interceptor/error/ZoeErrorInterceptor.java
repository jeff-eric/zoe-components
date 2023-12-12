package com.zoe.framework.components.starter.web.exception.global.interceptor.error;

import com.zoe.framework.components.core.common.response.Result;
import com.zoe.framework.components.core.exception.core.ZoeThrowable;
import com.zoe.framework.components.starter.web.entity.InterceptorResult;
import com.zoe.framework.components.starter.web.exception.core.interceptor.ErrorHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;

/**
 * @author 蒋时华
 * @date 2020-05-08 13:33:02
 */
@Slf4j
public class ZoeErrorInterceptor implements ErrorHandlerInterceptor {
    @Override
    public boolean support(Error e) {
        return e instanceof ZoeThrowable;
    }

    @Override
    public int order() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

    @Override
    public InterceptorResult execute(Error e) {
        ZoeThrowable zoeError = (ZoeThrowable) e;
        return InterceptorResult.builder()
                .next(false)
                .result(Result.Builder.result(zoeError.getIResult()))
                .build();
    }

}
