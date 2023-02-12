package com.small.demo.boot.chatgpt3.web.service;

import com.small.demo.boot.chatgpt3.web.vo.ChatRequestVO;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/2/12 13:16
 * @version: v1.0
 */
@Slf4j
@Service
public class ChatGptService {

    static String key = "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";


    public String  getResponse(ChatRequestVO chatRequestVO) {
        String token = key ;//System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token, Duration.ofSeconds(55));

        if (chatRequestVO.getLengthMsg()<100){
            chatRequestVO.setLengthMsg(100);
        }
        System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Q: "+chatRequestVO.getRequestMsg()+"\\n A:")
                .model("text-davinci-003")
                .temperature(0D)
                .maxTokens(16)
                .topP(1D)
                .frequencyPenalty(0D)
                .presencePenalty(0D)
                .stop(Arrays.asList("\n"))
                .n(chatRequestVO.getLengthMsg())
                .build();
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        choices.forEach(System.out::println);

        String  result = "";
        for (CompletionChoice choice : choices) {
            result += choice.getText();
        }
        System.out.println("请求结束");
        return result ;
    }
}
