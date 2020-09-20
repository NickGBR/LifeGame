package game;

import game.constants.StringConst;
import game.exceptions.IncorrectFieldException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface {

    private int idiotCoefficient  = 0;

    private Game game;
    private Display display;
    private Field field;

    public ConsoleInterface(Game game) {
        this.game = game;
    }

    public ConsoleInterface() throws IOException, IncorrectFieldException {
        setSettings();
    }

    public void setSettings() throws IOException, IncorrectFieldException {


        boolean check = false;
        String PATH;
        int iteration = 0;
        boolean fileCheck = false;
        ArrayList<String[]> fieldSlices = new ArrayList<>(); // For storing field lines.

        System.out.println("Welcome to the game of life, time of being god is coming,");
        System.out.println();
        System.out.println("Here is the rules:");
        System.out.println("1) Game setting field.txt file is located in \"src/game/resources\" folder.");
        System.out.println("2) Use \"1\" for alive cells and \"0\" for empty space. Split numbers by space");
        System.out.println("3) Field should be rectangle.");
        System.out.println("Here is example");
        System.out.println();
        System.out.println("1 0 0 1 0 1 0");
        System.out.println("1 0 0 0 0 0 0");
        System.out.println("1 0 0 1 1 1 1");
        System.out.println();
        System.out.println("You can use your own file, by giving you file location further");
        System.out.println();
        System.out.println("First of all, let me know where is game world setting file or");
        System.out.println("input \"1\" key, for using \"field.txt file, inside game's directory.\"");

        Scanner sc = new Scanner(System.in);
        BufferedReader fieldStatusReader = null;
        int recurses = 2;

            while (!fileCheck) {
                try {
                    PATH = sc.next();
                    if(PATH.equals("1")) PATH = "src/game/resources/field.txt";
                    fileCheck = true;
                    fieldStatusReader = new BufferedReader(new InputStreamReader(new FileInputStream(PATH)));
                } catch (FileNotFoundException e) {
                    System.out.println("This file doesn't exist, try again");
                    idiotCoefficient++;
                    userMistakesExitMessage(idiotCoefficient);
                    fileCheck = false;

                }
            }


        while(!check) {
            check = true;
            try{
                System.out.println("How many steps you want this world to live?");
                iteration = Integer.parseInt(sc.next());}
            catch (NumberFormatException e){
                idiotCoefficient++;
                userMistakesExitMessage(idiotCoefficient);
                System. out. print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Oh no, I need an Integer value my friend!");
                check = false;
            }
        }



        // Read file data line by line
        while(fieldStatusReader.ready()){
            String line = fieldStatusReader.readLine();
            fieldSlices.add(line.split(" "));
        }

        // Field properties checking.
        if(fieldSlices.isEmpty()){
            try{
            throw new IncorrectFieldException("Incorrect field format");
            }
            catch (IncorrectFieldException e){
                System.out.println("Field is empty, create new field and try again!");
            }
        }

        String[] linesChecker = fieldSlices.get(0);

        for(String[]slice : fieldSlices){
            if(linesChecker.length != slice.length){
                userMistakesExitMessage(idiotCoefficient);
                try{
                throw new IncorrectFieldException("Incorrect field, there are not enough elements");
                }
                catch (IncorrectFieldException e){
                    System.out.println("Field isn't rectangle, create new field and try again!");
                    System.exit(idiotCoefficient);
                }
            }
        }

        for(String[] slice : fieldSlices) {
            for (int i = 0; i < slice.length; i++) {
                if (!slice[i].equals("1")) {
                    if (!slice[i].equals("0")) {
                        try {
                            throw new IncorrectFieldException("Use \"1\" for alive cells and \"0\" for empty space. Split numbers by space");
                        }
                        catch (IncorrectFieldException e){
                            System.out.println("Create new field with \"1\" and \"0\" divided by space, and try again");
                            System.exit(idiotCoefficient);
                        }
                    }
                }
            }
        }

            //Creating necessary class objects
            this.field = new Field(fieldSlices.get(0).length,fieldSlices.size()); //Keeps field information.
            this.display = new Display(field); // Shows field in console output.
            this.game = new Game(display,iteration); // Keeps rules.

            int y = 0;
            for(String[] slice : fieldSlices){
                for(int i = 0; i < slice.length; i++){
                    if(slice[i].equals("1")){
                        display.addCell(i,y,display.getResult());
                    }
                }
                y++;
            }
        System.out.println();
        System.out.println("This is your field:");
        System.out.println();
        display.show(display.getResult());



    }

    private void userMistakesExitMessage(int idiotCoefficient){
        if(idiotCoefficient == 5){
            System.out.println(StringConst.tooMuchMistakes[(int)(Math.random()*StringConst.tooMuchMistakes.length)]);
            System.exit(idiotCoefficient);
        }
    }

}
