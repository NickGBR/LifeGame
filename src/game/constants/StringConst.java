package game.constants;

public class StringConst {

    public static String[] tooMuchMistakes = new String[]{"It was easiest think you needed to do, I need someone smarter for this game...",
            "So many mistakes, I think you are not smart enough for being GOD!!!"};



    public static String getIntroduction(){
        return "Welcome to the game of life, time of being god is coming...\n";
    }

    public static String getRules(){
        return "Here is the rules:\n" + "1) Game setting field.txt file is located in \"resources\" folder.\n"
                + "2) Use \"1\" for alive cells and \"0\" for empty space. Split numbers by space\n"
                + "3) Field should be rectangle.\n"
                + "Here is an example" + "\n" +"\n"
                + "1 0 0 1 0 1 0\n"
                + "1 0 1 1 0 1 0\n"
                + "1 0 0 1 1 1 0\n"
                + "0 1 0 1 0 1 0\n" + "\n"
                + "First of all, let me know where is game world setting file or\n"
                + "input \"1\" key, for using \"field.txt\' file, inside game's directory.\"\n"
                + "Also I can generate random game field for you, just input \"G\" ..." + "\n"
                + "For testing difference between Multithreaded and Single-threaded mode, input \"test\"" + "\n" + "\n";

    }
}
