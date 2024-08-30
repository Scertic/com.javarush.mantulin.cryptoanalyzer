package org.example;


public class MainApp {
    public static void main(String[] args) {
        if (args.length != 0) {
            ConsoleApp consoleApp = new ConsoleApp(args);
            consoleApp.run();
        }
        System.out.println("End MainApp");
    }
}
