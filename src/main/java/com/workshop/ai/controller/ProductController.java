package com.workshop.ai.controller;

import com.workshop.ai.dto.ProductDTO;
import com.workshop.ai.service.ProductService;
import com.workshop.ai.service.RecommendationsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private RecommendationsService recommendationsService;

    public ProductController(ProductService productService, RecommendationsService recommendationsService) {
        this.productService = productService;
        this.recommendationsService = recommendationsService;
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody @Validated ProductDTO product) {
        return productService.saveProduct(product);
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/recommendations/{userId}")
    public List<ProductDTO> getRecommendations(@PathVariable String userId) {
        return recommendationsService.getPersonalizedRecommendations(UUID.fromString(userId));
    }

    @GetMapping("/recommendations/{userId}/llm")
    public String getLLMRecommendations(@PathVariable String userId) {
        return recommendationsService.getPersonalizedRecommendationsExplanation(UUID.fromString(userId));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
