package org.example.model.boardLayout;

import org.example.model.deck.CardDeck;

public class GrandNapoleon extends BoardLayout {
    public GrandNapoleon() {
        super(new CardDeck(2), 11, 8);
    }
}
