public class King extends ChessPiece {
    public King(String col) {
        super(col);
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return true;
    }
    @Override
    public String getSymbol() {
        return "K";
    }
    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        return false;
    }
}
