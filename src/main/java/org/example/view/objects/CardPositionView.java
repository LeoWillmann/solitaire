package org.example.view.objects;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.view.BoardView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardPositionView implements Drawable {
    public static final int CARD_VERTICAL_DISTANCE = 30;
    private final CardPosition cardPosition;
    private final List<CardView> cardViews = new ArrayList<>();
    private final Point point = new Point();
    private final boolean showAllCards;
    private final BoardView boardView;

    public CardPositionView(CardPosition cardPosition, boolean showAllCards, BoardView boardView) {
        this.cardPosition = cardPosition;
        this.showAllCards = showAllCards;
        this.boardView = boardView;
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
            CardView cardView = new CardView(card, this);
            cardViews.add(cardView);
            setCardViewPos(cardView);

            boardView.addCardView(cardView);
        }
    }

    private void innitCardViewPositions() {
        for (CardView cardView : cardViews) {
            setCardViewPos(cardView);
        }
    }

    public void setCardViewPos(CardView cardView) {
        if (showAllCards) {
            int index = cardViews.indexOf(cardView);
            if (index == -1) {
                return;
            }
            cardView.setPosition((int) point.getX(), (int) (point.getY() + index * CARD_VERTICAL_DISTANCE));
        }
    }

    public void setPosition(int x, int y) {
        point.setLocation(x, y);
        innitCardViewPositions();
    }

    public List<CardView> getCardsAfter(CardView cardView) {
        int index = cardViews.indexOf(cardView);
        if (index == -1) {
            System.err.println("GetCardsAfter CardView not part of positions. index -1");
            return null;
        }
        return cardViews.subList(index, cardViews.size());
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        g.setColor(Color.GREEN);
        g.fillRect(x, y, CardView.CARD_WIDTH, CardView.CARD_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, CardView.CARD_WIDTH, CardView.CARD_HEIGHT);
    }
}
