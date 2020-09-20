package game;

import game.constants.StringConst;
import game.exceptions.IncorrectFieldException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface {

    private int idiotCoefficient  = 0;

    BufferedWriter finalStatusWriter = null;
    private Game game;
    private Display display;
    private Field field;
    private File fieldFile;
    private File finalResultFile;

    public ConsoleInterface() throws IOException, InterruptedException {
        File dir = new File("resources");
        dir.mkdir();
        fieldFile = new File ("resources","field.txt");
        File testFile = new File ("resources","test.txt");
        testFile.createNewFile();
        fillTestFile(testFile,17,20,30);
        finalResultFile = new File ("resources","finalResult.txt");
        fieldFile.createNewFile();
        finalResultFile.createNewFile();

        setSettings();
        startGame();
    }

    private void setSettings() throws IOException {

        finalStatusWriter = new BufferedWriter(new FileWriter(finalResultFile));
        BufferedReader fieldStatusReader = null;
        boolean check = false;
        String SETTING_FILE_PATH = "";
        String RESULT_FILE_PATH;
        int iteration = 0;
        boolean fileCheck = false;
        ArrayList<String[]> fieldSlices = new ArrayList<>(); // For storing field lines.

        System.out.println("Welcome to the game of life, time of being god is coming,");
        System.out.println();
        System.out.println("Here is the rules:");
        System.out.println("1) Game setting field.txt file is located in \"resources\" folder.");
        System.out.println("2) Use \"1\" for alive cells and \"0\" for empty space. Split numbers by space");
        System.out.println("3) Field should be rectangle.");
        System.out.println("Here is an example" + "\n");
        System.out.println("1 0 0 1 0 1 0");
        System.out.println("1 0 0 0 0 0 0");
        System.out.println("1 0 0 1 1 1 1");
        System.out.println();
        System.out.println("You can use your own file, by giving you file location further" + "\n");
        System.out.println("First of all, let me know where is game world setting file or");
        System.out.println("input \"1\" key, for using \"field.txt file, inside game's directory.\"");
        System.out.println("Also I can generate random game field for you, just input \"G\" ..." + "\n");

        Scanner sc = new Scanner(System.in);

        while (!fileCheck) {
            try {
                SETTING_FILE_PATH = sc.next();

                if (SETTING_FILE_PATH.equals("G") || SETTING_FILE_PATH.equals("g")) break;
                if (SETTING_FILE_PATH.equals("1")) SETTING_FILE_PATH = fieldFile.getPath();
                fileCheck = true;
                fieldStatusReader = new BufferedReader(new InputStreamReader(new FileInputStream(SETTING_FILE_PATH)));


            } catch (FileNotFoundException e) {
                System.out.println("This file doesn't exist, try again" + "\n");
                idiotCoefficient++;
                userMistakesExitMessage(idiotCoefficient);
                fileCheck = false;

            }
        }
        if (SETTING_FILE_PATH.equals("G") || SETTING_FILE_PATH.equals("g")) {

            int worldOccupancyRate = 0;
            check = false;
            int x = 0;
            int y = 0;

            setResultOutputFile(sc);

            while (!check) {
                check = true;
                try {
                    System.out.println("Input world width, from 5 to 70 cells" + "\n");
                    y = Integer.parseInt(sc.next());

                } catch (NumberFormatException e) {
                    idiotCoefficient++;
                    userMistakesExitMessage(idiotCoefficient);
                    if(idiotCoefficient<3) {
                        System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                    }
                    else System.out.println("I TOLD INTEGER !!!" + "\n");
                    System.out.println();
                    check = false;
                }

                if(y<5 || y>70){
                    idiotCoefficient++;
                    System.out.println("I need value from 5 to 70!" + "\n");
                    userMistakesExitMessage(idiotCoefficient);
                    check = false;
                }
            }

            idiotCoefficient = 0;
            check = false;

            while (!check) {
                check = true;
                try {
                    System.out.println("Input world length, from 5 to 70 cells." + "\n");
                    System.out.println();
                    x = Integer.parseInt(sc.next());

                } catch (NumberFormatException e) {
                    idiotCoefficient++;
                    userMistakesExitMessage(idiotCoefficient);
                    if(idiotCoefficient<3) {
                        System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                    }

                    else System.out.println("I TOLD INTEGER !!!" + "\n");
                    check = false;
                }

                if(x<5 || x>70){
                    idiotCoefficient++;
                    System.out.println("I need value from 5 to 70!" + "\n");
                    userMistakesExitMessage(idiotCoefficient);
                    check = false;
                }
            }

            check = false;

            while (!check) {
                check = true;
                try {
                    System.out.println("How many steps you want this world to live?" + "\n");
                    iteration = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    idiotCoefficient++;
                    userMistakesExitMessage(idiotCoefficient);
                    System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                    check = false;
                }
            }

            check = false;

            while (!check) {
                check = true;
                try {
                    System.out.println("Input \"world occupancy rate\", from 1 to 10 , the more rate, the more cells on the world's start!" + "\n");
                    worldOccupancyRate = Integer.parseInt(sc.next());

                } catch (NumberFormatException e) {
                    idiotCoefficient++;
                    userMistakesExitMessage(idiotCoefficient);
                    if(idiotCoefficient<3) {
                        System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                    }
                    else System.out.println("I TOLD INTEGER !!!" + "\n");
                    check = false;
                }
                if(worldOccupancyRate < 0 || worldOccupancyRate > 10){
                    System.out.println("I need value from 0 to 10!" + "\n");
                    userMistakesExitMessage(idiotCoefficient);
                    check = false;
                }
            }

            this.field = new Field(x,y);
            this.display = new Display(field);
            this.game = new Game(display,iteration);

            display.generateCells(worldOccupancyRate);


        } else {
            setResultOutputFile(sc);

            while (!check) {
                check = true;
                try {
                    System.out.println("How many steps you want this world to live?" + "\n");
                    iteration = Integer.parseInt(sc.next());
                } catch (NumberFormatException e) {
                    idiotCoefficient++;
                    userMistakesExitMessage(idiotCoefficient);
                    System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                    check = false;
                }
            }


            // Read file data line by line
            while (fieldStatusReader.ready()) {
                String line = fieldStatusReader.readLine();
                fieldSlices.add(line.split(" "));
            }


            // Field properties checking.
            if (fieldSlices.isEmpty()) {
                try {
                    throw new IncorrectFieldException("Incorrect field format" + "\n");
                } catch (IncorrectFieldException e) {
                    System.out.println("Field is empty, create new field and try again!" + "\n");
                }
            }

            String[] linesChecker = fieldSlices.get(0);

            for (String[] slice : fieldSlices) {
                if (linesChecker.length != slice.length) {
                    userMistakesExitMessage(idiotCoefficient);
                    try {
                        throw new IncorrectFieldException("Incorrect field, there are not enough elements" + "\n");
                    } catch (IncorrectFieldException e) {
                        System.out.println("Field isn't rectangle, create new field and try again!" + "\n");
                        System.exit(idiotCoefficient);
                    }
                }
            }

            for (String[] slice : fieldSlices) {
                for (int i = 0; i < slice.length; i++) {
                    if (!slice[i].equals("1")) {
                        if (!slice[i].equals("0")) {
                            try {
                                throw new IncorrectFieldException("Use \"1\" for alive cells and \"0\" for empty space. Split numbers by space" + "\n");
                            } catch (IncorrectFieldException e) {
                                System.out.println("Create new field with \"1\" and \"0\" divided by space, and try again" + "\n");
                                System.exit(idiotCoefficient);
                            }
                        }
                    }
                }
            }

            //Creating necessary class objects
            this.field = new Field(fieldSlices.get(0).length, fieldSlices.size()); //Keeps field information.
            this.display = new Display(field); // Shows field in console output.
            this.game = new Game(display, iteration); // Keeps rules.

            int y = 0;
            for (String[] slice : fieldSlices) {
                for (int i = 0; i < slice.length; i++) {
                    if (slice[i].equals("1")) {
                        display.addCell(i, y, display.getResult());
                    }
                }
                y++;
            }
            fieldStatusReader.close();
        }

        System.out.println("\n" + "This is your field:" + "\n");
        display.show(display.getResult());
        System.out.println();
    }

    private void setResultOutputFile(Scanner sc) throws IOException {
        boolean fileCheck;
        String RESULT_FILE_PATH;
        System.out.println("Input file name, for result saving or" + "\n");
        System.out.println("input \"1\" key, for using \"finalField.txt file, inside game's directory.\"" + "\n");
        System.out.println();

        fileCheck = false;

        while (!fileCheck) {
            try{
                RESULT_FILE_PATH = sc.next();
                if (!RESULT_FILE_PATH.equals("1")) finalStatusWriter = new BufferedWriter(new FileWriter(RESULT_FILE_PATH));
                fileCheck = true;

            } catch (FileNotFoundException e) {
                System.out.println("This file doesn't exist, try again" + "\n");
                idiotCoefficient++;
                userMistakesExitMessage(idiotCoefficient);
                fileCheck = false;
            }
        }
    }


    private void startGame () throws InterruptedException, IOException {
        boolean check = false;
        idiotCoefficient = 0;
        System.out.println("It's time to play");
        System.out.println("here you can see available commands:");
        System.out.println("1: start the game.");
        System.out.println("2: start with multithreading mode.");
        System.out.println("3: get result without animation.");
        System.out.println("4: get multithreading mode result without animation.");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        while (!check) {
            try {
                String command = sc.next();

                switch (Integer.parseInt(command)) {
                    case 1:
                        game.play();
                        check = true;
                        break;
                    case 2:
                        check = true;
                        game.playMultithreading();
                        break;
                    case 3:
                        check = true;
                        break;
                    case 4:
                        check = true;
                        break;
                    default:
                        System.out.println("There's no command " + command);
                        check = false;
                        break;
                }
            } catch (NumberFormatException e) {
                idiotCoefficient++;
                userMistakesExitMessage(idiotCoefficient);
                if (idiotCoefficient < 3) {
                    System.out.println("Oh no, I need an Integer value my friend!");
                } else System.out.println("I TOLD INTEGER !!!");
                check = false;
            }
        }
        finalStatusWriter.write(display.getResultString(display.getResult()));
        finalStatusWriter.flush();
        finalStatusWriter.close();
        System.out.println("\n" + "Thanks for the game.");
        System.out.println("Result added to result file."  + "\n");
    }

    private void userMistakesExitMessage ( int idiotCoefficient) throws IOException {
        if (idiotCoefficient == 5) {
            System.out.println(StringConst.tooMuchMistakes[(int) (Math.random() * StringConst.tooMuchMistakes.length)]);
            System.exit(idiotCoefficient);
        }

    }

    private void fillTestFile(File test, int displacementCoefficient, int width, int length) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(test));

        int[] testKey = new int[]{0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0};
        int[] testKey2 = new int[]{30, 22, 11, 40, 31, 55, 7, 6, 1, 4, 2};
        int i = 0;

        System.out.println(5 % 2);
        int a = displacementCoefficient % testKey2.length;
        String res = "";
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                if (i > testKey.length - 1) {
                    if(a >= testKey2.length){
                        a = displacementCoefficient % a;
                    }
                    i = testKey2[a];
                    a++;
                }
                if (testKey[i] == 0) {
                    res = res + " . ";
                } else res = res + " O ";
                i++;
            }
            res = res + "\n";
        }
        writer.write(res);
        writer.flush();
        writer.close();
    }
}



