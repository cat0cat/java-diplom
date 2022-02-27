package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema{
    @Override
    public char convert(int color) {

        char[] symbols = { '#', '$', '@', '%', '*', '+', '-', '\'' };
        int step = 256 / symbols.length;

        return symbols[color / step];
    }
}
