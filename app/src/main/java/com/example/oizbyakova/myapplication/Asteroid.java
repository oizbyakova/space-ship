package com.example.oizbyakova.myapplication;

import android.content.Context;

import java.util.Random;

class Asteroid extends SpaceBody {
    private int radius = 2; // радиус
    private float minSpeed = (float) 0.1; // минимальная скорость
    private float maxSpeed = (float) 0.5; // максимальная скорость

    Asteroid(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.asteroid;
        y = 0;
        x = random.nextInt(GameView.maxX) - radius;

        if (x <= 0)
            x = 1;

        size = radius * 2;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    public void update() {
        y += speed;
    }

    boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x + size) < shipX) || (x > (shipX + shipSize)) || ((y + size) < shipY) || (y > (shipY + shipSize)));
    }
}
