import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.imageio.ImageIO;

public class StickersGenerator {
    public void cria() throws IOException {
        var image = ImageIO.read(new File("entrada/filme.jpg"));
        var width = image.getWidth();
        var height = image.getHeight();

        var newHeight = height + 200;

        var sticker = new BufferedImage(width, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2d = (Graphics2D)sticker.getGraphics();
        graphics2d.drawImage(image, 0, 0, null);
        graphics2d.setColor(Color.red);
        graphics2d.setStroke(new BasicStroke(10));
        graphics2d.drawRect(0, 0, width, newHeight);
        graphics2d.setColor(Color.yellow);
        graphics2d.drawRect(10, 10, width - 20, newHeight - 20);

        ImageIO.write(sticker, "png", new File("saida/filme.png"));
    }
}
