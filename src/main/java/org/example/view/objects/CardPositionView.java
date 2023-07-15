package org.example.view.objects;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardPositionView implements Drawable {
    public static final int CARD_VERTICAL_DISTANCE = 30;
    private final CardPosition cardPosition;
    private final List<CardView> cardViews = new ArrayList<>();
    private final Point point = new Point();

    private final boolean showAllCards;

    public CardPositionView(CardPosition cardPosition, boolean showAllCards) {
        this.cardPosition = cardPosition;
        this.showAllCards = showAllCards;
        point.setLocation(0, 0);
        innitCardViews();
    }

    public List<CardView> getCardViews() {
        return cardViews;
    }

    public Point getPoint() {
        return point;
    }

    private void innitCardViews() {
        for (Card card : cardPosition.getCards()) {
            cardViews.add(new CardView(card));
        }
    }

    public void setPosition(int x, int y) {
        point.setLocation(x,y);
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        if (showAllCards) {
            for (CardView cardView : cardViews) {
                cardView.draw(g);
                y += CARD_VERTICAL_DISTANCE;
            }
        } else {
            cardViews.get(cardViews.size() - 1).draw(g);
        }
    }
}
