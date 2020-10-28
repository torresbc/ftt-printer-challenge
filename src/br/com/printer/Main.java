package br.com.printer;

import br.com.printer.clients.ManageClient;
import br.com.printer.print.PrinterManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ManageClient server = new ManageClient();
        server.start();

        PrinterManager.getInstance().activate();

        Scanner scan = new Scanner(System.in);
        try {
            boolean exit = false;
            do{
                System.out.println("Bem vindo(a) ao sistema de gerenciamento de impressão!");
                System.out.println("1 - Listar clients");
                System.out.println("2 - Sair");
                int opc = scan.nextInt();
                switch (opc){
                    case 1:
                        server.listClients();
                    case 2:
                        exit = true;
                        break;
                }
            }while (!exit);
        } finally {
            scan.close();
            server.finishThreadClient();
            PrinterManager.getInstance().deactivate();
        }






    }
}
