package org.example.model.board.rules.gameRules;

import org.example.model.board.deck.card.Card;

public class RedBlackSuitRule extends GameRule {
    @Override
    public boolean validateMove(Card lower, Card upper) {
        return (lower.suit().getColor() != upper.suit().getColor());
    }
}
