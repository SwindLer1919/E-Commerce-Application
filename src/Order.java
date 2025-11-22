// Constructor 
// getter setter??

public class Order {
    
    // Data Field
    // data types will change
    private User orderingUser;
    private Product orderedProduct;
    private CreditCard creditCard;
    private int orderQuantity;
  
   // method that can access "product--> stock control method" "user-->product ordering"

    public Order(User orderingUser, Product orderedProduct, CreditCard creditCard, int orderingQuantity) {
        this.orderingUser = orderingUser;
        this.orderedProduct = orderedProduct;
        this.creditCard = creditCard;
        this.orderQuantity = orderQuantity;
    }
    
    public void processOrder() {
        if (orderedProduct.getStockQuantity() >= orderQuantity) {
            if (orderedProduct.getStockQuantity() >= orderQuantity) {
                orderingUser.orderProduct(orderedProduct, orderQuantity);
            }
        }
    }

    public User getOrderingUser() {
        return orderingUser;
    }

    public void setOrderingUser(User orderingUser) {
        this.orderingUser = orderingUser;
    }

    public Product getOrderedProduct() {
        return orderedProduct;
    }

    public void setOrderedProduct(Product orderedProduct) {
        this.orderedProduct = orderedProduct;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
    
}
