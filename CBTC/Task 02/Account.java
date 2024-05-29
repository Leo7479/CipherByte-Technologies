import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Account extends JFrame implements Runnable, ActionListener{
    DatabaseTools db = null;
    String email = "", userName = "", accountNumber = "",formattedAccountNumber = "";
    JButton CreditBtn, DebitBtn, TransferBtn, BalanceBtn, LogOutBtn, ATMBtn;
    JPanel service = null;
    Transactions credit = null , debit = null;
    Balance balance = null;
    ATM atm = null;
    SendMoney transfer = null;
    Account(DatabaseTools db, String email){
        this.db = db;
        this.email = email;
        new Thread(this).start();
        try {
            this.userName = db.getName(email);
            this.accountNumber = db.getAccountNumber(email);
        }catch(Exception e){
            System.out.println(e);
            this.userName = "USER";
            this.accountNumber = "000000000000000";
        }
        this.formattedAccountNumber = this.accountNumber.substring(0, 4) +"-"+ this.accountNumber.substring(4, 8) +"-"+ this.accountNumber.substring(8, 12) +"-"+ this.accountNumber.substring(12);

        TitleBar titleBar = new TitleBar();
        titleBar.setBounds(0,0,700, 35);
        add(titleBar);

        // User Greeting:
        JLabel temp = new JLabel("Welcome, " + this.userName);
        temp.setFont(new Font("Lucida Sans",Font.BOLD, 20));
        temp.setBounds(20, 45, 400, 40);
        add(temp);
        temp = new JLabel("Account No: "+this.formattedAccountNumber);
        temp.setFont(new Font("Unispace", Font.BOLD, 12));
        temp.setBounds(20, 85, 350, 40);
        add(temp);

        // Buttons:
        CreditBtn = new JButton("Credit Cash");
        CreditBtn.setBounds(10, 145, 200, 40);
        CreditBtn.setBackground(new Color(194, 194, 8));
        CreditBtn.setForeground(Color.BLACK);
        add(CreditBtn);
        CreditBtn.addActionListener(this);

        DebitBtn = new JButton("Debit Cash");
        DebitBtn.setBounds(250, 145, 200, 40);
        DebitBtn.setBackground(new Color(194, 194, 8));
        DebitBtn.setForeground(Color.BLACK);
        add(DebitBtn);
        DebitBtn.addActionListener(this);

        BalanceBtn = new JButton("Balance Enquiry");
        BalanceBtn.setBounds(490, 145, 200, 40);
        BalanceBtn.setBackground(new Color(194, 194, 8));
        BalanceBtn.setForeground(Color.BLACK);
        add(BalanceBtn);
        BalanceBtn.addActionListener(this);

        ATMBtn = new JButton("ATM Service");
        ATMBtn.setBounds(10, 195, 200, 40);
        ATMBtn.setBackground(new Color(194, 194, 8));
        ATMBtn.setForeground(Color.BLACK);
        add(ATMBtn);
        ATMBtn.addActionListener(this);

        TransferBtn = new JButton("Transfer Money");
        TransferBtn.setBounds(250, 195, 200, 40);
        TransferBtn.setBackground(new Color(194, 194, 8));
        TransferBtn.setForeground(Color.BLACK);
        add(TransferBtn);
        TransferBtn.addActionListener(this);

        LogOutBtn = new JButton("Log Out");
        LogOutBtn.setBounds(490, 195, 200, 40);
        LogOutBtn.setBackground(new Color(194, 194, 8));
        LogOutBtn.setForeground(Color.BLACK);
        add(LogOutBtn);
        LogOutBtn.addActionListener(this);

        service = new JPanel();
        service.setBounds(10, 280, 680, 300);
        add(service);

        // Frame configurations:
        setUndecorated(true);
        setLayout(null);
        setSize(700, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setIconImage(new ImageIcon("./images/login.png").getImage());
    }

    @Override
    public void run(){
        credit = new Transactions(db, Transactions.CREDIT, this);
        debit = new Transactions(db, Transactions.DEBIT, this);
        balance = new Balance(db, this);
        atm = new ATM(db, this);
        transfer = new SendMoney(db, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == CreditBtn){
            credit.setVisible(true);
            this.setVisible(false);
        }else if(e.getSource() == DebitBtn){
            debit.setVisible(true);
            this.setVisible(false);
        }else if(e.getSource() == BalanceBtn){
            balance.setVisible(true);
            this.setVisible(false);
        }else if(e.getSource() == ATMBtn){
            atm.setVisible(true);
            this.setVisible(false);
        }else if(e.getSource() == TransferBtn){
            transfer.setVisible(true);
            this.setVisible(false);
        }else if(e.getSource() == LogOutBtn){
            this.setVisible(false);
            accountNumber = "";
            email = "";
            userName = "";
            formattedAccountNumber = "";
            service = null;
            credit = null;
            debit = null;
            balance = null;
            atm = null;
            transfer = null;
            new Login();
        }
    }
}
