package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Huracán EVO");
        product.setDescription("Deportivo V10 de alta gama");
        product.setPrice(220000.0);
        product.setStock(3);
        product.setCategory("Huracán");
    }

    @Test
    void getProduct_deberiaRetornarProductoCuandoExiste() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        assertEquals("Huracán EVO", result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void getProduct_deberiaLanzarExcepcionCuandoNoExiste() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProduct(99L));
        verify(productRepository).findById(99L);
    }

    @Test
    void getAllProducts_deberiaRetornarListaDeProductos() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Huracán EVO", result.get(0).getName());
        verify(productRepository).findAll();
    }

    @Test
    void saveProduct_deberiaGuardarYRetornarProducto() {
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertNotNull(result);
        assertEquals("Huracán EVO", result.getName());
        verify(productRepository).save(product);
    }

    @Test
    void deleteProduct_deberiaLlamarDeleteById() {
        productService.deleteProduct(1L);

        verify(productRepository).deleteById(1L);
    }

    @Test
    void updateProduct_deberiaActualizarCampos() {
        Product updated = new Product();
        updated.setName("Huracán STO");
        updated.setDescription("Versión pista");
        updated.setPrice(330000.0);
        updated.setStock(1);
        updated.setCategory("Huracán");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.updateProduct(1L, updated);

        verify(productRepository).findById(1L);
        verify(productRepository).save(product);
        assertEquals("Huracán STO", product.getName());
        assertEquals(330000.0, product.getPrice());
    }

    @Test
    void filterProducts_deberiaFiltrarPorCategoria() {
        Product urus = new Product();
        urus.setId(2L);
        urus.setName("Urus S");
        urus.setPrice(280000.0);
        urus.setStock(5);
        urus.setCategory("Urus");

        when(productRepository.findAll()).thenReturn(List.of(product, urus));

        List<Product> result = productService.filterProducts(
            "Huracán", null, null, null, false, "name", "asc"
        );

        assertEquals(1, result.size());
        assertEquals("Huracán EVO", result.get(0).getName());
    }

    @Test
    void filterProducts_deberiaFiltrarPorPrecioMinimo() {
        Product urus = new Product();
        urus.setId(2L);
        urus.setName("Urus S");
        urus.setPrice(280000.0);
        urus.setStock(5);
        urus.setCategory("Urus");

        when(productRepository.findAll()).thenReturn(List.of(product, urus));

        List<Product> result = productService.filterProducts(
            null, 250000.0, null, null, false, "price", "asc"
        );

        assertEquals(1, result.size());
        assertEquals("Urus S", result.get(0).getName());
    }

    @Test
    void filterProducts_soloEnStock_deberiaExcluirSinStock() {
        Product sinStock = new Product();
        sinStock.setId(3L);
        sinStock.setName("Revuelto");
        sinStock.setPrice(500000.0);
        sinStock.setStock(0); // sin stock
        sinStock.setCategory("Revuelto");

        when(productRepository.findAll()).thenReturn(List.of(product, sinStock));

        List<Product> result = productService.filterProducts(
            null, null, null, null, true, "name", "asc"
        );

        assertEquals(1, result.size());
        assertEquals("Huracán EVO", result.get(0).getName());
    }
}