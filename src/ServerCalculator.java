package src;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerCalculator {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting server on port 3000");
        //Creates a server socket and listens to a specific port

        ServerSocket server = new ServerSocket(3000);

        while (true) {
            System.out.println("[Server] Waiting for incoming connection");
            Socket connector = server.accept();

            System.out.println("[Server] Got a connection!");

            //do something
            InputStream isServer = connector.getInputStream();
            ObjectInputStream oisServer = new ObjectInputStream(isServer);
            String expression = oisServer.readUTF();
            System.out.println("[Server]Calculating..");
            Double result = arithmeticOperation(expression);
            String resultStr = Double.toString(result);


            System.out.println("[Server] Computed " + result);
            System.out.println("[Server] Sending " + result + " back to client.");

            OutputStream osServer = connector.getOutputStream();
            ObjectOutputStream oosServer = new ObjectOutputStream(osServer);
            oosServer.writeUTF(result.toString());

            oosServer.flush();

            connector.close();
        }

    }

    public static Double arithmeticOperation(String expression){
        Double result;
        Double firstNumber = null;
        Double secondNumber = null;
        char operator = 0;
        String regex = "+-*/";
        // split string by no space
        int length = expression.length();
        for (int i = 0; i<length; i++){
            if (regex.contains(expression.substring(i,i+1))){
                operator = expression.charAt(i);
                firstNumber = Double.parseDouble(expression.substring(0,i));
                secondNumber = Double.parseDouble(expression.substring(i+1));
                break;
            }
        }

        switch (operator){
            case '+':
                return result = firstNumber + secondNumber;

            case '-':
                return result = firstNumber - secondNumber;

            case '*':
                return result = firstNumber * secondNumber;

            case '/':
                return result = firstNumber / secondNumber;

            default:
                return null;

        }
    }



}
