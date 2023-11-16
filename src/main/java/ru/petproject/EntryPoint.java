package ru.petproject;

import ru.petproject.converters.SimpleConverter;
import ru.petproject.exceptions.BadImageSizeException;
import ru.petproject.interfaces.TextGraphicsConverter;
import ru.petproject.servers.AsciiArtServer;

import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new SimpleConverter();
        AsciiArtServer server = new AsciiArtServer(converter);
        server.start();


        // для быстрого тестирования конвертации пикселей без участия серверной части
        //simpleConvertWithoutServer(converter);
    }

    private static void simpleConvertWithoutServer(TextGraphicsConverter converter) throws BadImageSizeException, IOException {
        converter.setMaxWidth(500);
        converter.setMaxHeight(500);
        converter.setMaxRatio(3);
        String url1 = "https://w.forfun.com/fetch/03/03f8cd3f6796daaacc1fe43ffb7704b7.jpeg"; // nature size = 761x476
        String url2 = "https://avatanplus.com/files/resources/original/5c73febaab218169251b094d.jpg"; // white = 255 size = 762х762
        String url3 = "https://www.3dnews.ru/assets/external/illustrations/2017/04/03/950114/Black.jpg"; // black = 0 size = 615x870
        String url4 = "https://faizundead.github.io/sites/orange-rabbit/img/raccoon.jpg"; // Enot  size = 300x300
        String url5 = "https://avatars.dzeninfra.ru/get-zen-logos/5398874/pubsuite_94772ffc-12e9-4be8-83eb-3bac89ad10c1_643926494b511555f39af4c7/xxh"; // BW size = 761x870
        String url6 = "https://www.prorobot.ru/gallery/foto/11110041864.jpg"; // Futurama size = 761x570
        String imgTxt = converter.convert(url6);
        System.out.println(imgTxt);
    }
}