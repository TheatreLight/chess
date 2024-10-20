public class Horse extends ChessPiece {
    public Horse(String col) {
        super(col, EFigureType.eftHorse);
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return true;
    }
    @Override
    public String getSymbol() {
        return "H";
    }
}
