package org.example.view.objects;

import java.awt.*;

public interface Drawable {
    /**
     * Draws the object at pos (x, y).
     */
    void draw(Graphics g, int x, int y);
}
