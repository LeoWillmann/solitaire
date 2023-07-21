package org.example.model.board.gameRules.ruleContainers;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.List;

public class AndCheckable implements RuleCheckable {
    private final List<RuleCheckable> ruleCheckables = new ArrayList<>();

    public void addRule(RuleCheckable ruleCheckable) {
        ruleCheckables.add(ruleCheckable);
    }

    @Override
    public boolean checkRule(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        for (RuleCheckable ruleCheckable : ruleCheckables) {
            if (!ruleCheckable.checkRule(cardPosition, nextCard, newCards)) {
                return false;
            }
        }
        return true;
    }
}
