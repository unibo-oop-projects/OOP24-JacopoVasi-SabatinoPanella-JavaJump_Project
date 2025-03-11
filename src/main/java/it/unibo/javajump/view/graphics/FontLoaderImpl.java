package it.unibo.javajump.view.graphics;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * The type Font loader.
 */
public class FontLoaderImpl implements FontLoader {
    /**
     * Load font font.
     *
     * @param path the path
     * @param size the size
     * @return the font
     */
    public static Font loadFont(final String path, final float size) {
        try {
            final InputStream is = new FileInputStream(path);
            final Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);

        } catch (Exception e) {

            e.printStackTrace();
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }
}
