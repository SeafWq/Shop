package com.example.shop.services;

import com.example.shop.models.Product;
import com.example.shop.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> productList(String title){
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    public void saveProduct(Product product,
                            MultipartFile file1,
                            MultipartFile image2,
                            MultipartFile image3){
        log.info("Savin new: {}", product);
        productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id){
      return productRepository.findById(id).orElse(null);
    }
}
