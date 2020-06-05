package com.eichtec.productservice.service;

import com.eichtec.productservice.entity.Category;
import com.eichtec.productservice.entity.Product;
import com.eichtec.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {

        return productRepository.findById(id).orElse(null);
    }


    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATE");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product product1DB = getProduct(product.getId());
        if(product1DB == null){
            return null;
        }
        product1DB.setName(product.getName());
        product1DB.setDescription(product.getDescription());
        product1DB.setCategory(product.getCategory());
        product1DB.setPrice(product.getPrice());
        return productRepository.save(product1DB);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = getProduct(id);
        if(productDB ==  null){
            return null;
        }
        productDB.setStatus("DELETE");
        return productRepository.save(productDB);
    }

    @Override
    public List<Product> findByCategory(Category category) {

        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);
        if (productDB == null){
            return null;
        }
        Double newStock = productDB.getStock() + quantity;
        productDB.setStock(newStock);
        return productRepository.save(productDB);
    }
}
