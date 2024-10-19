public abstract class ChessPiece {
    private String color;
    private boolean check;

    public ChessPiece(String col) {
        color = col;
    }
    public String getColor() {
        return color;
    }
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
    public abstract String getSymbol();
}
