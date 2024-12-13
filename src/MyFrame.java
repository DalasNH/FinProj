import java.net.MalformedURLException;
import java.net.URL;
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
    private JLabel disp;
    private JLabel disp2;
    private JLabel budg1;
    private JLabel budg2;
    private JLabel budg3;
    private JLabel per;
    private JLabel bob;

    public MyFrame() throws MalformedURLException {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);

        per = new JLabel("/...");
        per.setBounds(430, 25, 90, 50);
        per.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        disp = new JLabel("Gross Monthly Income:");
        disp.setBounds(50, 190, 400, 50);
        disp.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        disp2 = new JLabel("Net Monthly Income:");
        disp2.setBounds(50, 220, 400, 50);
        disp2.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        budg1 = new JLabel("Necessities:");
        budg1.setBounds(50, 280, 400, 50);
        budg1.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        budg2 = new JLabel("Discretionary:");
        budg2.setBounds(50, 320, 400, 50);
        budg2.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        budg3 = new JLabel("Savings:");
        budg3.setBounds(50, 360, 400, 50);
        budg3.setFont(new Font("Comic Sans MS",Font.BOLD,20));

        JLabel dolla = new JLabel("$");
        dolla.setBounds(230,30,40,40);
        dolla.setFont(new Font("Comic Sans MS",Font.BOLD,30));

        JLabel perc = new JLabel("%");
        perc.setBounds(410,85,40,40);
        perc.setFont(new Font("Comic Sans MS",Font.BOLD,30));

        JLabel label = new JLabel("Enter your wage:");
        label.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        label.setBounds(50, 30, 200, 40);

        JLabel sttax = new JLabel("Enter state tax rate:");
        sttax.setFont(new Font("Comic Sans MS",Font.BOLD,20));
        sttax.setBounds(50, 85, 250, 40);

        hourly = new JButton("Hourly");
        hourly.setBounds(50,140, 100, 40);
        hourly.addActionListener(this);

        annu = new JButton("Annually");
        annu.addActionListener(this);
        annu.setBounds(290,140, 100, 40);

        monthly = new JButton("Monthly");
        monthly.addActionListener(this);
        monthly.setBounds(170, 140, 100, 40);

        rate = new JTextField();
        rate.setHorizontalAlignment(SwingConstants.RIGHT);
        rate.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        rate.setForeground(new Color(0x21385C));
        rate.setBackground(new Color(0xFFFFFF));
        rate.setBounds(250,30, 180, 40);

        STrate = new JTextField();
        STrate.setHorizontalAlignment(SwingConstants.RIGHT);
        STrate.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        STrate.setForeground(new Color(0x21385C));
        STrate.setBackground(new Color(0xFFFFFF));
        STrate.setBounds(280,85, 130, 40);

        ImageIcon icon = new ImageIcon("bobby.gif");
        bob = new JLabel(icon);
        bob.setBounds(300,300,200,200);
        bob.setVisible(false);

        this.add(bob);
        this.add(dolla);
        this.add(per);
        this.add(perc);
        this.add(STrate);
        this.add(rate);
        this.add(hourly);
        this.add(monthly);
        this.add(annu);
        this.add(label);
        this.add(sttax);
        this.getContentPane().setBackground(new Color(0xFFCDFFD0, true));
        this.add(disp);
        this.add(disp2);
        this.add(budg1);
        this.add(budg2);
        this.add(budg3);

        //this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        double sttax = Double.parseDouble(STrate.getText());
        double gross = -1;
        double net = -1;
        double nec;
        double dis;
        double sav;

        if(e.getSource()==hourly)
        {
            gross = normalize(1, Integer.parseInt(rate.getText()));
            net = TaxCalc(gross, sttax);
            per.setText("/hr");
        }

        if(e.getSource()==monthly)
        {
            gross = normalize(2, Integer.parseInt(rate.getText()));
            net = TaxCalc(gross, sttax);
            per.setText("/mo");
        }

        if(e.getSource()==annu)
        {
            gross = normalize(3, Integer.parseInt(rate.getText()));
            net = TaxCalc(gross, sttax);
            per.setText("/yr");
        }

        nec = net*50;
        nec = Math.round(nec);
        nec /=100;

        dis = net*30;
        dis = Math.round(dis);
        dis /=100;

        sav = net*20;
        sav = Math.round(sav);
        sav /=100;

        bob.setVisible(true);

        disp.setText("Gross Monthly Income: $" + gross);
        disp2.setText("Net Monthly Income: $" + net);
        budg1.setText("Necessities: $" + nec);
        budg2.setText("Discretionary: $" + dis);
        budg3.setText("Savings: $" + sav);

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
             month *=100;
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
    public double TaxCalc(double sal, double ST)
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


