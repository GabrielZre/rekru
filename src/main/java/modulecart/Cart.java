package modulecart;

import java.util.Date;

public class Cart {
    int id;
    int userId;
    Date date;
    ProductsCart[] products;
    String __v;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductsCart[] getProducts() {
        return products;
    }

    public void setProducts(ProductsCart[] products) {
        this.products = products;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
