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

package com.testvagrant.monitor.performance;

import com.testvagrant.monitor.utils.ImageResizer;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class CompressImageTest {

    private static final int IMG_WIDTH = 303;
    private static final int IMG_HEIGHT = 550;

    public static void main(String [] args){

        try{
//
            BufferedImage originalImage = ImageIO.read(new File("/Users/krishnanand/Development/TestVagrant/optimus-eco-system/optimusmonitor/src/test/java/resources/1.png"));

            File file = new File("/Users/krishnanand/Development/TestVagrant/optimus-eco-system/optimusmonitor/src/test/java/resources/1.png");
            byte[] bytes = new ImageResizer(file).resizeImage();
            int length = bytes.length;
            System.out.println(length);
            System.out.println(ImageResizer.getImageBytes(originalImage).length);
            System.out.println(getByte(originalImage).length);
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
            ImageIO.write(img,"png",new File("/Users/krishnanand/Development/TestVagrant/optimus-eco-system/optimusmonitor/src/test/java/resources/2.png"));


//            BufferedImage resizeImage = Thumbnails.of(originalImage).scale(0.4d).scalingMode(ScalingMode.BICUBIC).alphaInterpolation(AlphaInterpolation.QUALITY).asBufferedImage();
//            ImageIO.write(resizeImage, "png", new File("/Users/krishnanand/Development/TestVagrant/optimus-eco-system/optimusmonitor/src/test/java/resources/download9.png"));

        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }


    private static byte[] getByte(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "png", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){

        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }
}
