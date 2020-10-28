package br.com.printer.print;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrinterManager {
    ConcurrentLinkedQueue<String> printQueue;

    private static PrinterManager instance;

    private PrinterManager(){
        printQueue = new ConcurrentLinkedQueue<>();
    }

    public static PrinterManager getInstance(){
        if(instance == null){
            instance = new PrinterManager();
        }
        return instance;
    }



    List<ThreadPrintManage> threads;

    public void addMsgPrint(String msgAudit){
        printQueue.add(msgAudit);
    }

    String removeMsgPrint(){
        return printQueue.poll();
    }

    public void activate(){
        if(threads == null){
            threads = new LinkedList<>();
            for (int i = 0; i < 5; i++) {
                ThreadPrintManage thread = new ThreadPrintManage();
                thread.setName("Thead " + (i + 1));
                thread.start();
                threads.add(thread);
            }
        }

    }

    public void deactivate(){
        if(threads != null){
            for (ThreadPrintManage thread:
                 threads) {
                thread.setStatus(false);
                try{
                    thread.join(3000);
                }
                catch (InterruptedException ex){
                    Logger.getLogger(PrinterManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(thread.isAlive()){
                    thread.interrupt();
                }
            }

        }
    }

}
