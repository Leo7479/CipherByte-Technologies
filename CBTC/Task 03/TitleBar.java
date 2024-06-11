import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class TitleBar extends JPanel implements ActionListener {
    JButton closeBtn = null;
    TitleBar(){
        closeBtn = new JButton(new ImageIcon("./images/close.png"));
        closeBtn.setBounds(640, 3, 44, 44);
        closeBtn.addActionListener(this);
        closeBtn.setBackground(new Color(1,102,120));
        add(closeBtn);

        // Panel properties
        setBackground(new Color(0,78,153));
        setLayout(null);
        setSize(700, 40);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == closeBtn){
            System.exit(0);
        }
    }
}
