package org.example.model.board.gameRules;

import org.example.model.board.deck.card.Card;

public class DifferentSuitColorRule extends GameRule {
    @Override
    public boolean validateMove(Card bottom, Card top) {
        return (bottom.suit().getColor() != top.suit().getColor());
    }
}
