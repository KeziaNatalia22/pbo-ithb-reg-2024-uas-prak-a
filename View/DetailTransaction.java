package View;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Controller.DBController;
import Model.FrameLogin;
import Model.SingletonManager;
import Model.Customer;

public class DetailTransaction {
    FrameLogin frame;
    JPanel panel;

    public DetailTransaction(){
        detailTransaction();
    }
    public void detailTransaction() {
        SingletonManager login = SingletonManager.getInstance();
        Customer user =  login.getUser();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 400;
        final int FRAME_HEIGHT = 720;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new FrameLogin("Detail Transaksi Pengiriman");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT - 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); 

        JLabel stst = new JLabel("Status");
        stst.setBounds(50, 60, 150, 20);
        panel.add(stst);

        JTextField status = new JTextField();
        status.setBounds(50, 85, 300, 30);
        status.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(status);

        JLabel poss = new JLabel("Current Possition");
        poss.setBounds(50, 125, 150, 20);
        panel.add(poss);

        JTextField possition = new JTextField();
        possition.setBounds(50, 150, 300, 30);
        possition.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(possition);

        JLabel evidence = new JLabel("Evidence");
        evidence.setBounds(50, 190, 150, 20);
        panel.add(evidence);

        JTextField evidenc = new JTextField();
        evidenc.setBounds(50, 215, 300, 30);
        evidenc.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(evidenc);

        JLabel updt = new JLabel("Updated by");
        updt.setBounds(50, 255, 150, 20);
        panel.add(updt);

        JTextField update = new JTextField();
        update.setBounds(50, 280, 300, 30);
        update.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(update);

        JButton save = new JButton("Simpan");
        save.setBounds(50, 405, 150, 30);
        panel.add(save);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 405, 150, 30);
        panel.add(backButton);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status.getText().isEmpty() || possition.getText().isEmpty() || evidenc.getText().isEmpty() || update.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Isi semua field", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (DBController.inputDetail(SingletonManager.getInstance().getTransaction().getId(), status.getText(), possition.getText(), evidence.getText(), update.getText())) {
                        JOptionPane.showMessageDialog(frame, "Behasil", "yeay", JOptionPane.INFORMATION_MESSAGE);
                        new MenuUtama();
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Gagal", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
