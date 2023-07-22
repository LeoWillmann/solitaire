package org.example.model.board.gameRules.rules.cardPlacementRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.rules.GameRule;

public class SameSuitRule extends GameRule {

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        if (cardPosition.isEmpty()) {
            return true;
        }
        return topCard.suit() == card.suit();
    }
}
