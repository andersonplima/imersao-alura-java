import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class StickerMaker {
    public void make(InputStream inputStream, String outputPath, String[] message) throws IOException {
        var image = ImageIO.read(inputStream);
        var width = 640;
        var height = 480;
        var textAreaHeight = 120;
        var imageHeight = height - textAreaHeight;
        var frameThickness = 5;
        var numberOfLines = 3;
        var lineSpace = 6;
        var fontSize = textAreaHeight/numberOfLines;

        var sticker = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2d = (Graphics2D) sticker.getGraphics();
        graphics2d.drawImage(image, 0, 0, width, imageHeight, null);
        graphics2d.setColor(Color.red);
        graphics2d.setStroke(new BasicStroke(frameThickness));
        graphics2d.drawRect(0, 0, width, height);
        graphics2d.setColor(Color.yellow);
        graphics2d.drawRect(frameThickness, frameThickness, width - 2 * frameThickness, height - 2 * frameThickness);
        var font = new Font(Font.MONOSPACED, Font.BOLD, fontSize);
        graphics2d.setFont(font);
        var fontMetrics = graphics2d.getFontMetrics(font);
        var i = 0;
        for (String string : message) {
            var stringBounds = fontMetrics.getStringBounds(string, graphics2d);
            var stringWidth = (int) stringBounds.getWidth();
            var stringHeight = (int) stringBounds.getHeight();
            graphics2d.drawString(string, (width - stringWidth) / 2, imageHeight + (i + 1) * (lineSpace + stringHeight/2));
            ++i;
        }

        ImageIO.write(sticker, "png", new File(outputPath));
    }
}
