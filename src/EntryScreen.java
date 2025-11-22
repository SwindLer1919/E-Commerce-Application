import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class EntryScreen extends JFrame{ 
    
    public EntryScreen() {
        
        // Frame Settings
        setTitle("E-Commerce Application /  Entry"); // frame title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the entire application
        setSize(400, 250); // set frame size
        setResizable(false); // changeable or unchengeable frame size
        setLocationRelativeTo(null); // place frame to center on the screen
        
        // Main Panel (vertical location)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,30,20,30)); // for space
        
        // Username Panel
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel userLabel = new JLabel("Username: ");
        JTextField usernameField = new JTextField(15); // number of column
        usernameField.setMaximumSize(usernameField.getPreferredSize());
        userPanel.add(userLabel, BorderLayout.WEST);
        userPanel.add(usernameField, BorderLayout.CENTER);
        
        // Password Panel
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setMaximumSize(passwordField.getPreferredSize());
        passPanel.add(passLabel, BorderLayout.WEST);
        passPanel.add(passwordField, BorderLayout.CENTER);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        // Action Listener for loginButton
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Get datas from fields
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Process that read file for save
                FileManager<User> userManager = new FileManager<>("users.txt", new User());
                List<User> users = userManager.loadFromFile();
                
                // Check if a matching user exists
                User matchedUser = null;
                for (User user : users) {
                    if (user.getUsername().equals(username) &&
                        user.getPassword().equals(password)) {
                        matchedUser = user;
                        Session.user = matchedUser;
                        break; // when you find match break loop
                    }
                }
                
                if (matchedUser != null) {
                    // Change frame --> EntryScreen to ShoppingScreen
                    dispose();
                    ShoppingScreen sc = new ShoppingScreen();   
                }
                else {
                    JOptionPane.showMessageDialog(null, "Wrong username or password!");
                }
            }
        });
        
        // ActionListener for registerButton
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change frame --> EntryScreen to RegisterScreen
                setVisible(false);
                RegisterScreen rs = new RegisterScreen();
            }
        });
        
        // Add lower panels to main panel
        mainPanel.add(userPanel);
        mainPanel.add(passPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);
        
        add(mainPanel); // Add main panel to frame
        
        setVisible(true);
    }

}
