package org.example.view.objects.cardView;

import org.example.model.board.deck.card.Card;
import org.example.view.objects.cardPositionView.CardPositionView;
import org.example.view.objects.Drawable;
import org.example.view.util.TextureLoader;

import java.awt.*;

public class CardView implements Drawable {

    public static final int CARD_WIDTH = 120;
    public static final int CARD_HEIGHT = 150;
    public static final int BORDER_SPACING = 2;
    public static final int TEXT_ICON_SPACING = 3;
    public static final int TEXT_SIZE = 30;
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
            case 1 -> "Ace";
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
        drawCornerIcons(g);
    }

    private void drawCornerIcons(Graphics g) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        g.setColor(color);
        g.setFont(new Font("Helvetica Bold", Font.BOLD, TEXT_SIZE));
        Image image = TextureLoader.getInstance().getTexture(textureName, TEXT_SIZE - TEXT_ICON_SPACING, TEXT_SIZE - TEXT_ICON_SPACING);
//
        g.drawString(cardDescription(), x + BORDER_SPACING, y + TEXT_SIZE - TEXT_ICON_SPACING);
        g.drawImage(image, x + CARD_WIDTH - BORDER_SPACING - image.getWidth(this.parent.getBoardView()), y + BORDER_SPACING, this.parent.getBoardView());

        if (hasFaceCardImage()) {
//            drawCenterImage(g, x, y);
        } else {
//            drawCenterImage(g, x, y);
        }
    }

    private void drawCenterImage(Graphics g, int x, int y) {
        int size = CARD_WIDTH * 3 / 4;
        System.out.println(size);
        Image centerImage = TextureLoader.getInstance().getTexture(textureName, size, size);
        int xOffset = (CARD_HEIGHT - size) / 2;
        int yOffset = (CARD_WIDTH - size) / 2;
        g.drawImage(centerImage, x + xOffset, y + yOffset, this.parent.getBoardView());
    }


    private boolean hasFaceCardImage() {
        return false;
    }
}
