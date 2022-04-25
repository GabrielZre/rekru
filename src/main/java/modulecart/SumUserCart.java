package modulecart;

import java.util.Arrays;


public class SumUserCart {
    int userId;
    ProductsCart[] products;

    public SumUserCart(int userId, ProductsCart[] products) {
        this.userId = userId;
        this.products = products;
    }

    @Override
    public String toString() {
        return "SumUserCart{" +
                "userId=" + userId +
                ", products=" + Arrays.toString(products) +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ProductsCart[] getProducts() {
        return products;
    }


    public void setProducts(ProductsCart[] products) {
        this.products = products;
    }

}

