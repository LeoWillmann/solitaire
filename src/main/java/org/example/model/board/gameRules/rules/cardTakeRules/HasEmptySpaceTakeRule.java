package org.example.model.board.gameRules.rules.cardTakeRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.rules.GameRule;

import java.util.ArrayList;
import java.util.List;

public class HasEmptySpaceTakeRule extends GameRule {
    private final List<CardPosition> positions = new ArrayList<>();

    public void addPosition(CardPosition cardPosition) {
        positions.add(cardPosition);
    }

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        for (CardPosition position : positions) {
            if (position.isEmpty()) {
                return true;
            }
        }
        return positions.isEmpty();
    }
}
