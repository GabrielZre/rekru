package modelproduct;

import java.util.*;
import java.util.stream.Collectors;

public class ProductCategories {
        private String categoryName;
        private int productId;
        private double price;

        public ProductCategories(String categoryName, int productId, double price) {
            this.categoryName = categoryName;
            this.productId = productId;
            this.price = price;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public int getProductId() {
            return productId;
        }

        public double getPrice() {
        return price;
    }



        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public void setProductId(double price) {
        this.price = price;
    }


    @Override
        public String toString() {
            return "ProductCategories{" + "Category name='" + categoryName + '\'' + ", productID=" + productId + ", price='" + price + '\'' + '}';
        }

}
