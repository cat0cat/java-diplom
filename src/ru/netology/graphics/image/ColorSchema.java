package ru.netology.graphics.image;

public class ColorSchema implements TextColorSchema{

    private char[] symbols = { '#', '$', '@', '%', '*', '+', '-', '\'' };
    //private char[] symbols = { '▇', '●', '◉', '◍', '◎', '○', '☉', '◌', '-' };
    private int step = 0;

    public ColorSchema(){
        this.step = 256 / symbols.length;
    }

    @Override
    public char convert(int color) {
        return symbols[color / step];
    }
}
