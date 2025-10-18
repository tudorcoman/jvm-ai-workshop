package com.workshop.ai.service;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LLMRecommendationsAssistant {
    private static final String SYSTEM_PROMPT = "You are a helpful assistant reviewing user recommendations. " +
            "Keep in mind that the database of items is very small so even a similar category means a good recommendation. " +
            "You will receive a list of events that a user has made (name, description of the product and event type) " +
            "and a ranking of the recommendations, in the form of product names and descriptions. " +
            "Provide feedback on the quality of the recommendations based on the user's events " +
            "and explain why they are ranked in that specific order.";


    private final OpenAiChatModel openAIService;

    public LLMRecommendationsAssistant(@Value("${llm.endpoint}") String llmEndpoint,
                                       @Value("${llm.apiKey}") String llmApiKey,
                                       @Value("${llm.model}") String llmModel) {

        this.openAIService = OpenAiChatModel.builder()
                .apiKey(llmApiKey)
                .baseUrl(llmEndpoint)
                .modelName(llmModel)
                .build();
    }

    public String generateResponse(String prompt) {
        final SystemMessage systemMessage = new SystemMessage(SYSTEM_PROMPT);
        final ChatResponse response = openAIService.chat(systemMessage, new UserMessage(prompt));
        return response.aiMessage().text();
    }
}
