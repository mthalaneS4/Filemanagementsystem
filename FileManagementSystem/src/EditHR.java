import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditHR {
    private JTextField idField;
    private JLabel nameLabel;
    private JLabel nameResultLabel;
    private JTextArea medHistoryTextArea;
    private JButton searchButton;
    private JButton saveButton;
    private JButton Home;

    public EditHR() {
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
        saveButton = new JButton("Save to med_history");
        nameLabel = new JLabel("Name:");
        Home = new JButton("Home");
        nameResultLabel = new JLabel("");
        medHistoryTextArea = new JTextArea(10, 30);

        panel.add(idLabel);
        panel.add(idField);
        panel.add(searchButton);
        panel.add(saveButton);
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
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTextToDatabase();
            }
        });
        Home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomepgDoc userAccounts = new HomepgDoc();
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
Imports necessary Java libraries for Swing GUI components, AWT, and database connectivity.
Class Declaration (public class EditHR):
Defines a class named EditHR.
Instance Variables:
Declares instance variables for various GUI components (text fields, labels, buttons) to be used in the class.
Constructor (public EditHR()):
Sets up the GUI window when an object of the EditHR class is created.
22-30. Frame Configuration:
Configures the frame (a JFrame) with a title and size to fit the screen.
33-46. Creating and Configuring Components:
Initializes and configures GUI components, including labels, text fields, buttons, and a text area.
47-50. Adding Components to the Panel:
Adds various components to the panel using a FlowLayout.
51-53. Adding Components to the Frame:
Adds the panel and a scrollable text area to the frame.
54-66. Action Listeners for Buttons:
Defines action listeners for the "Search," "Save to med_history," and "Home" buttons.
searchButton invokes the searchAndDisplayData() method when clicked.
saveButton invokes the saveTextToDatabase() method when clicked.
Home opens a HomepgDoc frame.
Set Frame Visibility:
Makes the frame (EditHR) visible on the screen.
69-119. searchAndDisplayData() Method:
Performs a database search and displays data.
Extracts ID from the text field and checks if it's empty.
Establishes a database connection and executes a SQL query to fetch data.
If data is found, it populates the labels and text area with the retrieved information.
121-171. saveTextToDatabase() Method:
Saves text to the database.
Extracts ID and new medical history from the text field and text area.
Establishes a database connection and updates the database with the new medical history.
Notifies the user about the success or failure of the update.
 */