package org.example.model.board.gameRules.ruleConsequence;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.ruleContainers.RuleCheckable;

import java.util.List;

public class NestedConsequence extends RuleConsequence {
    private final RuleConsequence ruleConsequence;

    public NestedConsequence(RuleCheckable ruleCheckable, RuleConsequence ruleConsequence) {
        super(ruleCheckable);
        this.ruleConsequence = ruleConsequence;
    }

    @Override
    public void enactConsequence(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        if (ruleConsequence != null) {
            ruleConsequence.checkRule(cardPosition, nextCard, newCards);
        }

    }


}
