package br.com.printer.clients;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;

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
        stateMachine.
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
