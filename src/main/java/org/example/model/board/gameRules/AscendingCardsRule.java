package org.example.model.board.gameRules;

import org.example.model.board.deck.card.Card;

public class AscendingCardsRule extends GameRule {
    @Override
    public boolean validateMove(Card bottom, Card top) {
        return bottom.cardValue() + 1 == top.cardValue();
    }
}
