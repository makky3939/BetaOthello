package io.makky.betaothello;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        findViewById(R.id.dashboard_pvp).setOnClickListener(this);
        findViewById(R.id.dashboard_primitive_ai).setOnClickListener(this);
        findViewById(R.id.dashboard_random_ai).setOnClickListener(this);
        findViewById(R.id.dashboard_random_weighted_ai).setOnClickListener(this);

        findViewById(R.id.dashboard_pvp).getBackground().setColorFilter(getResources().getColor(R.color.colorCellWhite), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.dashboard_primitive_ai).getBackground().setColorFilter(getResources().getColor(R.color.colorCellWhite), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.dashboard_random_ai).getBackground().setColorFilter(getResources().getColor(R.color.colorCellWhite), PorterDuff.Mode.MULTIPLY);
        findViewById(R.id.dashboard_random_weighted_ai).getBackground().setColorFilter(getResources().getColor(R.color.colorCellWhite), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }

        Intent intent = new Intent(DashboardActivity.this, BoardActivity.class);

        switch (v.getId()) {
            case R.id.dashboard_pvp:
                intent.putExtra("BLACK", 0);
                intent.putExtra("WHITE", 0);
                startActivity(intent);
                break;

            case R.id.dashboard_primitive_ai:
                intent.putExtra("BLACK", 0);
                intent.putExtra("WHITE", 1);
                startActivity(intent);
                break;

            case R.id.dashboard_random_ai:
                intent.putExtra("BLACK", 0);
                intent.putExtra("WHITE", 2);
                startActivity(intent);
                break;

            case R.id.dashboard_random_weighted_ai:
                intent.putExtra("BLACK", 0);
                intent.putExtra("WHITE", 3);
                startActivity(intent);
                break;
        }
    }

}
