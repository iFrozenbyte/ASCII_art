package ru.petproject.converters;

import ru.petproject.interfaces.TextColorSchema;

public class ColorSchemaImpl implements TextColorSchema {
     /*
        35 - #
        36 - $
        64 - @
        37 - %
        42 - *
        43 - +
        45 - -
        39 - '
        */

    @Override
    public char[] getSymbol(int color) {
        char [] symbolsArray = new char[2];
        // для нормального масштабирования символы передаются парно
        if (color <= 32) {
            symbolsArray[0] =  35;
            symbolsArray[1] =  35;
        } else if (color <= 64) {
            symbolsArray[0] =  36;
            symbolsArray[1] =  36;
        } else if (color <= 96) {
            symbolsArray[0] =  64;
            symbolsArray[1] =  64;
        } else if (color <= 128) {
            symbolsArray[0] =  37;
            symbolsArray[1] =  37;
        } else if (color <= 164) {
            symbolsArray[0] =  42;
            symbolsArray[1] =  42;
        } else if (color <= 196) {
            symbolsArray[0] =  43;
            symbolsArray[1] =  43;
        } else if (color <= 228) {
            symbolsArray[0] =  45;
            symbolsArray[1] =  45;
        } else {
            symbolsArray[0] =  39;
            symbolsArray[1] =  39;
        }

        return symbolsArray;
    }
}