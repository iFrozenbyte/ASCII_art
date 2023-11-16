package ru.petproject.exceptions;

public class BadImageSizeException extends Exception {
    public BadImageSizeException(double ratio, double maxRatio) {
        super("Максимально допустимое соотношение сторон изображения: " + maxRatio + ", у текущего изображения соотношение сторон равно: " + ratio);
    }
}