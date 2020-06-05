package com.eichtec.productservice;

import com.eichtec.productservice.entity.Category;
import com.eichtec.productservice.entity.Product;
import com.eichtec.productservice.repository.ProductRepository;
import com.eichtec.productservice.service.ProductService;
import com.eichtec.productservice.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);

        Product product = Product.builder()
                .id(1L)
                .name("Mockito")
                .description("Mockito Description")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("10"))
                .stock(Double.parseDouble("5"))
                .build();

        Mockito.when(productRepository.findById(1l))
                .thenReturn(Optional.of(product));

        Mockito.when(productRepository.save(product))
                .thenReturn(product);
    }

    @Test
    public void whenValidGetId_thenReturnProduct(){
        Product found = productService.getProduct(1l);
        Assertions.assertThat(found.getName()).isEqualTo("Mockito");
    }
    @Test
    public void whenUpdateStock_thenReturnNewStock(){
        Product product = productService.updateStock(1l, 10.0);
        Assertions.assertThat(product.getStock()).isEqualTo(15);
    }
}
