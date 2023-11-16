package ru.petproject.converters;

import ru.petproject.exceptions.BadImageSizeException;
import ru.petproject.interfaces.TextGraphicsConverter;

import javax.imageio.ImageIO;
import org.imgscalr.Scalr;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class SimpleConverter implements TextGraphicsConverter {
    private int maxWidth, maxHeight;
    private double maxRatio;
    private final ColorSchemaImpl colorSchema = new ColorSchemaImpl();


    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        StringBuilder stringBuilder = new StringBuilder(Math.max(maxWidth, maxHeight)); // немного сэкономим время, указав capacity
        BufferedImage image = ImageIO.read(new URL(url));
        ImageIO.write(image, "png", new File("src/main/java/ru/petproject/images/original.png")); // сохранили оригинальную картинку на комп
        BufferedImage resizePic = checkAndResizePicture(image);
        ImageIO.write(resizePic, "png", new File("src/main/java/ru/petproject/images/resizePicture.png")); // сохранили resize-картинку на комп
        BufferedImage decolorizedPicture = desaturateImage(resizePic); // Обесцветили
        ImageIO.write(decolorizedPicture, "png", new File("src/main/java/ru/petproject/images/decolorizedPicture.png")); // сохранили Ч/Б картинку на комп
        WritableRaster bwRaster = decolorizedPicture.getRaster(); // для прохода по пикселям нужен этот инструмент

        int[] pixel = null;
        for (int heightCount = 0; heightCount < decolorizedPicture.getHeight(); heightCount++) {
            for (int i = 0; i < decolorizedPicture.getWidth(); i++) {
                pixel = bwRaster.getPixel(i, heightCount, pixel);
                stringBuilder.append(colorSchema.getSymbol(pixel[0]));
            }
            stringBuilder.append("\n");
        }
        File file = new File("src/main/java/ru/petproject/images/readyPic.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(stringBuilder);
        }
        return String.valueOf(stringBuilder);

    }

    public static BufferedImage desaturateImage(BufferedImage source) {
        ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        colorConvert.filter(source, source);
        return source;
    }

    private BufferedImage checkAndResizePicture(BufferedImage sourceImage) throws BadImageSizeException {
        if (maxRatio != 0) {
            double sourceRatio = Math.ceil((double) sourceImage.getWidth() / sourceImage.getHeight());
            if (sourceRatio > maxRatio) {
                throw new BadImageSizeException(sourceRatio, maxRatio);
            }
        }
        if ((maxWidth != 0 && maxHeight != 0) && (sourceImage.getWidth() > maxWidth || sourceImage.getHeight() > maxHeight)) {
            return Scalr.resize(sourceImage, Scalr.Method.BALANCED, Scalr.Mode.AUTOMATIC, maxWidth, maxHeight);
        } else {
            return sourceImage;
        }
    }

    @Override
    public void setMaxWidth(int width) {
        this.maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }
}
