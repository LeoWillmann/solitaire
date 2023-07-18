package org.example.view.objects;

import org.example.model.board.deck.CardDeck;
import org.example.view.objects.cardView.CardView;

import java.awt.*;

public class CardDeckView implements Drawable {
    private final CardDeck deck;

    private final Point point = new Point();

    public CardDeckView(CardDeck deck) {
        this.deck = deck;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public Point getPoint() {
        return point;
    }

    public void setPosition(int x, int y) {
        point.setLocation(x, y);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        g.setColor(Color.orange);
        g.fillRect(x, y, CardView.CARD_WIDTH, CardView.CARD_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, CardView.CARD_WIDTH, CardView.CARD_HEIGHT);
    }
}
