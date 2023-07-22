package org.example.model.board.gameRules.rules.cardPlacementRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.rules.GameRule;

import java.util.List;

public class AscendingCardsRule extends GameRule {

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        if (topCard == null) {
            return true;
        }
        return topCard.cardValue() + 1 == card.cardValue();
    }
}
