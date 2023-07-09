package org.example.model.board.rules.gameRules;

import org.example.model.board.deck.card.Card;

public abstract class GameRule {
    public abstract boolean validateMove(Card lower, Card upper);
}
