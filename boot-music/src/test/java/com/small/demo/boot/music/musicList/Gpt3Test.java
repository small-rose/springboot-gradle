package com.small.demo.boot.music.musicList;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2023/2/12 9:10
 * @version: v1.0
 */
public class Gpt3Test {

    static String key = "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";
    public static void main(String[] args) {
        String token = Gpt3Test.key ;//System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token, Duration.ofSeconds(55));

        System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("Q: Please answer in Chinese, 今天上海的天气咋样?\\n A:")
                .model("text-davinci-003")
                .temperature(0D)
                .maxTokens(1000)
                .topP(1D)
                .frequencyPenalty(0D)
                .presencePenalty(0D)
                .stop(Arrays.asList("\n"))
                .build();
        List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
        choices.forEach(System.out::println);
        for (CompletionChoice choice : choices) {
            System.out.println(choice.getText());
        }
        System.out.println("请求结束");
    }
}
