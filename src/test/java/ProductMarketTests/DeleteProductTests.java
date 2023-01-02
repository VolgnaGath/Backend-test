package ProductMarketTests;
import com.github.javafaker.Faker;
import ProductMarket.Services.CategoryService;
import ProductMarket.Services.ProductService;
import ProductMarket.dto.GetCategoryResponse;
import ProductMarket.dto.Product;
import ProductMarket.ultils.RetrofitUtils;
import lombok.SneakyThrows;

import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProductTests {
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

    void deleteProductByIdPositiveTest() {
        Response<Product> findId = productService.createProduct(product).execute();
        id = findId.body().getId();
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
