import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.UserIN;
import model.UserName;
import modelproduct.ProductCategories;
import model.User;
import modelproduct.Product;
import modulecart.Cart;

import modulecart.ProductsCart;
import modulecart.SumUserCart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    private static HttpURLConnection connection;
    private static HttpURLConnection connection2;
    private static HttpURLConnection connection3;

    public static void main(String[] args) {


        BufferedReader reader;
        BufferedReader reader2;
        BufferedReader reader3;
        String line;
        StringBuffer responseContent = new StringBuffer();
        StringBuffer responseContentProducts = new StringBuffer();
        StringBuffer responseContentCarts = new StringBuffer();
        try{
            URL url = new URL("https://fakestoreapi.com/users");
            URL productUrl = new URL("https://fakestoreapi.com/products");
            URL cartUrl = new URL("https://fakestoreapi.com/carts");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            connection2 = (HttpURLConnection) productUrl.openConnection();
            connection2.setRequestMethod("GET");
            connection2.setConnectTimeout(20000);
            connection2.setReadTimeout(20000);

            connection3 = (HttpURLConnection) cartUrl.openConnection();
            connection3.setRequestMethod("GET");
            connection3.setConnectTimeout(20000);
            connection3.setReadTimeout(20000);


            int status = connection.getResponseCode();

            if(status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                reader2 = new BufferedReader(new InputStreamReader(connection2.getErrorStream()));
                reader3 = new BufferedReader(new InputStreamReader(connection3.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                    responseContentProducts.append(reader2.readLine());
                    responseContentCarts.append(reader3.readLine());
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
                reader3 = new BufferedReader(new InputStreamReader(connection3.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                    responseContentProducts.append(reader2.readLine());
                    responseContentCarts.append(reader3.readLine());
                }
                reader.close();
            }

            String data= responseContent.toString();
            data=data.replace("long","longitude");

            System.out.println("***USERS***");
            System.out.println(data);

            System.out.println("***PRODUCTS***");

            String data2= responseContentProducts.toString();
            System.out.println(data2);

            System.out.println("***CARTS***");

            String data3= responseContentCarts.toString();
            System.out.println(data3);



            try {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<User>> typeReference = new TypeReference<>() {
                };
                TypeReference<List<Product>> typeReference1 = new TypeReference<>() {
                };
                TypeReference<List<Cart>> typeReference2 = new TypeReference<>() {
                };

                Map<Integer, UserName> map = new HashMap<>(); // <- by this way I will match the userName by user ID (task 3)
                List<UserIN> userList = new ArrayList<>();
                System.out.println("\n***USERS***");
                List<User> users = mapper.readValue(data, typeReference);
                for (User u : users) {
                    System.out.println("\nAddress: " + u.getAddress().getCity() + " " + u.getAddress().getStreet() + " " + u.getAddress().getNumber() + " " + u.getAddress().getZipcode()
                            + "\nLat: " + u.getAddress().getGeolocation().getLat() + "\nLong: " + u.getAddress().getGeolocation().getLongitude() + "\nId: " + u.getId() + "\nEmail: "
                            + u.getEmail() + "\nUsername: " + u.getUsername() + "\nPassword: "
                            + u.getPassword() + "\nName: " + u.getName().getFirstname() + " " + u.getName().getLastname() + "\nPhone: " + u.getPhone());
                    userList.add(new UserIN(u.getId(), u.getName()));
                    map.put(u.getId(), u.getName());
                }

                List<ProductCategories> categoryList = new ArrayList<>();
                List<Product> productList = new ArrayList<>();
                System.out.println("\n***PRODUCTS***");
                List<Product> products = mapper.readValue(data2, typeReference1);
                for (Product p : products) {
                    System.out.println("\nId: " + p.getId() + "\nTitle: " + p.getTitle() + "\nPrice: " + p.getPrice() + "\nDescription: " + p.getDescription()
                            + "\nCategory: " + p.getCategory() + "\nImage: " + p.getImage() + "\nRating: " + p.getRating().getRate() + " Count: " + p.getRating().getCount());
                    categoryList.add(new ProductCategories(p.getCategory(), p.getId(), p.getPrice()));
                    productList.add(new Product(p.getId(), p.getPrice()));
                }

                List<SumUserCart> cartList = new ArrayList<>();
                System.out.println("\n***CARTS***");
                List<Cart> carts = mapper.readValue(data3, typeReference2);
                for (Cart c : carts) {
                    System.out.println("\nId: " + c.getId() + "\nUser Id: " + c.getUserId() + "\nDate: " + c.getDate() + "\nProducts: " + Arrays.toString(c.getProducts()));
                    cartList.add(new SumUserCart(c.getUserId(), c.getProducts()));
                }


                System.out.println("\n***FUNCTIONALITY***");
                //System.out.println(productList);  \\ its helpful to calculate product value each cart manually to check the result
                //System.out.println("\n" + multiValueMap(categoryList) + "\n");   \\ its helpful to visualize the category list
                System.out.println("\n2. Total value of products of a given category:\n" + sumCategoryValue(categoryList) + "\n");
                System.out.println("Highest value category is: " + getMaxByCategoryName(categoryList) + " <-- Additional functionality" +"\n");

                System.out.println("\n3. Finds a cart with the highest value, determines its value and full name of its owner");
                printTheCart(productList, cartList, map);
                getDistance(users);

                //System.out.println(cartList);

            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void getDistance(List<User> users) {
        double distance = 0;
        String user1 = null;
        String user2 = null;
        for (User u : users) {
            for (User x : users) {
                double lat1 = u.getAddress().getGeolocation().getLat();
                double lon1 = u.getAddress().getGeolocation().getLongitude();
                double lat2 = x.getAddress().getGeolocation().getLat();
                double lon2 = x.getAddress().getGeolocation().getLongitude();
                if ((lat1 != lat2) && (lon1 != lon2)) {
                    double theta = lon1 - lon2;
                    double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
                    dist = Math.acos(dist);
                    dist = Math.toDegrees(dist);
                    dist = dist * 60 * 1.1515;
                    dist = dist * 1.609344;

                    if(distance < dist) {
                        distance = dist;
                        user1 = u.getName().getFirstname() + " " + u.getName().getLastname();
                        user2 = x.getName().getFirstname() + " " + x.getName().getLastname();
                    }
                }
            }
        }
        System.out.println("\n4. The following users live furthest from each other: " + user1 + " and " + user2 + " \nThey are separated by " + String.format("%.2f",distance) + "km");
    }




    private static Map<String, List<ProductCategories>> multiValueMap(List<ProductCategories> categoryList) {
        return categoryList.stream()
                .collect(Collectors.groupingBy(ProductCategories::getCategoryName));
    }

    private static Map<String, Double> sumCategoryValue(List<ProductCategories> categoryList) {
        return categoryList.stream()
                .collect(Collectors.groupingBy(ProductCategories::getCategoryName,
                        Collectors.summingDouble(ProductCategories::getPrice)));
    }

    private static Map.Entry<String, Double> getMaxByCategoryName(List<ProductCategories> categoryList) {
        return categoryList.stream()
                .collect(Collectors.toMap(ProductCategories::getCategoryName,
                        ProductCategories::getPrice,
                        Double::sum))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(Map.entry("no data evalable", 0d));
    }

    private static void printTheCart(List<Product> productList, List<SumUserCart> cartList, Map<Integer, UserName> map){
        double maxCartSum = 0;
        UserName user = new UserName();
        int id = 0;
        for (SumUserCart cartsByUser : cartList) {
            ProductsCart[] pcx = cartsByUser.getProducts();

            double cartSum = 0;
            for (ProductsCart pc : pcx) {
                int quantity = pc.getQuantity();
                double price = productList.get(pc.getProductId()-1).getPrice(); // <- -1 is correction, it was starting from 1
                cartSum += price * quantity;
            }
            if(maxCartSum < cartSum){
                maxCartSum = cartSum;
                id = cartsByUser.getUserId();
                user = map.get(id);
            }


        }

        System.out.println(" The cart with the highest value belongs to: " + user.getFirstname() + " " + user.getLastname() + " the value of the cart is: " + maxCartSum);

    }

}
