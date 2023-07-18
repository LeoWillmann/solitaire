package org.example.model.board.gameRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

public class FalseRule extends GameRule {
    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        return false;
    }
}
