package org.example.model.boardLayout;

import org.example.model.deck.CardDeck;

public class SolitaireLayout extends BoardLayout {
    public SolitaireLayout() {
        super(new CardDeck(1), 7, 4);
    }
}
