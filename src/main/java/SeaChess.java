import enums.CellValue;
import enums.WinnerState;

public class SeaChess {
    private final CellValue[][] table =
            {
                    {CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY},
                    {CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY},
                    {CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY}
            };

    public CellValue getCellValue(int x, int y) {
        return table[x][y];
    }

    public void setCellValue(CellValue state, int x, int y) {
        table[x][y] = state;
    }

    public boolean isTableFilled() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (getCellValue(i, j) == CellValue.EMPTY) return false;
            }
        }

        return true;
    }

    public WinnerState getWinner() {
        WinnerState winnerHorizontally = checkWinnerHorizontally();
        if (winnerHorizontally != WinnerState.TIE) return winnerHorizontally;

        WinnerState winnerVertically = checkWinnerVertically();
        if (winnerVertically != WinnerState.TIE) return winnerVertically;

        WinnerState winnerDiagonally = checkWinnerDiagonally();
        if (winnerDiagonally != WinnerState.TIE) return winnerDiagonally;

        if (isTableFilled())
            return WinnerState.TIE;
        else
            return WinnerState.GAME_NOT_COMPLETED;
    }

    private WinnerState checkWinnerHorizontally() {
        for (int row = 0; row < 3; row++) {
            CellValue val = getCellValue(row, 0);
            if (val == getCellValue(row, 1) && val == getCellValue(row, 2) && val != CellValue.EMPTY) {
                return val == CellValue.X ? WinnerState.X : WinnerState.O;
            }
        }

        return WinnerState.TIE;
    }

    private WinnerState checkWinnerVertically() {
        for (int column = 0; column < 3; column++) {
            CellValue val = getCellValue(0, column);
            if (val == getCellValue(1, column) && val == getCellValue(2, column) && val != CellValue.EMPTY) {
                return val == CellValue.X ? WinnerState.X : WinnerState.O;
            }
        }

        return WinnerState.TIE;
    }

    private WinnerState checkWinnerDiagonally() {
        CellValue val = getCellValue(0, 0);
        if (val == getCellValue(1, 1) && val == getCellValue(2, 2) && val != CellValue.EMPTY) {
            return val == CellValue.X ? WinnerState.X : WinnerState.O;
        }

        val = getCellValue(2, 2);
        if (val == getCellValue(1, 1) && val == getCellValue(0, 2) && val != CellValue.EMPTY) {
            return val == CellValue.X ? WinnerState.X : WinnerState.O;
        }

        return WinnerState.TIE;
    }
}
