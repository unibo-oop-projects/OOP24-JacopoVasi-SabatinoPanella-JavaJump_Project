package it.unibo.javajump.view.graphics;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Font loader.
 */
public final class FontLoaderImpl implements FontLoader {
    /**
     * Load font font.
     *
     * @param path the path
     * @param size the size
     * @return the font
     */
    private static final Logger LOGGER = Logger.getLogger(FontLoaderImpl.class.getName());

    public static Font loadFont(final String path, final float size) {
        try {
            final InputStream is = new FileInputStream(path);
            final Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);

        } catch (IOException | FontFormatException e) {
            // Log the error instead of printing stack trace
            LOGGER.log(Level.SEVERE, "Error loading font from path: " + path, e);
            // Fallback to default font
            return new Font("Arial", Font.PLAIN, (int) size);
        }
    }

    /**
     * Private constructor for utility class, it should not be instantiated.
     *
     * @throws AssertionError the assertion error if it is instantiated
     */
    private FontLoaderImpl() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }
}
