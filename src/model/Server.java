package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger waitingPeriod;
    private final int id;
    private boolean open = true;
    private final AtomicInteger totalWaitingTime;

    public Server(int id, int maxLoad) {
        this.id = id;
        this.tasks = new ArrayBlockingQueue(maxLoad);
        this.waitingPeriod = new AtomicInteger(0);
        this.totalWaitingTime = new AtomicInteger(0);
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }


    public void run(){
        while (this.open) { //cat timp serverul e deschis,coada
            while (tasks.peek() != null) {   //cat timp are clienti de procesat
                try {
                    assert this.tasks.peek() != null;  //verific sa nu fie null task.peek()
                    Thread.sleep(1000L*this.tasks.peek().getServiceTime()); //adormim thread-ul pentru o secunda
                    assert tasks.peek() != null;
                    waitingPeriod.addAndGet(-tasks.peek().getServiceTime());
                    assert tasks.peek() != null;
                    this.tasks.peek().setServiceTime(0);

                }catch (Exception ignored){}
            }
        }
    }

    private String print(){
        String result;
        if(this.getWaitingPeriod().get() != 0 && this.tasks.peek() != null && this.tasks.peek().getServiceTime() != 0){
            result = this.tasks.toString();
        }else{
            result = "closed";
        }
        return result;
    }
    public String toString(){
        return print();
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getId() {
        return id;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setTotalWaitingTime(int totalWaitingTime) {
        this.totalWaitingTime.addAndGet(totalWaitingTime);
    }

    public int getTotalWaitingTime() {
        return totalWaitingTime.get();
    }
}
