import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Transactions extends JFrame implements ActionListener, WindowListener {
    DatabaseTools db = null;
    public static int CREDIT = 1,credit = 1,DEBIT = 0,debit = 0;
    JTextField amount;
    Account a;
    int modeOfTransaction = 0;
    JButton Btn;
    JLabel temp;
    Transactions(DatabaseTools d,int mode, Account a){
        this.a = a;
        db = d;
        this.modeOfTransaction = mode;
        TitleBar titleBar = new TitleBar();
        titleBar.setBounds(0,0,700, 35);
        add(titleBar);

        // Panel Configuration:
        temp = new JLabel("Amount to Credit");
        temp.setBounds(10,50, 150, 40);
        add(temp);
        temp.setFont(new Font("Unispace", Font.PLAIN, 15));

        amount = new JTextField();
        amount.setBounds(190, 50, 250, 40);
        amount.setFont(new Font("Lucidia", Font.PLAIN, 15));
        add(amount);

        temp = new JLabel("Account Name");
        temp.setBounds(10,100, 150, 40);
        add(temp);
        temp.setFont(new Font("Unispace", Font.PLAIN, 15));

        temp = new JLabel(a.userName);
        temp.setBounds(190, 100, 250, 40);
        add(temp);

        temp = new JLabel("Account Number");
        temp.setBounds(10,150, 150, 40);
        add(temp);
        temp.setFont(new Font("Unispace", Font.PLAIN, 15));

        temp = new JLabel(a.accountNumber);
        temp.setBounds(190, 150, 250, 40);
        add(temp);

        temp = new JLabel("Current Balance");
        temp.setBounds(10, 200, 150, 40);
        if(modeOfTransaction == 0){
            add(temp);
        }

        temp = new JLabel();
        temp.setBounds(190, 200, 250, 40);
        add(temp);
        if(this.modeOfTransaction == 0){
            add(temp);
        }

        Btn = new JButton();
        Btn.addActionListener(this);
        add(Btn);

        temp.setFont(new Font("Unispace", Font.PLAIN, 15));

        if(modeOfTransaction == 1){
            Btn.setText("Credit To Account");
            this.setSize(700, 300);
            Btn.setBounds(275, 200, 150, 40);
        }else{
            Btn.setText("Debit from Account");
            this.setSize(700, 350);
            Btn.setBounds(275, 260, 150, 40);
        }
        // Frame Configurations:
        this.setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(a);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./images/login.png").getImage());
        addWindowListener(this);;
    }
    synchronized private void credit(String amount) {
        long money = Long.parseUnsignedLong(amount),moneyUpdated;
        try {
            moneyUpdated = Long.parseUnsignedLong(db.getBalance(a.accountNumber)) + money;
            db.updateBalance(a.accountNumber, Long.toString(moneyUpdated));
            JOptionPane.showMessageDialog(this,"Successfully Credited Rs."+money);
        }catch(Exception ignored){}

    }
    synchronized private boolean debit(String amount){
        try{
            long money = Long.parseUnsignedLong(amount),balance = Long.parseUnsignedLong(db.getBalance(a.accountNumber)),moneyUpdated;
            if(money <= balance){
                moneyUpdated = balance - money;
                db.updateBalance(a.accountNumber,Long.toString(moneyUpdated));
                JOptionPane.showMessageDialog(this,"Successfully Debited Rs."+money);
            }else{
                JOptionPane.showMessageDialog(this, "Not Enough Balance");
            }
        }catch(Exception ignored){}
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(modeOfTransaction == 1)
                credit(amount.getText());
            else
                debit(amount.getText());
        }catch(Exception ignored){}
        this.setVisible(false);
        a.setVisible(true);
        this.amount.setText("");
        this.amount.grabFocus();
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
            if(modeOfTransaction == 0)
                temp.setText(db.getBalance(a.accountNumber));
        }catch(Exception ignored){}
    }

    @Override
    public void windowDeactivated(WindowEvent e) {    }
}
