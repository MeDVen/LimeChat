package org.klaptech.limechat.client.gui.components;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;


/**
 * Captcha control (wrapper of canvas)
 * Click for change value
 *
 * @author rlapin
 */
public class CaptchaView {
    public static final int MIN_LINE_COUNT = 10;
    public static final int LINE_COUNT = 10;
    private final int width;
    private final int height;
    /**
     * Num of signs in captcha
     */
    private final int SIGN_COUNT = 4;
    private Canvas canvas;
    private String value;

    public CaptchaView(int width, int height) {
        this.width = width;
        this.height = height;
        canvas = new Canvas(width, height);
        canvas.setOnMouseClicked(event -> generateValue());
        generateValue();
    }

    private void generateValue() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SIGN_COUNT; i++) {
            stringBuilder.append(getRandomChar());
        }
        value = stringBuilder.toString();
        updateView();
    }

    /**
     * @return random digit/letter(uppercase or lowercase)
     */
    private char getRandomChar() {
        Random random = new Random();
        int randomType = random.nextInt(2);
        switch (randomType) {
            case 0:
                //digit
                return (char) ('0' + new Random().nextInt(10));
            case 1:
                //lower letter
                return (char) ('a' + new Random().nextInt('Z' - 'A'));
            default:
                //upper letter
                return (char) ('A' + new Random().nextInt('Z' - 'A'));
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void updateView() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLUE);
        gc.fillRect(0, 0, width, height);
        int fontSize = height / 3;
        gc.setFont(Font.font(fontSize));
        Random random = new Random();
        for (int i = 0; i < SIGN_COUNT; i++) {
            Color color = randomColor();
            gc.setFill(color);
            gc.fillText(String.valueOf(value.charAt(i)), i * fontSize / 2, (height + fontSize) / 2);

        }
        int lineCount = random.nextInt(LINE_COUNT) + MIN_LINE_COUNT;

        gc.setLineWidth(1);
        for (int i = 0; i < lineCount; i++) {
            gc.setStroke(randomColor());
            gc.strokeLine(0, random.nextInt(height), width, random.nextInt(height));
        }

    }

    private Color randomColor() {
        Random random = new Random();
        return new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1);
    }


}
