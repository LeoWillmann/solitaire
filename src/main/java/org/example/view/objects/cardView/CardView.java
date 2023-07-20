package org.example.view.objects.cardView;

import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.view.objects.cardPositionView.CardPositionView;
import org.example.view.objects.Drawable;
import org.example.view.util.TextureLoader;

import java.awt.*;

public class CardView implements Drawable {

    public static final int CARD_WIDTH = 120;
    public static final int CARD_HEIGHT = 150;
    private final Card card;
    private final Point point = new Point();
    private final CardPositionView parent;
    private final String textureName;
    private final Color color;

    public CardView(Card card, CardPositionView parent) {
        this.card = card;
        this.parent = parent;
        color = card.suit().getColor();
        textureName = "suit" + card.suit().getDescription();
    }

    public CardPositionView getParent() {
        return parent;
    }

    public Point getPoint() {
        return point;
    }

    public String cardDescription() {
        String valueName = switch (card.cardValue()) {
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            default -> String.valueOf(card.cardValue());
        };
        return valueName;
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
        g.setColor(Color.WHITE);
        g.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(color);
        g.setFont(new Font("Helvetica Bold", Font.BOLD, 20));
        g.drawString(cardDescription(), x, y + 20);
        Image image = TextureLoader.getInstance().getTexture(textureName, 20, 20);
        g.drawImage(image, x, y + 20, this.parent.getBoardView());

    }
}
