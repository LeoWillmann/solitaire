package org.example.model.board.gameRules.ruleContainers.trash;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;

import java.util.ArrayList;
import java.util.List;

public abstract class RuleOperation {
    protected final List<GameRule> rules = new ArrayList<>();

    public void addRule(GameRule rule) {
        rules.add(rule);
    }

    public abstract boolean performOperation(CardPosition cardPosition, Card nextCard, List<Card> newCards);
}
