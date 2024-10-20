import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
               For checking the game you should enter the commands:
               'exit' - for exit
               'replay' - for restart
               'castling0' or 'castling7' - for castling for corresponded line
               'move 1 1 2 3' - for moving figure from position 1 1 to 2 3(the field is a two-dimension array from 0 to 7)
               Check: 
                is it possible for figures moving through others; 
                are they 'eat' each other correctly; 
                is it possible to make check and do the castling?
               """);
        System.out.println();
        controller.getBoard().printBoard();

        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) {
                return;
            }
            controller.run(s);
        }
    }
}