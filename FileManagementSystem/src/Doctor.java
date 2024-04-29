import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.*;

public class Doctor extends JFrame {
    JFrame frame2 = new JFrame("Doctors");
    JLabel Doctors = new JLabel("Doctors Login");
    JLabel username = new JLabel("Username:");
    JTextField txtName = new JTextField();
    JLabel password = new JLabel("Password: ");
    JPasswordField txtPass = new JPasswordField();
    private Connection con;
    private PreparedStatement pst;
    JButton Login = new JButton("Login");

    public Doctor() {
        // Set frame layout to null
        frame2.setLayout(null);
        // Set bounds for components
        Doctors.setBounds(500, 100, 400, 40);
        Doctors.setFont(new Font(null, Font.PLAIN, 25));
        Doctors.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\medical-team.png"));
        username.setBounds(450, 200, 150, 25);
        username.setFont(new Font(null, Font.PLAIN, 25));
        txtName.setBounds(600, 200, 150, 25);
        password.setBounds(450, 250, 150, 25);
        password.setFont(new Font(null, Font.PLAIN, 25));
        txtPass.setBounds(600, 250, 150, 25);
        Login.setBounds(600, 300, 100, 50);

        // Adding components to the frame
        frame2.add(Doctors);
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

        // Database connection setup
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
                        pst = con.prepareStatement("SELECT * FROM doctors WHERE username = ? AND password = ?");
                        pst.setString(1, name);
                        pst.setString(2, pass);

                        ResultSet rs = pst.executeQuery();
                        if (rs.next()) {
                            frame2.dispose();
                            new HomepgDoc();
                            setVisible(true);
                            setVisible(false);
                            dispose();
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
Line 1-4: Import Statements
These lines import necessary libraries and classes, such as Swing components, AWT, ActionEvent, ActionListener, and database-related classes.
Line 6-18: Class and Instance Variable Declaration
Line 6 declares a class named Doctor that extends JFrame.
Line 7-17 declare various instance variables, including a JFrame (frame2), labels (Doctors, username, password), text fields (txtName, txtPass), a database connection (con), a prepared statement (pst), and a login button (Login).
Line 20-36: Constructor (Doctor())
Line 20 starts the constructor.
Line 22 sets the layout of frame2 to null, which allows components to be explicitly positioned.
Line 24-31 configure the positions, fonts, and icons for labels (Doctors, username, password) and text fields (txtName, txtPass).
Line 33-34 sets the bounds for the login button (Login).
Line 37 adds the components to frame2.
Line 39-46: Frame Configuration
Line 39 sets the default close operation for frame2 to exit the application when the window is closed.
Line 40-43 calculate the width and height of the frame to occupy the entire screen, except for 50 pixels from the bottom.
Line 44 sets the size of frame2.
Line 45 makes frame2 visible.
Line 47-58: Database Connection Setup
Line 47 starts a try-catch block to handle exceptions related to database connection setup.
Line 49-52 load the PostgreSQL database driver and establish a connection to a database named "FMS" on localhost using specific credentials ("postgres" and "Tiny@2000").
Line 53-57 catch any exceptions related to database connection and print stack traces if an exception occurs.
Line 59-76: Login Button Action Listener
Line 59-60 create an action listener for the "Login" button (Login) using an anonymous inner class.
Line 62-63 retrieve the entered username and password from the text fields.
Line 65-70 check if both the username and password are provided. If not, it displays a message using JOptionPane.
Line 72-75, if both fields are filled, it prepares and executes a SQL query to check if the provided credentials match a record in the database.
Line 73-74 sets the values for the prepared statement based on the entered username and password.
Line 76 checks if the query result contains a match. If a match is found, it disposes of the current frame2, creates a new HomepgDoc frame, makes it visible, and disposes of the current Doctor frame. If there's no match, it displays an error message using JOptionPane.
 */