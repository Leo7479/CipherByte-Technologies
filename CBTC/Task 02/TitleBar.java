import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TitleBar extends JPanel implements ActionListener{
    JButton closeBtn;
    TitleBar(){
        // Image on one side
        JLabel imgHolder = new JLabel();
        ImageIcon image = new ImageIcon("./images/login.png");
        imgHolder.setIcon(image);
        imgHolder.setBounds(0, 2, 45, 28);
        add(imgHolder);
        // Image on one side
        JLabel title = new JLabel("One Banking");
        title.setBounds(50, 2, 150, 28);
        title.setForeground(Color.white);
        title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        add(title);
        closeBtn = new JButton();
        closeBtn.setIcon(new ImageIcon("images/close.png"));
        closeBtn.setBounds(665, 2, 32, 28);
        closeBtn.addActionListener(this);
        closeBtn.setBackground(Color.BLACK);
        add(closeBtn);
        // Panel configuration:
        setSize(700, 30);
        setVisible(true);
        setLayout(null);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == closeBtn){
            System.exit(0);
        }
    }
}
