import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class MyFrame extends JFrame implements ActionListener {

    JButton annu;
    JButton monthly;
    JButton hourly;
    JTextField rate;

    MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);

        JLabel label = new JLabel("Enter your wage:");
        label.setBounds(50, 30, 200, 40);

        hourly = new JButton("Hourly");
        hourly.setBounds(50,100, 100, 40);
        hourly.addActionListener(this);

        annu = new JButton("Annually");
        annu.addActionListener(this);
        annu.setBounds(170,100, 100, 40);

        monthly = new JButton("Monthly");
        monthly.addActionListener(this);
        monthly.setBounds(290, 100, 100, 40);

        rate = new JTextField();
        rate.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        rate.setForeground(Color.yellow);
        rate.setBackground(Color.magenta);

        //rate.setPreferredSize(new Dimension(250,40));
        rate.setBounds(150,30, 240, 40);
        this.add(rate);
        this.add(hourly);
        this.add(monthly);
        this.add(annu);
        this.add(label);
        this.getContentPane().setBackground(new Color(0x00ffff));

        //this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==hourly)
        {
            System.out.println("Your monthly income is " + normalize(1, Integer.parseInt(rate.getText())));
        }
        if(e.getSource()==monthly)
        {
            System.out.println("Your monthly income is " + normalize(2, Integer.parseInt(rate.getText())));
        }
        if(e.getSource()==annu)
        {
            System.out.println("Your monthly income is " + normalize(3, Integer.parseInt(rate.getText())));
        }
    }

    public static int normalize(int time, int amount)
    {
         int month;

         if(time == 1)
         {
             month = amount*160;
             return month;
         }
         if(time == 2)
         {
             month = amount;
             return month;
         }
         if(time == 3)
         {
             month = amount/12;
             return month;
         }
         else {
             return -1;
         }

    }

}


