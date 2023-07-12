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
    private final Board board;

    public BoardView() {
        board = InnitGameBoards.makeSolitaire();
        innitBoardView();

        MouseMovementListener mouseMovementListener = new MouseMovementListener(this);
        addMouseListener(mouseMovementListener);
    }

    private void innitBoardView() {
        for (CardPosition cardPosition : board.getCardPositions()) {
            cardPositionViews.add(new CardPositionView(cardPosition, true));
        }
    }

    public List<CardPositionView> getCardPositionViews() {
        return cardPositionViews;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int i = 0;
        for (CardPositionView cardPositionView : cardPositionViews) {
            cardPositionView.draw(g, POSX + i * (CardView.CARD_WIDTH + HORIZONTAL_COLUMN_DISTANCE), POSY);
            i++;
        }
    }

    @Override
    public void notifyListener() {
        repaint();
    }
}
