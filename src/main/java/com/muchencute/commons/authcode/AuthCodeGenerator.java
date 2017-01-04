package com.muchencute.commons.authcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@SuppressWarnings("ALL")
public class AuthCodeGenerator {

    private AuthCodeGenerator() {
    }

    public static String generateAuthCode() {
        String[] beforeShuffle = new String[]{"2", "3", "4", "5", "6", "7",
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                "W", "X", "Y", "Z"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder stringBuilder = new StringBuilder();
        for (String aList : list)
            stringBuilder.append(aList);
        String afterShuffle = stringBuilder.toString();
        return afterShuffle.substring(5, 9);
    }

    private static Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public static String generateAuthCodeImage(int width, int height, String authCode,
                                               String absolutePath, String baseURL) throws IOException {
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.setColor(getRandomColor(160, 200));

        Random random = new Random();
        for (int i = 0; i < 155; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(12);
            int y2 = random.nextInt(12);
            g.drawLine(x1, y1, x1 + x2, y1 + y2);
        }

        g.setColor(getRandomColor(100, 150));
        for (int i = 0; i < authCode.length(); i++)
            g.drawString(String.valueOf(authCode.charAt(i)), 13 * i + 6, 16);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = formatter.format(date) + ".png";

        File file = new File(absolutePath);
        if (!file.exists() && !file.isDirectory() && !file.mkdir())
            throw new IOException("Fail to create directory.");
        ImageIO.write(image, "png", new File(absolutePath + filename));
        return baseURL + filename;
    }

}
