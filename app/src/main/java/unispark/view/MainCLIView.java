package unispark.view;




import java.io.IOException;


import unispark.controller.guicontroller.clicontroller.CLI;

public class MainCLIView {

    public static void main(String[] args) throws IOException {


        //Console View
        CLI cli = new CLI();
        cli.start();

    }
}
