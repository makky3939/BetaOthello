package io.makky.betaothello;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener {

    Boolean turn = false;

    int[][] boardState = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 2, 0, 0, 0},
            {0, 0, 0, 2, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        boardRender();


        for (int[] buttonRow : buttonIds) {
            for (int buttonId : buttonRow) {
                findViewById(buttonId).setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v != null) {
            int vid = v.getId();
            for (int i = 0; i<buttonIds.length; i++) {
                for (int j = 0; j<buttonIds[i].length; j++) {
                    if (buttonIds[i][j] == vid) {
                        if (boardState[i][j] == 0) {
                            if (turn) {
                                boardState[i][j] = 1;
                            } else {
                                boardState[i][j] = 2;
                            }
                            turn = !turn;
                            boardRender();
                        }
                    }
                }
            }
        }
    }

    private void boardRender() {
        for (int i=0; i<boardState.length; i++) {
            for (int j=0; j<boardState[i].length; j++) {
                if (boardState[i][j] == 1) {
                    findViewById(buttonIds[i][j]).getBackground().setColorFilter(0xff000000, PorterDuff.Mode.MULTIPLY);
                } else if (boardState[i][j] == 2) {
                    findViewById(buttonIds[i][j]).getBackground().setColorFilter(0xffffffff, PorterDuff.Mode.MULTIPLY);
                }
            }
        }
    }

}
