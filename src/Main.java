import java.util.*;
import javax.swing.SwingUtilities;


public class Main {
    
    public static void main(String[] args) {
        
        // Creating products
        Product product1 = new Product("Laptop", "Black", "Computers", 25, 2, "Powerful Laptop");
        Product product2 = new Product("Telephone", "Lime Green", "Smartphones and Headphones", 120, 0.5, "Small and Useful");
        Product product3 = new Product("Wireless Adaptor", "White", "House Technologies", 200, 0.8, "Better Connection");
        Product product4 = new Product("Camera", "Dark Blue", "Camera", 70, 2, "High Qualitiy Photos");
        Product product5 = new Product("Smartwatch", "Black", "Wearable Teachnologies", 50, 0.2, "Check Your Health");
        Product product6 = new Product("Headphones", "White", "Smartphones and Headphones", 340, 0.2, "Higher Volumes");
        //bu kısımda ürünlerin özelliklerini kaç adet olduklarını ve fiyatlarını diğer classlardaki methodlara göre belirliyoruz
        
        // Add products to list
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        
        // Write to file with FileManager
        FileManager<Product> productManager = new FileManager<>("products.txt", new Product("","","",0,0.0,""));
        productManager.saveToFile(productList); 
        
        // to run our frame
        // run this program on the event dispatch thread (EDT)
        // member reference
        // thread açıklamaları report edilecek
        SwingUtilities.invokeLater(EntryScreen::new);
        
        
    }
}
