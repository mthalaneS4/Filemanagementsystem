import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserAccounts implements ActionListener {
    JFrame frame = new JFrame("UserAccounts");
    JButton ADMIN = new JButton("ADMIN LOGIN");
    JButton DOC = new JButton("DOCTOR'S LOGIN");
    JLabel Acc = new JLabel("USER ACCOUNTS");

    UserAccounts() {
        Acc.setFont(new Font("Arial", Font.BOLD, 25));
        Acc.setBounds(500, 300, 400, 40); // Set the label's position above the buttons
        Acc.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\user.png"));
        ADMIN.setBounds(400, 400, 200, 40); // Adjusted the button positions and width
        ADMIN.addActionListener(this);
        ADMIN.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\administration.png"));
        DOC.setBounds(650, 400, 200, 40); // Adjusted the button positions and width
        DOC.addActionListener(this);
        DOC.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\medical-team.png"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(Acc);
        frame.add(ADMIN);
        frame.add(DOC);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ADMIN) {
            frame.dispose();
            new Admin();

        } else if (e.getSource() == DOC) {
            frame.dispose();
            Doctor doctor = new Doctor();
        }
    }
}
/*
Lines 1-6: Import Statements
These lines import the necessary Java Swing and AWT libraries to handle GUI components.

Lines 8-17: Class Definition
This code defines a class named UserAccounts, which implements the ActionListener interface. The class contains various Swing components and methods for setting up and handling the GUI.

Lines 18-27: Instance Variables
Inside the UserAccounts class, several instance variables are defined:

Lines 29-50: Constructor (UserAccounts())
The constructor is a special method that is called when an object of the class is created. In this constructor, the GUI components are initialized and configured:

Lines 52-61: Frame Configuration
These lines set up the JFrame (frame):
Lines 63-65: Adding Components to the Frame
These lines add the Acc label, "ADMIN LOGIN" button (ADMIN), and "DOCTOR'S LOGIN" button (DOC) to the frame.

Lines 67-79: actionPerformed(ActionEvent e) Method (implements ActionListener interface)
This method is called when a button is clicked. It handles button click events:

It checks which button was clicked using e.getSource() and performs specific actions accordingly.
If the "ADMIN LOGIN" button is clicked, it disposes of the current frame and creates a new Admin object (presumably, it opens an admin login screen).
If the "DOCTOR'S LOGIN" button is clicked, it disposes of the current frame and creates a new Doctor object (presumably, it opens a doctor login screen).
 */
