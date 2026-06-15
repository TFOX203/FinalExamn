package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
	    "spring.sql.init.mode=never",
	    "spring.jpa.hibernate.ddl-auto=create-drop",
	    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
	})
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();

        product = new Product();
        product.setName("Huracán EVO");
        product.setDescription("Deportivo V10 de alta gama");
        product.setPrice(220000.0);
        product.setStock(3);
        product.setCategory("Huracán");
    }

    @Test
    void shouldSaveProduct() {
        Product saved = productRepository.save(product);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Huracán EVO");
    }

    @Test
    void shouldFindProductById() {
        Product saved = productRepository.save(product);

        Optional<Product> found = productRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getPrice()).isEqualTo(220000.0);
    }

    @Test
    void shouldFindAllProducts() {
        productRepository.save(product);

        Product otro = new Product();
        otro.setName("Urus S");
        otro.setDescription("SUV de lujo");
        otro.setPrice(280000.0);
        otro.setStock(5);
        otro.setCategory("Urus");
        productRepository.save(otro);

        List<Product> products = productRepository.findAll();

        assertThat(products).hasSize(2);
    }

    @Test
    void shouldDeleteProduct() {
        Product saved = productRepository.save(product);

        productRepository.deleteById(saved.getId());

        Optional<Product> deleted = productRepository.findById(saved.getId());
        assertThat(deleted).isEmpty();
    }

    @Test
    void shouldUpdateProduct() {
        Product saved = productRepository.save(product);

        saved.setPrice(215000.0);
        productRepository.save(saved);

        Optional<Product> updated = productRepository.findById(saved.getId());
        assertThat(updated.get().getPrice()).isEqualTo(215000.0);
    }
}