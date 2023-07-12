package org.example.model.board.gameRules.cardPlacementRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

public class DifferentSuitColorRule extends GameRule {

    @Override
    public boolean validateMove(CardPosition cardPosition, Card card) {
        if (cardPosition.numberOfCards() == 0) {
            return true;
        }
        return cardPosition.getTopCard().suit().getColor() != card.suit().getColor();
    }
}
