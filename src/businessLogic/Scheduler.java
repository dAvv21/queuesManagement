package businessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private SelectionPolicy selectionPolicy;
    private int maxNoServers;


    public Scheduler(int nrOfServers, int nrOfTasks,SelectionPolicy selectionPolicy) {
        this.servers = new ArrayList<>(nrOfServers);
        ArrayList<Thread> threads = new ArrayList<>(nrOfTasks);
        this.selectionPolicy = selectionPolicy;
        this.maxNoServers = nrOfServers;


        for (int i = 0; i < nrOfServers; i++) {
            this.servers.add(new Server(i, this.maxNoServers)); //create server object
            threads.add(new Thread(this.servers.get(i))); //create thdread with the object
            threads.get(i).start();

        }
    }



    public int minTimeQueue() {
        int initialWaitingTime = servers.get(0).getWaitingPeriod().get();

        for (int i = 0; i < this.maxNoServers; i++) {
            if (servers.get(i).getWaitingPeriod().get() < initialWaitingTime) { //vreau sa gasesc cel mai mic waitingTime
                initialWaitingTime = servers.get(i).getWaitingPeriod().get();
            }

        }
        return initialWaitingTime;
    }


    public void dispatchTask(Task t) throws InterruptedException {

        int minWaitingTime = minTimeQueue();

        for(Server server : servers){
            if(server.getWaitingPeriod().get() == minWaitingTime){
                server.setTotalWaitingTime(minWaitingTime);
                server.addTask(t);
                break; //sa nu adaug acelasi client in mai multe cozi
            }

        }
    }

    public void killTreads(){

        for (Server server : this.servers) {
            server.setOpen(false);
        }
    }

    public List<Server> getServers() {
        return servers;
    }



}
