package businessLogic;

import model.Server;
import model.Task;
import view.OutPutView;
import view.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationManager implements Runnable {
    private int minArrivalTime;
    private int maxArrivalTime;
    private int nOfClients;
    private int nOfQueues;
    private int timeLimit;
    private int minServiceTime;
    private int maxServiceTime;

    private final OutPutView outView;
    private final Scheduler scheduler;
    private List<Task> taskList = new LinkedList<>();


    float averageServiceTime = 0;
    int peakH;
    int nrOfClients = 0;
    int maxQueue = 0;
    int averageWaitingTime = 0;
    int initialNoClients;
    String txtForFile;

    public SimulationManager(OutPutView outView, int minArrivalTime, int maxArrivalTime, int nOfClients, int nOfQueues, int timeLimit, int minServiceTime, int maxServiceTime, String policy) {
        this.outView = outView;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.nOfClients = nOfClients;
        this.nOfQueues = nOfQueues;
        this.timeLimit = timeLimit;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.initialNoClients = nOfClients;

        SelectionPolicy selectionPolicy = null;

        if (policy.equals("SHORTEST_QUEUE")) {
            selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
        } else if (policy.equals("SHORTEST_TIME")) {
            selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        }
        scheduler = new Scheduler(nOfQueues,nOfClients,selectionPolicy);
        this.generateNRandomTasks();
    }


    public void generateNRandomTasks() {

        this.taskList = new ArrayList(nOfClients);

        for (int i = 0; i < this.nOfClients; i++) {
            int var = this.randomizedServiceTime();
            Task task = new Task(i, this.randomizedArrivalTime(), var);
            averageServiceTime += var;
            this.taskList.add(task);
        }
        averageServiceTime = averageServiceTime / initialNoClients;
        SortTask sortTask = new SortTask();
        taskList.sort(sortTask);

    }
    //genereaza un int random intre minArrivalTime si maxArrivalTime
    public int randomizedArrivalTime() {
        int random = -1;
        random = (minArrivalTime) + (int) (Math.random() * (maxArrivalTime - minArrivalTime + 1));
        if (random == -1) {
            System.out.println("Error at randomArrivalTime!");
            return 55;
        } else
            return random;
    }

    //genereaza un int random intre minServiceTime si maxServiceTime
    public int randomizedServiceTime() {
        int random = -1;
        random = (minServiceTime) + (int) (Math.random() * (maxServiceTime - minServiceTime + 1));
        if (random == -1) {
            System.out.println("Error at randomArrivalTime.\n");
            return 55;
        } else
            return random;
    }

    @Override
    public void run() {
        int currentTime = 0;

        while (currentTime < timeLimit) {
            StringBuilder txt = new StringBuilder();
            int ctTime = currentTime;

            for (int i = 0; i < nOfClients; i++) {
                if(taskList.get(i).getArrivalTime() == ctTime) {

                    try {
                        scheduler.dispatchTask(taskList.get(i));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    taskList.remove(taskList.get(i));
                    nOfClients--;
                    i--;
                }
            }

            txt.append("Time:").append(currentTime).append("\n").append("Waiting clients: ").append(taskList).append(", ");

            for(Server server : scheduler.getServers()){
                if(!server.getTasks().isEmpty()) {
                    assert server.getTasks().peek() != null;
                    if (server.getTasks().peek().getServiceTime() == 0)
                        server.getTasks().remove();
                }
            }

            nrOfClients = 0;
            for (Server server : scheduler.getServers()) {
                txt.append("\nQueue ").append(server.getId()).append(": ");
                nrOfClients += server.getTasks().size();
                if (server.getTasks().isEmpty()) {
                    txt.append("closed\n");
                } else{
                   // BlockingQueue<Task> tasks = server.getTasks();
                    for (Task task : server.getTasks()) {
                        txt.append(task.toString()).append(" ");
                    }
                    txt.append("\n");

                }
            }

            if (nrOfClients > maxQueue) {
                maxQueue = nrOfClients;
                peakH = currentTime;
            }

            this.outView.setDisplayTextArea(txt);
            txtForFile = String.valueOf(txt);
            currentTime++;



            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(Server server : scheduler.getServers()){
            averageWaitingTime += server.getTotalWaitingTime();
        }

        scheduler.killTreads();

        outView.setAvgServiceTTxt(averageServiceTime);
        outView.setPeakHTxt(peakH);
        outView.setAvgWaitingTTxt((double) averageWaitingTime / initialNoClients);

        // https://www.w3schools.com/java/java_files_create.asp de ajutor
        File myFile = new File("output3.txt");
        try {
            if(myFile.createNewFile()){
                //System.out.println("File created : " + myFile.getName() + "in " + myFile.getAbsolutePath());
                FileWriter myWriter = new FileWriter("output.txt");
                myWriter.write(String.valueOf(txtForFile));
                myWriter.close();
                //System.out.println("Successfully wrote in the file");
            }else{
                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        View view = new View();
        OutPutView outView = new OutPutView();
        new Controller(view,outView);
    }

}

