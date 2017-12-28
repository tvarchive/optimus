/*
 * Copyright (c) 2017.  TestVagrant Technologies
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.testvagrant.monitor.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageComparator {

    public static double getImageDifference(File file1, File file2) {
        try {
            BufferedImage image1 = null;
            BufferedImage image2 = null;
            try {
                image1 = ImageIO.read(file1);
                image2 = ImageIO.read(file2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int width1 = image1.getWidth(null);
            int height1 = image1.getHeight(null);
            long diff = 0;
            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    int rgb1 = image1.getRGB(x, y);
                    int rgb2 = image2.getRGB(x, y);
                    int r1 = (rgb1 >> 16) & 0xff;
                    int g1 = (rgb1 >> 8) & 0xff;
                    int b1 = (rgb1) & 0xff;
                    int r2 = (rgb2 >> 16) & 0xff;
                    int g2 = (rgb2 >> 8) & 0xff;
                    int b2 = (rgb2) & 0xff;
                    diff += Math.abs(r1 - r2);
                    diff += Math.abs(g1 - g2);
                    diff += Math.abs(b1 - b2);
                }
            }
            double n = width1 * height1 * 3;
            double p = diff / n / 255.0;
            return p * 100.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}
