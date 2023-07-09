package org.example.model.board.boardLayout;

import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.Arrays;
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

    protected abstract void innitLayout();

    public void addCard(int column, Card card) {
        columns.get(column).add(card);
    }

    public Card getCard(int column, int i) {
        if (i >= 0 && i < columns.get(column).size()) {
            return columns.get(column).get(i);
        }
        return null;
    }

    public void printBoard() {
        int maxColumnSize = maxColumnSize();
        String[] strings = new String[maxColumnSize];
        Arrays.fill(strings, "");
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < maxColumnSize; j++) {
                Card card = getCard(i, j);
                String description;
                if (card != null) {
                    if (card.isVisible()) {
                        description = "|\t" + card.cardDescription() + "\t";
                    } else {
                        description = "|\tcovered\t\t";
                    }
                } else {
                    description = "|\tempty\t\t";
                }
                strings[j] = strings[j] + description;
            }
        }
        for (int i = 0; i < maxColumnSize; i++) {
            System.out.println(strings[i]);
        }

    }

    private int maxColumnSize() {
        int max = 0;
        for (int i = 0; i < numberOfColumns; i++) {
            int size = columns.get(i).size();
            max = Math.max(max, size);
        }
        return max;
    }
}
