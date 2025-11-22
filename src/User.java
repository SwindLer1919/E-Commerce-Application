
import java.util.ArrayList;


public class User implements FileSerializable<User>{
    
    // Data Field
    private String username;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String password;
    private String email;
    private String homeAdress;
    private String workAdress;
    private ArrayList<Product> productOrdered;
    private ArrayList<Product> favoriteProducts;
    private ArrayList<CreditCard> creditCardObjects; 

    public User(String username, String name, String surname, String dateOfBirth, String password, String email, String homeAdress, String workAdress) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
        this.homeAdress = homeAdress;
        this.workAdress = workAdress;
        this.productOrdered = new ArrayList<>();
        this.favoriteProducts = new ArrayList<>();
        this.creditCardObjects = new ArrayList<>();
    }
    
    // Override Constructor for Register Screen
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "-";
        this.surname = "-";
        this.dateOfBirth = "-";
        this.email = "-";
        this.homeAdress = "-";
        this.workAdress = "-";
    }
    
    // Override Constructor
    public User() {
        
    }
    
    // Method that controls order process 
    public void orderProduct(Product p, int orderQuantity) {
        if(p.getStockQuantity() < orderQuantity) {
            throw new IllegalArgumentException("Not enough stock!");
        }
        else{
            this.productOrdered.add(p);
            p.reduceStock(orderQuantity);
        }
    }
    
    // Methods that favorites and unfavorites products
    public void favoriteProduct(Product p) {
        this.favoriteProducts.add(p);
    }
    
    public void unfavoriteProduct(Product p) {
        this.favoriteProducts.remove(p);
    }
    
    // Serialize Override
    @Override
    public User deserialize(String line) {
        String[] parts = line.split(",", -1);
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
    }
    
    // Deserialize Override
    @Override
    public String serialize() {
        return username + "," + name + "," + surname + "," + dateOfBirth + "," + password + "," + email + "," + homeAdress + "," + workAdress;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public void setHomeAdress(String homeAdress) {
        this.homeAdress = homeAdress;
    }

    public String getWorkAdress() {
        return workAdress;
    }

    public void setWorkAdress(String workAdress) {
        this.workAdress = workAdress;
    }

}
