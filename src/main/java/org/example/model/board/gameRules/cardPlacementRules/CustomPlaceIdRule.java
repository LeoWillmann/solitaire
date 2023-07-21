package org.example.model.board.gameRules.cardPlacementRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

public class CustomPlaceIdRule extends GameRule {
    private final int id_1;
    private final int id_2;

    public CustomPlaceIdRule(int id_1, int id_2) {
        this.id_1 = id_1;
        this.id_2 = id_2;
    }

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        if (topCard != null && card != null) {
            return topCard.cardValue() == id_1 && card.cardValue() == id_2;
        } else {
            return true;
        }
    }
}
