package com.workshop.ai.service;

import com.workshop.ai.dto.ProductDTO;
import com.workshop.ai.entity.Product;
import com.workshop.ai.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private EmbeddingService embeddingService;

    public ProductService(ProductRepository productRepository, EmbeddingService embeddingService) {
        this.productRepository = productRepository;
        this.embeddingService = embeddingService;
    }

    // TODO: build the entity and save it here
    public ProductDTO saveProduct(ProductDTO product) {
        final Product entity = null;

        final Product savedProduct = productRepository.save(entity);
        return mapToDTO(savedProduct);
    }

    protected Product findProductById(String id) {
        return productRepository.findById(UUID.fromString(id)).orElse(null);
    }

    protected List<ProductDTO> getRecommendedProducts(UUID userId, float[] embedding) {
        return productRepository.findRecommendedProducts(userId, embedding)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public ProductDTO getProductById(String id) {
        final Product product = findProductById(id);
        return product != null ? mapToDTO(product) : null;
    }

    protected float[] getProductEmbedding(String id) {
        final Product product = findProductById(id);
        return product != null ? product.getEmbedding() : null;
    }

    public List<ProductDTO> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(UUID.fromString(id));
    }

    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
            product.getId().toString(),
            product.getName(),
            product.getDescription()
        );
    }
}
