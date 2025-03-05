import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;

public class GUI extends JFrame {
    JRadioButton redORadioButton;
    JRadioButton redSRadioButton;
    JRadioButton blueORadioButton;
    JRadioButton blueSRadioButton;
    JPanel bluePlayerEmptyPanel;
    JPanel bluePlayerRadioPanel;
    JPanel redPlayerRadioPanel;
    JPanel mainPanel;
    JPanel topPanel;
    JLabel sosLabel;
    JLabel boardSizeLabel;
    JTextField boardSizeTextField;
    JRadioButton simpleGameRadioButton;
    JRadioButton generalGameRadioButton;
    JPanel centerPanel;
    JPanel bluePlayerPanel;
    JLabel bluePlayerLabel;
    JPanel redPlayerPanel;
    JPanel redPlayerEmptyPanel;
    JLabel redPlayerLabel;
    JPanel boardPanel;
    JPanel bottomPanel;
    JLabel currentTurnLabel;
    JButton newGameButton;
    boolean gameModeChanged = false;
    boolean boardSizeChanged = false;
    private Game game;

    public GUI() {
        game = new Game(8);
        setTitle("SOS Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 650));

        mainPanel = new JPanel(new BorderLayout());
        topPanel = new JPanel(new FlowLayout());
        sosLabel = new JLabel("SOS");
        simpleGameRadioButton = new JRadioButton("Simple Game");
        simpleGameRadioButton.setSelected(true);
        simpleGameRadioButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                game.setSimpleGame(true);
                gameModeChanged = true;
            }
        });

        generalGameRadioButton = new JRadioButton("General Game");
        generalGameRadioButton.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                game.setSimpleGame(false);
                gameModeChanged = true;
            }
        });

        ButtonGroup gameTypeGroup = new ButtonGroup();
        gameTypeGroup.add(simpleGameRadioButton);
        gameTypeGroup.add(generalGameRadioButton);

        boardSizeLabel = new JLabel("Board Size:");
        boardSizeLabel.setBorder(new EmptyBorder(0, 100, 0, 0));
        boardSizeTextField = new JTextField(String.valueOf(game.getBoardSize()));
        boardSizeTextField.setColumns(2);
        boardSizeTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boardSizeChanged = true;
                handleBoardSizeTextChange(boardSizeTextField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        topPanel.add(sosLabel);
        topPanel.add(simpleGameRadioButton);
        topPanel.add(generalGameRadioButton);
        topPanel.add(boardSizeLabel);
        topPanel.add(boardSizeTextField);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        bluePlayerPanel = new JPanel(new BorderLayout());
        bluePlayerLabel = new JLabel("Blue Player");
        bluePlayerLabel.setBorder(new EmptyBorder(40, 20, 10, 20));
        bluePlayerRadioPanel = new JPanel(new BorderLayout());
        bluePlayerRadioPanel.setBorder(new EmptyBorder(40, 20, 10, 20));
        bluePlayerPanel.add(bluePlayerLabel, BorderLayout.NORTH);

        blueSRadioButton = new JRadioButton("S");
        blueSRadioButton.setSelected(true);
        blueORadioButton = new JRadioButton("O");
        bluePlayerRadioPanel.add(blueSRadioButton, BorderLayout.NORTH);
        bluePlayerRadioPanel.add(blueORadioButton, BorderLayout.SOUTH);

        ButtonGroup bluePlayerRadioGroup = new ButtonGroup();
        bluePlayerRadioGroup.add(blueSRadioButton);
        bluePlayerRadioGroup.add(blueORadioButton);

        bluePlayerPanel.add(bluePlayerRadioPanel, BorderLayout.CENTER);
        bluePlayerEmptyPanel = new JPanel();
        bluePlayerEmptyPanel.setPreferredSize(new Dimension(50, 300));
        bluePlayerPanel.add(bluePlayerEmptyPanel, BorderLayout.SOUTH);

        redPlayerPanel = new JPanel(new BorderLayout());
        redPlayerLabel = new JLabel("Red Player");
        redPlayerLabel.setBorder(new EmptyBorder(40, 20, 10, 20));
        redPlayerPanel.add(redPlayerLabel, BorderLayout.NORTH);

        redPlayerRadioPanel = new JPanel(new BorderLayout());
        redPlayerRadioPanel.setBorder(new EmptyBorder(40, 10, 10, 20));
        redSRadioButton = new JRadioButton("S");
        redSRadioButton.setSelected(true);
        redORadioButton = new JRadioButton("O");

        ButtonGroup redPlayerRadioGroup = new ButtonGroup();
        redPlayerRadioGroup.add(redSRadioButton);
        redPlayerRadioGroup.add(redORadioButton);

        redPlayerRadioPanel.add(redSRadioButton, BorderLayout.NORTH);
        redPlayerRadioPanel.add(redORadioButton, BorderLayout.SOUTH);
        redPlayerPanel.add(redPlayerRadioPanel, BorderLayout.CENTER);

        redPlayerEmptyPanel = new JPanel();
        redPlayerEmptyPanel.setPreferredSize(new Dimension(50, 300));
        redPlayerPanel.add(redPlayerEmptyPanel, BorderLayout.SOUTH);

        centerPanel = new JPanel(new BorderLayout());
        updateBoard();
        centerPanel.add(bluePlayerPanel, BorderLayout.WEST);
        centerPanel.add(redPlayerPanel, BorderLayout.EAST);

        bottomPanel = new JPanel(new BorderLayout());
        currentTurnLabel = new JLabel("Current Turn: blue");
        currentTurnLabel.setBorder(new EmptyBorder(0, 250, 0, 0));
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> newGameButtonClicked());

        bottomPanel.add(currentTurnLabel, BorderLayout.CENTER);
        bottomPanel.add(newGameButton, BorderLayout.EAST);
        bottomPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    private void newGameButtonClicked() {
        gameModeChanged = false;
        boardSizeChanged = false;
        boolean isValidBoardSize = handleBoardSizeTextChange(boardSizeTextField.getText());
        if (isValidBoardSize) {
            if (game.isSimpleGame()) {
                game.reset(Integer.parseInt(boardSizeTextField.getText()), true);
                JOptionPane.showMessageDialog(GUI.this, "Starting new Simple Game with board size " + game.getBoardSize(), "Starting New Game", JOptionPane.INFORMATION_MESSAGE);
            } else {
                game.reset(Integer.parseInt(boardSizeTextField.getText()), false);
                JOptionPane.showMessageDialog(GUI.this, "Starting new General Game with board size " + game.getBoardSize(), "Starting New Game", JOptionPane.INFORMATION_MESSAGE);
            }
            currentTurnLabel.setText("Current Turn: blue");
        }
    }

    boolean handleBoardSizeTextChange(String txtBoardSize) {
        boolean isSuccess = false;
        if (game.isBoardSizeTextNumeric(txtBoardSize)) {
            int newSize = Integer.parseInt(txtBoardSize);
            if (game.isBoardSizeGreaterThanTwo(newSize)) {
                game.setBoardSize(newSize);
                updateBoard();
                isSuccess = true;
            } else {
                JOptionPane.showMessageDialog(GUI.this, "Board size must be greater than 2.", "Invalid Board Size", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(GUI.this, "Invalid input for board size.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
        return isSuccess;
    }

    private void updateBoard() {
        if (boardPanel != null) {
            centerPanel.remove(boardPanel);
        }
        int boardSize = game.getBoardSize();
        boardPanel = new JPanel(new GridLayout(boardSize, boardSize));
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton button = new JButton("");
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> cellClicked(button, finalI, finalJ));
                boardPanel.add(button);
            }
        }
        centerPanel.add(boardPanel, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    private void cellClicked(JButton button, int finalI, int finalJ) {
        if (boardSizeChanged) {
            JOptionPane.showMessageDialog(GUI.this, "Board size has been changed, please press New Game button to start the new game", "Start new game", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (gameModeChanged) {
            JOptionPane.showMessageDialog(GUI.this, "Game mode has been changed, please press New Game button to start the new game", "Start new game", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (button.getText().contains("S") || button.getText().contains("O")) {
            JOptionPane.showMessageDialog(GUI.this, "Please click on an empty square", "Square already filled", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (game.isBluePlayersTurn()) {
            if (blueSRadioButton.isSelected()) {
                button.setText("S");
                game.makeMove(finalI, finalJ, "S");
            } else {
                button.setText("O");
                game.makeMove(finalI, finalJ, "O");
            }
            game.setBluePlayersTurn(false);
            currentTurnLabel.setText("Current Turn: red");
        } else {
            if (redSRadioButton.isSelected()) {
                button.setText("S");
                game.makeMove(finalI, finalJ, "S");
            } else {
                button.setText("O");
                game.makeMove(finalI, finalJ, "O");
            }
            game.setBluePlayersTurn(true);
            currentTurnLabel.setText("Current Turn: blue");
        }
    }
}
