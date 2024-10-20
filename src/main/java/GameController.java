public class GameController {
    private ChessBoard board;
    private void buildBoard() {
        board = new ChessBoard("White");

        board.board[0][0] = new Rook("White");
        board.board[0][1] = new Horse("White");
        board.board[0][2] = new Bishop("White");
        board.board[0][3] = new Queen("White");
        board.board[0][4] = new King("White");
        board.board[0][5] = new Bishop("White");
        board.board[0][6] = new Horse("White");
        board.board[0][7] = new Rook("White");
        board.board[1][0] = new Pawn("White");
        board.board[1][1] = new Pawn("White");
        board.board[1][2] = new Pawn("White");
        board.board[1][3] = new Pawn("White");
        board.board[1][4] = new Pawn("White");
        board.board[1][5] = new Pawn("White");
        board.board[1][6] = new Pawn("White");
        board.board[1][7] = new Pawn("White");

        board.board[7][0] = new Rook("Black");
        board.board[7][1] = new Horse("Black");
        board.board[7][2] = new Bishop("Black");
        board.board[7][3] = new Queen("Black");
        board.board[7][4] = new King("Black");
        board.board[7][5] = new Bishop("Black");
        board.board[7][6] = new Horse("Black");
        board.board[7][7] = new Rook("Black");
        board.board[6][0] = new Pawn("Black");
        board.board[6][1] = new Pawn("Black");
        board.board[6][2] = new Pawn("Black");
        board.board[6][3] = new Pawn("Black");
        board.board[6][4] = new Pawn("Black");
        board.board[6][5] = new Pawn("Black");
        board.board[6][6] = new Pawn("Black");
        board.board[6][7] = new Pawn("Black");
    }
    public GameController() {
        buildBoard();
    }

    public ChessBoard run(String s) {
        if (s.equals("replay")) {
            System.out.println("Restart");
            buildBoard();
            board.printBoard();
        } else {
            if (s.equals("castling0")) {
                if (board.castling0()) {
                    System.out.println("Castling success");
                    board.printBoard();
                } else {
                    System.out.println("Castling unsuccess");
                }
            } else if (s.equals("castling7")) {
                if (board.castling7()) {
                    System.out.println("Castling success");
                    board.printBoard();
                } else {
                    System.out.println("Castling unsuccess");
                }
            } else if (s.contains("move")) {
                String[] a = s.split(" ");
                try {
                    int line = Integer.parseInt(a[1]);
                    int column = Integer.parseInt(a[2]);
                    int toLine = Integer.parseInt(a[3]);
                    int toColumn = Integer.parseInt(a[4]);
                    if (board.moveToPosition(line, column, toLine, toColumn)) {
                        System.out.println("Move success");
                        board.printBoard();
                    } else System.out.println("Move unsuccess");
                } catch (Exception e) {
                    System.out.println("Wrong command, try another");
                }

            }
        }
        return board;
    }

    public ChessBoard getBoard() {
        return board;
    }
}
