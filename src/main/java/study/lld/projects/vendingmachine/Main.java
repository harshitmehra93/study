package study.lld.projects.vendingmachine;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
Learnings
- Learned state pattern
- time - ~4 hours
- we should use abstract class in state pattern to avoid duplicates "unknown operation"
 */
public class Main {
    public static void main(String[] args) {
        /*

        VendingMachine(lines)
        -> List<Line> lines
        -> currentOrder
        -> State: {Init, Vending, Ready, Order In Progress, Checkout, Error, Out Of Items}
        -> vend()
        -> reset()
        -> addProductToOrder(line_id, quantity)
        -> init()
        -> checkout()
        -> ready()

        Order
        -> id
        -> Bag
        -> paymentMethod

        Bag
        -> Map<line_id, quantity>
        -> totalAmount

        VendingMachineAdminService
        -> setPriceOfLine(line_id, price): void
        -> addProductToLine(line_id, product): void

        VendingMachineCustomerInterface
        -> addProductToOrder(line_id, quantity)
        -> checkout(PaymentMethod)
        -> reset()

        Line
        - id
        - SlotSize {SMALL, MEDIUM, LARGE}
        - price
        - maxSlots
        - size
        - addProductToLast(Product)
        - vendLastProduct(): Product

        Product
        - id
        - SlotSize {SMALL, MEDIUM, LARGE}
        - name

        Design a vending machine.

        -> We will have a machine that have multiple products line and their max slots.
        -> A product line can be small medium large slots, based on size we can keep products on a slot.
        -> A product line can have different products but all of them have to be of same price.
        -> Each product line will have a unique code, with the code a customer can select top product in the line.
        -> Customer will select code and pay for the code they selected.
        -> A customer can have multiple items from different lines selected
        -> Vending machine will have states: Init, Vending, Ready, Order In Progress, Checkout, Error


        System will have
        -> Fixed number of lines with different slot size. Operator can refill the lines with products and set price of line.
        -> VendingMachineService(lines)
            - addProductsInLine(line, Product, quantity)
            - setPriceOfLine(line)
            - addToBag(lineCode, quantity)
            - checkout()

        happy - Ready -> Order In Progress -> Checkout -> Vending -> Ready
         -> VM is Ready
         -> VM has 5 items in line 1
         -> Customer adds 2 items from line 1 in bag.
         -> Customer pushes checkout()
         -> VM moves to Checkout
         -> Payment completes.
         -> VM moves to Vending
         -> All products in the checkout bag are vended.
         -> machine moves to Ready State

         payment fails - Ready -> Order In Progress -> Checkout -> Failed -> Ready
         -> VM is Ready
         -> VM has 5 items in line 1
         -> Customer adds 2 items from line 1 in bag.
         -> Customer pushes checkout()
         -> VM moves to Checkout
         -> Payment fails. Retry payment a couple times.
         -> Payment fails
         -> VM moves to Failed state
         -> Bag is emptied
         -> VM moves to Ready State

         checkout fails - Ready -> Order In Progress -> Failed -> Ready
         -> VM is Ready
         -> VM has 5 items in line 1
         -> Customer tries to add 6 items from line 1 in bag.
         -> items are not available
         -> order timeout occurs
         -> VM moves to Failed state
         -> Bag is emptied
         -> VM moves to Ready State

         vending fails - Ready -> Order In Progress -> Checkout -> Vending -> Failed -> Ready
         -> VM is Ready
         -> VM has 5 items in line 1
         -> Customer adds 2 items from line 1 in bag.
         -> Customer pushes checkout()
         -> VM moves to Checkout
         -> Payment completes.
         -> VM moves to Vending
         -> Vending Fails
         -> Payment is reversed
         -> Bag is emptied
         -> machine moves to Ready State

         init fails - Ready -> Failed
         -> VM does prellime checks of all systems
         -> VM discovers payment system is not working
         -> VM moves to ready Failed state
         */
        OrderManagementService orderManagementService =
                new OrderManagementService(
                        new CardPaymentStrategy(),
                        new UpiPaymentStrategy(),
                        new CashPaymentStrategy());
        VendingMachine vendingMachine = new VendingMachine(orderManagementService);
        Line one = new Line(SlotSize.SMALL, 10, 20);
        VendingMachineConfiguration vendingMachineConfiguration =
                new VendingMachineConfiguration(vendingMachine);
        vendingMachineConfiguration.addLineToVendingMachine(one);
        vendingMachineConfiguration.addProductToLine(
                one.id, new Product(SlotSize.SMALL, "Cheetos"), 10);
        vendingMachineConfiguration.readyForOrders();
        VendingMachineCustomerInterface customerInterface =
                new VendingMachineCustomerInterface(vendingMachine);
        customerInterface.addProductToOrder(0, 5);
        customerInterface.checkout(PaymentMethodType.UPI);
        customerInterface.addProductToOrder(0, 5);
        customerInterface.checkout(PaymentMethodType.CARD);

        vendingMachineConfiguration.init();
        vendingMachineConfiguration.addProductToLine(
                one.id, new Product(SlotSize.SMALL, "Mars"), 1);
        vendingMachineConfiguration.addProductToLine(
                one.id, new Product(SlotSize.SMALL, "Sneakers"), 1);
        vendingMachineConfiguration.addProductToLine(
                one.id, new Product(SlotSize.SMALL, "Toblerone"), 1);
        vendingMachineConfiguration.addProductToLine(
                one.id, new Product(SlotSize.SMALL, "Lindt"), 1);
        vendingMachineConfiguration.readyForOrders();
        customerInterface.addProductToOrder(0, 4);
        customerInterface.checkout(PaymentMethodType.CASH);
    }
}

class VendingMachineCustomerInterface {
    private final VendingMachine vendingMachine;

    VendingMachineCustomerInterface(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    void addProductToOrder(int lineId, int quantity) {
        vendingMachine.addProductToOrder(lineId, quantity);
    }

    void checkout(PaymentMethodType paymentMethodType) {
        vendingMachine.checkout(paymentMethodType);
    }

    void reset() {
        vendingMachine.reset();
    }

    //            -> addProductToOrder(line_id, quantity)
    //        -> checkout(PaymentMethod)
    //        -> reset()
}

class VendingMachineConfiguration {
    private final VendingMachine vendingMachine;

    VendingMachineConfiguration(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    void addLineToVendingMachine(Line line) {
        vendingMachine.addLineInMachine(line);
    }

    void addProductToLine(int lineId, Product product, int quantity) {
        vendingMachine.addProductToLine(lineId, product, quantity);
    }

    void readyForOrders() {
        vendingMachine.ready();
    }

    public void init() {
        vendingMachine.init();
    }
}

class VendingMachine {
    List<Line> lines;
    Order currentOrder;
    VendingMachineState state;
    Instant transitionTime;
    final OrderManagementService orderManagementService;
    VendingMachineState initState;
    VendingMachineState emptyState;
    VendingMachineState readyState;
    VendingMachineState orderInProgressState;
    VendingMachineState checkoutState;
    VendingMachineState vendingState;

    public VendingMachine(OrderManagementService orderManagementService) {
        lines = new ArrayList<>();
        currentOrder = null;
        transitionTime = Instant.now();
        this.orderManagementService = orderManagementService;
        initState = new InitState(this);
        emptyState = new EmptyState(this);
        readyState = new ReadyState(this);
        orderInProgressState = new OrderInProgressState(this);
        checkoutState = new CheckoutState(this);
        vendingState = new VendingState(this);
        state = initState;
    }

    void vend() {
        state.vend();
    }

    void reset() {
        state.reset();
    }

    void addProductToOrder(int lineId, int quantity) {
        state.addProductToOrder(lineId, quantity);
    }

    void init() {
        state.init();
    }

    void checkout(PaymentMethodType paymentMethodType) {
        state.checkout(paymentMethodType);
    }

    void ready() {
        state.reset();
    }

    void addLineInMachine(Line line) {
        state.addLineInMachine(line);
    }

    void addProductToLine(int lineId, Product product, int quantity) {
        state.addProductToLine(lineId, product, quantity);
    }

    void setState(VendingMachineState state) {
        if (this.state == state) {
            System.out.println("machine already in the target state");
            return;
        }
        System.out.println("moving to state " + state);
        this.state = state;
        this.transitionTime = Instant.now();
    }

    boolean isEmpty() {
        for (Line line : lines) {
            if (line.getSize() > 0) {
                return false;
            }
        }
        return true;
    }

    Line getLine(int lineId) {
        Optional<Line> actualLine = lines.stream().filter(l -> l.id == lineId).findFirst();
        if (actualLine.isEmpty()) throw new RuntimeException("line does not Exist");
        return actualLine.get();
    }

    public void completeOrder(PaymentMethodType paymentMethodType) {
        currentOrder.paymentMethodType = paymentMethodType;
        orderManagementService.completeOrder(currentOrder);
    }

    public void refundOrderIfPaymentCompleted() {
        orderManagementService.refundOrder(currentOrder);
    }
}

abstract class VendingMachineState {
    void vend() {
        System.out.println("invalid operation");
    }

    void reset() {
        System.out.println("invalid operation");
    }

    void addProductToOrder(int lineId, int quantity) {
        System.out.println("invalid operation");
    }

    void init() {
        System.out.println("invalid operation");
    }

    void checkout(PaymentMethodType paymentMethodType) {
        System.out.println("invalid operation");
    }

    void addLineInMachine(Line line) {
        System.out.println("invalid operation");
    }

    void addProductToLine(int id, Product product, int quantity) {
        System.out.println("invalid operation");
    }
}

class VendingState extends VendingMachineState {

    private final VendingMachine vendingMachine;

    VendingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void vend() {
        try {
            if (vendingMachine.currentOrder.paymentCompleted) {
                System.out.println("Vending items");
                vendingMachine
                        .currentOrder
                        .bag
                        .lineItemAndQuantity
                        .entrySet()
                        .forEach(this::vendAllProducts);
                vendingMachine.setState(vendingMachine.readyState);
                vendingMachine.state.reset();
            }
        } catch (Exception e) {
            System.out.println("Vending failed");
            vendingMachine.refundOrderIfPaymentCompleted();
            vendingMachine.reset();
        }
    }

    private void vendAllProducts(Map.Entry<Line, Integer> entry) {
        Line line = entry.getKey();
        int quantity = entry.getValue();
        Line actualLine = vendingMachine.getLine(line.id);
        for (int i = 0; i < quantity; i++) {
            actualLine.vendProduct();
        }
    }

    @Override
    public void reset() {
        vendingMachine.setState(vendingMachine.readyState);
        vendingMachine.state.reset();
    }
}

class CheckoutState extends VendingMachineState {

    private final VendingMachine vendingMachine;

    CheckoutState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void vend() {
        if (vendingMachine.currentOrder.paymentCompleted) {
            vendingMachine.setState(vendingMachine.vendingState);
            vendingMachine.state.vend();
        } else {
            System.out.println("order payment is not yet completed");
        }
    }

    @Override
    public void reset() {
        vendingMachine.setState(vendingMachine.readyState);
        vendingMachine.reset();
    }

    @Override
    public void addProductToOrder(int lineId, int quantity) {
        vendingMachine.setState(vendingMachine.orderInProgressState);
        vendingMachine.addProductToOrder(lineId, quantity);
    }

    @Override
    public void checkout(PaymentMethodType paymentMethodType) {
        try {
            vendingMachine.completeOrder(paymentMethodType);
            if (vendingMachine.currentOrder.paymentCompleted) {
                vendingMachine.setState(vendingMachine.vendingState);
                vendingMachine.vend();
            }
        } catch (Exception e) {
            System.out.println("Checkout failed");
            vendingMachine.refundOrderIfPaymentCompleted();
            vendingMachine.reset();
        }
    }
}

class OrderInProgressState extends VendingMachineState {

    private final VendingMachine vendingMachine;

    OrderInProgressState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void reset() {
        vendingMachine.setState(vendingMachine.readyState);
        vendingMachine.reset();
    }

    @Override
    public void addProductToOrder(int lineId, int quantity) {
        Line actualLine = vendingMachine.getLine(lineId);
        int quantityOfTheSameLineAlreadyAddedToOrder =
                vendingMachine.currentOrder.bag.getQuantity(actualLine);
        int availableQuantity = actualLine.getSize() - quantityOfTheSameLineAlreadyAddedToOrder;
        if (availableQuantity < quantity) {
            throw new RuntimeException("quantity is greater than available products of the line");
        }
        vendingMachine.currentOrder.bag.addToBag(actualLine, quantity);
    }

    @Override
    public void checkout(PaymentMethodType paymentMethodType) {
        if (vendingMachine.currentOrder.bag.lineItemAndQuantity.isEmpty()) {
            System.out.println("cannot checkout with empty bag");
        } else {
            vendingMachine.setState(vendingMachine.checkoutState);
            vendingMachine.checkout(paymentMethodType);
        }
    }
}

class ReadyState extends VendingMachineState {

    private final VendingMachine vendingMachine;

    ReadyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void reset() {
        System.out.println("Resetting");
        vendingMachine.currentOrder = null;
        if (vendingMachine.isEmpty()) {
            vendingMachine.setState(vendingMachine.emptyState);
        } else {
            vendingMachine.setState(vendingMachine.readyState);
        }
    }

    @Override
    public void addProductToOrder(int lineId, int quantity) {
        vendingMachine.currentOrder = new Order();
        vendingMachine.setState(vendingMachine.orderInProgressState);
        vendingMachine.addProductToOrder(lineId, quantity);
    }

    @Override
    public void init() {
        System.out.println("Changing to init state");
        vendingMachine.setState(vendingMachine.initState);
        vendingMachine.init();
    }

    @Override
    public void addProductToLine(int id, Product product, int quantity) {
        System.out.println("invalid operation");
    }
}

class EmptyState extends VendingMachineState {

    private final VendingMachine vendingMachine;

    EmptyState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void reset() {
        vendingMachine.currentOrder = null;
        if (vendingMachine.isEmpty()) {
            vendingMachine.setState(vendingMachine.emptyState);
        } else {
            vendingMachine.setState(vendingMachine.readyState);
        }
    }

    @Override
    public void init() {
        vendingMachine.setState(vendingMachine.initState);
    }
}

class InitState extends VendingMachineState {

    private final VendingMachine vendingMachine;

    InitState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void reset() {
        vendingMachine.currentOrder = null;
        if (vendingMachine.isEmpty()) {
            vendingMachine.setState(vendingMachine.emptyState);
        } else {
            vendingMachine.setState(vendingMachine.readyState);
        }
    }

    @Override
    public void addLineInMachine(Line line) {
        vendingMachine.lines.add(line);
        System.out.println("Added line in machine");
    }

    @Override
    public void addProductToLine(int id, Product product, int quantity) {
        Line line = vendingMachine.getLine(id);
        line.addProduct(product, quantity);
        System.out.println("Added " + quantity + " " + product.name + " in line " + line.id);
    }
}

class Line {
    Stack<Product> products;
    private static AtomicInteger counter = new AtomicInteger(0);
    int id;
    SlotSize slotSize;
    int price;
    int maxQuantity;

    public Line(SlotSize slotSize, int maxQuantity, int price) {
        id = counter.getAndIncrement();
        this.maxQuantity = maxQuantity;
        this.slotSize = slotSize;
        this.price = price;
        products = new Stack<>();
    }

    void addProduct(Product product, int quantity) {
        if (quantity + getSize() > maxQuantity)
            throw new RuntimeException("insufficient slots in line");
        if (!canFit(product, slotSize)) throw new RuntimeException("product cant fit in slot");
        for (int i = 0; i < quantity; i++) {
            products.push(product);
        }
    }

    private boolean canFit(Product product, SlotSize slotSize) {
        SlotSize productSize = product.slotSize;
        if (slotSize == SlotSize.SMALL) {
            if (productSize == SlotSize.SMALL) return true;
            return false;
        } else if (slotSize == SlotSize.MEDIUM) {
            if (productSize == SlotSize.SMALL || productSize == SlotSize.MEDIUM) return true;
            return false;
        } else {
            return true;
        }
    }

    Product vendProduct() {
        System.out.println("Vending Product " + products.peek());
        return products.pop();
    }

    public int getSize() {
        return products.size();
    }
}

class Product {
    static final AtomicInteger counter = new AtomicInteger(0);
    final int id;
    final SlotSize slotSize;
    final String name;

    public Product(SlotSize slotSize, String name) {
        this.id = counter.getAndIncrement();
        this.slotSize = slotSize;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", slotSize=" + slotSize + ", name='" + name + '\'' + '}';
    }
}

enum SlotSize {
    SMALL,
    MEDIUM,
    LARGE
}

class Order {
    static final AtomicInteger counter = new AtomicInteger(0);

    public Order() {
        id = counter.getAndIncrement();
        orderStarted = Instant.now();
        bag = new Bag();
    }

    int id;
    Bag bag;
    PaymentMethodType paymentMethodType;
    Instant orderStarted;
    Instant paymentCompletedTime;
    boolean paymentCompleted = false;

    public void completePayment() {
        System.out.println("completeing payment with " + paymentMethodType);
        paymentCompleted = true;
        paymentCompletedTime = Instant.now();
    }
}

class Bag {
    Map<Line, Integer> lineItemAndQuantity;
    int totalAmount;

    public Bag() {
        lineItemAndQuantity = new HashMap<>();
        totalAmount = 0;
    }

    boolean isEmpty() {
        if (lineItemAndQuantity.isEmpty()) return true;
        for (Map.Entry<Line, Integer> entry : lineItemAndQuantity.entrySet()) {
            int quantity = entry.getValue();
            if (quantity > 0) return false;
        }
        return true;
    }

    void addToBag(Line line, int quantity) {
        lineItemAndQuantity.put(line, lineItemAndQuantity.getOrDefault(line, 0) + quantity);
        totalAmount += line.price * quantity;
    }

    void calculateTotal() {
        if (isEmpty()) {
            totalAmount = 0;
        } else {
            totalAmount = 0;
            for (Map.Entry<Line, Integer> lineItem : lineItemAndQuantity.entrySet()) {
                Line line = lineItem.getKey();
                int quantity = lineItem.getValue();
                totalAmount += line.price * quantity;
            }
        }
    }

    int getQuantity(Line line) {
        Optional<Line> optionalLine =
                lineItemAndQuantity.entrySet().stream()
                        .filter(e -> e.getKey().id == line.id)
                        .map(e -> e.getKey())
                        .findFirst();
        if (optionalLine.isEmpty()) return 0;
        return lineItemAndQuantity.get(optionalLine.get());
    }
}

enum PaymentMethodType {
    UPI,
    CASH,
    CARD
}

class OrderManagementService {
    private final CardPaymentStrategy cardPaymentStrategy;
    private final UpiPaymentStrategy upiPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    OrderManagementService(
            CardPaymentStrategy cardPaymentStrategy,
            UpiPaymentStrategy upiPaymentStrategy,
            CashPaymentStrategy cashPaymentStrategy) {
        this.cardPaymentStrategy = cardPaymentStrategy;
        this.upiPaymentStrategy = upiPaymentStrategy;
        this.cashPaymentStrategy = cashPaymentStrategy;
    }

    void completeOrder(Order order) {
        Objects.requireNonNull(order.paymentMethodType, "payment method");
        Objects.requireNonNull(order.bag, "bag");
        Objects.requireNonNull(order.id, "order id");

        // calculate bag total
        order.bag.calculateTotal();

        // complete payment
        if (order.paymentCompleted) {
            System.out.println("Payment already completed");
        }
        switch (order.paymentMethodType) {
            case CARD -> cardPaymentStrategy.pay(order);
            case UPI -> upiPaymentStrategy.pay(order);
            case CASH -> cashPaymentStrategy.pay(order);
        }
        order.paymentCompleted = true;
        order.paymentCompletedTime = Instant.now();
    }

    public void refundOrder(Order order) {
        Objects.requireNonNull(order.paymentMethodType, "payment method");
        Objects.requireNonNull(order.bag, "bag");
        Objects.requireNonNull(order.id, "order id");

        if (!order.paymentCompleted) {
            System.out.println("no refund needed");
        }
        switch (order.paymentMethodType) {
            case CARD -> cardPaymentStrategy.refund(order);
            case UPI -> upiPaymentStrategy.refund(order);
            case CASH -> cashPaymentStrategy.refund(order);
        }
    }
}

interface PaymentStrategy {
    void pay(Order order);

    void refund(Order order);
}

class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(Order order) {
        System.out.println("CARD payment of " + order.bag.totalAmount + " completed");
    }

    @Override
    public void refund(Order order) {
        System.out.println("CARD refund of " + order.bag.totalAmount + " completed");
    }
}

class UpiPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(Order order) {
        System.out.println("UPI payment of " + order.bag.totalAmount + " completed");
    }

    @Override
    public void refund(Order order) {
        System.out.println("UPI refund of " + order.bag.totalAmount + " completed");
    }
}

class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(Order order) {
        System.out.println("CASH payment of " + order.bag.totalAmount + " completed");
    }

    @Override
    public void refund(Order order) {
        System.out.println("CASH refund of " + order.bag.totalAmount + " completed");
    }
}
