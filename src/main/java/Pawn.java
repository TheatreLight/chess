public class Pawn extends ChessPiece {
    public Pawn(String col) {
        super(col, EFigureType.eftPawn);
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int diff = (chessBoard.nowPlayer.compareTo("White")== 0) ? toLine - line : line - toLine;
        boolean isTheFirstMove = (chessBoard.nowPlayer.compareTo("White")== 0) ? line == 1 : line == 6;
        if (isTheFirstMove) {
            if (diff > 2 || diff < 1) {
                return false;
            }
        } else {
            if (diff != 1) {
                return false;
            }
        }

        if (!isTrackEmpty(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }

        return chessBoard.board[toLine][toColumn] == null;
    }
    @Override
    public String getSymbol() {
        return "P";
    }
}
