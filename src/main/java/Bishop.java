public class Bishop extends ChessPiece {
    public Bishop(String col) {
        super(col, EFigureType.eftBishop);
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return true;
    }
    @Override
    public String getSymbol() {
        return "B";
    }
}
