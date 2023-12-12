package com.zoe.framework.components.example.starters.simple.controller;

import com.zoe.framework.components.core.common.response.DefaultResult;
import com.zoe.framework.components.core.exception.ZoeBusinessException;
import com.zoe.framework.components.core.exception.ZoeDataRuntimeException;
import com.zoe.framework.components.core.exception.ZoeRuntimeException;
import com.zoe.framework.components.core.exception.core.data.ThrowableData;
import com.zoe.framework.components.example.starters.simple.entity.msyql.Example1;
import com.zoe.framework.components.example.starters.simple.exception.CustomErrorCode;
import com.zoe.framework.components.util.value.data.CollectionUtil;
import com.zoe.framework.components.util.value.data.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 蒋时华
 * @date 2023-11-30 17:52:44
 */
@RestController
@Slf4j
@Validated
public class ExampleExceptionController {

    /**
     * 异常返回值样例
     *
     * @param exception
     * @return
     */
    @GetMapping("/exampleException")
    public List<Example1> exampleException(Integer exception) {
        if (ObjectUtil.isNull(exception)) {
            throw new RuntimeException("参数为空");
        }
        switch (exception) {
            case 1:
                throw new ZoeRuntimeException(DefaultResult.Builder.error());
            case 2:
                throw new ZoeRuntimeException(CustomErrorCode.CUSTOM_ERROR);
            case 3:
                throw new ZoeDataRuntimeException(CustomErrorCode.CUSTOM_ERROR, new ThrowableData<List<Integer>>() {

                    @Override
                    public List<Integer> getData() {
                        return CollectionUtil.newArrayList(1, 2, 3, 4, 5);
                    }
                });
            case 4:
                throw new ZoeBusinessException(CustomErrorCode.CUSTOM_ERROR);
        }
        throw new RuntimeException("未知异常");
    }

}
