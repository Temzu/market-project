package com.temzu.market_project.msproduct.services;

<<<<<<< HEAD
import com.temzu.market_project.mscore.exceptions.PageDeterminationException;
import com.temzu.market_project.mscore.exceptions.ProductNotFoundException;
=======
>>>>>>> dev-spliting-project-into-microservices
import com.temzu.market_project.msproduct.model.dtos.ProductDto;
import com.temzu.market_project.msproduct.model.entities.Product;
import com.temzu.market_project.msproduct.model.mappers.ProductMapper;
import com.temzu.market_project.msproduct.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper;
    private final ProductRepository productRepository;

    @Override
    public Page<ProductDto> getAllProducts(Specification<Product> spec, int page, int pageSize) {
        Page<ProductDto> products = productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(mapper::toProductDto);
        int totalPages = products.getTotalPages();
        if (totalPages < page) {
            throw new PageDeterminationException(
                    String.format("Page not found: page %d, size %d, total pages %d", page, pageSize, totalPages));
        }
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductDto getProductDtoById(Long id) {
        return mapper.toProductDto(productRepository
                .findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " not found")));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto saveOrUpdate(ProductDto productDto) {
        Product product = mapper.toProduct(productDto);
        return mapper.toProductDto(productRepository.save(product));
    }
}
