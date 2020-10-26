package br.com.printer.clients;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ManageClient extends Thread {

    List<ClientThread> clientThreadList;

    public ManageClient(){
        clientThreadList = new ArrayList<>();
    }

    public ServerSocket server;

    private Boolean ativo;

    @Override
    public void run() {
        try{
            ativo = true;
            server = new ServerSocket(123);

            while (ativo){
                Socket socket = server.accept();
                addThreadClient(socket);
                Thread.sleep(10);
            }

            if(!server.isClosed()){
                server.close();
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void addThreadClient(Socket s){
        ClientThread client = new ClientThread();
        client.setClientSocket(s);
        clientThreadList.add(client);
        client.start();
    }

    public void finishThreadClient() throws IOException {
        for (ClientThread clientThread:
             clientThreadList) {
            try{
                clientThread.finish();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        ativo = false;
        server.close();
    }

    public void listClients(){
        for (ClientThread clientThread:
             clientThreadList) {
            clientThread.list();
        }
    }

}
