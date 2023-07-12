package org.example.view.objects;

import org.example.model.board.deck.card.Card;

import java.awt.*;

public class CardView implements Drawable {

    private static final int CARD_HEIGHT = 200;
    private static final int CARD_WIDTH = 100;
//    private static final Image cardImage;

    private final Card card;
    private boolean isVisible;

    public CardView(Card card) {
        this.card = card;
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
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.drawString(cardDescription(), x, y);
    }
}
