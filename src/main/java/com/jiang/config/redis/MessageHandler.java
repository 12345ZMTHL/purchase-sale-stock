package com.jiang.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 12345芝麻糖葫芦
 * @ClassName: MessageHandler
 * @Description: TODO
 * @date 2020/6/20 18:22
 */
@Component
@Slf4j
public class MessageHandler {

    public void onMessage(String message,String chanel){
      log.info("收到消息："+message);
    }
}
