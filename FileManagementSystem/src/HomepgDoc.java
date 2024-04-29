import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomepgDoc extends JFrame {
    // Create a menu bar
    JMenuBar menuBar = new JMenuBar();
    JMenu filemenu = new JMenu("Home");
    // Create menu items
    JMenuItem edit_and_appointment = new JMenuItem("Edit and appointments");
    JMenuItem prescribe = new JMenuItem("Prescribe");
    JMenuItem Signout = new JMenuItem("Sign Out");

    public HomepgDoc() {
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
        filemenu.add(edit_and_appointment);
        filemenu.add(prescribe);
        filemenu.add(Signout);

        menuBar.add(filemenu);

        // Set the JMenuBar for the frame
        setJMenuBar(menuBar);

       prescribe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Edit Patient form
                new Prescribe();
                dispose();
            }
        });
        Signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new Dashboard();
                dispose();
            }
        });

        edit_and_appointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditHR();
                dispose();
            }
        });

        setVisible(true);
    }
}
/*
Import Statements:
These lines import necessary Java libraries for Swing GUI components and event handling.
Class Declaration (public class HomepgDoc extends JFrame):
Defines a class named HomepgDoc that extends JFrame, representing a graphical user interface window.
Instance Variables:
These lines declare instance variables for various GUI components, including a menu bar, menu, menu items, and actions.
**Constructor (public HomepgDoc()):
This constructor is executed when an object of the HomepgDoc class is created.
10-14. Frame Configuration:
Sets the default close operation for the frame, gets the screen size, and adjusts the frame's size to fit the screen while leaving a margin at the bottom.
17-22. Setting Background Image:
Creates an ImageIcon from an image file and assigns it to a backgroundImage.
Creates a JLabel (backgroundLabel) to display the image.
Sets the layout of the content pane to BorderLayout and adds the backgroundLabel.
25-29. Menu Configuration:
Creates a menu labeled "Home."
Creates menu items, including "Edit and Appointments," "Prescribe," and "Sign Out."
31-35. Menu Bar Configuration:
Adds the menu items to the menu labeled "Home."
Adds the "Home" menu to the menu bar (menuBar).
Set Menu Bar for the Frame:
Sets the menu bar for the frame using setJMenuBar().
39-49. Action Listeners for Menu Items:
Defines action listeners for the menu items.
prescribe opens the "Prescribe" form (Prescribe) and disposes of the current frame.
Signout opens the Dashboard (presumably a login screen) and disposes of the current frame.
edit_and_appointment opens the "EditHR" form (EditHR) and disposes of the current frame.
Set Frame Visibility:
Makes the frame (HomepgDoc) visible on the screen.
 */