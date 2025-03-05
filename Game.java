public class Game {
    private int boardSize;
    private String[][] gameBoard;
    private boolean isBluePlayersTurn;
    private boolean isSimpleGame;
    public Game(int boardSize) {
        reset(boardSize, true);
    }
    public void reset(int boardSize, boolean isSimpleGame) {
        this.boardSize = boardSize;
        this.gameBoard = new String[boardSize][boardSize];
        this.isBluePlayersTurn = true;
        this.isSimpleGame = isSimpleGame;
        initializeBoard();
    }
    public void initializeBoard() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                gameBoard[row][col] = "";
            }
        }
    }
    public void makeMove(int row, int col, String symbol) {
        gameBoard[row][col] = symbol;
    }
    public boolean isBluePlayersTurn() {
        return isBluePlayersTurn;
    }
    public String[][] getGameBoard() {
        return gameBoard;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    public int getBoardSize() {
        return boardSize;
    }
    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
    public void setBluePlayersTurn(boolean bluePlayersTurn) {
        isBluePlayersTurn = bluePlayersTurn;
    }
    public boolean isSimpleGame() {
        return isSimpleGame;
    }
    public void setSimpleGame(boolean simpleGame) {
        isSimpleGame = simpleGame;
    }
    boolean isBoardSizeTextNumeric(String txtBoardSize) {
        try {
            Integer.parseInt(txtBoardSize);
        } catch (NumberFormatException ex) {
            if (txtBoardSize.length() > 0) {
                return false;
            }
        }
        return true;
    }
    boolean isBoardSizeGreaterThanTwo(int newSize) {
        return newSize > 2;
    }
}
