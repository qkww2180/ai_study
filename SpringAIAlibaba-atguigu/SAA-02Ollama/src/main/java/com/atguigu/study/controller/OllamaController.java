package com.atguigu.study.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @auther zzyybs@126.com
 * @create 2025-07-22 18:56
 */
@RestController
public class OllamaController
{
    /*@Resource(name = "ollamaChatModel")
    private ChatModel chatModel;*/

    //方式2
    @Resource
    @Qualifier("ollamaChatModel")
    private ChatModel chatModel;


    /**auther zzyybs@126.com
     * http://localhost:8002/ollama/chat?msg=你是谁
     * @param msg
     * @return
     */
    @GetMapping("/ollama/chat")
    public String chat(@RequestParam(name = "msg") String msg)
    {
        String result = chatModel.call(msg);
        System.out.println("---结果：" + result);
        return result;
    }

    @GetMapping("/ollama/streamchat")
    public Flux<String> streamchat(@RequestParam(name = "msg",defaultValue = "你是谁") String msg)
    {
        return chatModel.stream(msg);
    }
}








