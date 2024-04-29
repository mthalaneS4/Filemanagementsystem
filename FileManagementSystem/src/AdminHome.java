import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdminHome extends JFrame {
    JMenuBar menuBar = new JMenuBar();
    JMenu filemenu = new JMenu("Home");
    JMenuItem addNewPatientItem = new JMenuItem("New Patient");
    JMenuItem editPatientItem = new JMenuItem("View Patient");
    JMenuItem reports = new JMenuItem("Reports");
    JMenuItem Signout = new JMenuItem("Sign out");

    public AdminHome() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        setSize(width, height);

        // Create a JLabel to display the image
        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\resize.jpg"); // Replace with the path to your image
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Add menu items to the patient menu
        filemenu.add(addNewPatientItem);
        filemenu.add(editPatientItem);
        filemenu.add(reports);
        filemenu.add(Signout);
        menuBar.add(filemenu);

        // Set the JMenuBar for the frame
        setJMenuBar(menuBar);
        addNewPatientItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Add New Patient form
                new NewPatients();
                dispose();
            }
        });
        editPatientItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Edit Patient form
                new ViewPatient();
                dispose();
            }
        });
        Signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dashboard dashboard = new Dashboard();
                dispose();
            }
        });
        reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Reports reports1 = new Reports();
                dispose();
            }
        });
        setVisible(true);
    }
}
/*
Import Statements:

These lines import necessary Java libraries for Swing GUI components and event handling.
Class Declaration (public class AdminHome extends JFrame):
Defines a class named AdminHome that extends JFrame, representing a graphical user interface window.
Instance Variables:
These lines declare instance variables for various GUI components such as a menu bar, menu, menu items, and actions.
**Constructor (public AdminHome()):
This constructor is executed when an object of the AdminHome class is created.
10-14. Frame Configuration:
Sets the default close operation for the frame, gets the screen size, and adjusts the frame's size to fit the screen while leaving a margin at the bottom.
17-22. Setting Background Image:
Creates an ImageIcon from an image file and assigns it to a backgroundImage.
Creates a JLabel (backgroundLabel) to display the image.
Sets the layout of the content pane to BorderLayout and adds the backgroundLabel.
25-30. Menu Configuration:
Adds menu items to the "Home" menu, including "New Patient," "View Patient," "Reports," and "Sign out."
32-35. Menu Bar Configuration:
Adds the "Home" menu to the menu bar (menuBar).
Sets the menu bar for the frame using setJMenuBar().
37-47. Action Listeners for Menu Items:
Defines action listeners for various menu items.
addNewPatientItem opens the "Add New Patient" form (NewPatients) and disposes of the current frame.
editPatientItem opens the "Edit Patient" form (ViewPatient) and disposes of the current frame.
Signout opens the Dashboard (presumably a login screen) and disposes of the current frame.
reports opens the "Reports" form (Reports) and disposes of the current frame.
Set Frame Visibility:
Makes the frame (AdminHome) visible on the screen.
 */