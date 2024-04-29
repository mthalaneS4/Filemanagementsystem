import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutUs extends JFrame {
    JLabel titleLabel = new JLabel("About Us");
    JLabel locationLabel = new JLabel();
    JLabel phoneNumberLabel = new JLabel();
    JLabel emailLabel = new JLabel();
    JLabel locationLabelinfo = new JLabel("141 lilian ngoye st");
    JLabel phoneNumberLabelinfo = new JLabel("0625393078");
    JLabel emailLabelinfo = new JLabel("sagi.richfield@ca.za");
    JButton Dashb = new JButton("Dashboard");
    public AboutUs(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height - 50;
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        add(titleLabel);
        add(locationLabel);
        add(phoneNumberLabel);
        add(emailLabel);
        add(locationLabelinfo);
        add(phoneNumberLabelinfo);
        add(emailLabelinfo);
        add(Dashb);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 10, 100, 30);
        //location
        locationLabel.setBounds(20, 50, 100, 50);
        locationLabel.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\location.png"));
        //phone
        phoneNumberLabel.setBounds(20, 150, 100, 50);
        phoneNumberLabel.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\telephone-call.png"));
        //email
        emailLabel.setBounds(20, 250, 100, 50);
        emailLabel.setIcon(new ImageIcon("C:\\Users\\Slindokuhle Mthalane\\OneDrive\\Desktop\\projectSFMCS\\icons\\email.png"));
//add infor to the label
        locationLabelinfo.setBounds(150, 60, 200, 20);
        locationLabelinfo.setFont(new Font("Arial",Font.BOLD,18));
        //
        phoneNumberLabelinfo.setBounds(150, 160, 100, 20);
        phoneNumberLabelinfo.setFont(new Font("Arial",Font.BOLD,18));
        //
        emailLabelinfo.setBounds(150, 260, 300, 20);
        emailLabelinfo.setFont(new Font("Arial",Font.BOLD,18));
        Dashb.setBounds(170, 350, 100, 25);

        Dashb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 new Dashboard();
                 dispose();
            }
        });
    }

}
/*
Import Statements:
Import necessary Java libraries for Swing GUI components and event handling.
Class Declaration (public class AboutUs extends JFrame):
Defines a class named AboutUs.
Instance Variables:
Declares instance variables for various GUI components, including labels, text, and a button.
**Constructor (public AboutUs()):
Sets up the GUI window when an object of the AboutUs class is created.
13-19. Frame Configuration:
Configures the AboutUs frame with a size that fits the screen, a close operation, and a null layout.
21-32. Adding Components to the Frame:
Adds various components (labels, text, and a button) to the AboutUs frame.
34-48. Configuring Labels:
Configures the appearance and position of labels (titleLabel, locationLabel, phoneNumberLabel, and emailLabel).
50-59. Adding Icons to Labels:
Adds icons to locationLabel, phoneNumberLabel, and emailLabel.
61-73. Adding Information Labels:
Adds labels (locationLabelinfo, phoneNumberLabelinfo, and emailLabelinfo) to display corresponding information.
75-86. Configuring Information Labels:
Configures the appearance and position of information labels and sets their font.
88-96. "Dashboard" Button:
Configures the "Dashboard" button (Dashb) and defines an action listener.
When the button is clicked, it opens a new Dashboard frame and disposes of the current frame.
 */