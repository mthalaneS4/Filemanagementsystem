import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewPatient {
    private JTextField idField;
    private JLabel nameLabel;
    private JLabel nameResultLabel;
    private JTextArea medHistoryTextArea;
    private JButton searchButton;
    private JButton Home;

    public ViewPatient() {
        JFrame frame = new JFrame("Database Search and Update App");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        frame.setSize(width, height);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel idLabel = new JLabel("Enter ID:");
        idField = new JTextField(10);
        searchButton = new JButton("Search");
        nameLabel = new JLabel("Name:");
        Home = new JButton("Home");
        nameResultLabel = new JLabel("");
        medHistoryTextArea = new JTextArea(10, 30);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(searchButton);
        panel.add(Home);
        panel.add(nameLabel);
        panel.add(nameResultLabel);
        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(medHistoryTextArea), BorderLayout.CENTER);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAndDisplayData();
            }
        });
        Home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminHome userAccounts = new AdminHome();
            }
        });
        frame.setVisible(true);
    }
    private void searchAndDisplayData() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an ID to search.");
            return;
        }
        try {
            String url = "jdbc:postgresql://localhost:5432/FMS";
            String username = "postgres";
            String password = "Tiny@2000";

            Connection conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT name, med_history FROM testreg WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String medHistory = resultSet.getString("med_history");
                nameResultLabel.setText(name);
                medHistoryTextArea.setText(medHistory); // Display medical history in the JTextArea
            } else {
                nameResultLabel.setText("No record found for ID: " + id);
                medHistoryTextArea.setText(""); // Clear the JTextArea
            }
            resultSet.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error.");
        }
    }
    private void saveTextToDatabase() {
        String id = idField.getText().trim();
        String newMedHistory = medHistoryTextArea.getText();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an ID to save to med_history.");
            return;
        }
        try {
            String url = "jdbc:postgresql://localhost:5432/FMS";
            String username = "postgres";
            String password = "Tiny@2000";

            Connection conn = DriverManager.getConnection(url, username, password);

            String updateSql = "UPDATE testreg SET med_history = ? WHERE id = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateSql);
            updateStatement.setString(1, newMedHistory);
            updateStatement.setString(2, id);

            int updatedRows = updateStatement.executeUpdate();
            if (updatedRows > 0) {
                JOptionPane.showMessageDialog(null, "Med_history updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No record found for ID: " + id);
            }
            updateStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error.");
        }
    }
}
/*
Import Statements (Lines 1-7):
Import necessary Java libraries for Swing GUI components, AWT, and database connectivity.
Class Declaration (public class ViewPatient):
Defines a class named ViewPatient for viewing and updating patient information.
Instance Variables (Lines 9-14):
Declare instance variables for GUI components such as text fields, labels, buttons, and a text area.
Constructor (public ViewPatient() - Lines 16-70):
Creates and configures a JFrame for the application.
Sets up a panel, labels, text fields, buttons, and a text area.
Adds these components to the frame.
Defines action listeners for the "Search" and "Home" buttons.
searchAndDisplayData() Method (Lines 72-109):
Extracts the entered ID from the text field.
Validates that an ID is provided.
Establishes a database connection to PostgreSQL.
Executes a SQL query to retrieve patient information.
If data is found, displays the patient's name and medical history. If no data is found, displays an appropriate message.
Closes database resources.
saveTextToDatabase() Method (Lines 111-157):
Extracts the entered ID and updated medical history from the text area.
Validates that an ID is provided.
Establishes a database connection to PostgreSQL.
Executes an SQL update to save the new medical history.
Notifies the user of the update result.
 */