package org.example.model.board.gameRules.ruleConsequence;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.ruleContainers.RuleCheckable;

import java.util.List;

public abstract class RuleConsequence implements RuleCheckable {
    protected RuleCheckable ruleCheckable;

    public RuleConsequence(RuleCheckable ruleCheckable) {
        this.ruleCheckable = ruleCheckable;
    }

    public abstract void enactConsequence(CardPosition cardPosition, Card nextCard, List<Card> newCards);

    @Override
    public boolean checkRule(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        if (ruleCheckable.checkRule(cardPosition, nextCard, newCards)) {
            enactConsequence(cardPosition, nextCard, newCards);
            return true;
        }
        return false;
    }
}
