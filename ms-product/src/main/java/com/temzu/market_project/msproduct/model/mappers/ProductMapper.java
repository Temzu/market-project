package com.temzu.market_project.msproduct.model.mappers;

<<<<<<< HEAD
import com.temzu.market_project.msproduct.model.dtos.ProductDto;
import com.temzu.market_project.msproduct.model.entities.Product;
=======
import com.temzu.market.model.dtos.ProductDto;
import com.temzu.market.model.entities.Product;
>>>>>>> dev-spliting-project-into-microservices
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private ModelMapper mapper;

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ProductDto toProductDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    public Product toProduct(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }
}
