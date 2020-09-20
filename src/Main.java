import game.*;
import game.exceptions.IncorrectFieldException;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, IncorrectFieldException {
                        ConsoleInterface consoleInterface = new ConsoleInterface();






        //consoleInterface.start();

//        BufferedReader fieldStatusReader = new BufferedReader(new InputStreamReader(new FileInputStream("src/game/field.txt")));
//
//        ArrayList<String[]> fieldSlices = new ArrayList<>();
//
//        // Read file data line by line
//        while(fieldStatusReader.ready()){
//            String line = fieldStatusReader.readLine();
//            fieldSlices.add(line.split(" "));
//        }
//
//        // Read file data line by line
//        if(fieldSlices.isEmpty()){
//            throw new IncorrectFieldException("Incorrect field format");
//        }
//
//        String[] linesChecker = fieldSlices.get(0);
//
//        for(String[]slice : fieldSlices){
//            if(linesChecker.length != slice.length){
//                throw new IncorrectFieldException("Incorrect field, there are not enough elements");
//            }
//        }
//
//        for(String[] slice : fieldSlices) {
//            for (int i = 0; i < slice.length; i++) {
//                System.out.println(slice[i]);
//                if(!slice[i].equals("1")) {
//                    if(!slice[i].equals("0")) {
//                        throw new IncorrectFieldException("Use \"1\" for alive cells and \"0\" for empty space. Split numbers by space");
//                    }
//                }
//            }
//        }
//
//        Field field = new Field(fieldSlices.get(0).length,fieldSlices.size());
//        Display display = new Display(field);
//
//        display.show(display.getResult());
//
//        Game game = new Game(display,1);
//        ConsoleInterface consoleInterface = new ConsoleInterface(game);
//
//        int y = 0;
//        for(String[] slice : fieldSlices){
//            for(int i = 0; i < slice.length; i++){
//                if(slice[i].equals("1")){
//                    display.addCell(i,y,display.getResult());
//                }
//            }
//            y++;
//        }
//
//        display.show(display.getResult());





















        //String

//
//
////        for(int i = 0; i < 1000; i++){
////            display.addCell((int) (Math.random() * 50), (int) (Math.random() * 50), display.getResult());
////        }
//        display.addCell(29,29,display.getResult());
//        display.addCell(29,0,display.getResult());
//        display.addCell(29,28,display.getResult());
//
//        game.playMultithreading();
    }
}

