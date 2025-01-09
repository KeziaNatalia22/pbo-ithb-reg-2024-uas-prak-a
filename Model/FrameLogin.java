package Model;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FrameLogin extends JFrame{
    public FrameLogin(String title){
        innitComponent(title);
    }
    
    private void innitComponent(String title){
        ImageIcon logo = new ImageIcon("Tokopaedi.jpg");
        this.setIconImage(logo.getImage());
        this.setTitle(title);
    }
}
