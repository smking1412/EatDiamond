package com.e.eatdiamond;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

public class StartActivity extends AppCompatActivity {

    private LinearLayout btnPlay;
    private LinearLayout btnShop;
    private LinearLayout btnHighScore;
    private LinearLayout btnHelp;
    private ImageView logoGame;
    private boolean isClickLogo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //full screnn

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnPlay =  findViewById(R.id.play);
        btnShop =  findViewById(R.id.shop);
        btnHighScore = findViewById(R.id.highscore);
        btnHelp = findViewById(R.id.help);
        logoGame = findViewById(R.id.logogame);
        logoGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClickLogo == false){
                    Drawable treasureOpen = getResources().getDrawable(R.drawable.ic_treasure);
                    logoGame.setImageDrawable(treasureOpen);
                    isClickLogo = true;
                } else if (isClickLogo == true){
                    Drawable treasureClose = getResources().getDrawable(R.drawable.ic_treasure_close);
                    logoGame.setImageDrawable(treasureClose);
                    isClickLogo = false;
                }
            }
        });



        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, ShopActivity.class));
                finish();
            }
        });

        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,HighscoreActivity.class));
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this,HelpActivity.class));
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case  KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }
        return  super.dispatchKeyEvent(event);
    }
}
