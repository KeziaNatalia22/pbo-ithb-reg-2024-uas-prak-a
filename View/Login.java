package View;

import javax.swing.*;
import Model.FrameLogin;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.*;
import Model.Customer;


public class Login {
    FrameLogin frame;
    JPanel panel;

    public Login() {
        Login();
    }

    public void Login() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize(); 

        int screenWidth = screenSize.width; 
        int screenHeight = screenSize.height; 

        final int FRAME_WIDTH = 300; 
        final int FRAME_HEIGHT = 300; 

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); 
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); 

        frame = new FrameLogin("Anjay Mabar"); 
        
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // SET FRAME BOUND
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        JLabel label1 = new JLabel("No Telpon");
        label1.setBounds(30, 50, 150, 30);
        panel.add(label1);

        JTextField textField1 = new JTextField();
        textField1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textField1.setBounds(30, 80, 150, 30);
        panel.add(textField1);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(30, 120, 150, 30);
        panel.add(label2);

        JPasswordField passwordField1 = new JPasswordField();
        passwordField1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        passwordField1.setBounds(30, 150, 150, 30);
        panel.add(passwordField1);
        
        JButton submit = new JButton("Login");
        submit.setBounds(30, 200, 100, 30);
        panel.add(submit);

        JButton back = new JButton("Back");
        back.setBounds(140, 200, 100, 30);
        panel.add(back);
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DBController.Login(textField1.getText(), new String(passwordField1.getPassword()));
                new MenuUtama();
            }
            
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MenuUtama();

            }
        });
        

        frame.setVisible(true);
        frame.add(panel);
    }
}