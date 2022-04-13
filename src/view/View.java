package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {

    private final JTextField arrivalTimeTxt;
    private final JTextField clientsTxt;
    private final JButton startButton;
    private final JTextField queuesTxt;
    private final JTextField simIntervalTxt;
    private final JTextField serviceTimeTxt;
    private final JButton validateButton;



    public View() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setBounds(100, 100, 571, 519);
        JPanel contentPane = new JPanel();
        contentPane.setForeground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setLayout(null);

        arrivalTimeTxt = new JTextField();
        arrivalTimeTxt.setFont(new Font("Arial", Font.PLAIN, 17));
        arrivalTimeTxt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (arrivalTimeTxt.getText().equals("First Polynomial")) {
                    arrivalTimeTxt.setText("");
                } else
                    arrivalTimeTxt.selectAll();

            }
        });

        arrivalTimeTxt.setBounds(278, 91, 283, 32);
        contentPane.add(arrivalTimeTxt);
        arrivalTimeTxt.setColumns(10);

        JLabel arrivalTimeLbl = new JLabel("Arrival Time(min,max)");
        arrivalTimeLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        arrivalTimeLbl.setHorizontalAlignment(SwingConstants.LEFT);
        arrivalTimeLbl.setBounds(10, 93, 258, 23);
        contentPane.add(arrivalTimeLbl);

        JLabel clientsLbl = new JLabel("No. Clients");
        clientsLbl.setHorizontalAlignment(SwingConstants.LEFT);
        clientsLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        clientsLbl.setBounds(10, 153, 179, 25);
        contentPane.add(clientsLbl);

        clientsTxt = new JTextField();
        clientsTxt.setFont(new Font("Arial", Font.PLAIN, 17));
        clientsTxt.setColumns(10);
        clientsTxt.setBounds(278, 152, 283, 32);
        contentPane.add(clientsTxt);

        JLabel titleLbl = new JLabel("Queues Management");
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        titleLbl.setFont(new Font("Arial", Font.PLAIN, 29));
        titleLbl.setBounds(10, 20, 537, 38);
        contentPane.add(titleLbl);

        startButton = new JButton("Start");
        startButton.setForeground(Color.GREEN);
        startButton.setFont(new Font("Arial", Font.PLAIN, 21));
        startButton.setBounds(323, 435, 210, 55);
        contentPane.add(startButton);

        JLabel lblX = new JLabel("X");
        lblX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0)
                    View.this.dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblX.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblX.setForeground(Color.WHITE);
            }
        });
        lblX.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblX.setBounds(552, 5, 19, 23);
        contentPane.add(lblX);

        queuesTxt = new JTextField();
        queuesTxt.setFont(new Font("Arial", Font.PLAIN, 17));
        queuesTxt.setColumns(10);
        queuesTxt.setBounds(278, 211, 283, 32);
        contentPane.add(queuesTxt);

        JLabel policyLbl = new JLabel("Policy SHORTEST_TIME");
        policyLbl.setHorizontalAlignment(SwingConstants.CENTER);
        policyLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        policyLbl.setBounds(10, 375, 551, 37);
        contentPane.add(policyLbl);

        simIntervalTxt = new JTextField();
        simIntervalTxt.setFont(new Font("Arial", Font.PLAIN, 17));
        simIntervalTxt.setColumns(10);
        simIntervalTxt.setBounds(278, 264, 283, 32);
        contentPane.add(simIntervalTxt);

        JLabel queuesLbl = new JLabel("No. Queues");
        queuesLbl.setHorizontalAlignment(SwingConstants.LEFT);
        queuesLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        queuesLbl.setBounds(10, 212, 179, 25);
        contentPane.add(queuesLbl);

        serviceTimeTxt = new JTextField();
        serviceTimeTxt.setFont(new Font("Arial", Font.PLAIN, 17));
        serviceTimeTxt.setColumns(10);
        serviceTimeTxt.setBounds(278, 316, 283, 32);
        contentPane.add(serviceTimeTxt);

        validateButton = new JButton("Validate");
        validateButton.setForeground(Color.GREEN);
        validateButton.setFont(new Font("Arial", Font.PLAIN, 21));
        validateButton.setBounds(41, 435, 210, 55);
        contentPane.add(validateButton);

        JLabel simIntervalLbl = new JLabel("Simulation interval");
        simIntervalLbl.setHorizontalAlignment(SwingConstants.LEFT);
        simIntervalLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        simIntervalLbl.setBounds(10, 266, 258, 23);
        contentPane.add(simIntervalLbl);

        JLabel serviceTimeLbl = new JLabel("Service Time(min,max)");
        serviceTimeLbl.setHorizontalAlignment(SwingConstants.LEFT);
        serviceTimeLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        serviceTimeLbl.setBounds(10, 316, 258, 23);
        contentPane.add(serviceTimeLbl);


        this.setVisible(true);
    }

    public String getArrivalTimeTxt() {
        return arrivalTimeTxt.getText();
    }

    public int getIntegerClientsTxt() {
        return Integer.parseInt(clientsTxt.getText());
    }

    public String getStringClientsTxt() {
        return clientsTxt.getText();
    }

    public int getIntegerQueuesTxt() {
        return Integer.parseInt(queuesTxt.getText());
    }

    public String getStringQueuesTxt() {
        return queuesTxt.getText();
    }

    public int getIntegerSimIntervalTxt() {
        return Integer.parseInt(simIntervalTxt.getText());
    } // 60 sec,max time

    public String getStringSimIntervalTxt() {
        return simIntervalTxt.getText();
    }

    public String getServiceTimeTxt() {
        return serviceTimeTxt.getText();
    }

    public void validateButtonActionListener(ActionListener actionListener){
        validateButton.addActionListener(actionListener);
    }

    public void startButtonActionListener(ActionListener actionListener){
        startButton.addActionListener(actionListener);
    }
}
