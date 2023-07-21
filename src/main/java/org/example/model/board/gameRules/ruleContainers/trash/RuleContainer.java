package org.example.model.board.gameRules.ruleContainers.trash;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.List;

public class RuleContainer {
    protected final List<RuleOperation> ruleOperations = new ArrayList<>();

    public void addRuleOperation(RuleOperation ruleOperation) {
        ruleOperations.add(ruleOperation);
    }

    public boolean checkRules(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        for (RuleOperation ruleOperation : ruleOperations) {
            if (!ruleOperation.performOperation(cardPosition, nextCard, newCards)) {
                return false;
            }
        }
        return true;
    }

}
