package game;

import game.constants.StringConst;
import game.exceptions.IncorrectFieldException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleInterface {

    private int idiotCoefficient = 0;

    private BufferedWriter finalStatusWriter = null;
    private Game game;
    private Field field;
    private File fieldFile;
    private File finalResultFile;
    private File testFile;
    private File multiResultFile;
    private File singleResultFile;
    private String settingFilePath = "";

    public ConsoleInterface() throws IOException, InterruptedException {
        File dir = new File("resources");
        dir.mkdir();

        fieldFile = new File("resources", "field.txt");
        finalResultFile = new File("resources", "finalResult.txt");
        fieldFile.createNewFile();
        finalResultFile.createNewFile();

        setSettings();
        startGame();
    }

    private void setSettings() throws IOException {
        finalStatusWriter = new BufferedWriter(new FileWriter(finalResultFile));
        BufferedReader fieldStatusReader = null;
        boolean check = false;
        boolean fileCheck = false;
        int iteration = 0;

        System.out.println(StringConst.getIntroduction());
        System.out.print(StringConst.getRules());

        Scanner sc = new Scanner(System.in);

        while (!fileCheck) {
            try {
                settingFilePath = sc.nextLine();

                if (settingFilePath.toLowerCase().equals("g") ||
                        settingFilePath.toLowerCase().equals("test")) break;

                if (settingFilePath.equals("1")) settingFilePath
                        = fieldFile.getPath();

                fileCheck = true;
                fieldStatusReader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(settingFilePath)));


            } catch (FileNotFoundException e) {
                System.out.println("This file doesn't exist, try again" + "\n");
                idiotCoefficient++;
                userMistakesExitMessage(idiotCoefficient);
                fileCheck = false;

            }
        }
        if (settingFilePath.toLowerCase().equals("g") || settingFilePath.toLowerCase().equals("test")) {

            int worldOccupancyRate = 0;
            check = false;
            int x = 0;
            int y = 0;

            //if(settingFilePath.toLowerCase().equals("g")) setResultOutputFile(sc);

            while (!check) {
                check = true;
                try {
                    System.out.println("Input world width, from 5 to 70 cells" + "\n");
                    y = Integer.parseInt(sc.next());

                } catch (NumberFormatException e) {
                    idiotCoefficient++;
                    userMistakesExitMessage(idiotCoefficient);
                    if (idiotCoefficient < 3) {
                        System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                    } else System.out.println("I TOLD INTEGER !!!" + "\n");
                    check = false;
                }

                if (y < 5 || y > 70) {
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
                    if (idiotCoefficient < 3) {
                        System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                    } else System.out.println("I TOLD INTEGER !!!" + "\n");
                    check = false;
                }

                if (x < 5 || x > 70) {
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

            this.field = new Field(x, y);
            this.game = new Game(field, iteration);

            if(settingFilePath.toLowerCase().equals("g")) {
                while (!check) {
                    check = true;
                    try {
                        System.out.println("Input \"world occupancy rate\", from 1 to 50 , the more rate, the more cells on the world's start!" + "\n");
                        worldOccupancyRate = Integer.parseInt(sc.next());

                    } catch (NumberFormatException e) {
                        idiotCoefficient++;
                        userMistakesExitMessage(idiotCoefficient);
                        if (idiotCoefficient < 3) {
                            System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                        } else System.out.println("I TOLD INTEGER !!!" + "\n");
                        check = false;
                    }
                    if (worldOccupancyRate < 0 || worldOccupancyRate > 50) {
                        System.out.println("I need value from 0 to 50!" + "\n");
                        userMistakesExitMessage(idiotCoefficient);
                        check = false;
                    }
                }
                field.generateCells(worldOccupancyRate);
            }

            else if(settingFilePath.toLowerCase().equals("test")) {
                createTestFiles();
                while (!check) {
                    check = true;
                    try {
                        System.out.println("Input displacement coefficient for test file" + "\n");
                        int displacementCoefficient = Integer.parseInt(sc.next());
                        fillTestFile(testFile, displacementCoefficient,x,y);
                    } catch (NumberFormatException e) {
                        idiotCoefficient++;
                        userMistakesExitMessage(idiotCoefficient);
                        if (idiotCoefficient < 3) {
                            System.out.println("Oh no, I need an Integer value my friend!" + "\n");
                        } else System.out.println("I TOLD INTEGER !!!" + "\n");
                        check = false;
                    }
                }
                fieldStatusReader = new BufferedReader(new InputStreamReader(new FileInputStream(testFile)));

                //Creating necessary class objects
                field.setDeadCellBody(" . ");
                this.field = getDisplayFromFile(fieldStatusReader); //Keeps information about alive and dead cells.
                this.game = new Game(field, iteration); // Keeps rules.
                fieldStatusReader.close();

            }
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
            this.field = getDisplayFromFile(fieldStatusReader); //Keeps information about alive and dead cells
            this.game = new Game(field, iteration); // Keeps rules.


            fieldStatusReader.close();
        }

        System.out.println("\n" + "This is your field:" + "\n");
        field.show(field.getResultField());
        System.out.println();
    }

    private Field getDisplayFromFile(BufferedReader fieldStatusReader) throws IOException {

        ArrayList<String[]> fieldSlices = new ArrayList<>(); // For storing field lines. From file.


        while (fieldStatusReader.ready()) {
            String line = fieldStatusReader.readLine();
            line = line.trim();
            fieldSlices.add(line.split(" "));
        }


        // Field properties checking.
        if (fieldSlices.isEmpty()) {
            try {
                throw new IncorrectFieldException("Incorrect field format" + "\n");
            } catch (IncorrectFieldException e) {
                System.out.println("Field is empty, create new field and try again!" + "\n");
                System.exit(idiotCoefficient);
            }
        }

        // Width and length field checking.
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

        // Filling txt file checking.
        for (String[] slice : fieldSlices) {
            for (String aSlice : slice) {
                if (!aSlice.equals("1")) {
                    if (!aSlice.equals("0")) {
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

        Field field = new Field(fieldSlices.get(0).length, fieldSlices.size()); // Shows field in console output.

        // Filling cells array, this block fills array of game field.
        int y = 0;
        for (String[] slice : fieldSlices) {
            for (int i = 0; i < slice.length; i++) {
                if (slice[i].equals("1")) {
                    field.addCell(i, y, field.getResultField());
                }
            }
            y++;
        }
        return field;
    }

    private void setResultOutputFile(Scanner sc) throws IOException {
        boolean fileCheck;
        String RESULT_FILE_PATH;
        System.out.print("Input file name, for result saving or" + "\n");
        System.out.print("input \"1\" key, for using \"finalField.txt file, inside game's directory.\"" + "\n");

        fileCheck = false;

        while (!fileCheck) {
            try {
                RESULT_FILE_PATH = sc.next();
                if (!RESULT_FILE_PATH.equals("1"))
                    finalStatusWriter = new BufferedWriter(new FileWriter(RESULT_FILE_PATH));
                fileCheck = true;

            } catch (FileNotFoundException e) {
                System.out.println("This file doesn't exist, try again" + "\n");
                idiotCoefficient++;
                userMistakesExitMessage(idiotCoefficient);
                fileCheck = false;
            }
        }
    }

    private void startGame() throws InterruptedException, IOException {
        if (settingFilePath.toLowerCase().equals("test")) {

            game.setAnimated(false);
            int timeBefore = (int) System.currentTimeMillis();
            game.play();
            int timeAfter = (int) System.currentTimeMillis();
            writeTestResult(timeBefore, timeAfter, singleResultFile);
            System.out.println("single thread finished");
            System.out.println("this is singe result:\n" + field.getResultString());

            // field reloading
            field.reloadDisplay();
            System.out.println("reloaded fields:\n" +  field.getResultString()+ "\n\n" + field.getString(field.getResultField()));

            BufferedReader fieldStatusReader = new BufferedReader(new InputStreamReader(new FileInputStream(testFile)));
            field = getDisplayFromFile(fieldStatusReader); //Keeps information about alive and dead cells.
            game.setField(field);
            System.out.println("this is reloaded world from test file\n" + game.getField().getResultString());

            timeBefore = (int) System.currentTimeMillis();
            game.playMultithreading();
            timeAfter = (int) System.currentTimeMillis();
            writeTestResult(timeBefore, timeAfter, multiResultFile);

            System.out.println("Multi thread finished");
            field.setDeadCellBody("   ");

        } else {
            boolean check = false;
            idiotCoefficient = 0;
            System.out.println("It's time to play");
            System.out.println("here you can see available commands:");
            System.out.println("1: start the game.");
            System.out.println("2: start with multithreading mode.");
            System.out.println();

            Scanner sc = new Scanner(System.in);

            while (!check) {
                try {
                    String command = sc.next();

                    switch (Integer.parseInt(command)) {
                        case 1:
                            game.setAnimated(true);
                            game.play();
                            check = true;
                            break;
                        case 2:
                            check = true;
                            game.setAnimated(true);
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
                finalStatusWriter.write(field.getString(field.getResultField()));
                finalStatusWriter.flush();
                finalStatusWriter.close();
                System.out.println("\n" + "Thanks for the game.");
                System.out.println("Result added to result file." + "\n");
            }

        }
    }

    private void writeTestResult(int timeBefore, int timeAfter, File resultFile) throws IOException {
        BufferedWriter multiThreadFile = new BufferedWriter(new FileWriter(resultFile));
        multiThreadFile.write("Spent " + (timeAfter-timeBefore) + " millis\n\n");
        multiThreadFile.write(field.getResultString());
        multiThreadFile.flush();
        multiThreadFile.close();
    }

    private void userMistakesExitMessage(int idiotCoefficient) throws IOException {
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
        StringBuilder res = new StringBuilder();
        String line = "";
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                if (i > testKey.length - 1) {
                    if (a >= testKey2.length) {
                        a = displacementCoefficient % a;
                    }
                    i = testKey2[a];
                    a++;
                }
                if (testKey[i] == 0) {
                    line = line + " 0";
                } else line = line + " 1";
                i++;
            }
            res.append(line.trim());
            res.append("\n");
            line = "";
        }
        writer.write(res.toString());
        writer.flush();
        writer.close();
    }

    private void createTestFiles() throws IOException {
        testFile = new File("resources", "test.txt");

        multiResultFile = new File("resources", "multithreadedResult.txt");
        singleResultFile= new File("resources", "single-threadedResult.txt");
        testFile.createNewFile();
        multiResultFile.createNewFile();
        singleResultFile.createNewFile();
    }
}



