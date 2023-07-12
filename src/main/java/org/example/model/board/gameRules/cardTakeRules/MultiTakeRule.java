package org.example.model.board.gameRules.cardTakeRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

public class MultiTakeRule extends GameRule {
    @Override
    public boolean validateMove(CardPosition cardPosition, Card card) {
        int index = cardPosition.getIndexOfCard(card);
        if (index == -1) {
            return false;
        }

        for (int i = index + 1; i < cardPosition.numberOfCards(); i++) {
            if (!cardPosition.isValidPlacement(cardPosition.getCard(i))) {
                return false;
            }
        }
        return true;
    }
}
