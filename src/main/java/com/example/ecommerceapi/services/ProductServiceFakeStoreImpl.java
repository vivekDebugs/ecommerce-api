package com.example.ecommerceapi.services;

import com.example.ecommerceapi.dtos.FakeStoreProductRequestDTO;
import com.example.ecommerceapi.dtos.FakeStoreProductResponseDTO;
import com.example.ecommerceapi.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("productServiceFakeStoreImpl")
public class ProductServiceFakeStoreImpl implements ProductService {
    private final String FAKESTORE_API_ENDPOINT = "https://fakestoreapi.com/products";

    private RestTemplate restTemplate;

    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product getProduct(long productId) {
        String GET_PRODUCT_ENDPOINT = FAKESTORE_API_ENDPOINT + "/" + productId;
        FakeStoreProductResponseDTO fakeStoreProductDTO = this.restTemplate.getForObject(GET_PRODUCT_ENDPOINT, FakeStoreProductResponseDTO.class);
        return fakeStoreProductDTO.toProduct();
    };

    public List<Product> getAllProducts() {
        FakeStoreProductResponseDTO[] listOfFakeStoreProductDTO = this.restTemplate.getForObject(FAKESTORE_API_ENDPOINT, FakeStoreProductResponseDTO[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductResponseDTO fakeStoreProductDTO : listOfFakeStoreProductDTO) {
            products.add(fakeStoreProductDTO.toProduct());
        }
        return products;
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return List.of();
    }

    public Product createProduct(Product product) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = FakeStoreProductRequestDTO.fromProduct(product);
        FakeStoreProductResponseDTO fakeStoreProductDTO = this.restTemplate.postForObject(FAKESTORE_API_ENDPOINT, fakeStoreProductRequestDTO, FakeStoreProductResponseDTO.class);
        return fakeStoreProductDTO.toProduct();
    };

    public Product updateProduct(long productId, Product product) {
        String UPDATE_PRODUCT_ENDPOINT = FAKESTORE_API_ENDPOINT + "/" + productId;
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = FakeStoreProductRequestDTO.fromProduct(product);
        HttpEntity<FakeStoreProductRequestDTO> fakeStoreProductUpdateRequestEntity = new HttpEntity<>(fakeStoreProductRequestDTO);
        ResponseEntity<FakeStoreProductResponseDTO> fakeStoreProductUpdateResponseEntity = this.restTemplate.exchange(UPDATE_PRODUCT_ENDPOINT, HttpMethod.PUT, fakeStoreProductUpdateRequestEntity, FakeStoreProductResponseDTO.class);
        return fakeStoreProductUpdateResponseEntity.getBody().toProduct();
    };

    public Product deleteProduct(long productId) {
        String DELETE_PRODUCT_ENDPOINT = FAKESTORE_API_ENDPOINT + "/" + productId;
        ResponseEntity<FakeStoreProductResponseDTO> fakeStoreProductDeleteResponseEntity = this.restTemplate.exchange(DELETE_PRODUCT_ENDPOINT, HttpMethod.DELETE, null, FakeStoreProductResponseDTO.class);
        return fakeStoreProductDeleteResponseEntity.getBody().toProduct();
    };
}
