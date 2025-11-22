import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoppingScreen extends JFrame {

    public ShoppingScreen() {
        setTitle("EEB Shopping");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // place frame to center on the screen

        String[] columnNames = {"Product Name", "Color", "Category", "Stock", "Weight", "Description"};
        
        // Create model and set non-editable
        DefaultTableModel model;
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Acces file and read products
        FileManager<Product> productManager = new FileManager<>("products.txt", new Product("", "", "", 0, 0, ""));
        List<Product> products  = productManager.loadFromFile();
        
        // Add each product to table
        for (Product p : products) {
            Object[] rowData = {
                p.getProductName(),
                p.getProductColor(),
                p.getProductCategory(),
                p.getStockQuantity(),
                p.getProductWeight(),
                p.getProductDescription()
            };
            model.addRow(rowData);
        }
        
        // Table ve Scroll Pane ve oluştur
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Create buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // buttons are centered with flow layout
        
        // Buttonları oluştur
        JButton addFavoriteButton = new JButton("Add Favorite");
        JButton removeFavoriteButton = new JButton("Remove Favorite");
        JButton orderButton = new JButton("Order Button");
        
        // Action Listener for addFavoriteButton
        addFavoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    
                    Product selectedProduct = new Product(
                model.getValueAt(selectedRow, 0).toString(), // name
                model.getValueAt(selectedRow, 1).toString(), // color
           model.getValueAt(selectedRow, 2).toString(), // category
                Integer.parseInt(model.getValueAt(selectedRow, 3).toString()), // stock
             Double.parseDouble(model.getValueAt(selectedRow, 4).toString()), // weight
        model.getValueAt(selectedRow, 5).toString() // description
                );

                    Session.getCurrentUser().favoriteProduct(selectedProduct);
                    JOptionPane.showMessageDialog(null, "You Favorited this Product: " + selectedProduct.getProductName());
                }
         }});
        
        // Action Listener for removeFavoriteButton
        removeFavoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    
                    Product selectedProduct = new Product(
                model.getValueAt(selectedRow, 0).toString(), // name
                model.getValueAt(selectedRow, 1).toString(), // color
           model.getValueAt(selectedRow, 2).toString(), // category
                Integer.parseInt(model.getValueAt(selectedRow, 3).toString()), // stock
             Double.parseDouble(model.getValueAt(selectedRow, 4).toString()), // weight
        model.getValueAt(selectedRow, 5).toString() // description
                );

                    Session.getCurrentUser().unfavoriteProduct(selectedProduct);
                    JOptionPane.showMessageDialog(null, "You Unfavorited this Product: " + selectedProduct.getProductName());
                }
            }
        });
        
        // ActionListener for orderButton
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    
                    Product selectedProduct = new Product(
                model.getValueAt(selectedRow, 0).toString(), // name
                model.getValueAt(selectedRow, 1).toString(), // color
           model.getValueAt(selectedRow, 2).toString(), // category
                Integer.parseInt(model.getValueAt(selectedRow, 3).toString()), // stock
             Double.parseDouble(model.getValueAt(selectedRow, 4).toString()), // weight
        model.getValueAt(selectedRow, 5).toString() // description
                );
                    
                    String input = JOptionPane.showInputDialog(null, "Enter Quantity: ");
                    
                    if (input != null) {
                        try {
                            int quantity = Integer.parseInt(input);
                            Session.getCurrentUser().orderProduct(selectedProduct, quantity);
                            JOptionPane.showMessageDialog(null, "Succesfully Ordered!");
                            
                            // Update Stock and Model
                             int currentStock = Integer.parseInt(model.getValueAt(selectedRow, 3).toString());
                             int newStock = currentStock - quantity;
                             model.setValueAt(newStock, selectedRow, 3);
                             
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid number entered!");
                        } catch (IllegalArgumentException ex1) {
                            JOptionPane.showMessageDialog(null, "Not Enoug Stock!");
                        }
                    }  
                }
            }
        });
        
        // Add Scroll Pane
        add(scrollPane, BorderLayout.CENTER);
        
        // Add buttons to buttonPanel
        buttonPanel.add(addFavoriteButton);
        buttonPanel.add(removeFavoriteButton);
        buttonPanel.add(orderButton);
        
        // Add buttonPanel to frame
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
        
    }
}