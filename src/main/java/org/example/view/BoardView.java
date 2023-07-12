package org.example.view;

import org.example.model.board.Board;
import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.view.objects.CardView;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    private Board board;
    private CardView cardView;

    public BoardView() {
        cardView = new CardView(new Card(12, CardSuit.DIAMONDS));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        cardView.draw(g, 100, 100);
    }
}
