package com.bookstore.product.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.bookstore.product.domain.Description;
import com.bookstore.product.domain.Name;
import com.bookstore.product.domain.Price;
import com.bookstore.product.domain.Product;
import com.bookstore.product.domain.ProductStatus;
import com.bookstore.product.domain.Stock;
import com.bookstore.product.dto.ProductRequest;
import com.bookstore.product.exception.ProductNotFoundException;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("상품을 데이터베이스에 등록한다.")
    public void register() {
        //given
        ProductRequest productRequest = new ProductRequest("더 나은 코드를 작성하는 간단하고 실질적인 테크닉",
            "읽기 좋은 코드가 좋은코드다", 1_000L, ProductStatus.SELL.name(),
            "https://image.aladin.co.kr/product/1610/82/cover500/897914914x_1.jpg", 18_000L);
        Product saveProduct = productRepository.save(productRequest.toProduct());
        em.clear();

        //when
        Product findProduct = productRepository.findById(saveProduct.getId())
            .orElseThrow(ProductNotFoundException::new);

        //then
        assertThat(findProduct).isEqualTo(saveProduct);
    }

    @Test
    @DisplayName("상품을 데이터베이스에서 수정한다.")
    public void update() {
        //given
        ProductRequest productRequest = new ProductRequest("더 나은 코드를 작성하는 간단하고 실질적인 테크닉",
            "읽기 좋은 코드가 좋은코드다", 1_000L, ProductStatus.SELL.name(),
            "https://image.aladin.co.kr/product/1610/82/cover500/897914914x_1.jpg", 18_000L);
        Product product = productRepository.save(productRequest.toProduct());
        em.clear();

        //when
        ProductRequest updateRequest = new ProductRequest(productRequest.getDescription(),
            productRequest.getName(), 0L, ProductStatus.OUT_OF_STOCK.name(),
            productRequest.getThumbnail(), productRequest.getPrice());
        product.update(
            new Description(updateRequest.getDescription()),
            new Name(updateRequest.getName()),
            new Stock(updateRequest.getStock()),
            ProductStatus.valueOf(updateRequest.getProductStatus()),
            updateRequest.getThumbnail(),
            new Price(updateRequest.getPrice())
        );
        Product updateProduct = productRepository.save(product);
        em.clear();

        //then
        assertThat(updateProduct.getId()).isEqualTo(product.getId());
    }

    @Test
    @DisplayName("상품을 데이터베이스에서 삭제한다.")
    public void delete() {
        //given
        ProductRequest productRequest = new ProductRequest("더 나은 코드를 작성하는 간단하고 실질적인 테크닉",
            "읽기 좋은 코드가 좋은코드다", 1_000L, ProductStatus.SELL.name(),
            "https://image.aladin.co.kr/product/1610/82/cover500/897914914x_1.jpg", 18_000L);
        Product product = productRepository.save(productRequest.toProduct());
        em.clear();

        //when
        productRepository.delete(product);

        //then
        assertThatThrownBy(() -> productRepository.findById(product.getId()).orElseThrow(ProductNotFoundException::new))
            .isInstanceOf(ProductNotFoundException.class);
    }
}