package com.example.oizbyakova.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public final class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    protected GameView gameView;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        CreateUi();
    }

    private void CreateUi() {
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        //noinspection ConstantConditions
        gameLayout.setOnTouchListener(this);

        Button leftButton = (Button) findViewById(R.id.leftButton); // находим кнопки
        //noinspection ConstantConditions
        leftButton.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)

        Button rightButton = (Button) findViewById(R.id.rightButton);
        //noinspection ConstantConditions
        rightButton.setOnTouchListener(this);

        //TODO Move Game instance to Application class
        gameView = new GameView(BaseApplication.getInstance()); // создаём gameView

        gameLayout.addView(gameView); // и добавляем в него gameView
    }

    @Override
    public final boolean onTouch(View button, MotionEvent motion) {
        switch (button.getId()) { // определяем какая кнопка
            case R.id.leftButton:
                processLeftButton(motion);
                break;
            case R.id.rightButton:
                processRightButton(motion);
                break;
            case R.id.gameLayout:
                processGameFieldTouch();
                break;
        }
        return true;
    }

    private void processGameFieldTouch() {
        if (!gameView.isGameRunning()) {
            gameView.startNewGame();
        }
    }

    private void processRightButton(MotionEvent motion) {
        switch (motion.getAction()) {
            case MotionEvent.ACTION_DOWN:
                GameView.setRightButtonPressed(true);
                break;
            case MotionEvent.ACTION_UP:
                GameView.setRightButtonPressed(false);
                break;
        }
    }

    private void processLeftButton(MotionEvent motion) {
        switch (motion.getAction()) {
            case MotionEvent.ACTION_DOWN:
                GameView.setLeftButtonPressed(true);
                break;
            case MotionEvent.ACTION_UP:
                GameView.setLeftButtonPressed(false);
                break;
        }
    }
}