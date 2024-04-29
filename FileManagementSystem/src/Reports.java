import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class Reports extends JFrame {
    private JButton generateReportButton;
    private JButton Home;
    private JTextField idField;
    private JTable reportTable;
    private DefaultTableModel tableModel;
    public Reports() {
        setTitle("PostgreSQL Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        setSize(width, height);
        setLayout(null);
        setVisible(true);
        tableModel = new DefaultTableModel();
        reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        scrollPane.setBounds(10, 50, 780, 300);

        generateReportButton = new JButton("Generate Report");
        Home = new JButton("Home");
        generateReportButton.setBounds(10, 10, 150, 30);
        Home.setBounds(180, 10, 100, 30);
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                generateReport();
            }
        });
        Home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new  AdminHome();
                dispose();
            }
        });
        idField = new JTextField();
        idField.setBounds(300, 10, 300, 30);
        add(generateReportButton);
        add(idField);
        add(Home);
        add(scrollPane);
    }
    private void generateReport() {
        // Database connection details
        String url = "jdbc:postgresql://localhost:5432/FMS";
        String user = "postgres";
        String password = "Tiny@2000";

        String idList = idField.getText();
        String[] ids = idList.split(",");
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            StringBuilder query = new StringBuilder("SELECT id, med_history FROM testreg WHERE id IN (");

            for (int i = 0; i < ids.length; i++) {
                query.append("?");
                if (i < ids.length - 1) {
                    query.append(",");
                }
            }
            query.append(")");
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            for (int i = 0; i < ids.length; i++) {
                preparedStatement.setString(i + 1, ids[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            tableModel.setRowCount(0);
            tableModel.setColumnIdentifiers(new Object[]{"id", "med_history"});
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{resultSet.getString("id"), resultSet.getString("med_history")});
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error generating report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
/*
Import Statements: These lines import necessary Java libraries for Swing GUI components, AWT, and database connectivity.
10-36. Constructor (public Reports()):

Sets up the GUI frame for the report generation.
Defines and configures components such as buttons, text fields, and a table to display the reports.
Adds action listeners to the "Generate Report" button and "Home" button.
38-73. generateReport() Method:

Handles the generation of the report.
Establishes a connection to the PostgreSQL database using the specified connection details.
Builds a SQL query to select data from the database for the provided IDs.
Uses a prepared statement to set the IDs in the query and execute it.
Populates a table model with the retrieved data.
Handles potential SQL exceptions and displays an error message if an error occurs.
 */