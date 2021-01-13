package com.e.eatdiamond;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HighscoreActivity extends AppCompatActivity {
    private TextView tvPlayer1Score;
    private TextView tvPlayer2Score;
    private TextView tvPlayer3Score;
    private TextView tvPlayer4Score;
    private TextView tvPlayer5Score;
    ImageView btnHomeHighScore;
    int top1, top2,top3,top4,top5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        init();
        btnHomeHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HighscoreActivity.this,StartActivity.class));
                finish();
            }
        });

        final SharedPreferences spHighScore = getSharedPreferences("HIGHSCORE", Context.MODE_PRIVATE);
        top1 = spHighScore.getInt("TOP1",0);
        top2 = spHighScore.getInt("TOP2",0);
        top3 = spHighScore.getInt("TOP3",0);
        top4 = spHighScore.getInt("TOP4",0);
        top5 = spHighScore.getInt("TOP5",0);
        tvPlayer1Score.setText(top1 + " điểm");
        tvPlayer2Score.setText(top2 + " điểm");
        tvPlayer3Score.setText(top3 + " điểm");
        tvPlayer4Score.setText(top4 + " điểm");
        tvPlayer5Score.setText(top5 + " điểm");

    }

    private void init() {
        tvPlayer1Score = findViewById(R.id.tv_player1_score);
        tvPlayer2Score = findViewById(R.id.tv_player2_score);
        tvPlayer3Score = findViewById(R.id.tv_player3_score);
        tvPlayer4Score = findViewById(R.id.tv_player4_score);
        tvPlayer5Score = findViewById(R.id.tv_player5_score);
        btnHomeHighScore = findViewById(R.id.homehighscore);
    }
}