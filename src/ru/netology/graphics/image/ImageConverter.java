package ru.netology.graphics.image;

import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import java.net.URL;


public class ImageConverter implements TextGraphicsConverter  {
    private int maxWidth;
    private int maxHeight ;
    private double maxRatio;
    private TextColorSchema schema;

    public ImageConverter (){
        ColorSchema schema = new ColorSchema();
        setTextColorSchema(schema);
        setMaxWidth(0);
        setMaxHeight(0);
        setMaxRatio(0.0);
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {

        BufferedImage img = ImageIO.read(new URL(url));
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        // проверка на максимально допустимое соотношение сторон
        if (this.maxRatio > 0) {
            double ratio = imgWidth * 1.0 / imgHeight;
            if (ratio > maxRatio) {
                throw new BadImageSizeException(ratio, maxRatio);
            }
        }
        // масштабирование при заданной допустимой ширине и/или высоте
        int newWidth = 0;
        int newHeight = 0;
        if (this.maxWidth > 0 && this.maxHeight > 0) { // если заданы максимальные ширина и высота
            double widthRatio = imgWidth * 1.0 / maxWidth;
            double heightRatio = imgHeight * 1.0 / maxWidth;
            double ratio = 0.0;
            ratio = (widthRatio > heightRatio) ?  widthRatio : heightRatio;
            newWidth = (int) (imgWidth / ratio);
            newHeight = (int) (imgHeight / ratio);
        } else if ((this.maxWidth > 0) && (imgWidth > this.maxWidth)) { // если задана максимальная ширина
            double ratio = imgWidth * 1.0 / maxWidth;
            newWidth = (int) (imgWidth / ratio);
            newHeight = (int) (imgHeight / ratio);
        } else if ((this.maxHeight > 0) && (imgHeight > this.maxHeight)) { // если задана максимальная высота
            double ratio = imgHeight * 1.0 / maxWidth;
            newWidth = (int) (imgWidth / ratio);
            newHeight = (int) (imgHeight / ratio);
        } else { // если не заданы максимальные ширина и высота
            newHeight = imgHeight;
            newWidth = imgWidth;
        }

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        //ImageIO.write(imageObject, "png", new File("out.png")); // После вызова этой инструкции у вас в проекте появится файл картинки out.png
        WritableRaster bwRaster = bwImg.getRaster();

        int[] colorArr = new int[3];
        String textImg = "";
        for (int y = 0 ; y < newHeight; y++) {
            for (int x = 0 ; x < newWidth; x++) {
                int color = bwRaster.getPixel(x, y, colorArr)[0];
                char c = this.schema.convert(color);
                textImg = textImg + c + c + c;
            }
            textImg += "\n";
        }

        return textImg; // Возвращаем собранный текст.
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
