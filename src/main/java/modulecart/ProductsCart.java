package modulecart;

public class ProductsCart {
    int productId;
    int quantity;


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "ProductsCart{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductsCart() {}

    public ProductsCart(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
