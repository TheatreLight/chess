import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {

    private ChessBoard buildSampleBoard() {
        ChessBoard board = new ChessBoard("White");

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
        return board;
    }

    private void moveFromTo(ChessBoard board, int x_from, int y_from, int x_to, int y_to) {
        if(x_from > 7 || x_from < 0 || x_to > 7 || x_to < 0
        || y_from > 7 || y_from < 0 || y_to > 7 || y_to < 0) {
            return;
        }
        board.board[x_to][y_to] = board.board[x_from][y_from];
        board.board[x_from][y_from] = null;
    }

    private void printCurrentBoard(ChessBoard board) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if(board.board[i][j] != null) {
                    System.out.print(board.board[i][j].getSymbol());
                } else {
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    private void assertBoards(ChessBoard expected, ChessBoard actual) {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (expected.board[i][j] != null &&
                    actual.board[i][j] != null) {
                    assertSame(expected.board[i][j].getSymbol(), actual.board[i][j].getSymbol());
                } else {
                    assertSame(expected.board[i][j], actual.board[i][j]);
                }
            }
        }
    }

    @Test
    void PawnMoveTest() {
        ChessBoard expected = buildSampleBoard();
        moveFromTo(expected, 1, 0, 2, 0);
        GameController game = new GameController();
        var actual = game.run("move 1 0 2 0");
        assertBoards(expected, actual);
    }

    @Test
    void PawnMove2StepsForward() {
        ChessBoard expected = buildSampleBoard();
        moveFromTo(expected, 1, 0, 3, 0);
        GameController game = new GameController();
        var actual = game.run("move 1 0 3 0");
        assertBoards(expected, actual);
    }

    @Test
    void PawnMove3StepsForward() {
        ChessBoard expected = buildSampleBoard();
        GameController game = new GameController();
        var actual = game.run("move 1 0 4 0");
        assertBoards(expected, actual);
    }

    @Test
    void PawnMove2StepsForwardAfterStart() {
        ChessBoard expected = buildSampleBoard();
        moveFromTo(expected, 1, 0, 2, 0);  // white move
        moveFromTo(expected, 6, 0, 5, 0);  // black move
        GameController game = new GameController();
        String[] commands = {"move 1 0 2 0", "move 6 0 5 0", "move 2 0 4 0"};
        ChessBoard actual = new ChessBoard("White");
        for(var command : commands) {
            actual = game.run(command);
        }
        assertBoards(expected, actual);
    }

    @Test
    void MoveBack() {
        ChessBoard expected = buildSampleBoard();
        moveFromTo(expected, 1, 0, 2, 0);
        moveFromTo(expected, 6, 0, 5, 0);
        GameController game = new GameController();
        String[] commands = {"move 1 0 2 0", "move 6 0 5 0", "move 2 0 1 0"};
        ChessBoard actual = new ChessBoard("White");
        for(var command : commands) {
            actual = game.run(command);
        }
        assertBoards(expected, actual);
    }

    @Test
    void MoveToTheSamePlace() {
        ChessBoard expected = buildSampleBoard();
        GameController game = new GameController();
        var actual = game.run("move 1 0 1 0");
        assertBoards(expected, actual);
    }

    @Test
    void MoveToPlaceWithAnotherPawn() {
        ChessBoard expected = buildSampleBoard();
        moveFromTo(expected, 1, 0, 3, 0);  // white move
        moveFromTo(expected, 6, 0, 4, 0);  // black move
        GameController game = new GameController();
        String[] commands = {"move 1 0 3 0", "move 6 0 4 0", "move 3 0 4 0"};
        ChessBoard actual = new ChessBoard("White");
        for(var command : commands) {
            actual = game.run(command);
        }
        assertBoards(expected, actual);
    }

    @Test
    void JumpOverTheEnemy() {
        /*
        --Try Pb 6 0 jump over Pw to 4 0

            0	1	2	3	4	5	6	7
        7	Rb	Hb	Bb	Qb	Kb	Bb	Hb	Rb

        6	Pb	Pb	Pb	Pb	Pb	Pb	Pb	..

        5	Pw	..	..	..	..	..	..	..

        4	..	..	..	..	..	..	..	Pb

        3	..	..	..	..	..	..	..	..

        2	..	..	..	..	..	..	..	..

        1	..	Pw	Pw	Pw	Pw	Pw	Pw	Pw

        0	Rw	Hw	Bw	Qw	Kw	Bw	Hw	Rw
        */
        ChessBoard expected = buildSampleBoard();
        moveFromTo(expected, 5, 7, 4, 7);
        moveFromTo(expected, 4, 0, 5, 0);

        GameController game = new GameController();
        String[] commands = {"move 1 0 3 0", // white move
                            "move 6 7 5 7",  // black move
                            "move 3 0 4 0",  // white move
                            "move 5 7 4 7",  // black move
                            "move 4 0 5 0",  // white move
                            "move 6 0 4 0"}; // black move
        ChessBoard actual = new ChessBoard("White");
        for (String command : commands) {
            actual = game.run(command);
        }
        assertBoards(expected, actual);
    }
}
