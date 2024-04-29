import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.*;

public class Admin extends JFrame {
    JFrame frame2 = new JFrame("Admin");
    JLabel ADminLogin = new JLabel("Admin Login");
    JLabel username = new JLabel("Username:");
    JTextField txtName = new JTextField();
    JLabel password = new JLabel("Password: ");
    JPasswordField txtPass = new JPasswordField();
    private Connection con;
    private PreparedStatement pst;
    JButton Login = new JButton("Login");

    public Admin() {
        // Set frame layout to null
        frame2.setLayout(null);

        ADminLogin.setBounds(500, 100, 400, 40);
        ADminLogin.setFont(new Font(null, Font.PLAIN, 25));
        ADminLogin.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\administration.png"));
        username.setBounds(450, 200, 100, 25);
        txtName.setBounds(600, 200, 150, 25);
        password.setBounds(450, 250, 100, 25);
        txtPass.setBounds(600, 250, 150, 25);
        Login.setBounds(600, 300, 100, 50);

        frame2.add(ADminLogin);
        frame2.add(username);
        frame2.add(txtName);
        frame2.add(password);
        frame2.add(txtPass);
        frame2.add(Login);

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        frame2.setSize(width, height);
        frame2.setVisible(true);

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FMS", "postgres", "Tiny@2000");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String pass = new String(txtPass.getPassword());

                if (name.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Both Username and Password are required.");
                } else {
                    try {
                        pst = con.prepareStatement("SELECT * FROM mytablee WHERE username = ? AND password = ?");
                        pst.setString(1, name);
                        pst.setString(2, pass);

                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            frame2.dispose();
                            AdminHome homepage = new AdminHome();
                            homepage.setVisible(true);
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect User");
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}
/*
Import Statements:
Imports necessary libraries such as Swing for GUI components, AWT for window-related functionality, and JDBC for database interaction.
Class Declaration:
Defines a class named Admin that extends JFrame, which represents a GUI window.
Instance Variables:
Several instance variables are defined within the Admin class, such as a JFrame, labels (ADminLogin, username, password), text fields (txtName, txtPass), and a Connection object (con).
Constructor (Admin()):
The constructor sets up and configures the GUI components for the admin login screen.
It sets the layout of the frame2 (a JFrame) to null, allowing explicit positioning of components.
It configures the positions, fonts, and icons for labels (ADminLogin, username, password) and text fields (txtName, txtPass).
It adds these components to the frame2.
It configures the frame by setting the default close operation, sizing it to fit the screen, and making it visible.
Inside a try-catch block, it establishes a database connection using JDBC to a PostgreSQL database.
It sets up an action listener for the "Login" button, which is executed when the button is clicked.
It retrieves the entered username and password from the text fields.
It checks if both the username and password are provided, and if not, it displays a message using JOptionPane.
If both fields are filled, it prepares and executes a SQL query to check if the provided credentials match a record in the database.
If a match is found, it disposes of the current admin login frame and opens a new AdminHome frame, making it visible. If there is no match, it displays an error message using JOptionPane.
 */