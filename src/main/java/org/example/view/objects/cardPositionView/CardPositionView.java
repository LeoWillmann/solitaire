package org.example.view.objects.cardPositionView;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.view.BoardView;
import org.example.view.objects.Drawable;
import org.example.view.objects.cardView.CardView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardPositionView implements Drawable, CardMovementListener {
    public static final int CARD_VERTICAL_DISTANCE = 30;
    public static final int CARD_HORIZONTAL_DISTANCE = 30;
    private final CardPosition cardPosition;
    private final List<CardView> cardViews = new ArrayList<>();
    private final Point point = new Point();
    private final int maxCardDraw;
    private final boolean isVerticalDraw;
    private final BoardView boardView;

    public CardPositionView(CardPosition cardPosition, int maxCardDraw, boolean isVerticalDraw, BoardView boardView) {
        this.cardPosition = cardPosition;
        this.maxCardDraw = maxCardDraw;
        this.isVerticalDraw = isVerticalDraw;
        this.boardView = boardView;

        cardPosition.setCardMovementListener(this);
        innitCardPosition();
    }

    public List<CardView> getCardViews() {
        return cardViews;
    }

    public Point getPoint() {
        return point;
    }

    private void innitCardPosition() {
        boardView.getCardViews().removeAll(cardViews);
        cardViews.clear();
        for (Card card : cardPosition.getCards()) {
            CardView cardView = new CardView(card, this);
            cardViews.add(cardView);
            boardView.addCardView(cardView);
        }
        innitCardViewPositions();
    }

    private void innitCardViewPositions() {
        for (CardView cardView : cardViews) {
            setCardViewPos(cardView);
        }
    }

    public void setCardViewPos(CardView cardView) {
        int index = cardViews.indexOf(cardView);
        if (index == -1) {
            return;
        }
        int x = (int) point.getX();
        int y = (int) point.getY();

        if (maxCardDraw == -1 || cardViews.size() <= maxCardDraw) {
            if (isVerticalDraw) {
                y += index * CARD_VERTICAL_DISTANCE;
            } else {
                x += index * CARD_HORIZONTAL_DISTANCE;
            }
        } else {
            if (cardViews.size() - index <= maxCardDraw) {
                int position = maxCardDraw - cardViews.size() + index;
                if (isVerticalDraw) {
                    y += position * CARD_VERTICAL_DISTANCE;
                } else {
                    x += position * CARD_HORIZONTAL_DISTANCE;
                }
            }
        }
        cardView.setPosition(x, y);
    }

    public void setPosition(int x, int y) {
        point.setLocation(x, y);
        innitCardViewPositions();
    }

    public int placementDistanceFromPoint(Point point) {
        return (int) getTopCardPosition().distance(point);
    }

    private Point getTopCardPosition() {
        if (cardViews.size() == 0) {
            return point;
        } else {
            return getTopCardView().getPoint();
        }
    }

    public CardView getTopCardView() {
        if (cardViews.size() == 0) {
            return null;
        } else {
            return cardViews.get(cardViews.size() - 1);
        }
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public List<CardView> getCardsAfterCard(CardView cardView) {
        int index = cardViews.indexOf(cardView);
        if (index == -1) {
            System.err.println("getCardsAfterCard CardView not part of positions. index -1");
            return null;
        }
        return cardViews.subList(index, cardViews.size());
    }

    public CardPosition getCardPosition() {
        return cardPosition;
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        g.setColor(Color.GREEN);
        g.fillRect(x, y, CardView.CARD_WIDTH, CardView.CARD_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, CardView.CARD_WIDTH, CardView.CARD_HEIGHT);

        for (CardView cardView : cardViews) {
            cardView.draw(g);
        }
    }

    @Override
    public void updateView() {
        innitCardPosition();
        boardView.notifyListener();
    }
}
