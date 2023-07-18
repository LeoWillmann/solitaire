package org.example.view;

import org.example.controller.MouseMovementListener;
import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.innit.InnitGameBoards;
import org.example.view.objects.CardDeckView;
import org.example.view.objects.cardPositionView.CardPositionView;
import org.example.view.objects.cardView.CardView;

import javax.swing.*;
import java.awt.*;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends JPanel implements Listenable {

    public static final int HORIZONTAL_COLUMN_DISTANCE = 30;
    public static final int VERTICAL_COLUMN_DISTANCE = 30;
    private static final int POSX = 100;
    private static final int POSY = 0;
    private final Point point = new Point(POSX, POSY);
    private final List<CardPositionView> cardPositionViews = new ArrayList<>();
    private final List<CardView> cardViews = new ArrayList<>();
    private final List<CardView> selectedCards = new ArrayList<>();
    private final CardDeckView deckView;
    private final CardPositionView cardPool;
    private Board board;

    public BoardView(Board board) {
        this.board = board;
        deckView = new CardDeckView(board.getDeck());
        deckView.setPosition(POSX, POSY);
        cardPool = new CardPositionView(board.getCardPool(), board.getDeck().getDrawNumberOfCards(), false, this);
        cardPool.setPosition(POSX + (CardView.CARD_WIDTH + HORIZONTAL_COLUMN_DISTANCE), POSY);

        MouseMovementListener mouseMovementListener = new MouseMovementListener(this);
        addMouseListener(mouseMovementListener);
        addMouseMotionListener(mouseMovementListener);
    }

    public Point getPoint() {
        return point;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public CardPositionView getCardPool() {
        return cardPool;
    }

    public CardDeckView getDeckView() {
        return deckView;
    }

    public void setSelectedCards(List<CardView> selectedCards) {
        clearSelectedCards();
        this.selectedCards.addAll(selectedCards);
    }

    public void clearSelectedCards() {
        this.selectedCards.clear();
    }

    public void addCardView(CardView cardView) {
        cardViews.add(cardView);
    }

    public List<CardView> getCardViews() {
        return cardViews;
    }

    public void setCardViewsToTop(List<CardView> cardViewList) {
        for (CardView cardView : cardViewList) {
            if (cardViews.contains(cardView)) {
                cardViews.remove(cardView);
                cardViews.add(cardView);
            }
        }
    }

    public List<CardPositionView> getCardPositionViews() {
        return cardPositionViews;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        deckView.draw(g);
        cardPool.draw(g);
        drawPositions(g);
        drawSelectedCards(g);
    }

    private void drawPositions(Graphics g) {
        for (CardPositionView cardPositionView : cardPositionViews) {
            cardPositionView.draw(g);
        }
    }

    private void drawSelectedCards(Graphics g) {
        for (CardView cardView : selectedCards) {
            cardView.draw(g);
        }
    }

    @Override
    public void notifyListener() {
        repaint();
    }
}
