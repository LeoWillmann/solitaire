package org.example.model.board.gameRules.ruleContainers;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

import java.util.List;

public class AtomicCheckable implements RuleCheckable {
    private final GameRule rule;

    public AtomicCheckable(GameRule rule) {
        this.rule = rule;
    }

    @Override
    public boolean checkRule(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        return rule.isValidMove(cardPosition, nextCard, newCards);
    }
}
