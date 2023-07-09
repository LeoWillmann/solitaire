package org.example.model.boardLayout;

import org.example.model.deck.CardDeck;
import org.example.model.deck.card.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Layout for the board with initial card deck.
 */
public abstract class BoardLayout {
    protected CardDeck deck;
    protected int numberOfColumns;
    protected List<List<Card>> columns;
    protected int extraCardPositions;

    public BoardLayout(CardDeck deck, int numberOfColumns, int extraCardPositions) {
        this.deck = deck;
        this.numberOfColumns = numberOfColumns;
        this.extraCardPositions = extraCardPositions;
        columns = new ArrayList<>();
        for (int i = 0; i < numberOfColumns; i++) {
            columns.add(new ArrayList<>());
        }
    }

    public void addCard(int column, Card card) {
        columns.get(column).add(card);
    }
}
