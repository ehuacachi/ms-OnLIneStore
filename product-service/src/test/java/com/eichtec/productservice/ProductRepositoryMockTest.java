package com.eichtec.productservice;

import com.eichtec.productservice.entity.Category;
import com.eichtec.productservice.entity.Product;
import com.eichtec.productservice.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        Product product = Product.builder()
                .name("TestMock")
                .category(Category.builder().id(1L).build())
                .description("")
                .stock(Double.parseDouble("10"))
                .price(Double.parseDouble("50"))
                .status("CREATE")
                .createAt(new Date())
                .build();
        productRepository.save(product);

        List<Product> products = productRepository.findByCategory(product.getCategory());

        Assertions.assertThat(products.size()).isEqualTo(3);

    }
}
