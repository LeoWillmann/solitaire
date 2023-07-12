package org.example.model.board.gameRules.cardPlacementRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

public class SameSuitRule extends GameRule {

    @Override
    public boolean validateMove(CardPosition cardPosition, Card card) {
        return cardPosition.getTopCard().suit() == card.suit();
    }
}
