package org.example.model.board.gameRules;

import org.example.model.board.deck.card.Card;

public abstract class GameRule {
    public abstract boolean validateMove(Card bottom, Card top);
}
