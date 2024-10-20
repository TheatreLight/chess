

enum EFigureType {
    eftPawn,
    eftRook,
    eftBishop,
    eftHorse,
    eftQueen,
    eftKing,
    eftAnyFigure
}

public abstract class ChessPiece {
    private String color;
    private boolean check;
    private EFigureType type= EFigureType.eftAnyFigure;

    protected boolean isTrackEmpty(ChessBoard board, int curX, int curY, int nextX, int nextY) {
        if (type == EFigureType.eftAnyFigure) {
            throw new RuntimeException("Unexpected figure type");
        }

        switch(type) {
            case EFigureType.eftPawn:
                if (board.nowPlayer.compareTo("White") == 0) {
                    for (int i = curX + 1; i <= nextX; ++i) {
                        if (board.board[curX][i] != null) {
                            return false;
                        }
                    }
                } else {
                    for (int i = curX - 1; i >= nextX; --i) {
                        if (board.board[curX][i] != null) {
                            return false;
                        }
                    }
                }
                break;
            case EFigureType.eftRook:
            case EFigureType.eftHorse:
            case EFigureType.eftBishop:
            case EFigureType.eftQueen:
            case EFigureType.eftKing:
                break;
        }
        return true;
    }

    public ChessPiece(String col, EFigureType t) {
        color = col;
        type = t;
    }
    public String getColor() {
        return color;
    }
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);
    public abstract String getSymbol();
}
