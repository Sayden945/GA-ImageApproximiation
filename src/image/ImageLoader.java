package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ImageLoader {
    private ImageLoader() {}

    public static BufferedImage load(String path) throws IOException{
        BufferedImage img = ImageIO.read(new File(path));
        if(img == null) throw new IOException("Unreadable image: " + path);
        return img;
    }
}
