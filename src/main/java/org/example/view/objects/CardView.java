package org.example.view.objects;

import org.example.model.board.deck.card.Card;

import java.awt.*;

public class CardView implements Drawable {

    public static final int CARD_WIDTH = 120;
    public static final int CARD_HEIGHT = 150;
    private static final Color DEFAULT_COLOR = Color.BLUE;
    private static final Color SELECT_COLOR = Color.RED;
    //    private static final Image cardImage;
    private final Point point = new Point();
    private final Card card;
    private Color color;

    public CardView(Card card) {
        this.card = card;
        point.setLocation(0, 0);
        color = DEFAULT_COLOR;
    }

    public void selectColor() {
        color = SELECT_COLOR;
    }

    public void defaultColor() {
        color = DEFAULT_COLOR;
    }

    public Point getPoint() {
        return point;
    }

    public String cardDescription() {
        String valueName = switch (card.cardValue()) {
            case 1 -> "ace";
            case 11 -> "jack";
            case 12 -> "queen";
            case 13 -> "king";
            default -> String.valueOf(card.cardValue());
        };
        return valueName + " of " + String.valueOf(card.suit()).toLowerCase();
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        point.setLocation(x, y);
        g.setColor(color);
        g.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.drawString(cardDescription(), x + 10, y + 20);
    }
}
