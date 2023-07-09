package org.example.model.deck.card;

public enum CardSuit {
    CLUBS(CardColor.BLACK),
    SPADES(CardColor.BLACK),
    HEARTS(CardColor.RED),
    DIAMONDS(CardColor.RED);

    private final CardColor color;
    private static final CardSuit[] values = values();

    CardSuit(CardColor color) {
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    public CardSuit next() {
        return values[(ordinal() + 1) % values.length];
    }

    public CardSuit previous() {
        return values[(ordinal() - 1 + values.length) % values.length];
    }
}
