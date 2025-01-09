package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Controller.DBController;
import Model.FrameLogin;

public class Registrasi {
    FrameLogin frame;
    JPanel panel;

    public Registrasi() {
        Regist();
    }

    public void Regist() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 400;
        final int FRAME_HEIGHT = 720;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new FrameLogin("Register Form");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT - 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); 

        JLabel usnL = new JLabel("Username");
        usnL.setBounds(50, 60, 150, 20);
        panel.add(usnL);

        JTextField username = new JTextField();
        username.setBounds(50, 85, 300, 30);
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(username);

        JLabel passwordL = new JLabel("Password");
        passwordL.setBounds(50, 125, 150, 20);
        panel.add(passwordL);

        JPasswordField password = new JPasswordField();
        password.setBounds(50, 150, 300, 30);
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(password);

        JLabel telpL = new JLabel("No Telp");
        telpL.setBounds(50, 190, 150, 20);
        panel.add(telpL);

        JTextField telpf = new JTextField();
        telpf.setBounds(50, 215, 300, 30);
        telpf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(telpf);

        JLabel addressL = new JLabel("Alamat");
        addressL.setBounds(50, 255, 150, 20);
        panel.add(addressL);

        JTextField address = new JTextField();
        address.setBounds(50, 280, 300, 30);
        address.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(address);

        JLabel emailL = new JLabel("Email");
        emailL.setBounds(50, 315, 150, 20);
        panel.add(emailL);

        JTextField email = new JTextField();
        email.setBounds(50, 345, 300, 30);
        email.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(email);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(50, 405, 150, 30);
        panel.add(registerButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 405, 150, 30);
        panel.add(backButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isEmpty() || new String(password.getPassword()).isEmpty() || telpf.getText().isEmpty() || address.getText().isEmpty() || email.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Lengkapi semua field", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (DBController.checkUniqueTelp(telpf.getText()) == 0) {
                    JOptionPane.showMessageDialog(frame, "No Telp sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (DBController.checkUniqueEmail(email.getText()) == 0) {
                    JOptionPane.showMessageDialog(frame, "Email sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (DBController.inputDatatoDB(username.getText(), telpf.getText(), address.getText(), new String(password.getPassword()), email.getText())) {
                    JOptionPane.showMessageDialog(frame, "Registrasi berhasil!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Login();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Gagal mendaftarkan akun", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(e -> {
            new MenuUtama();
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
