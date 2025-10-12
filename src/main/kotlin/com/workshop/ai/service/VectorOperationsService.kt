package com.workshop.ai.service

import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.api.zeros
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.operations.sum
import org.springframework.stereotype.Service
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Service demonstrating Multik usage for mathematical operations on arrays.
 * Multik is a Kotlin library for multidimensional arrays, similar to NumPy.
 */
@Service
class VectorOperationsService {

    /**
     * Calculate cosine similarity between two vectors.
     * 
     * @param vector1 First vector
     * @param vector2 Second vector
     * @return Cosine similarity score (0 to 1)
     */
    fun cosineSimilarity(vector1: FloatArray, vector2: FloatArray): Float {
        require(vector1.size == vector2.size) { 
            "Vectors must have the same dimension" 
        }

        val dotProduct = vector1.zip(vector2).sumOf { (a, b) -> 
            (a * b).toDouble() 
        }.toFloat()

        val magnitude1 = sqrt(vector1.sumOf { it.pow(2).toDouble() }.toFloat())
        val magnitude2 = sqrt(vector2.sumOf { it.pow(2).toDouble() }.toFloat())

        return if (magnitude1 == 0f || magnitude2 == 0f) {
            0f
        } else {
            dotProduct / (magnitude1 * magnitude2)
        }
    }

    /**
     * Create a sample embedding matrix using Multik.
     * This demonstrates creating and manipulating multidimensional arrays.
     * 
     * @param numDocuments Number of documents
     * @param embeddingDim Dimension of embeddings
     * @return 2D array of embeddings
     */
    fun createEmbeddingMatrix(numDocuments: Int, embeddingDim: Int): D2Array<Float> {
        // Create a zero matrix
        val embeddings = mk.zeros<Float>(numDocuments, embeddingDim)
        
        // In a real scenario, you would fill this with actual embeddings
        // from a model like sentence-transformers
        
        return embeddings
    }

    /**
     * Example of creating a simple multidimensional array.
     */
    fun createSampleMatrix(): D2Array<Int> {
        return mk.ndarray(mk[mk[1, 2, 3], mk[4, 5, 6], mk[7, 8, 9]])
    }

    /**
     * Calculate the sum of all elements in a matrix.
     */
    fun sumMatrix(matrix: D2Array<Int>): Int {
        return matrix.sum()
    }
}
