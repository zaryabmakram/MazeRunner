// module 4 projecct: Maze Runner
import java.util.Scanner;

public class MazeRunner {

    public static Maze myMap = new Maze();
    public static final Scanner input = new Scanner(System.in);
    public static boolean playing = true;

    public static void main(String[] arg) {
        intro();
        int moves = 0;
        while (playing) {
            String move = userMove();
            navigatePit(move);
            move = checkCanMove(move);
            Move(move);
            moves++;
            movesMessage(moves);
        }
    }
    public static void intro() {
        System.out.println("Welcome to Maze Runner!");
        System.out.println("Here is your current position:");
        myMap.printMap();
        System.out.println("_____________________________");
    }
    public static String userMove() {
        String move = "";
        boolean running = true;
        while (running) {
            System.out.print("Where would you like to move? (R,L,U,D) ");
            move = input.next();
            if (move.equals("R") || move.equals("L") || move.equals("U") || move.equals("D")) {
                running = false;
            }
        }
        return move;
    }
    public static String checkCanMove(String move) {
        String ans = "";
        if (move.equals("R") && myMap.canIMoveRight()) {
            ans = "R";
        } else if (move.equals("L") && myMap.canIMoveLeft()) {
            ans = "L";
        } else if (move.equals("U") && myMap.canIMoveUp()) {
            ans = "U";
        } else if (move.equals("D") && myMap.canIMoveDown()) {
            ans = "D";
        } else {
            System.out.println("Sorry, you\' ve hit a wall");
        }
        return ans;
    }
    public static void Move(String move) {
        if (move.equals("R")) {
            myMap.moveRight();
            myMap.printMap();
        } else if (move.equals("L")) {
            myMap.moveLeft();
            myMap.printMap();
        } else if (move.equals("U")) {
            myMap.moveUp();
            myMap.printMap();
        } else if (move.equals("D")) {
            myMap.moveDown();
            myMap.printMap();
        }
    }
    public static void movesMessage(int moves) {
        if (moves==50) {
            System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes.");
        } else if (moves==75) {
            System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
        } else if (moves==90) {
            System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
        } else if (moves==100) {
            System.out.println("Oh no! You took too long to escape, and now the maze exite is cloased FOREVER>:[");
        }
        if (moves==100) {
            System.out.println("Sorry, but you didn't escape in time- you lose!");
            playing = false;
        }
        if (myMap.didIWin()) {
            System.out.println("Congratulations, you made it out alive!");
            System.out.println("and you did it in "+moves+" moves");
            playing = false;
        }
    }
    public static void navigatePit(String move) {
        if (myMap.isThereAPit(move)) {
            Scanner input = new Scanner(System.in);
            System.out.print("Watch out! There is a pit ahead, jump it? ");
            String respond = input.next();
            if (respond.startsWith("y")) {
                myMap.jumpOverPit(move);
            } else {
                System.out.println("You fall into a pit. You died !!!!");
                playing = false;
            }
        }
    }
}
