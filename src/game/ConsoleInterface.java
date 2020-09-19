package game;

import java.util.Scanner;

public class ConsoleInterface {

    Game game;

    public ConsoleInterface(Game game) {
        this.game = game;
    }

    public void start(){

        int idiotCoefficient  = 0;
        boolean check = false;
        String PATH;
        int iteration;

        System.out.println("Welcome to game of life, time of being god coming,");
        System.out.println("but first, let me know where is game world setting file?");

        Scanner sc = new Scanner(System.in);

        PATH = sc.next();

        while(!check) {
            check = true;
            try{
            System.out.println("How many steps you want this world to live?");
            iteration = Integer.parseInt(sc.next());}
            catch (NumberFormatException e){
                idiotCoefficient++;
                if(idiotCoefficient==5){
                System.out.println("So many mistakes, I think you are not smart enough for being GOD!!!");
                System.exit(idiotCoefficient);
                }
                System. out. print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Oh no, I need an Integer value my friend!");
                check = false;
            }
        }
    }
}
