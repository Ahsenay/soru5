import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Musteri olustur
        Customer customer = new Customer();
        customer.id = 1L;
        customer.name = "John Doe";
        customer.email = "john.doe@example.com";

        // Urun olustur
        Product product = new Product();
        product.id = 1L;
        product.name = "Laptop";
        product.description = "Powerful laptop with high performance";
        product.price = new BigDecimal("1500.00");
        product.stock = 100;

        // Sepet olustur
        Cart cart = new Cart();
        cart.id = 1L;
        cart.customer_id = customer.id;
        cart.total_price = new BigDecimal("0.00");

        // Sepete urun ekle
        CartItem cartItem = new CartItem();
        cartItem.id = 1L;
        cartItem.cart_id = cart.id;
        cartItem.product_id = product.id;
        cartItem.quantity = 1;
        cartItem.price = product.price;

        // Sepetin toplam fiyatini guncelle
        cart.total_price = cart.total_price.add(cartItem.price.multiply(BigDecimal.valueOf(cartItem.quantity)));

        // Siparis olustur
        Order order = new Order();
        order.id = 1L;
        order.customer_id = customer.id;
        order.cart_id = cart.id;
        order.total_price = cart.total_price;
        order.order_date = LocalDateTime.now();

        // Siparisi isle
        System.out.println("Order placed successfully!");
        System.out.println("Order ID: " + order.id);
        System.out.println("Customer ID: " + order.customer_id);
        System.out.println("Cart ID: " + order.cart_id);
        System.out.println("Total Price: $" + order.total_price);
        System.out.println("Order Date: " + order.order_date);

        // Urun fiyatini guncelle ve fiyat degisimini kaydet
        BigDecimal newPrice = new BigDecimal("1600.00");
        product.price = newPrice;

        // Urun fiyat degisimini kaydet
        ProductPriceHistory priceHistory = new ProductPriceHistory();
        priceHistory.product_id = product.id;
        priceHistory.price = newPrice;
        priceHistory.date = LocalDateTime.now();

        // Urun fiyatini guncelleme
        System.out.println("\nProduct price updated successfully!");
        System.out.println("New Price for Product '" + product.name + "': $" + product.price);
        System.out.println("Price change history recorded at: " + priceHistory.date);
    }
}

class ProductPriceHistory {
    public Long id;
    public Long product_id;
    public BigDecimal price;
    public LocalDateTime date;
}

class BaseEntity {
    public Long id;
}

class Customer extends BaseEntity {
    public String name;
    public String email;
}

class Product extends BaseEntity {
    public String name;
    public String description;
    public BigDecimal price;
    public Integer stock;
}

class Cart extends BaseEntity {
    public Long id;
    public Long customer_id;
    public BigDecimal total_price;
}

class CartItem extends BaseEntity {
    public Long id;
    public Long cart_id;
    public Long product_id;
    public Integer quantity;
    public BigDecimal price;
}

class Order extends BaseEntity {
    public Long id;
    public Long customer_id;
    public Long cart_id;
    public BigDecimal total_price;
    public LocalDateTime order_date;
}