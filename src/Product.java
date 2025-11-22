
public class Product  implements FileSerializable<Product>{
    
    // Data Field
    private String productName;
    private String productColor;
    private String productCategory;
    private int stockQuantity;
    private double productWeight;
    private String productDescription;
    
    // Constructor
    public Product(String productName, String productColor, String productCategory, int stockQuantity, double productWeight, String productDescription) {
        this.productName = productName;
        this.productColor = productColor;
        this.productCategory = productCategory;
        this.stockQuantity = stockQuantity;
        this.productWeight = productWeight;
        this.productDescription = productDescription;
    }
    
    // Serialize Override
    @Override
    public String serialize() {
        return productName + "," + productColor + "," + productCategory + "," +
           stockQuantity + "," + productWeight + "," + productDescription;
    }
    
    // Deserialize Override
    @Override
    public Product deserialize(String line) {
        String[] parts = line.split(",", -1);
        String name = parts[0];
        String color = parts[1];
        String category = parts[2];
        int stock = Integer.parseInt(parts[3]);
        double weight = Double.parseDouble(parts[4]);
        String description = parts[5];
        return new Product(name, color, category, stock, weight, description);
    }
    
    // Method that reduces stock by given quantity
    public void reduceStock(int orderQuantity) { 
        this.stockQuantity = this.stockQuantity - orderQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(double productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    
    
}
