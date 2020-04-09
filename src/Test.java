import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Test {
    public static void main(String[] args) throws Exception {

        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_BINARY);
        LineDrawer ld = new LineDrawer(image);
        ld.BresenhamAlgorithm(10, 20, 60, 99);
        File f = new File("test.png");
        ImageIO.write(image, "png", f);


    }
}
