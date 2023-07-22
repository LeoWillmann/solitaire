package org.example.model.board.gameRules.rules.cardTakeRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.rules.GameRule;

import java.util.Collections;

public class MultiTakeRule extends GameRule {
    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        if (topCard == null) {
            return true;
        }

        return cardPosition.isValidPlacement(topCard, Collections.singletonList(card));
    }
}
