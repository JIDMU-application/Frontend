package JIDMU.product.service;

import JIDMU.product.dto.ProductDTO;
import JIDMU.product.model.Product;
import JIDMU.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    //   ----> we are mapping DAO → DTO
    public List<ProductDTO> getProduct() {
        List<Product> restaurants = repository.findAll();

        List<ProductDTO> dtos = restaurants
                .stream()
                .map(restaurant -> modelMapper.map(restaurant,
                        ProductDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }

    public void create(ProductDTO productDto) {
        Product product = modelMapper.map(productDto,
                Product.class);
        product.setCreatedAt(Instant.now());
        repository.save(product);
    }
}

