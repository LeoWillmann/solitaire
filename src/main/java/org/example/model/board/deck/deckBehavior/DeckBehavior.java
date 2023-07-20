package org.example.model.board.deck.deckBehavior;

import org.example.model.board.deck.CardDeck;

public abstract class DeckBehavior {
    protected final int deckProperty;

    public DeckBehavior(int deckProperty) {
        this.deckProperty = deckProperty;
    }

    public int getDeckProperty() {
        return deckProperty;
    }

    public abstract void dealCards(CardDeck deck);
}
