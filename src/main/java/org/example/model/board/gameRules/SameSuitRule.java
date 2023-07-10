package org.example.model.board.gameRules;

import org.example.model.board.deck.card.Card;

public class SameSuitRule extends GameRule {
    @Override
    public boolean validateMove(Card bottom, Card top) {
        return bottom.suit() == top.suit();
    }
}
