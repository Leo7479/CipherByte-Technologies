import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Balance extends JFrame implements ActionListener, WindowListener{
    DatabaseTools db = null;
    Account account = null;
    JLabel temp = null;
    JButton btn = null;
    Balance(DatabaseTools db, Account a){
        this.db = db;
        this.account = a;
        TitleBar titleBar = new TitleBar();
        titleBar.setBounds(0,0,700, 35);
        add(titleBar);

        temp = new JLabel("Current Balance");
        temp.setBounds(250, 50, 150, 40);
        temp.setFont(new Font("Unispace", Font.BOLD, 15));
        add(temp);

        temp = new JLabel("Error Occured at our End");
        temp.setBounds(400, 50, 250, 40);
        temp.setFont(new Font("Unispace", Font.PLAIN, 15));
        add(temp);

        btn = new JButton("Back To Main Menu");
        btn.setBounds(290, 100, 150, 40);
        add(btn);
        btn.addActionListener(this);

        setUndecorated(true);
        setLayout(null);
        setSize(700, 150);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./images/login.png").getImage());
        addWindowListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        this.setVisible(false);
        account.setVisible(true);
    }
    @Override
    public void windowOpened(WindowEvent e) {    }

    @Override
    public void windowClosing(WindowEvent e) {    }

    @Override
    public void windowClosed(WindowEvent e) {    }

    @Override
    public void windowIconified(WindowEvent e) {    }

    @Override
    public void windowDeiconified(WindowEvent e) {    }

    @Override
    public void windowActivated(WindowEvent e) {
        try {
            temp.setText(db.getBalance(account.accountNumber));
        }catch(Exception ignored){}
    }

    @Override
    public void windowDeactivated(WindowEvent e) {    }

}
