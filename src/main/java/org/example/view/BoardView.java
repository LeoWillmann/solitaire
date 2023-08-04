package org.example.view;

import org.example.controller.MouseMovementListener;
import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.innit.InnitGameBoards;
import org.example.view.objects.CardDeckView;
import org.example.view.objects.Drawable;
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
    private final List<Drawable> drawables = new ArrayList<>();
    private final List<CardPositionView> cardPositionViews = new ArrayList<>();
    private final List<CardView> cardViews = new ArrayList<>();
    private final List<CardView> selectedCards = new ArrayList<>();
    private CardDeckView deckView;
    private Board board;

    public BoardView(Board board) {
        this.board = board;

        MouseMovementListener mouseMovementListener = new MouseMovementListener(this);
        addMouseListener(mouseMovementListener);
        addMouseMotionListener(mouseMovementListener);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    public void setDeckView(CardDeckView deckView) {
        drawables.remove(this.deckView);
        this.deckView = deckView;
        addDrawable(this.deckView);
    }

    public void addCardPositionView(CardPositionView cardPositionView) {
        cardPositionViews.add(cardPositionView);
        addDrawable(cardPositionView);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable drawable : drawables) {
            drawable.draw(g);
        }
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
