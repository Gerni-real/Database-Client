/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.stfleet.core;

import de.stfleet.GUI.Login;
import de.stfleet.GUI.MainInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public static BufferedReader in;
    public static PrintWriter out;
    private static Login loginWindow = new Login();
    private static MainInterface MainInterface = new MainInterface();
    public static Socket connection;
    public static String username;
    public static String password;
    public static String MACAdress;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                loginWindow.setVisible(false);
                MainInterface.setVisible(true);
            }
        });
        if (connectToServer()) {
            loginWindow.changeVariable("ConnectionLabel", "Verbunden!");
            loginWindow.changeVariable("LoginButton", "true");
            loginWindow.changeVariable("PasswordTextField", "true");
            loginWindow.changeVariable("UsernameTextField", "true");
        } else {
            loginWindow.changeVariable("ConnectionLabel", "Nicht verbunden!");
        }
    }

    public static boolean connectToServer() {
        loginWindow.changeVariable("ConnectionLabel", "Verbinde...");
        String serverAddress = "localhost";
        try {
            connection = new Socket(serverAddress, 4571);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream(), true);
            
            if (in.readLine().equals("Connected")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
