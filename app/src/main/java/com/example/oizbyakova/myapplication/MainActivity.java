package com.example.oizbyakova.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public final class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    public static boolean isLeftPressed = false; // нажата левая кнопка
    public static boolean isRightPressed = false; // нажата правая кнопка

    private GameView gameView;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        CreateUi();
    }

    private void CreateUi() {
        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        assert gameLayout != null;
        gameLayout.setOnTouchListener(this);

        Button leftButton = (Button) findViewById(R.id.leftButton); // находим кнопки
        assert leftButton != null;
        leftButton.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)

        Button rightButton = (Button) findViewById(R.id.rightButton);
        assert rightButton != null;
        rightButton.setOnTouchListener(this);

        //TODO Move Game instance to Application class
        gameView = new GameView(BaseApplication.getInstance()); // создаём gameView

        gameLayout.addView(gameView); // и добавляем в него gameView
    }

    @Override
    public final boolean onTouch(View button, MotionEvent motion) {
        switch (button.getId()) { // определяем какая кнопка
            case R.id.leftButton:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.rightButton:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
            case R.id.gameLayout:
                if (!gameView.isGameRunning()) {
                    gameView.startNewGame();
                }
                break;
        }
        return true;
    }
}