package study.lld.designpatterns.lldbootcampweek2;

import lombok.AllArgsConstructor;

// USES BUILDER, STRATEGY, AND FACTORY
public class Checkout {
    PaymentStategy paymentStategy;
    DiscountStrategy discountStrategy;

    void setPaymentStategy(PaymentStategy paymentStategy) {
        this.paymentStategy = paymentStategy;
    }

    void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    void checkout() {
        paymentStategy.pay(10);
        discountStrategy.applyDiscount();
    }

    @Override
    public String toString() {
        return "Checkout{\n"
                + "\n\tpaymentStategy="
                + paymentStategy
                + "\n\tdiscountStrategy="
                + discountStrategy
                + "\n}";
    }

    public static void main(String[] args) {
        Checkout checkout = new Checkout();

        PaymentStategy pay = PaymentFactory.createInstance(PaymentType.UPI);
        DiscountStrategy dis = DiscountFactory.createInstance(DiscountType.FLAT_OFF);
        checkout.setDiscountStrategy(dis);
        checkout.setPaymentStategy(pay);
        checkout.checkout();

        PaymentStategy pay2 = PaymentFactory.createInstance(PaymentType.CARD);
        DiscountStrategy dis2 = DiscountFactory.createInstance(DiscountType.PERCENT_OFF);
        checkout.setDiscountStrategy(dis2);
        checkout.setPaymentStategy(pay2);
        checkout.checkout();
    }
}

enum PaymentType {
    UPI,
    CARD;
}

class PaymentFactory {
    public static PaymentStategy createInstance(PaymentType type) {
        return switch (type) {
            case UPI -> new UpiPayment("upi");
            case CARD -> new CardPayment("card");
        };
    }
}

class DiscountFactory {
    public static DiscountStrategy createInstance(DiscountType type) {
        return switch (type) {
            case PERCENT_OFF -> new PercentOff("percent off");
            case FLAT_OFF -> new FlatDiscount("flatOff");
        };
    }
}

// BUILDER WAY
// public class Checkout {
//    List<Item> items;
//    PaymentStategy paymentStategy;
//    DiscountStrategy discountStrategy;
//
//    private Checkout(List<Item> items, PaymentStategy paymentStategy, DiscountStrategy
// discountStrategy) {
//        this.paymentStategy=paymentStategy;
//        this.discountStrategy=discountStrategy;
//        this.items=items;
//    }
//
//    static Builder builder(){
//        return new Builder();
//    }
//
//    @Override
//    public String toString() {
//        return "Checkout{\n" +
//                "\titems=" + printItems() +
//                "\n\tpaymentStategy=" + paymentStategy +
//                "\n\tdiscountStrategy=" + discountStrategy +
//                "\n}";
//    }
//
//    private String printItems() {
//        StringBuilder sb = new StringBuilder();
//        for (Item item : items){
//            sb.append("\n\t\t"+item);
//        }
//        return sb.toString();
//    }
//
//    static class Builder{
//        List<Item> items;
//        PaymentStategy paymentStategy;
//        DiscountStrategy discountStrategy;
//
//        public Builder(){
//            items = new ArrayList<>();
//        }
//
//        public Builder setPaymentStategy(PaymentStategy paymentStategy) {
//            this.paymentStategy = paymentStategy;
//            return this;
//        }
//
//        public Builder setDiscountStrategy(DiscountStrategy discountStrategy) {
//            this.discountStrategy = discountStrategy;
//            return this;
//        }
//        public Builder addItem(Item item){
//            items.add(item);
//            return this;
//        }
//
//        Checkout build(){
//            return new Checkout(items, paymentStategy, discountStrategy);
//        }
//    }
//
//    public static void main(String[] args) {
//        PaymentStategy pay = new PaymentStategy("pay1");
//        DiscountStrategy dis = new DiscountStrategy("dis1");
//        Checkout checkout = Checkout.builder()
//                .setDiscountStrategy(dis)
//                .setPaymentStategy(pay)
//                .addItem(new Item("item1"))
//                .addItem(new Item("item2"))
//                .build();
//        System.out.println(checkout);
//
//    }
// }

interface PaymentStategy {
    void pay(double amount);
}

@AllArgsConstructor
class UpiPayment implements PaymentStategy {
    String name;

    @Override
    public String toString() {
        return "UpiPayment{" + "name='" + name + '\'' + '}';
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying with UPI = " + amount);
    }
}

@AllArgsConstructor
class CardPayment implements PaymentStategy {

    String name;

    @Override
    public String toString() {
        return "CardPayment{" + "name='" + name + '\'' + '}';
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying with Card = " + amount);
    }
}

enum DiscountType {
    FLAT_OFF,
    PERCENT_OFF;
}

interface DiscountStrategy {
    void applyDiscount();
}

@AllArgsConstructor
class FlatDiscount implements DiscountStrategy {
    String name;

    @Override
    public String toString() {
        return "FlatDiscount{" + "name='" + name + '\'' + '}';
    }

    @Override
    public void applyDiscount() {
        System.out.println("Applying FlatOff Discount");
    }
}

@AllArgsConstructor
class PercentOff implements DiscountStrategy {
    String name;

    @Override
    public String toString() {
        return "PercentOff{" + "name='" + name + '\'' + '}';
    }

    @Override
    public void applyDiscount() {
        System.out.println("Applying PercentOff Discount");
    }
}

@AllArgsConstructor
class Item {
    String name;

    @Override
    public String toString() {
        return "Item{" + "name='" + name + '\'' + '}';
    }
}
