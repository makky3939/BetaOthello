package io.makky.betaothello;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {

    int[][] buttonIds = {
            {R.id.button0_0, R.id.button0_1, R.id.button0_2, R.id.button0_3, R.id.button0_4, R.id.button0_5, R.id.button0_6, R.id.button0_7},
            {R.id.button1_0, R.id.button1_1, R.id.button1_2, R.id.button1_3, R.id.button1_4, R.id.button1_5, R.id.button1_6, R.id.button1_7},
            {R.id.button2_0, R.id.button2_1, R.id.button2_2, R.id.button2_3, R.id.button2_4, R.id.button2_5, R.id.button2_6, R.id.button2_7},
            {R.id.button3_0, R.id.button3_1, R.id.button3_2, R.id.button3_3, R.id.button3_4, R.id.button3_5, R.id.button3_6, R.id.button3_7},
            {R.id.button4_0, R.id.button4_1, R.id.button4_2, R.id.button4_3, R.id.button4_4, R.id.button4_5, R.id.button4_6, R.id.button4_7},
            {R.id.button5_0, R.id.button5_1, R.id.button5_2, R.id.button5_3, R.id.button5_4, R.id.button5_5, R.id.button5_6, R.id.button5_7},
            {R.id.button6_0, R.id.button6_1, R.id.button6_2, R.id.button6_3, R.id.button6_4, R.id.button6_5, R.id.button6_6, R.id.button6_7},
            {R.id.button7_0, R.id.button7_1, R.id.button7_2, R.id.button7_3, R.id.button7_4, R.id.button7_5, R.id.button7_6, R.id.button7_7}
    };

    GameBoard gameBoard = new GameBoard();

    GameAi gameAi = new GameAi();

    int blackSide = 0;
    int whiteSide = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        for (int[] buttonRow : buttonIds) {
            for (int buttonId : buttonRow) {
                findViewById(buttonId).setOnClickListener(this);
            }
        }

        Intent intent = getIntent();

        blackSide = intent.getIntExtra("BLACK", 0);
        whiteSide = intent.getIntExtra("WHITE", 0);

        boardRender();
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        int viewId = v.getId();

        for (int i = 0; i < buttonIds.length; i++) {
            for (int j = 0; j < buttonIds[i].length; j++) {
                if (buttonIds[i][j] == viewId) {
                    if (gameBoard.isSelectableCell(i, j)) {
                        
                            gameBoard.selectCell(i, j);
                            boardRender();

                        if (whiteSide == 1) {
                            int[] ai = gameAi.primitive(gameBoard.getBoard());
                            if (ai[0] == 8 && ai[1] == 8) {
                                gameBoard.pass();
                            } else {
                                gameBoard.selectCell(ai[0], ai[1]);
                            }

                            boardRender();
                        }

                    } else {
                        Toast.makeText(this, String.valueOf(i) + "," + String.valueOf(j), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private void boardRender() {
        int boardState[][] = gameBoard.getBoard();
        int gameStep = gameBoard.getStep();

        Map<String, Integer> cellsStat = gameBoard.countCellsStat();

        boolean turn = gameBoard.getTurn();

        TextView blackCellStatus = (TextView)findViewById(R.id.boardStatusBlack);
        blackCellStatus.setText(String.valueOf(cellsStat.get("BLACK")));

        TextView whiteCellStatus = (TextView)findViewById(R.id.boardStatusWhite);
        whiteCellStatus.setText(String.valueOf(cellsStat.get("WHITE")));

        TextView freeCellStatus = (TextView)findViewById(R.id.boardStatusFree);
        freeCellStatus.setText("Free:" + String.valueOf(cellsStat.get("FREE")));

        TextView stepStatus = (TextView)findViewById(R.id.boardStatusStep);
        stepStatus.setText(String.valueOf("Step:" + gameStep));

        TextView turnStatus = (TextView)findViewById(R.id.boardStatusTurn);
        turnStatus.setText((turn ? "<" : ">"));

        blackCellStatus.getBackground().setColorFilter(getResources().getColor(R.color.colorCellBlack), PorterDuff.Mode.MULTIPLY);
        whiteCellStatus.getBackground().setColorFilter(getResources().getColor(R.color.colorCellWhite), PorterDuff.Mode.MULTIPLY);

        TextView boardStatusBlackSide = (TextView)findViewById(R.id.boardStatusBlackSide);
        TextView boardStatusWhiteSide = (TextView)findViewById(R.id.boardStatusWhiteSide);

        boardStatusBlackSide.setText(getControllerName(blackSide));
        boardStatusWhiteSide.setText(getControllerName(whiteSide));

        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                if (boardState[i][j] == 1) {
                    findViewById(buttonIds[i][j]).getBackground().setColorFilter(getResources().getColor(R.color.colorCellWhite), PorterDuff.Mode.MULTIPLY);
                } else if (boardState[i][j] == 2) {
                    findViewById(buttonIds[i][j]).getBackground().setColorFilter(getResources().getColor(R.color.colorCellBlack), PorterDuff.Mode.MULTIPLY);
                } else if (boardState[i][j] == 3) {
                    findViewById(buttonIds[i][j]).getBackground().setColorFilter(getResources().getColor(R.color.colorCellSelectable), PorterDuff.Mode.MULTIPLY);
                } else {
                    findViewById(buttonIds[i][j]).getBackground().setColorFilter(getResources().getColor(R.color.colorCellFree), PorterDuff.Mode.MULTIPLY);
                }
            }
        }

        switch (gameBoard.completionJudge()) {
            case 1:
            case 2:
                resultModal();
                break;
            case 3:
                passModal();
                break;
        }

    }

    private String getControllerName(int i) {
        switch (i) {
            case 0:
                return "Player";
            case 1:
                return "Primitive AI";
        }
        return "";
    }

    public void resultModal() {
        new AlertDialog.Builder(this)
                .setTitle("Result")
                .setMessage((gameBoard.completionJudge() == 1) ? "White win" : "Black win")
                .setPositiveButton("Back to Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(BoardActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                })
                .show();

    }

    public void passModal() {
        new AlertDialog.Builder(this)
                .setTitle("There is no choice")
                .setMessage("Are you sure that you want to pass")
                .setPositiveButton("Pass", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameBoard.pass();
                        boardRender();
                    }
                })
                .setNegativeButton("Retire", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultModal();
                    }
                })
                .show();
    }
}


