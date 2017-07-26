package com.example.oizbyakova.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

abstract class SpaceBody {

    private Bitmap bitmap; // картинка

    float x; // координаты
    float y;
    float size; // размер
    float speed; // скорость
    int bitmapId; // id картинки

    abstract void update();

    void init(Context context) {
        // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(cBitmap, (int) (size * GameView.unitW), (int) (size * GameView.unitH), false);
        cBitmap.recycle();
    }

    void draw(Paint paint, Canvas canvas) {
        // рисуем картинку
        canvas.drawBitmap(bitmap, x * GameView.unitW, y * GameView.unitH, paint);
    }
}
