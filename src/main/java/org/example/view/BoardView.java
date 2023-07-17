package org.example.view;

import org.example.controller.MouseMovementListener;
import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.innit.InnitGameBoards;
import org.example.view.objects.CardPositionView;
import org.example.view.objects.CardView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends JPanel implements Listenable {

    private static final int POSX = 0;
    private static final int POSY = 0;
    private static final int HORIZONTAL_COLUMN_DISTANCE = 30;
    private final List<CardPositionView> cardPositionViews = new ArrayList<>();
    private final List<CardView> cardViews = new ArrayList<>();
    private final Board board;

    public BoardView() {
        board = InnitGameBoards.makeSolitaire();
        innitBoardView();

        MouseMovementListener mouseMovementListener = new MouseMovementListener(this);
        addMouseListener(mouseMovementListener);
        addMouseMotionListener(mouseMovementListener);

    }

    private void innitBoardView() {
        int i = 0;
        for (CardPosition cardPosition : board.getCardPositions()) {
            CardPositionView cardPositionView = new CardPositionView(cardPosition, true, this);
            cardPositionView.setPosition(POSX + i * (CardView.CARD_WIDTH + HORIZONTAL_COLUMN_DISTANCE), POSY);
            cardPositionViews.add(cardPositionView);
            i++;
        }
    }

    public void addCardView(CardView cardView) {
        cardViews.add(cardView);
    }

    public List<CardView> getCardViews() {
        return cardViews;
    }

    public void removeCardViews(List<CardView> cardViewList) {
        cardViews.removeAll(cardViewList);
    }

    public void setCardViewsToTop(List<CardView> cardViewList) {
        for (CardView cardView : cardViewList) {
            if (cardViews.contains(cardView)) {
                cardViews.remove(cardView);
                cardViews.add(cardView);
            }
        }
    }

    public CardPositionView getCardPositionView(CardPosition cardPosition) {
        for (CardPositionView cardPositionView : cardPositionViews) {
            if (cardPositionView.getCardPosition() == cardPosition) {
                return cardPositionView;
            }
        }
        return null;
    }

    public List<CardPositionView> getCardPositionViews() {
        return cardPositionViews;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPositions(g);
        drawCards(g);
    }

    private void drawPositions(Graphics g) {
        for (CardPositionView cardPositionView : cardPositionViews) {
            cardPositionView.draw(g);
        }
    }

    private void drawCards(Graphics g) {
        for (CardView cardView : cardViews) {
            cardView.draw(g);
        }
    }

    @Override
    public void notifyListener() {
        repaint();
    }
}
