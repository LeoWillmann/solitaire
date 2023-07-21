package org.example.model.board.gameRules.ruleContainers.trash;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

import java.util.List;

public class AndRuleOperation extends RuleOperation {
    @Override
    public boolean performOperation(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        for (GameRule rule : rules) {
            if (!rule.isValidMove(cardPosition, nextCard, newCards)) {
                return false;
            }
        }
        return true;
    }
}
