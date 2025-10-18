package com.workshop.ai.service;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import org.springframework.stereotype.Service;

/**
 * Service demonstrating LangChain4j integration for generating text embeddings.
 * Uses the MiniLM-L6-v2 model for creating semantic embeddings.
 * This is where you will plug in an ONNX model for generating different embeddings
 */

@Service
public class EmbeddingService {
    private EmbeddingModel embeddingModel;

    public EmbeddingService() {
        this.embeddingModel = new AllMiniLmL6V2EmbeddingModel();
    }

    public float[] generateEmbedding(String text) {
        return embeddingModel.embed(text).content().vector();
    }
}
