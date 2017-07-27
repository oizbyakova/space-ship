package com.example.oizbyakova.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public final class GameView extends SurfaceView implements Runnable {

    private static final int ASTEROID_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)

    private static boolean isLeftButtonPressed = false; // нажата левая кнопка
    private static boolean isRightButtonPressed = false; // нажата правая кнопка

    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали


    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Ship ship;
    private Paint paint;
    private SurfaceHolder surfaceHolder;

    private ArrayList<Asteroid> asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private int currentTime = 0;

    public GameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();

        startNewGame();
    }

    public final boolean isGameRunning() {
        return gameRunning;
    }

    public static boolean isLeftButtonPressed() {
        return isLeftButtonPressed;
    }

    public static void setLeftButtonPressed(boolean leftButtonPressed) {
        isLeftButtonPressed = leftButtonPressed;
    }

    public static boolean isRightButtonPressed() {
        return isRightButtonPressed;
    }

    public static void setRightButtonPressed(boolean rightButtonPressed) {
        isRightButtonPressed = rightButtonPressed;
    }

    public final void startNewGame(){
        //инициализируем игру
        firstTime = true;
        asteroids = new ArrayList<>();

        gameRunning = true;

        // инициализируем поток
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public final void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewAsteroid();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            ship.update();

            for (Asteroid asteroid : asteroids) {
                asteroid.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                ship = new Ship(getContext()); // добавляем корабль
            }

            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK); // заполняем фон чёрным

            ship.draw(paint, canvas); // рисуем корабль

            for (Asteroid asteroid : asteroids) {
                asteroid.draw(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){ // перебираем все астероиды и проверяем не касается ли один из них корабля
        for (Asteroid asteroid : asteroids) {
            if(asteroid.isCollision(ship.x, ship.y, ship.size)){
                // игрок проиграл
                gameRunning = false; // останавливаем игру
                // TODO добавить анимацию взрыва
            }
        }
    }

    private void checkIfNewAsteroid(){ // каждые 50 итераций добавляем новый астероид
        if(currentTime >= ASTEROID_INTERVAL){
            Asteroid asteroid = new Asteroid(getContext());
            asteroids.add(asteroid);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }
}