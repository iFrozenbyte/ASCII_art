package ru.petproject.interfaces;

import ru.petproject.exceptions.BadImageSizeException;

import java.io.IOException;

public interface TextGraphicsConverter {
    /**
     * Конвертация изображения, переданного как урл, в текстовую графику (ASCII symbols).
     * @param url урл изображения
     * @return текст, представляющий собой текстовую графику переданного изображения
     * @throws IOException
     * @throws BadImageSizeException Если соотношение сторон изображения отличается от параметра ratio в большую сторону
     */
    String convert(String url) throws IOException, BadImageSizeException;
    void setMaxWidth(int width);
    void setMaxHeight(int height);

    /**
     * Устанавливает максимально допустимое соотношение сторон исходного изображения.
     * Если исходное изображение имеет характеристики, превышающие максимальные,
     * при конвертации выбрасывается исключение.
     * @param maxRatio
     */
    void setMaxRatio(double maxRatio);
}
