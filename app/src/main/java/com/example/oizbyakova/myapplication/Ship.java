package com.example.oizbyakova.myapplication;


import android.content.Context;

final class Ship extends SpaceBody {

    Ship(Context context) {
        bitmapId = R.drawable.ship;
        // определяем начальные параметры
        size = 4;
        x = 7;
        y = GameView.maxY - size - 1;
        speed = (float) 0.2;

        init(context); // инициализируем корабль
    }

    @Override
    public final void update() {
        // перемещаем корабль в зависимости от нажатой кнопки
        if (GameView.isLeftButtonPressed() && x >= 0) {
            x -= speed;
        }
        if (GameView.isRightButtonPressed() && x <= GameView.maxX - 5) {
            x += speed;
        }
    }
}
