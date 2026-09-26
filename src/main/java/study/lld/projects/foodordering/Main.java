package study.lld.projects.foodordering;

import java.time.Instant;
import java.util.Map;

/*
- time: 1h10m
- mostly decent attempt
 */
public class Main {
    public static void main(String[] args) {
        /*
        Food ordering system design

        user chooses items from menu, adds to cart, then the order is placed,
            payment methods are prompted, and user pays.
        The cart hold the items and when order is placed then we finalise payment and change the status of the order to PAYMENT COMPLETE.
        Once we add items in the cart, then we have an option to apply discount to order. If its a cash discount then
            we can have a discount field updated once, but if ita a percentage discount then we need to update discount at every addToCArt, or removeFromCart
            for simplification we can calculate discount only upon checkout

        Then the ORDER is sent to deliveryService which manages the delivery.

        FoodOrderingService{
            - OrderService
            - DeliveryService
            - startOrder(menuId, userId): Order
            - applyDiscount(Discount,Order): void
            - checkout(Order)
            - addToCart(Order, FoodItemId, quantity)
        }
        OrderService{
            - paymentService
            - discountService
            - startOrder(menuId, userId): Order
            - checkout(Order)
            - addToCart(Order, FoodItemId, quantity)
        }
        DeliveryService{
            - submitOrderForDelivery(Order)
            - This can submit to a queue where available partners in the area keep polling the queue, and they are assigned the order based on a strategy

        }

        Menu
        - id
        - Map<Integer, FoodItem>

        FoodItem
        - id
        - name
        - price
        - isAvailable

        User
        - id
        - name
        - email
        - phone

        Cart
        - id
        - userId
        - Map<FoodItem, Integer>
        - currentTotal
        - addToCart(FoodItem,Quantity)
        - isEmpty(): boolean

        Discount
        - id
        - calculateDiscount(order): double

        Order
        - id
        - userId
        - cartId
        - paymentMethod
        - finalTotal
        - subTotal
        - discountAmount
        - tax
        - orderCreatedAt
        - orderDeliveredAt
        - status - ADDING_TO_CART, PAYMENT_COMPLETE, PREPARING, ON_THE_WAY, DELIVERED
        -
         */
    }
}

class FoodOrderingService {
    OrderService orderService;
    DeliveryService deliveryService;

    Order startOrder(int menuId, int userId) {
        return orderService.createOrder(menuId, userId);
    }

    void applyDiscount(Discount discount, Order order) {
        orderService.applyDiscount(discount, order);
    }

    void checkout(Order order, PaymentMethod paymentMethod) {
        // These two operations can happen outside a transaction because checkout is transactional
        orderService.checkout(order, paymentMethod);
        deliveryService.submitOrderForDelivery(order);
    }

    void addToCart(Order order, int foodItemId, int quantity) {
        orderService.addToCart(order, foodItemId, quantity);
    }
}

class OrderService {

    TaxService taxService;
    PaymentService paymentService;
    MenuDao menuDao;
    OrderDao orderDao;

    public Order createOrder(int menuId, int userId) {
        return orderDao.create(menuId, userId);
    }

    public void applyDiscount(Discount discount, Order order) {
        // mutation boundary start - use a db transaction or lock on order
        order.setDiscount(discount);
        // mutation boundary end
    }

    public void checkout(Order order, PaymentMethod paymentMethod) {
        if (order.orderStatus != OrderStatus.ADDING_TO_CART)
            throw new RuntimeException("invalid state");
        // mutation boundary start - use a db transaction or lock on order
        order.calulateSubtotal();
        order.calculateDiscount();
        double tax = taxService.calculateTax(order);
        order.setTaxAmount(tax);
        paymentService.pay(order, paymentMethod);
        order.orderPaid();
        orderDao.update(order);
        // mutation boundary end
    }

    public void addToCart(Order order, int foodItemId, int quantity) {
        if (order.orderStatus != OrderStatus.ADDING_TO_CART)
            throw new RuntimeException("invalid state");
        Menu menu = menuDao.get(order.menuId);
        // mutation boundary start - use a db transaction or lock on order
        order.addToCart(menu.foodItems.get(foodItemId), quantity);
        // mutation boundary end
    }
}

class OrderDao {
    Order get(int id) {
        return null;
    }

    void save(Order order) {}

    public Order create(int menuId, int userId) {
        return null;
    }

    public void update(Order order) {}
}

class MenuDao {
    Map<Integer, Menu> menus;

    Menu get(int id) {
        return menus.get(id);
    }
}

class PaymentService {
    void pay(Order order, PaymentMethod paymentMethod) {}
}

class TaxService {
    double calculateTax(Order order) {
        return 0.0;
    }
}

class DeliveryService {

    public void submitOrderForDelivery(Order order) {}
}

interface Discount {
    double calculateDiscount(Order order);
}

class Order {
    int id;
    int userId;
    int menuId;
    PaymentMethod paymentMethod;
    double finalTotal;
    double subtotal;
    Discount discount;
    double discountAmount;
    double tax;
    Instant orderCreationTime;
    Instant orderCompletionTime;
    OrderStatus orderStatus;

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void calculateDiscount() {
        discountAmount = discount.calculateDiscount(this);
    }

    public void calulateSubtotal() {}

    public void setTaxAmount(double tax) {}

    public void addToCart(FoodItem foodItem, int quantity) {}

    public void orderPaid() {
        orderStatus = OrderStatus.PAYMENT_COMPLETE;
    }
}

enum PaymentMethod {
    UPI,
    CASH,
    CARD
}

enum OrderStatus {
    ADDING_TO_CART,
    PAYMENT_COMPLETE,
    PREPARING,
    ON_THE_WAY,
    DELIVERED
}

class Menu {
    int id;
    Map<Integer, FoodItem> foodItems;
}

class FoodItem {
    int id;
}

class User {
    int id;
    String name;
    String email;
    int phone;
}

class Cart {
    int id;
    int userId;
    Map<FoodItem, Integer> lineItems;
    double currentTotal;

    void addToCart(FoodItem item, int quantity) {}

    boolean isEmpty() {
        return false;
    }
}
/*
       Cart
       - id
       - userId
       - Map<FoodItem, Integer>
       - currentTotal
       - addToCart(FoodItem,Quantity)
       - isEmpty(): boolean
*/
