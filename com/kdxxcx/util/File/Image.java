package com.kdxxcx.util.File;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

import static com.kdxxcx.util.KDXXCX.ExceptionDealing.IOExceptionDealing;

public class Image {
    public java.awt.Image getImagePath(String resource){
        java.awt.Image image=null;
        InputStream is = (InputStream) this.getClass().getClassLoader().getResourceAsStream(resource);
        try {
            assert is != null;
            image = ImageIO.read(is);
        } catch (IOException e) {
            IOExceptionDealing(e);
        }
        return image;
    }
}
