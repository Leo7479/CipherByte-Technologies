import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SendMoney extends JFrame implements ActionListener {
    JTextField reAccountNumber, name;
    JPasswordField accountNumber;
    DatabaseTools db;
    Account ac;
    JButton send;
    JSpinner amount;
    SendMoney(DatabaseTools db, Account a){
        this.db = db;
        this.ac = a;
        TitleBar titleBar = new TitleBar();
        titleBar.setBounds(0,0,700, 35);
        add(titleBar);

        // Receiver's Account:
        JLabel text = new JLabel("Receiver's Account");
        text.setBounds(10, 50, 150, 40);
        add(text);

        accountNumber = new JPasswordField();
        accountNumber.setBounds(200, 50, 250, 40);
        add(accountNumber);

        text = new JLabel("Re-Enter Receiver's Account");
        text.setBounds(10, 100, 150, 40);
        add(text);

        reAccountNumber = new JTextField();
        reAccountNumber.setBounds(200, 100, 250, 40);
        add(reAccountNumber);

        // Receiver's Name:
        text = new JLabel("Receiver's Name");
        text.setBounds(10, 150, 150, 40);
        add(text);

        name = new JTextField();
        name.setBounds(200, 150, 250, 40);
        add(name);

        // Amount:
        text = new JLabel("Amount to Transfer");
        text.setBounds(10, 200, 150, 40);
        add(text);

        SpinnerModel value = new SpinnerNumberModel(10, 10, 100000, 100);
        amount = new JSpinner(value);
        amount.setBounds(200, 200, 150, 40);
        add(amount);

        // Button:
        send = new JButton("Send Money");
        send.setBounds(150, 250, 150, 40);
        add(send);
        send.addActionListener(this);

        setUndecorated(true);
        setLayout(null);
        setSize(700, 415);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./images/login.png").getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == send){
            String s = null;
            try {
                String temp = "";
                for( char c: accountNumber.getPassword())
                    temp += Character.toString(c);
                if(!temp.equalsIgnoreCase(reAccountNumber.getText()))
                    throw new Exception("Account Mismatch");
                s = db.getName(Long.parseLong(reAccountNumber.getText()));
                if(s.equalsIgnoreCase(name.getText())) {
                    int con = JOptionPane.showConfirmDialog(this, "Account Number: "+ reAccountNumber.getText() + "\nAmount: "+ amount.getValue().toString()+"\nContinue?");
                    if(con != 0)
                        throw new Exception();
                    Long t = Long.parseLong(db.getBalance(ac.accountNumber))-Long.parseLong(amount.getValue().toString());
                    if(t<0)
                        throw new Exception();
                    db.updateBalance(ac.accountNumber, String.valueOf(t));
                    t = Long.parseLong(db.getBalance(reAccountNumber.getText()))+Long.parseLong(amount.getValue().toString());
                    db.updateBalance(reAccountNumber.getText(), String.valueOf(t));
                    JOptionPane.showMessageDialog(this,"Successfully transferred to "+ reAccountNumber.getText() + ", Rs. "+amount.getValue().toString()+".");
                }

            } catch (UserNotFoundException ex) {
                JOptionPane.showMessageDialog(this,"No User Found.");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Error, Money not debited. Try Again.");
            }finally {
                this.setVisible(false);
                ac.setVisible(true);
            }
        }
    }
}
