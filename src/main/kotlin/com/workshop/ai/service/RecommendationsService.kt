package com.workshop.ai.service

import com.workshop.ai.dto.EventDTO
import com.workshop.ai.dto.ProductDTO
import com.workshop.ai.entity.EventType
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.operations.plus
import org.jetbrains.kotlinx.multik.ndarray.operations.times
import org.jetbrains.kotlinx.multik.ndarray.operations.toFloatArray
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecommendationsService(
    private val productService: ProductService,
    private val eventService: EventService,
    private val llmRecommendationsAssistant: LLMRecommendationsAssistant
) {
    fun getPersonalizedRecommendations(userId: UUID): List<ProductDTO> {
        val events = eventService.getEventsByUserId(userId.toString())

        // TODO: build the centroid using the events list
        var centroid = mk.ndarray(FloatArray(384) { 0f }) // Assuming 384-dimensional embeddings
        return productService.getRecommendedProducts(userId, centroid.toFloatArray())
    }

    fun getPersonalizedRecommendationsExplanation(userId: UUID): String {
        val events = eventService.getEventsByUserId(userId.toString())
        val recommendations = getPersonalizedRecommendations(userId)

        val userEventsString: String = events.stream()
            .map { e -> extractEventInformation(e) }
            .reduce("") { acc: String, event: String -> acc + event + "\n" }

        val recommendationsString = recommendations.subList(0, 3).stream()
            .map { product: ProductDTO -> String.format("%s, %s", product.name, product.description) }
            .reduce("") { acc: String, rec: String -> acc + rec + "\n" }

        val prompt =
            "User Events (product name, product description, event type):\n$userEventsString\nRecommendations: $recommendationsString"

        return llmRecommendationsAssistant.generateResponse(prompt)
    }

    private fun extractEventInformation(eventDTO: EventDTO): String {
        val product = productService.getProductById(eventDTO.productId)
        return String.format("%s, %s, %s", product.name, product.description, eventDTO.eventType)
    }
}