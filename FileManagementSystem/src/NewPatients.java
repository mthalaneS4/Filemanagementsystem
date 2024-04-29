import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NewPatients extends JFrame {
    private JLabel patientID = new JLabel("Patient ID: ");
    private JLabel fName = new JLabel("First Name: ");
    private JLabel lName = new JLabel("Last Name: ");
    private JLabel Email = new JLabel("Email Address: ");
    private JLabel Phone_number = new JLabel("Phone number: ");
    private JLabel MEDH = new JLabel("Medical History");
    private JLabel Addnew = new JLabel("ADD NEW PATIENTS");
    private JTextField idPatient = new JTextField();
    private JTextField firstName = new JTextField();
    private JTextField lastName = new JTextField();
    private JTextField Emailid = new JTextField();
    private JTextField Phone = new JTextField();
    JTextArea textArea = new JTextArea(400, 700);
    private JButton reg = new JButton("REGISTER");
    private JButton homebut = new JButton("Home");
    private Connection con;
    private PreparedStatement pst;

    public NewPatients() {
        setTitle("New Patients"); // Set frame title
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        setSize(width, height);
        setVisible(true);
//set label

        Addnew.setFont(new Font("Arial", Font.BOLD, 25));
        Addnew.setBounds(400, 10, 300, 25);
        Addnew.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\new-user.png"));
//
        patientID.setBounds(300, 80, 150, 25);
        patientID.setFont(new Font(null, Font.PLAIN, 16));
        idPatient.setBounds(550, 80, 150, 25);
//set label
        fName.setBounds(300, 130, 150, 25);
        fName.setFont(new Font(null, Font.PLAIN, 16));
        firstName.setBounds(550, 130, 150, 25);
//set label
        lName.setBounds(300, 180, 150, 25);
        lName.setFont(new Font(null, Font.PLAIN, 16));
        lastName.setBounds(550, 180, 150, 25);

        Email.setBounds(300, 230, 150, 25);
        Email.setFont(new Font(null, Font.PLAIN, 16));
        Emailid.setBounds(550, 230, 150, 25);
        //set phone
        Phone_number.setBounds(300, 280, 150, 25);
        Phone_number.setFont(new Font(null, Font.PLAIN, 16));
        Phone.setBounds(550, 280, 150, 25);
        //set tetxt area
        MEDH.setBounds(300, 330, 150, 25);
        MEDH.setFont(new Font(null, Font.PLAIN, 16));
        textArea.setBounds(500, 330, 200, 100);

        reg.setBounds(600, 450, 120, 30);
        reg.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\online-registration.png"));
        homebut.setBounds(600, 500, 120, 30);
        homebut.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\home.png"));

        add(Addnew);
        add(patientID);
        add(fName);
        add(lName);
        add(idPatient);
        add(firstName);
        add(lastName);
        add(reg);
        add(Emailid);
        add(Email);
        add(Phone_number);
        add(Phone);
        add(MEDH);
        add(textArea);
        add(homebut);


        reg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idPatient.getText();
                String name = firstName.getText();
                String surname = lastName.getText();
                String email = Emailid.getText();
                String phones = Phone.getText();
                String text = textArea.getText();


                if (id.isEmpty() || name.isEmpty() || surname.isEmpty() || phones.isEmpty()) {
                    JOptionPane.showMessageDialog(NewPatients.this, "Please enter all fields");
                    return;
                }
                //vv
                if (!id.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(NewPatients.this, "Invalid ID format. Please enter a 4-digit ID.");
                    return;
                }

                if (!name.matches("^[A-Za-z\\s'-]+$")) {
                    JOptionPane.showMessageDialog(NewPatients.this, "Invalid First Name format.");
                    return;
                }

                if (!surname.matches("^[A-Za-z\\s'-]+$")) {
                    JOptionPane.showMessageDialog(NewPatients.this, "Invalid Last Name format.");
                    return;
                }

                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    JOptionPane.showMessageDialog(NewPatients.this, "Invalid Email format.");
                    return;
                }

                if (!phones.matches("^\\d{10}$")) {
                    JOptionPane.showMessageDialog(NewPatients.this, "Invalid Phone number format (10 digits).");
                    return;
                }
                User user = addUserToDatabase(id, name, surname, email, phones,text);

                if (user != null) {
                    JOptionPane.showMessageDialog(NewPatients.this, "Registration successful for: " + user.name + " " + user.surname);


                } else {
                    JOptionPane.showMessageDialog(NewPatients.this, "Registration Failed");
                }
            }
            private User addUserToDatabase(String id, String name, String surname, String email, String phones,String text) {
                User user = null;
                try {
                    Class.forName("org.postgresql.Driver");
                    con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FMS", "postgres", "Tiny@2000");

                    //check if exist
                    PreparedStatement checkStmt = con.prepareStatement("SELECT id FROM testreg WHERE id = ?");
                    checkStmt.setString(1, id);
                    ResultSet resultSet = checkStmt.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(NewPatients.this, "User with this ID already exists.");
                    } else {
                        pst = con.prepareStatement("INSERT INTO testreg (id, name, surname, email, phones,Med_History) VALUES (?, ?, ?, ?, ?,?)");
                        pst.setString(1, id);
                        pst.setString(2, name);
                        pst.setString(3, surname);
                        pst.setString(4, email);
                        pst.setString(5, phones);
                        pst.setString(6, text);

                        int rowsAffected = pst.executeUpdate();
                        if (rowsAffected > 0) {
                            user = new User();
                            user.id = id;
                            user.name = name;
                            user.surname = surname;
                            user.email = email;
                            user.phones = phones;
                            user.Med_History = text;
                        }

                    }
                }catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (pst != null) {
                            pst.close();
                        }
                        if (con != null) {
                            con.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                return user;
            }
        });
        homebut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminHome homepage = new AdminHome();
                dispose();
            }
        });
    }
}
class User {
    String id;
    String name;
    String surname;
    String email;
    String phones;
    String Med_History;
}
/*
Import Statements:
Imports necessary Java libraries for Swing GUI components, AWT, and database connectivity.
Class Declaration (public class NewPatients extends JFrame):
Defines a class named NewPatients that represents a GUI for registering new patients.
Instance Variables:
Declares instance variables for various GUI components (labels, text fields, buttons), as well as a database connection and prepared statement.
**Constructor (public NewPatients()):
Sets up the GUI window when an object of the NewPatients class is created.
26-29. Frame Configuration:
Configures the frame with a title, layout, and size to fit the screen.
Set Frame Visibility:
Makes the frame (NewPatients) visible on the screen.
33-47. Label Configuration:
Configures and adds labels for "Patient ID," "First Name," "Last Name," "Email Address," "Phone Number," "Medical History," and "ADD NEW PATIENTS."
49-78. Text Field Configuration:
Configures and adds text fields for patient information.
80-84. Text Area Configuration:
Configures and adds a text area for medical history.
86-96. Button Configuration
Configures and adds buttons for "REGISTER" and "Home."
98-108. Action Listener for "REGISTER" Button:
Validates user input for various fields.
Adds the patient's information to the database if it's valid.
109-184. addUserToDatabase() Method:
Attempts to add a new user to the database.
Checks if the user with the same ID already exists.
If not, inserts the new user's information into the database.
186-208. Action Listener for "Home" Button:
Opens the "AdminHom" frame when the "Home" button is clicked.
212-253. User Class:
Defines a simple User class to hold user information (ID, name, surname, email, phone, and medical history).
 */