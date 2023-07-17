package org.example.model.board.gameRules.cardPlacementRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

import java.util.ArrayList;
import java.util.List;

public class FirstCardIdRule extends GameRule {
    private final List<Integer> allowedCardIds = new ArrayList<>();

    public FirstCardIdRule(int id) {
        allowedCardIds.add(id);
    }

    public FirstCardIdRule(List<Integer> ids) {
        allowedCardIds.addAll(ids);
    }

    public void addAllowedCardId(int id) {
        allowedCardIds.add(id);
    }

    public void addAllowedCardId(List<Integer> ids) {
        allowedCardIds.addAll(ids);
    }

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        if (cardPosition.isEmpty() && topCard == null) {
            return allowedCardIds.contains(card.cardValue());
        }
        return true;
    }
}
