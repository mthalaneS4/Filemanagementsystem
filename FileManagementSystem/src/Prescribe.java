import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Prescribe extends JFrame {
    private JLabel head = new JLabel("Presciption");
    private JLabel doc_Name = new JLabel("Doctor's Name: ");
    private JLabel Patient_id = new JLabel("Patient Id: ");
    private JLabel Medicine = new JLabel("Medicine: ");
    private JLabel Diagnosis = new JLabel("Diagnosis: ");
    private JLabel Dosage = new JLabel("Dosage: ");
    private JLabel pills = new JLabel("Amount of Dosage: ");
    private JTextField Docter_name = new JTextField();
    private JTextField Patient_ID =new JTextField();
    private JTextField Medisic = new JTextField();
    private JTextField DIAGNOSIS = new JTextField();
    private JButton addprescription = new JButton("prescribe");
    private JButton Home = new JButton("Home");

    public Prescribe() {
        setTitle("New Patients"); // Set frame title
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        setSize(width, height);
        setVisible(true);

        head.setFont(new Font("Arial", Font.BOLD, 25));
        head.setBounds(500, 10, 300, 25);
        //doctors name
        doc_Name.setFont(new Font("Arial", Font.BOLD, 16));
        doc_Name.setBounds(400, 80, 300, 25);
        Docter_name.setBounds(550, 80, 150, 25);
        //patient id
        Patient_id.setFont(new Font("Arial", Font.BOLD, 16));
        Patient_id.setBounds(400, 130, 300, 25);
        Patient_ID.setBounds(550, 130, 150, 25);
//medicine
        Medicine.setFont(new Font("Arial", Font.BOLD, 16));
        Medicine.setBounds(400, 180, 300, 25);
        Medisic.setBounds(550, 180, 150, 25);
        //diagnosis
        Diagnosis.setFont(new Font("Arial", Font.BOLD, 16));
        Diagnosis.setBounds(400, 230, 300, 25);
        DIAGNOSIS.setBounds(550, 230, 150, 25);
        //dosage
        Dosage.setFont(new Font("Arial", Font.BOLD, 16));
        Dosage.setBounds(400, 280, 300, 25);
        String[] option1 = {"ones a day","twice a day","3 times a day"};
         JComboBox<String> times = new JComboBox(option1);
        String[] option2 = {"1","2","3","4","5"};
         JComboBox<String> quantity = new JComboBox(option2);
        times.setBounds(550, 280, 150, 25);
        //pilss
        pills.setFont(new Font("Arial", Font.BOLD, 16));
        pills.setBounds(400, 330, 300, 25);
        quantity.setBounds(550, 330, 150, 25);
//nutton
        addprescription.setBounds(550, 380, 150, 25);
        Home.setBounds(750, 380, 150, 25);

        add(head);
        add(doc_Name);
        add(Docter_name);
        add(Patient_id);
        add(Patient_ID);
        add(Medicine);
        add(Medisic);
        add(Diagnosis);
        add(DIAGNOSIS);
        add(Dosage);
        add(times);
        add(quantity);
        add(addprescription);
        add(Home);
        add(pills);
        addprescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String doctorName = Docter_name.getText();
                String patientId = Patient_ID.getText();
                String medicine = Medisic.getText();
                String diagnosis = DIAGNOSIS.getText();
                String dosageTime = (String) times.getSelectedItem();
                String dosageQuantity = (String) quantity.getSelectedItem();
                insertPrescription(doctorName, patientId, medicine, diagnosis, dosageTime, dosageQuantity);
            }
            private void insertPrescription(String doctorName, String patientId, String medicine, String diagnosis, String dosageTime, String dosageQuantity) {
                String url = "jdbc:postgresql://localhost:5432/FMS";
                String user = "postgres";
                String password = "Tiny@2000";

                try {
                    Connection connection = DriverManager.getConnection(url, user, password);

                    String sql = "INSERT INTO prescriptions (doctor_name, patient_id, medicine, diagnosis, dosage_time, dosage_quantity) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, doctorName);
                    statement.setString(2, patientId);
                    statement.setString(3, medicine);
                    statement.setString(4, diagnosis);
                    statement.setString(5, dosageTime);
                    statement.setString(6, dosageQuantity);

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Prescription added successfully.");
                    }
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding prescription: " + e.getMessage());
                }
            }
        });
        Home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomepgDoc homepgDoc = new HomepgDoc();
                dispose();
            }
        });
    }
}
/*
Import Statements:
Imports necessary Java libraries for Swing GUI components, AWT, event handling, and database connectivity.
Class Declaration (public class Prescribe):
Defines a class named Prescribe.
Instance Variables:
Declares instance variables for various GUI components, including labels, text fields, buttons, and combo boxes.
**Constructor (public Prescribe()):
Sets up the GUI window when an object of the Prescribe class is created.
31-47. Frame Configuration:
Configures the frame with a title, layout, size to fit the screen, and makes it visible.
50-100. Creating and Configuring Components:
Initializes and configures GUI components, including labels, text fields, combo boxes, and buttons.
Sets fonts and positions for various components.
102-118. Adding Components to the Frame:
Adds various components to the frame.
120-184. Action Listeners for Buttons:
Defines action listeners for the "prescribe" and "Home" buttons.
addprescription button invokes the insertPrescription method when clicked.
insertPrescription inserts prescription information into a database.
Home button opens a HomepgDoc frame and disposes of the current frame.
186-230. insertPrescription() Method:
Inserts prescription information into a database using JDBC.
Establishes a database connection and prepares an SQL statement for insertion.
Executes the SQL statement and displays a success message or an error message.
 */