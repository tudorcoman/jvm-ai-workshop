package com.workshop.ai.service

import dev.langchain4j.data.embedding.Embedding
import dev.langchain4j.model.embedding.EmbeddingModel
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel
import org.springframework.stereotype.Service

/**
 * Service demonstrating LangChain4j integration for generating text embeddings.
 * Uses the MiniLM-L6-v2 model for creating semantic embeddings.
 */
@Service
class EmbeddingService {

    private val embeddingModel: EmbeddingModel = AllMiniLmL6V2EmbeddingModel()

    /**
     * Generate embeddings for a given text.
     * 
     * @param text The input text to embed
     * @return Float array representing the embedding vector
     */
    fun embedText(text: String): FloatArray {
        val embedding: Embedding = embeddingModel.embed(text).content()
        return embedding.vector()
    }

    /**
     * Generate embeddings for multiple texts in a batch.
     * 
     * @param texts List of texts to embed
     * @return List of embedding vectors
     */
    fun embedTexts(texts: List<String>): List<FloatArray> {
        return texts.map { text ->
            embedText(text)
        }
    }

    /**
     * Get the dimension of the embedding vectors produced by this model.
     * MiniLM-L6-v2 produces 384-dimensional embeddings.
     */
    fun getEmbeddingDimension(): Int {
        return 384
    }
}
