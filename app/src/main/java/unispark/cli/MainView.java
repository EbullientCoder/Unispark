package unispark.cli;




import java.io.IOException;


import unispark.cli.clicontroller.CLI;

public class MainView {

    public static void main(String[] args) throws IOException {


        //Console View
        CLI cli = new CLI();
        cli.start();

    }
}
