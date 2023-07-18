package org.example.model.board.gameRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

public class OrRule extends GameRule {
    private GameRule rule0;
    private GameRule rule1;

    public OrRule(GameRule rule0, GameRule rule1) {
        this.rule0 = rule0;
        this.rule1 = rule1;
    }

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        return rule0.validateMove(cardPosition, topCard, card) || rule1.validateMove(cardPosition, topCard, card);
    }
}
