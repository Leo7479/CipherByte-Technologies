import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM extends JFrame implements ActionListener{
    DatabaseTools db = null;
    Account account = null;
    JButton btn = null;
    String ATMNumber = null, ATMExpiry = null;
    ATM(DatabaseTools d, Account a){
        this.db = d;
        this.account = a;
        ATMNumber = db.getATMNumber(account.accountNumber);
        ATMNumber = this.ATMNumber.substring(0, 4) +"-"+ this.ATMNumber.substring(4, 8) +"-"+ this.ATMNumber.substring(8, 12) +"-"+ this.ATMNumber.substring(12);
        ATMExpiry = db.getATMExpiry(account.accountNumber);

        TitleBar titleBar = new TitleBar();
        titleBar.setBounds(0,0,700, 35);
        add(titleBar);

        JLabel temp = new JLabel("ATM Number");
        temp.setBounds(10, 50, 250, 40);
        temp.setFont(new Font("Unispace", Font.BOLD, 20));
        add(temp);

        temp = new JLabel(ATMNumber);
        temp.setBounds(280, 50, 400, 40);
        temp.setFont(new Font("Unispace", Font.BOLD, 20));
        add(temp);

        temp = new JLabel("Name on ATM");
        temp.setBounds(10, 110, 250, 40);
        temp.setFont(new Font("Unispace", Font.BOLD, 20));
        add(temp);

        temp = new JLabel(account.userName);
        temp.setBounds(280, 110, 400, 40);
        temp.setFont(new Font("Unispace", Font.BOLD, 20));
        add(temp);

        temp = new JLabel("Expiry Date");
        temp.setBounds(10, 180, 250, 40);
        temp.setFont(new Font("Unispace", Font.BOLD, 20));
        add(temp);

        temp = new JLabel(ATMExpiry);
        temp.setBounds(280, 180, 400, 40);
        temp.setFont(new Font("Unispace", Font.BOLD, 20));
        add(temp);

        btn = new JButton("Back To Main Menu");
        btn.setBounds(275, 250, 150, 40);
        add(btn);
        btn.addActionListener(this);

        setUndecorated(true);
        setLayout(null);
        setSize(700, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./images/login.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        account.setVisible(true);
    }
}
