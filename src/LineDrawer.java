import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class LineDrawer {
    BufferedImage image;

    public LineDrawer(BufferedImage image) {
        this.image = image;
    }

    public void LinearFunction(int x1, int y1, int x2, int y2) {
        if (x2 < x1) {
            int tmp = x2;
            int tmp2 = y2;
            x2 = x1;
            y2 = y1;
            x1 = tmp;
            y1 = tmp2;
        }

        WritableRaster raster = image.getRaster();

        double a = ((double) y2 - y1) / (x2 - y1);
        double b = y1 - a * x1;

        int y;
        for (int x = x1; x <= x2; x++) {
            y = (int) Math.round(a * x + b);
            if (y < image.getWidth()) {
                raster.setPixel(x, y, new int[]{1});
            }
        }
    }


    public void BresenhamAlgorithm(int x1, int y1, int x2, int y2) {
        WritableRaster raster = this.image.getRaster();

        int kx = (x1 <= x2) ? 1 : -1;
        int ky = (y1 <= y2) ? 1 : -1;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int curr_x = x1;
        int curr_y = y1;

        raster.setPixel(curr_x, curr_y, new int[]{1});

        double e;
        if (!(dx < dy)) {
            e = (double) dx / 2;
            for (int i = 0; i < dx; i++) {
                curr_x = curr_x + kx;
                e = e - dy;
                if (!(e >= 0)) {
                    curr_y = curr_y + ky;
                    e = e + dx;
                }
                if (curr_x < image.getWidth() && curr_y < image.getHeight()) {
                    raster.setPixel(curr_x, curr_y, new int[]{1});
                }
            }
        } else {
            e = (double) dy / 2;
            for (int i = 0; i < dy; i++) {
                curr_y = curr_y + ky;
                e = e - dx;
                if (!(e >= 0)) {
                    curr_x = curr_x + kx;
                    e = e + dy;
                }
                if (curr_x < image.getWidth() && curr_y < image.getHeight()) {
                    raster.setPixel(curr_x, curr_y, new int[]{1});
                }
            }

        }
    }

    public void ELFA(int x1, int y1, int x2, int y2) {
        if (x2 < x1) {
            int tmp = x2;
            int tmp2 = y2;
            x2 = x1;
            y2 = y1;
            x1 = tmp;
            y1 = tmp2;
        }


        WritableRaster raster = this.image.getRaster();
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int kx = 1;
        double v = 32.768 + 65.536 * (double) y1;
        double i = 65.536 * (double) dy / (double) dx;
        i = (y1 <= y2) ? i : -i;
        int curr_x = x1;
        while (curr_x <= x2) {
            int curr_y = (int) (v / 65.536);
            if (curr_x <= image.getWidth() && curr_y <= image.getHeight())
                raster.setPixel(curr_x, curr_y, new int[]{1});
            v = v + i;
            curr_x = curr_x + kx;
        }

    }


}

