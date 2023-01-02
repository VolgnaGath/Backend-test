package ProductMarketTests;

import ProductMarket.Services.ProductService;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PutProductTest {
    Faker faker = new Faker();
    Product product;
    String title = faker.food().ingredient();
    int id;
    static ProductService productService;
    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice(1000);}

    @SneakyThrows
    @Test
    void putProductTest() {
        Response<Product> findId = productService.createProduct(product).execute();
        id = findId.body().getId();
        product.setTitle(title);
        Response<Product> response = productService.modifyProduct(product.withId(id)).execute();
        assertThat(response.body().getTitle(), equalTo(title));
    }
}
