package org.example.controller;

import org.example.view.BoardView;
import org.example.view.objects.CardPositionView;
import org.example.view.objects.CardView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseMovementListener extends MouseAdapter {

    private final BoardView boardView;

    public MouseMovementListener(BoardView boardView) {
        this.boardView = boardView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        CardView cardView = getCardView(e);
        if (cardView != null) {
            cardView.selectColor();
            boardView.notifyListener();
        }
    }

    private CardView getCardView(MouseEvent e) {
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

    private CardPositionView getCardPositionView(MouseEvent e) {
        for (CardPositionView cardPositionView : boardView.getCardPositionViews()) {
            if (cardPositionView.getCardViews().size() > 0) {
                int x1 = (int) cardPositionView.getPoint().getX();
                int y1 = (int) cardPositionView.getPoint().getY();
                int x2 = x1 + CardView.CARD_WIDTH;
                int y2 = y1 + (cardPositionView.getCardViews().size() - 1) * CardPositionView.CARD_VERTICAL_DISTANCE + CardView.CARD_HEIGHT;
                if (e.getX() >= x1 && e.getX() <= x2 && e.getY() >= y1 && e.getY() <= y2) {
                    return cardPositionView;
                }
            }
        }
        return null;
    }
}
