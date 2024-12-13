import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {

    JButton annu;
    JButton monthly;
    JButton hourly;
    JTextField rate;
    JTextField STrate;

    MyFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);

        JLabel label = new JLabel("Enter your wage:");
        label.setBounds(50, 30, 200, 40);

        JLabel sttax = new JLabel("Enter state tax rate:");
        sttax.setBounds(50, 100, 200, 40);

        hourly = new JButton("Hourly");
        hourly.setBounds(50,160, 100, 40);
        hourly.addActionListener(this);

        annu = new JButton("Annually");
        annu.addActionListener(this);
        annu.setBounds(290,160, 100, 40);

        monthly = new JButton("Monthly");
        monthly.addActionListener(this);
        monthly.setBounds(170, 160, 100, 40);

        rate = new JTextField();
        rate.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        rate.setForeground(new Color(0x21385C));
        rate.setBackground(new Color(0xFFFFFF));
        rate.setBounds(180,30, 210, 40);

        STrate = new JTextField();
        STrate.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        STrate.setForeground(new Color(0x21385C));
        STrate.setBackground(new Color(0xFFFFFF));
        STrate.setBounds(180,100, 210, 40);

        this.add(STrate);
        this.add(rate);
        this.add(hourly);
        this.add(monthly);
        this.add(annu);
        this.add(label);
        this.add(sttax);
        this.getContentPane().setBackground(new Color(0xFFCDFFD0, true));

        //this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        double sttax = Double.parseDouble(STrate.getText());
        if(e.getSource()==hourly)
        {
            double gross = normalize(1, Integer.parseInt(rate.getText()));
            System.out.println("Your gross monthly income is " + gross);
            System.out.println("Your net monthly income is " + TaxCalc(gross, sttax));
        }
        if(e.getSource()==monthly)
        {
            double gross = normalize(2, Integer.parseInt(rate.getText()));
            System.out.println("Your gross monthly income is " + gross);
            System.out.println("Your net monthly income is " + TaxCalc(gross, sttax));
        }
        if(e.getSource()==annu)
        {
            double gross = normalize(3, Integer.parseInt(rate.getText()));
            System.out.println("Your gross monthly income is " + gross);
            System.out.println("Your net monthly income is " + TaxCalc(gross, sttax));
        }
    }

    public static double normalize(int time, double amount)
    {
         double month;

         if(time == 1)
         {
             month = amount*17300;
             month = Math.round(month);
             month = month/100;
             return month;
         }
         if(time == 2)
         {
             month = amount;
             month = Math.round(month);
             month = month/100;
             return month;
         }
         if(time == 3)
         {
             month = (amount/12)*100;
             month = Math.round(month);
             month = month/100;
             return month;
         }
         else {
             return -1;
         }

    }
    public static double TaxCalc(double sal, double ST)
    {
        sal = sal*12;
        //converts to annual pay instead of monthly
        double real = 0;
        double x = 0;
        //variable x is used as remainder of income (the income that does not fully fill your highest tax bracket)
        if (sal >= 11000)
        {
            real += (11000*.9);
            x = (sal-11000)*.88;
            if (sal >= 44725)
            {
                real += ((44725-11000)*.88);
                x = (sal-44725)*.78;
                if (sal >= 95375)
                {
                    real += ((95375-44725)*.78);
                    x = (sal-95375)*.76;
                    if (sal >= 182100)
                    {
                        real += ((182100-95375)*.76);
                        x = (sal-182100)*.68;
                        if (sal >= 231250)
                        {
                            real += ((231250-182100)*.68);
                            x = (sal-231250)*.65;
                            if (sal >= 578126)
                            {
                                real += ((578126-231250)*.65);
                                x = (sal-578126)*.63;
                            }
                        }
                    }
                }
            }
        }
        else
        {
            real = sal*.9;
        }
        double state = sal*(ST/100);
        real = real+x;
        real -= state;
        real = real/12;
        real = Math.round(real*100);
        real = real/100;
        return real;
    }

}


