package org.example.view.objects.cardView;

import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardColor;
import org.example.model.board.deck.card.CardSuit;
import org.example.view.objects.cardPositionView.CardPositionView;
import org.example.view.objects.Drawable;
import org.example.view.util.TextureLoader;

import java.awt.*;

public class CardView implements Drawable {

    public static final int CARD_WIDTH = 120;
    public static final int CARD_HEIGHT = 150;
    private static final Color DEFAULT_COLOR = Color.BLUE;
    private static final Color SELECT_COLOR = Color.RED;
    private final Card card;
    private final Point point = new Point();
    private CardPositionView parent;
    private Color color;

    public CardView(Card card, CardPositionView parent) {
        this.card = card;
        this.parent = parent;
//        parent.setCardViewPos(this);
        if (card.suit().getColor() == CardColor.RED) {
            color = Color.RED;
        } else {
            color = Color.blue;
        }
    }

    public CardPositionView getParent() {
        return parent;
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

    public Card getCard() {
        return card;
    }

    public void setPosition(int x, int y) {
        point.setLocation(x, y);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        g.setColor(color);
        g.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.drawString(cardDescription(), x + 10, y + 20);
        if (card.suit() == CardSuit.DIAMONDS) {
            Image image = TextureLoader.getInstance().getTexture("suitDiamonds", 30, 50);
            g.drawImage(image, x + 30, y + 20, this.parent.getBoardView());

        }
    }
}
