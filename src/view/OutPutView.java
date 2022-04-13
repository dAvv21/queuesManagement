package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class OutPutView extends JFrame {

    private final JPanel contentPane;
    private final JTextArea displayTextArea;
    private final JTextField avgServiceTTxt;
    private final JTextField peakHTxt;
    private final JTextField avgWaitingTTxt;


    public OutPutView() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setBounds(100, 100, 571, 519);
        contentPane = new JPanel();
        contentPane.setForeground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setLayout(null);

        JLabel titleLbl = new JLabel("Queues Management");
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        titleLbl.setFont(new Font("Arial", Font.PLAIN, 29));
        titleLbl.setBounds(10, 20, 537, 38);
        contentPane.add(titleLbl);

        JLabel lblX = new JLabel("X");
        lblX.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION) == 0)
                    OutPutView.this.dispose();
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

        displayTextArea = new JTextArea();
        displayTextArea.setLineWrap(true);
        displayTextArea.setBounds(22, 140, 525, 352);
        contentPane.add(displayTextArea);

        avgServiceTTxt = new JTextField();
        avgServiceTTxt.setBounds(49, 100, 76, 30);
        contentPane.add(avgServiceTTxt);
        avgServiceTTxt.setColumns(10);

        peakHTxt = new JTextField();
        peakHTxt.setColumns(10);
        peakHTxt.setBounds(233, 100, 76, 30);
        contentPane.add(peakHTxt);

        avgWaitingTTxt = new JTextField();
        avgWaitingTTxt.setColumns(10);
        avgWaitingTTxt.setBounds(415, 100, 76, 30);
        contentPane.add(avgWaitingTTxt);

        JLabel avgServiceTLblb = new JLabel("AvgServiceTime");
        avgServiceTLblb.setHorizontalAlignment(SwingConstants.CENTER);
        avgServiceTLblb.setFont(new Font("Arial", Font.PLAIN, 17));
        avgServiceTLblb.setBounds(22, 78, 126, 13);
        contentPane.add(avgServiceTLblb);

        JLabel peakHLbl = new JLabel("PeakHour");
        peakHLbl.setHorizontalAlignment(SwingConstants.CENTER);
        peakHLbl.setFont(new Font("Arial", Font.PLAIN, 17));
        peakHLbl.setBounds(209, 79, 126, 13);
        contentPane.add(peakHLbl);

        JLabel avgWaitingTLbl = new JLabel("AvgWaitingTime");
        avgWaitingTLbl.setHorizontalAlignment(SwingConstants.CENTER);
        avgWaitingTLbl.setFont(new Font("Arial", Font.PLAIN, 17));
        avgWaitingTLbl.setBounds(395, 77, 126, 13);
        contentPane.add(avgWaitingTLbl);


        this.setVisible(false);
    }


    public void setDisplayTextArea(StringBuilder displayTextArea) {
        this.displayTextArea.setText(String.valueOf(displayTextArea));
    }

    public void setAvgServiceTTxt(float avgServiceTTxt) {
        this.avgServiceTTxt.setText(String.valueOf(avgServiceTTxt));
    }

    public void setPeakHTxt(int peakHTxt) {
        this.peakHTxt.setText(String.valueOf(peakHTxt));
    }

    public void setAvgWaitingTTxt(double avgWaitingTTxt) {
        this.avgWaitingTTxt.setText(String.valueOf(avgWaitingTTxt));
    }
}
