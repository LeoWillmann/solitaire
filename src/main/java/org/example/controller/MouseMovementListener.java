package org.example.controller;

import org.example.model.board.deck.card.Card;
import org.example.view.BoardView;
import org.example.view.objects.CardPositionView;
import org.example.view.objects.CardView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static org.example.view.objects.CardPositionView.CARD_VERTICAL_DISTANCE;

public class MouseMovementListener extends MouseAdapter {

    private final BoardView boardView;
    private List<CardView> cardViewList = null;

    private int xOffset;
    private int yOffset;


    public MouseMovementListener(BoardView boardView) {
        this.boardView = boardView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        CardView cardView = getCardView(e);
        if (cardView != null) {

            cardViewList = cardView.getParent().getCardsAfter(cardView);
            xOffset = (int) (e.getX() - cardView.getPoint().getX());
            yOffset = (int) (e.getY() - cardView.getPoint().getY());
//            boardView.notifyListener();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     * @since 1.6
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (cardViewList != null) {
            // move cards
            setPositionsOfCardList(e);
            boardView.notifyListener();
        }
    }

    private void setPositionsOfCardList(MouseEvent e) {
        int y = e.getY() - yOffset;
        for (CardView cardView : cardViewList) {
            cardView.setPosition(e.getX() - xOffset, y);
            y += CARD_VERTICAL_DISTANCE;
        }
        boardView.setCardViewsToTop(cardViewList);
    }

    private CardView getCardView(MouseEvent e) {
        CardView cardClicked = null;
        for (CardView cardView : boardView.getCardViews()) {
            int x1 = (int) cardView.getPoint().getX();
            int y1 = (int) cardView.getPoint().getY();
            int x2 = x1 + CardView.CARD_WIDTH;
            int y2 = y1 + CardView.CARD_HEIGHT;
            if (e.getX() >= x1 && e.getX() <= x2 && e.getY() >= y1 && e.getY() <= y2) {
                cardClicked = cardView;
            }
        }
        return cardClicked;
    }

    private CardView getCardViewFromCardPosition(MouseEvent e) {
        CardPositionView cardPositionView = getCardPositionView(e);
        if (cardPositionView == null) {
            return null;
        }
        CardView cardViewed = null;
        for (CardView cardView : cardPositionView.getCardViews()) {
            int y1 = (int) cardView.getPoint().getY();
            int y2 = y1 + CardView.CARD_HEIGHT;
            if (e.getY() >= y1 && e.getY() <= y2) {
                cardViewed = cardView;
            }
        }
        return cardViewed;
    }

    /**
     * {@inheritDoc}
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        resetCardViewListPositions();
        cardViewList = null;
    }

    private void resetCardViewListPositions() {
        if (cardViewList == null) {
            return;
        }
        for (CardView cardView : cardViewList) {
            cardView.getParent().setCardViewPos(cardView);
        }
        boardView.notifyListener();
    }

    private CardPositionView getCardPositionView(MouseEvent e) {
        for (CardPositionView cardPositionView : boardView.getCardPositionViews()) {
            if (cardPositionView.getCardViews().size() > 0) {
                int x1 = (int) cardPositionView.getPoint().getX();
                int y1 = (int) cardPositionView.getPoint().getY();
                int x2 = x1 + CardView.CARD_WIDTH;
                int y2 = y1 + (cardPositionView.getCardViews().size() - 1) * CARD_VERTICAL_DISTANCE + CardView.CARD_HEIGHT;
                if (e.getX() >= x1 && e.getX() <= x2 && e.getY() >= y1 && e.getY() <= y2) {
                    return cardPositionView;
                }
            }
        }
        return null;
    }
}
