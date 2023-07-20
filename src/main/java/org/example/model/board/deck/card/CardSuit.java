package org.example.model.board.deck.card;

import java.awt.*;

public enum CardSuit {
    CLUBS(Color.BLACK, "Clubs"),
    SPADES(Color.BLACK, "Spades"),
    HEARTS(Color.RED, "Hearts"),
    DIAMONDS(Color.RED, "Diamonds");

    private static final CardSuit[] values = values();
    private final Color color;
    private final String description;

    CardSuit(Color color, String description) {
        this.color = color;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Color getColor() {
        return color;
    }

    public CardSuit next() {
        return values[(ordinal() + 1) % values.length];
    }

    public CardSuit previous() {
        return values[(ordinal() - 1 + values.length) % values.length];
    }
}
