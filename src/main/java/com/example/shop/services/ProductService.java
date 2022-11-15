package com.example.shop.services;

import com.example.shop.models.Image;
import com.example.shop.models.Product;
import com.example.shop.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
                            MultipartFile file2,
                            MultipartFile file3) throws IOException {

        Image image1;
        Image image2;
        Image image3;

        if (file1.getSize() != 0){
            image1 = toImageEntity(file1);
        }
        log.info("Savin new: {}", product);
        productRepository.save(product);
    }

    private Image toImageEntity(MultipartFile file1) throws IOException {
        Image image = new Image();
        image.setName(file1.getName());
        image.setOriginalFileName(file1.getOriginalFilename());
        image.setContentType(file1.getContentType());
        image.setSize(image.getSize());
        image.setBytes(image.getBytes());
        return image;
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id){
      return productRepository.findById(id).orElse(null);
    }
}
