package org.example.model.board.boardLayout;

import org.example.model.board.deck.CardDeck;

public class GrandNapoleon extends BoardLayout {
    public GrandNapoleon() {
        super(new CardDeck(2), 11, 8);
    }

    @Override
    protected void innitLayout() {

    }
}
