public class Queen extends ChessPiece {
    public Queen(String col) {
        super(col);
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return true;
    }
    @Override
    public String getSymbol() {
        return "Q";
    }
}
