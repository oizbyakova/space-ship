package com.example.oizbyakova.myapplication;

import android.content.Context;

import java.util.Random;

final class Asteroid extends SpaceBody {
    private static final int radius = 1; // радиус
    private static final float minSpeed = (float) 0.1; // минимальная скорость
    private static final float maxSpeed = (float) 0.5; // максимальная скорость

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
    public final void update() {
        y += speed;
    }

    final boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x + size) < shipX) || (x > (shipX + shipSize)) || ((y + size) < shipY) || (y > (shipY + shipSize)));
    }
}
