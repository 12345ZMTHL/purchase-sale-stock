package com.jiang.config.interceptor;

import com.jiang.config.interceptor.AnalysisaAnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 12345芝麻糖葫芦
 * @ClassName: AnalysisAnotationInterceptor
 * @Description: TODO
 * @date 2020/6/20 15:27
 */
@Slf4j
public class AnalysisAnotationInterceptor implements HandlerInterceptor {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /* boolean assignableFrom = handler.getClass().isAssignableFrom(HandlerMethod.class);
        log.info("是否进行数据处理-》"+assignableFrom);
        if (assignableFrom){*/
            AnalysisaAnotation analysisaAnotation = ((HandlerMethod) handler).getMethodAnnotation(AnalysisaAnotation.class);
            if (null != analysisaAnotation && analysisaAnotation.flagAnalysis()){
                Map<String, String[]> parameterMap = request.getParameterMap();
                int eventId = analysisaAnotation.eventId();
                boolean  flagAnalysis= analysisaAnotation.flagAnalysis();
                log.info("是否进行数据处理:"+flagAnalysis);
                log.info("事件ID："+eventId);
                redisTemplate.convertAndSend("CLICK","事件ID："+eventId);
                //redisTemplate.opsForValue().set("jiang","jiang");
                return true;
            }
            log.info("不需要进行数据处理");
//        }
        return true;

    }
}
