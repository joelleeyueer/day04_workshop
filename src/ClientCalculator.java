package src;

import java.io.*;
import java.net.Socket;

public class ClientCalculator {

    public static void main(String[] args) throws IOException {


        while (true){
            Socket clientConnection = new Socket("127.0.0.2", 3000);
            System.out.println("Connected to server on localhost:3000");
            OutputStream osClient = clientConnection.getOutputStream();
            ObjectOutputStream oosClient = new ObjectOutputStream(osClient);
            Console cons = System.console();
            String clientExpression = cons.readLine("Type an expression!");
            oosClient.writeUTF(clientExpression);
            oosClient.flush();

            InputStream isClient = clientConnection.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(isClient);
            System.out.println("[Client] Received " + ois.readUTF() + " from server.");
        }


    }


}
