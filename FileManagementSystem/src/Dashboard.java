import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {
    private JToolBar toolBar = new JToolBar();
    private JLabel head = new JLabel("Mobile Health View System");
    private JLabel message = new JLabel();
    private JButton Signin = new JButton("Sign In");
    private JButton Aboutus = new JButton("About Us");

    public Dashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        setSize(width, height);
        setLocationRelativeTo(null);

        Signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserAccounts userAccounts = new UserAccounts();
                dispose();
            }
        });

        Aboutus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            AboutUs aboutUs = new AboutUs();
            dispose();
            }
        });
        toolBar.add(head);
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.add(Signin);
        toolBar.addSeparator();
        toolBar.addSeparator();
        toolBar.add(Aboutus);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\resize.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);

                Font labelFont = new Font("Arial", Font.BOLD, 80);
                g.setFont(labelFont);
                g.setColor(Color.BLACK);
                String label = "Health Care Simp" +
                        "lified" ;
                int labelX = 180; // X-coordinate for the label
                int labelY = 200; // Y-coordinate for the label
                g.drawString(label, labelX, labelY);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(toolBar, BorderLayout.NORTH);

        backgroundPanel.add(panel, BorderLayout.NORTH);

        add(backgroundPanel);
        setVisible(true);
    }
    public static void main(String[] args) {
        Dashboard tool = new Dashboard();
      //  dispose();
    }
}
/*
Imports: The code begins by importing necessary Java Swing and AWT libraries to handle GUI components.
3-12. Class Definition: The class Dashboard extends JFrame, initializing various Swing components such as JToolBar, JLabel, and JButton for the graphical user interface (GUI).

14-34. Constructor - Dashboard() Method:

Lines 16-18: Sets up the frame properties - default close operation, size, and location to occupy the whole screen except for 50 pixels from the bottom.
Lines 20-28: Define action listeners for the "Sign In" and "About Us" buttons. Upon a button click, they create new instances of the respective classes (UserAccounts and AboutUs) and dispose of the current Dashboard frame.
Lines 30-38: Adds components to the toolbar (head, several separators, Signin, and Aboutus buttons).
Lines 40-67: Creates a custom panel (backgroundPanel) that overrides the paintComponent method to display an image and some text in the GUI. It sets the layout as BorderLayout and adds the toolBar panel to the top.
Lines 69-71: Adds the backgroundPanel to the frame and sets it visible.
73-82. main() Method:

Line 75: Instantiates an object of the Dashboard class, effectively launching the application.
 */
