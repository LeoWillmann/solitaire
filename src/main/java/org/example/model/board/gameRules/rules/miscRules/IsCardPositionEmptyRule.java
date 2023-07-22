package org.example.model.board.gameRules.rules.miscRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.rules.GameRule;

import java.util.List;

public class IsCardPositionEmptyRule extends GameRule {
    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        return cardPosition.isEmpty();
    }

    @Override
    public boolean checkRule(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        return cardPosition.isEmpty();
    }
}
