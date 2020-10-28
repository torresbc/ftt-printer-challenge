package br.com.printer.clients;

import br.com.printer.Message;
import br.com.printer.print.PrinterManager;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread {
    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private Socket clientSocket;

    private StateMachineMessage stateMachine = new StateMachineMessage();

    @Override
    public void run() {
        try{
            stateMachine.start();
            System.out.println(clientSocket.hashCode() +
                    ": conexão estabelecida");
            InputStream stream = clientSocket.getInputStream();
            try{
                int readBytes = 0;
                do{
                    byte[] data = new byte[1024];
                    readBytes = stream.read(data);
                    if(readBytes>0){
                        List<Message> msgs = stateMachine.normalizeData(data, readBytes);
                        resolveMessages(msgs);
                    }
                } while (readBytes!=-1);
            }
            finally {
                if(stream !=null){
                    stream.close();
                }
                if(clientSocket.isConnected()){
                    clientSocket.close();
                }
            }
        }
        catch (IOException ex){
            if(ex.getMessage().equals("Socket Closed")){
                System.out.println(clientSocket.hashCode() + ": Conexão cliente encerrada");
            }
            else{
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex );
            }
        }
    }

    private void resolveMessages(List<Message> messages){
        for (Message m:
             messages) {
            switch (m.getOpCode()){
                case "P":
                    //PrinterManager
                    break;
                case "C":

                    break;
                case "S":

                    break;
                case "T":
                    System.out.println(clientSocket.hashCode() + ": Status:");
                    break;
            }
        }
    }


    public void finish() throws IOException {
        if(clientSocket.isConnected()){
            clientSocket.close();
        }
    }

    public void list(){
        if(clientSocket.isConnected() && !clientSocket.isClosed()){
            System.out.println(clientSocket.hashCode() + ": Conexão estabelecida");
        }
        else{
            System.out.println(clientSocket.hashCode() + ": Conexão encerrada");
        }
    }
}
