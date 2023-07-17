package org.example.model.board.gameRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.List;

public class RuleContainer {
    private final List<GameRule> ruleList = new ArrayList<>();

    public void addRule(GameRule rule) {
        ruleList.add(rule);
    }

    public boolean checkRules(CardPosition cardPosition, Card topCard, Card newcard) {
        for (GameRule rule : ruleList) {
            if (!rule.validateMove(cardPosition, topCard, newcard)) {
                return false;
            }
        }
        return true;
    }


}
