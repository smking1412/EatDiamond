package com.e.eatdiamond;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    int scoreTop1;
    int scoreTop2;
    int scoreTop3;
    int scoreTop4;
    int scoreTop5;
    ImageView medal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView hightScoreLabel = (TextView) findViewById(R.id.hightScoreLabel);
        TextView gamesPlayedLabel = (TextView) findViewById(R.id.gamesPlayedLabel);
        medal = (ImageView) findViewById(R.id.medal);


        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText("" + score);

        if (score < 50) {
            medal.setImageResource(R.drawable.bronze);
        } else if (score >= 100) {
            medal.setImageResource(R.drawable.gold);
        } else {
            medal.setImageResource(R.drawable.silver);
        }

        SharedPreferences preferencesScore = getSharedPreferences("HIGHSCORE", Context.MODE_PRIVATE);
        scoreTop1 = preferencesScore.getInt("TOP1", 0);
        scoreTop2 = preferencesScore.getInt("TOP2", 0);
        scoreTop3 = preferencesScore.getInt("TOP3", 0);
        scoreTop4 = preferencesScore.getInt("TOP4", 0);
        scoreTop5 = preferencesScore.getInt("TOP5", 0);

        // chưa có bxh, tất cả = 0
        if (scoreTop1 == 0) {
            hightScoreLabel.setText("New High Score: " + score);
            SharedPreferences.Editor editor = preferencesScore.edit();
            editor.putInt("TOP1", score);
            editor.commit();
        }
        //có bxh của top 1, top 2,3,4,5 = 0
        else if (scoreTop1 > 0 && scoreTop2 == 0) {
            if (score > scoreTop1) {
                hightScoreLabel.setText("New High Score: " + score);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP2", scoreTop1);
                editor.putInt("TOP1", score);
                editor.commit();
            } else if (score < scoreTop1) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP2", score);
                editor.commit();
            }
        }
        //có bxh top 1,2, top 3,4,5 = 0
        else if (scoreTop2 > 0 && scoreTop3 == 0) {
            if (score > scoreTop1) {
                hightScoreLabel.setText("New High Score: " + score);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP3", scoreTop2);
                editor.putInt("TOP2", scoreTop1);
                editor.putInt("TOP1", score);
                editor.commit();
            } else if (score > scoreTop2 && score < scoreTop1) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP3", scoreTop2);
                editor.putInt("TOP2", score);
                editor.commit();
            } else if (score < scoreTop2) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP3", score);
                editor.commit();
            }
        }
        //có bxh top 1,2,3, top 4,5 = 0
        else if (scoreTop3 > 0 && scoreTop4 == 0) {
            if (score > scoreTop1) {
                hightScoreLabel.setText("New High Score: " + score);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP4", scoreTop3);
                editor.putInt("TOP3", scoreTop2);
                editor.putInt("TOP2", scoreTop1);
                editor.putInt("TOP1", score);
                editor.commit();
            } else if (score > scoreTop2 && score < scoreTop1) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP4", scoreTop3);
                editor.putInt("TOP3", scoreTop2);
                editor.putInt("TOP2", score);
                editor.commit();
            } else if (score > scoreTop3 && score < scoreTop2) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP4", scoreTop3);
                editor.putInt("TOP3", score);
                editor.commit();
            } else if (score < scoreTop3) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP4", score);
                editor.commit();
            }
        }
        //có bxh top 1,2,3,4, top 5 = 0
        else if (scoreTop4 > 0) {
            if (score > scoreTop1) {
                hightScoreLabel.setText("New High Score: " + score);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP5", scoreTop4);
                editor.putInt("TOP4", scoreTop3);
                editor.putInt("TOP3", scoreTop2);
                editor.putInt("TOP2", scoreTop1);
                editor.putInt("TOP1", score);
                editor.commit();
            } else if (score > scoreTop2 && score < scoreTop1) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP5", scoreTop4);
                editor.putInt("TOP4", scoreTop3);
                editor.putInt("TOP3", scoreTop2);
                editor.putInt("TOP2", score);
                editor.commit();
            } else if (score > scoreTop3 && score < scoreTop2) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP5", scoreTop4);
                editor.putInt("TOP4", scoreTop3);
                editor.putInt("TOP3", score);
                editor.commit();
            } else if (score > scoreTop4 && score < scoreTop3) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP5", scoreTop4);
                editor.putInt("TOP4", score);
                editor.commit();
            } else if (score > scoreTop5 && score < scoreTop4) {
                hightScoreLabel.setText("High Score: " + scoreTop1);
                SharedPreferences.Editor editor = preferencesScore.edit();
                editor.putInt("TOP5", score);
                editor.commit();
            }
        } else {
            hightScoreLabel.setText("High Score: " + scoreTop1);
        }

        SharedPreferences preferencesGames = getSharedPreferences("GAMES", Context.MODE_PRIVATE);
        int games = preferencesGames.getInt("GAMES", 0);

        gamesPlayedLabel.setText("Games Played: " + (games + 1));

        SharedPreferences.Editor editor = preferencesGames.edit();
        editor.putInt("GAMES", (games + 1));
        editor.commit();
    }

    public void tryAgain(View view) {
        startActivity(new Intent(getApplicationContext(), StartActivity.class));
        finish();
    }

    //disable return button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
