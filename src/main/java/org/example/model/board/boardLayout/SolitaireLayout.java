package org.example.model.board.boardLayout;

import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.card.Card;

public class SolitaireLayout extends BoardLayout {
    public SolitaireLayout() {
        super(new CardDeck(1), 7, 4);
        innitLayout();
    }

    @Override
    protected void innitLayout() {
        for (int i = 0; i < numberOfColumns; i++) {
            Card card = deck.takeCard();
            addCard(i, card.flipVisibility());
            for (int j = i + 1; j < numberOfColumns; j++) {
                card = deck.takeCard();
                addCard(j, card);
            }
        }
        printBoard();
    }
}
