package com.hn;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {


    int N = 30, M = 20;
    int size = 16;
    int w = size * N;
    int h = size * M;

    PImage img1, img2, img3;

    int dir = 0, num = 10;
    Snake[] s = new Snake[100];

    Fruit f = new Fruit(10, 10);

    int timer = 0, delay = 100;
    int timer_start;

    public void settings() {
        size(w, h);
    }

    public void setup() {
        smooth();

        img1 = loadImage("images/white.png");
        img2 = loadImage("images/red.png");
        img3 = loadImage("images/green.png");

        for (int i = s.length - 1; i >= 0; i--) {
            s[i] = new Snake();
        }

        timer_start = millis();
    }

    public static void main(String[] args) {
        PApplet.main("com.hn.Main", args);
    }


    public void draw() {
        background(255);

        fill(100);

        text("Test card: #", width * 0.18f, height * 0.89f);

        //time
        timer = millis() - timer_start;
        if (timer >= delay) {
            timer = 0;
            timer_start = millis();
            tick();
        }

        text(timer, 20, 20);


        // draw field
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                image(img1, i * size, j * size, size, size);
            }
        }


        //draw snake
        for (int i = 0; i < num; i++) {
            image(img2, s[i].x * size, s[i].y * size, size, size);
        }


        //draw fruct
        image(img3, f.x * size, f.y * size, size, size);

    }


    public void tick() {
        //tail
        for (int i = num; i > 0; i--) {
            s[i].x = s[i - 1].x;
            s[i].y = s[i - 1].y;
        }

        //move
        if (dir == 0) s[0].y += 1;
        if (dir == 1) s[0].x -= 1;
        if (dir == 2) s[0].x += 1;
        if (dir == 3) s[0].y -= 1;

        //fruct collision
        if ((s[0].x == f.x) && (s[0].y == f.y)) {
            num++;
            f.x = ((int) random(N));
            f.y = ((int) random(M));
        }

        //borders
        if (s[0].x > N) s[0].x = 0;
        if (s[0].x < 0) s[0].x = N;

        if (s[0].y > M) s[0].y = 0;
        if (s[0].y < 0) s[0].y = M;


        for (int i = 1; i < num; i++) {
            if (s[0].x == s[i].x && s[0].y == s[i].y) num = i;
        }
    }

    public void mousePressed() {

    }

    public void keyPressed() {
        if (key == CODED) {
            System.out.println("input");
            if (keyCode == LEFT) {
                if (dir != 2)
                    dir = 1;
            }
            if (keyCode == RIGHT) {
                if (dir != 1)
                    dir = 2;
            }
            if (keyCode == UP) {
                if (dir != 0)
                    dir = 3;
            }
            if (keyCode == DOWN) {
                if (dir != 3)
                    dir = 0;
            }
        }
    }

}
