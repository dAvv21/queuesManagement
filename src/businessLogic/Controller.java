package businessLogic;

import view.OutPutView;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private final View view;
    private final OutPutView viewOut;
    private SimulationManager simulationManager;


    public Controller(View view, OutPutView viewOut) {
        this.view = view;
        this.viewOut = viewOut;
        this.view.validateButtonActionListener(new ValidateActionListerner());
        this.view.startButtonActionListener(new StartActionListener());
    }

    class ValidateActionListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //verificam fiecare camp din View sa aiba introduce datele corect
            String arrivalTime = view.getArrivalTimeTxt();
            String noClients = view.getStringClientsTxt();
            String noQueues = view.getStringQueuesTxt();
            String maxTime = view.getStringSimIntervalTxt();
            String serviceTime = view.getServiceTimeTxt();


            String intervalFormat = "[0-9]+(, [0-9]+)+";
            Pattern p = Pattern.compile(intervalFormat);
            Matcher m = p.matcher(arrivalTime);
            boolean condition = m.matches();
            if (!condition)
                JOptionPane.showMessageDialog(null, "Please enter a string that follows the following pattern : %d, %d !", "Error.", JOptionPane.INFORMATION_MESSAGE);


            String numberFormat = "[0-9]+";
            Pattern p2 = Pattern.compile(numberFormat);
            Matcher m2 = p2.matcher(noClients);
            boolean condition2 = m2.matches();
            if (!condition2)
                JOptionPane.showMessageDialog(null, "Please enter a string that follows the following pattern : %d%d%d or %d%d or %d !", "Error.", JOptionPane.INFORMATION_MESSAGE);

            Pattern p3 = Pattern.compile(numberFormat);
            Matcher m3 = p3.matcher(noQueues);
            boolean condition3 = m3.matches();
            if (!condition3)
                JOptionPane.showMessageDialog(null, "Please enter a string that follows the following pattern : %d%d%d or %d%d or %d !", "Error.", JOptionPane.INFORMATION_MESSAGE);

            Pattern p4 = Pattern.compile(numberFormat);
            Matcher m4 = p4.matcher(maxTime);
            boolean condition4 = m4.matches();
            if (!condition4)
                JOptionPane.showMessageDialog(null, "Please enter a string that follows the following pattern : %d%d%d or %d%d or %d !", "Error.", JOptionPane.INFORMATION_MESSAGE);

            Pattern p1 = Pattern.compile(intervalFormat);
            Matcher m1 = p1.matcher(serviceTime);
            boolean condition1 = m1.matches();
            if (!condition1)
                JOptionPane.showMessageDialog(null, "Please enter a string that follows the following pattern : %d, %d !", "Error.", JOptionPane.INFORMATION_MESSAGE);

            if (condition && condition1 && condition2 && condition3 && condition4) {
                JOptionPane.showMessageDialog(null, "All inputs are correct! Press START button!", "Valid.", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class StartActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String interval = view.getArrivalTimeTxt();
            String[] stringNumbers = interval.split(",\\s+");
            int[] intNumbers = new int[stringNumbers.length];
            for (int i = 0; i < stringNumbers.length; i++) {
                intNumbers[i] = Integer.parseInt(stringNumbers[i]);
            }
            int minArrivalTime = intNumbers[0];
            int maxArrivalTime = intNumbers[1];
            int nOfClients = view.getIntegerClientsTxt();
            int nOfQueues = view.getIntegerQueuesTxt();
            int timeLimit = view.getIntegerSimIntervalTxt();

            String interval2 = view.getServiceTimeTxt();
            String[] stringNumbers2 = interval2.split(",\\s+");
            int[] intNumbers2 = new int[stringNumbers2.length];
            for (int i = 0; i < stringNumbers2.length; i++) {
                intNumbers2[i] = Integer.parseInt(stringNumbers2[i]);
            }
            int minServiceTime = intNumbers2[0];
            int maxServiceTime = intNumbers2[1];

            String policy = "";
            if (minServiceTime < maxServiceTime && minArrivalTime < maxArrivalTime) {
                simulationManager = new SimulationManager(viewOut, minArrivalTime, maxArrivalTime, nOfClients, nOfQueues, timeLimit, minServiceTime, maxServiceTime,policy);
                viewOut.setVisible(true);
                Thread thread = new Thread(simulationManager);
                thread.start();
            }

        }
    }
}
