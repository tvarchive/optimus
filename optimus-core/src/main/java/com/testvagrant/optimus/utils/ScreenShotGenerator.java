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

package com.testvagrant.optimus.utils;


import com.testvagrant.commons.entities.SmartBOT;
import org.openqa.selenium.OutputType;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScreenShotGenerator {

    private List<SmartBOT> smartBOTs;

    public ScreenShotGenerator(List<SmartBOT> smartBOTs) {
        this.smartBOTs = smartBOTs;
    }

    public byte[] getFailureImage() throws IOException {
        List<BufferedImage> images = new ArrayList<>();
        for (SmartBOT smartBOT : smartBOTs) {
            File screenshotAs = smartBOT.getDriver().getScreenshotAs(OutputType.FILE);
            BufferedImage bufferedImage = ImageIO.read(screenshotAs);
            images.add(bufferedImage);
        }
        BufferedImage joinedImg = joinBufferedImage(images);
        return getBytes(joinedImg);
    }


    private byte[] getBytes(BufferedImage joinedImg) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(joinedImg, "png", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    public static BufferedImage joinBufferedImage(List<BufferedImage> images) {

        //do some calculate first
        int offset = 5;
//        int wid = img1.getWidth() + img2.getWidth() + offset;
//        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        int width = offset;
        for (BufferedImage image : images) {
            width = width + image.getWidth();
        }

        List<Integer> heightList = new ArrayList<>();
        for (BufferedImage image : images) {
            int imageHeight = image.getHeight();
            heightList.add(imageHeight);
        }

        Integer height = Collections.max(heightList) + offset;

        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        //draw image
        g2.setColor(oldColor);


        for (int imageIterator = 0; imageIterator < images.size(); imageIterator++) {
            if (imageIterator == 0)
                g2.drawImage(images.get(imageIterator), null, 0, 0);
            else
                g2.drawImage(images.get(imageIterator), null, getWidthUptoLastImage(images, imageIterator) + offset, 0);
        }
        g2.dispose();
        return newImage;
    }

    private static int getWidthUptoLastImage(List<BufferedImage> images, int imageIterator) {
        int width = 0;
        for (int iterator = 0; iterator < imageIterator; iterator++) {
            width = width + images.get(iterator).getWidth();
        }
        return width;
    }
}
