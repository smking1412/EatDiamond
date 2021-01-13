package com.e.eatdiamond;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //declaration of all variables
    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView player;
    private ImageView ruby;
    private ImageView gold;
    private ImageView diamond;
    private ImageView enemy1;
    private ImageView enemy2;
    private ImageView star;
    ImageButton pauseLb;

    private ImageButton pause;
    ImageButton startLb;

    private FrameLayout frameLb;
    TextView tv_coins;

    private int frameHeight;
    private int playerSize;
    private int screenWidth;
    private int screenHeight;

    //postion
    private int playerY;
    private int rubyY;
    private int rubyX;
    private int goldY;
    private int goldX;
    private int diamondY;
    private int diamondX;
    private int starY;
    private int starX;
    private int enemy1Y;
    private int enemy1X;
    private int enemy2Y;
    private int enemy2X;


    private SoundEffects sound;

    private int playerSpeed;
    private int rubySpeed;
    private int goldSpeed;
    private int starSpeed;
    private int diamondSpeed;
    private int enemy1Speed;
    private int enemy2Speed;
    private int score = 0;

    private Handler handler = new Handler();
    private Timer timer = new Timer();


    //status
    private boolean action_flg = false;
    private boolean start_flg = false;
    private boolean pause_flg = false;

    int action;
    int coins = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        action = settings.getInt("ACTION", 1);
        coins = settings.getInt("COINS", 0);//here is 0 dont forget

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sound = new SoundEffects(this);

        scoreLabel = findViewById(R.id.score_lb);
        startLabel = findViewById(R.id.startLb);
        pauseLb = findViewById(R.id.pause_lb);
        player = findViewById(R.id.player);
        ruby = findViewById(R.id.ruby);
        diamond = findViewById(R.id.diamond);
        gold = findViewById(R.id.gold);
        star = findViewById(R.id.star);
        enemy1 = findViewById(R.id.enemy1);
        enemy2 = findViewById(R.id.enemy2);
        tv_coins = findViewById(R.id.tv_coins);

        player.setImageResource(getResources().getIdentifier("player" + action + "a", "drawable", getPackageName()));

        startLb = (ImageButton) findViewById(R.id.start_lb);

        frameLb = (FrameLayout) findViewById(R.id.frame_lb);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        pauseLb.setEnabled(false);
        frameLb.setVisibility(View.GONE);

        screenWidth = size.x;
        screenHeight = size.y;

        playerSpeed = Math.round(screenWidth / 100);
        goldSpeed = Math.round(screenWidth / 100);
        rubySpeed = Math.round(screenWidth / 90);
        starSpeed = Math.round(screenWidth / 60);
        diamondSpeed = Math.round(screenWidth / 80);
        enemy1Speed = Math.round(screenWidth / 100);
        enemy2Speed = Math.round(screenWidth / 80);

        ruby.setX(-80f);
        ruby.setY(-80f);
        gold.setX(-80f);
        gold.setY(-80f);
        diamond.setX(-80f);
        diamond.setY(-80f);
        star.setX(-80f);
        star.setY(-80f);
        enemy1.setX(-80f);
        enemy1.setY(-80f);
        enemy2.setX(-80f);
        enemy2.setY(-80f);

        scoreLabel.setText("Score: 0");
        tv_coins.setText("" + coins);

    }

    public void position() {
        hit();
        updateLevel();

        //gold positin
        goldX -= goldSpeed;
        if (goldX < 0) {
            goldX = screenWidth + 10;
            goldY = (int) Math.floor(Math.random() * (frameHeight - gold.getHeight()));
        }
        gold.setX(goldX);
        gold.setY(goldY);
        gold.setVisibility(View.VISIBLE);

        //ruby positin
        rubyX -= rubySpeed;
        if (rubyX < 0) {
            rubyX = screenWidth + 20;
            rubyY = (int) Math.floor(Math.random() * (frameHeight - ruby.getHeight()));
        }
        ruby.setX(rubyX);
        ruby.setY(rubyY);
        ruby.setVisibility(View.VISIBLE);


        //enemy1
        enemy1X -= enemy1Speed;
        if (enemy1X < 0) {
            enemy1X = screenWidth + 10;
            enemy1Y = (int) Math.floor(Math.random() * (frameHeight - enemy1.getHeight()));
        }
        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);
        enemy1.setVisibility(View.VISIBLE);

        //enemy2 xuất hiện sau mỗi 4s
        enemy2X -= enemy2Speed;
        if (enemy2X < 0) {
            enemy2X = screenWidth + 4000;
            enemy2Y = (int) Math.floor(Math.random() * (frameHeight - enemy2.getHeight()));
        }
        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);
        enemy2.setVisibility(View.VISIBLE);

        //diamond xuất hiện sau mỗi 5s
        diamondX -= diamondSpeed;
        if (diamondX < 0) {
            diamondX = screenWidth + 5000;
            diamondY = (int) Math.floor(Math.random() * (frameHeight - diamond.getHeight()));
        }
        diamond.setX(diamondX);
        diamond.setY(diamondY);
        diamond.setVisibility(View.VISIBLE);

        //Star xuất hiện sau mỗi 60s
        starX -= starSpeed;
        if (starX < 0) {
            starX = screenWidth + 60000;
            starY = (int) Math.floor(Math.random() * (frameHeight - diamond.getHeight()));
        }
        star.setX(starX);
        star.setY(starY);
        star.setVisibility(View.VISIBLE);
        //

        //player
        if (action_flg == true) {

            playerY -= playerSpeed;
            /*if (action == 1){
                player.setImageResource(R.drawable.player1a);
            } else if (action == 2){
                player.setImageResource(R.drawable.player2a);
            }*/
            // I prefer this
            player.setImageResource(getResources().getIdentifier("player" + action + "a", "drawable", getPackageName()));
            player.setVisibility(View.VISIBLE);

        } else {
            playerY += playerSpeed;
            player.setImageResource(getResources().getIdentifier("player" + action + "b", "drawable", getPackageName()));
            player.setVisibility(View.VISIBLE);
        }

        if (playerY < 0) {
            playerY = 0;
        }

        if (playerY > frameHeight - playerSize) {
            playerY = frameHeight - playerSize;
        }

        player.setY(playerY);

        scoreLabel.setText("Score: " + score);
        tv_coins.setText("" + coins);
    }

    private void updateLevel() {
        if (score >= 50 && score < 100) {
            playerSpeed = Math.round(screenWidth / 75);
            goldSpeed = Math.round(screenWidth / 75);
            rubySpeed = Math.round(screenWidth / 70);
            diamondSpeed = Math.round(screenWidth / 65);
            enemy1Speed = Math.round(screenWidth / 75);
            enemy2Speed = Math.round(screenWidth / 65);
            starSpeed = Math.round(screenWidth / 45);
        }
        if (score >= 100) {
            playerSpeed = Math.round(screenWidth / 50);
            rubySpeed = Math.round(screenWidth / 50);
            rubySpeed = Math.round(screenWidth / 45);
            diamondSpeed = Math.round(screenWidth / 40);
            enemy1Speed = Math.round(screenWidth / 50);
            enemy2Speed = Math.round(screenWidth / 45);
            starSpeed = Math.round(screenWidth / 30);
        }
    }

    public boolean onTouchEvent(MotionEvent me) {

        if (start_flg == false) {

            start_flg = true;

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            playerY = (int) player.getY();

            playerSize = player.getHeight();

            startLabel.setVisibility(View.GONE);

            pauseLb.setEnabled(true);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            position();
                        }
                    });
                }
            }, 0, 20);
        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }

        return true;
    }

    public void hit() {

        //gold hit
        int goldCenterX = goldX + gold.getWidth() / 2;
        int goldCenterY = goldY + gold.getHeight() / 2;

        if (0 <= goldCenterX && goldCenterX <= playerSize &&
                playerY <= goldCenterY && goldCenterY <= playerY + playerSize) {

            score += 1;
            goldX = -10;
            sound.collectSound();
        }

        //ruby hit
        int rubyCenterX = rubyX + ruby.getWidth() / 2;
        int rubyCenterY = rubyY + ruby.getHeight() / 2;

        if (0 <= rubyCenterX && rubyCenterX <= playerSize &&
                playerY <= rubyCenterY && rubyCenterY <= playerY + playerSize) {

            score += 2;
            rubyX = -10;
            sound.collectSound();
        }


        //diamond hit
        int diamondCenterX = diamondX + diamond.getWidth() / 2;
        int diamondCenterY = diamondY + diamond.getHeight() / 2;

        if (0 <= diamondCenterX && diamondCenterX <= playerSize &&
                playerY <= diamondCenterY && diamondCenterY <= playerY + playerSize) {

            coins++;
            score += 3;
            diamondX = -10;
            sound.collectSound();
        }

        //star hit làm biến mất kẻ thù trong 10s
        int starCenterX = starX + star.getWidth() / 2;
        int starCenterY = starY + star.getHeight() / 2;

        if (0 <= starCenterX && starCenterX <= playerSize &&
                playerY <= starCenterY && starCenterY <= playerY + playerSize) {
            enemy1X += 10000;
            enemy1.setX(enemy1X);
            enemy2X += 10000;
            enemy2.setX(enemy2X);
            starX = -10;
            sound.clearSound();
        }

        //enemy1 hit
        int enemy1CenterX = enemy1X + enemy1.getWidth() / 2;
        int enemy1CenterY = enemy1Y + enemy1.getHeight() / 2;

        if (0 <= enemy1CenterX && enemy1CenterX <= playerSize &&
                playerY <= enemy1CenterY && enemy1CenterY <= playerY + playerSize) {

            timer.cancel();
            timer = null;
            sound.loseSound();

            SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("COINS", coins);
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }

        //enemy2 hit
        int enemy2CenterX = enemy2X + enemy2.getWidth() / 2;
        int enemy2CenterY = enemy2Y + enemy2.getHeight() / 2;

        if (0 <= enemy2CenterX && enemy2CenterX <= playerSize &&
                playerY <= enemy2CenterY && enemy2CenterY <= playerY + playerSize) {

            timer.cancel();
            timer = null;
            sound.loseSound();

            SharedPreferences settings = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("COINS", coins);
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }
    }

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

    public void pauseGame(View view) {

        if (pause_flg == false) {
            pause_flg = true;

            timer.cancel();
            timer = null;

            Drawable d = getResources().getDrawable(R.drawable.ic_action_paused);
            pauseLb.setBackgroundDrawable(d);

            frameLb.setVisibility(View.VISIBLE);
        } else {
            pause_flg = false;

            Drawable d = getResources().getDrawable(R.drawable.ic_action_pause);
            pauseLb.setBackgroundDrawable(d);

            frameLb.setVisibility(View.GONE);

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            position();
                        }
                    });
                }
            }, 0, 20);
        }

    }


}
