import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterScreen extends JFrame{
    
    private List<User> userArray = new ArrayList<>();

    public List<User> getUserArray() {
        return userArray;
    }

    // Constructor
    public RegisterScreen() {
        
        // Frame Settings
        setTitle("E-Commerce Application / Register"); // frame title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the entire application
        setSize(400, 250); // set frame size
        setResizable(false); // changeable or unchengeable frame size
        setLocationRelativeTo(null);// place frame to center on the screen
        //setLocationRelativeTo(null); // place frame to center on the 
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,30,20,30)); // for space
        
        // Usurname Panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel userLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15); // number of column
        usernameField.setMaximumSize(usernameField.getPreferredSize());
        userPanel.add(userLabel, BorderLayout.WEST);
        userPanel.add(usernameField, BorderLayout.CENTER);
        
        // Password Panel
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(passwordField.getPreferredSize());
        passPanel.add(passLabel, BorderLayout.WEST);
        passPanel.add(passwordField, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        // Action Listener for registerButton
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Get datas from fields
                String newUsername = usernameField.getText();
                String newPassword = new String(passwordField.getPassword());
                
                // Creating instance
                User newUser = new User(newUsername, newPassword); 
                
                // Process that read file for save
                FileManager<User> userManager = new FileManager("users.txt", new User());
                List<User> users = userManager.loadFromFile();
                
                // Check username exist
                boolean exists = users.stream().anyMatch(u -> u.getUsername().equals(newUsername));
                
                if (exists) {
                    JOptionPane.showMessageDialog(null, "This username is already taken!");
                }
                else {
                    users.add(newUser); // add user to our user array
                    userManager.saveToFile(users); // save user to file
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    dispose();
                    EntryScreen entryScreen = new EntryScreen();
                }
            }
        });
        
        // Action Listener for backButton
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Change frame RegisterScreen --> EntryScreen
                dispose();
                EntryScreen entryScreen = new EntryScreen();
            }
        });
        
        // Add Panel
        mainPanel.add(userPanel);
        mainPanel.add(passPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
        add(mainPanel); // ana paneli pencereye ekle
        
        setVisible(true); // show screen
    }
}
