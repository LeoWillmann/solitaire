package org.example.model.board.rules;

import org.example.model.board.deck.card.Card;
import org.example.model.board.rules.gameRules.GameRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Rules for the board. Constitutes what a valid move is and the victory condition.
 */
public class BoardRules {
    private final List<GameRule> ruleList;

    public BoardRules() {
        ruleList = new ArrayList<>();
    }

    public void addRule(GameRule rule) {
        ruleList.add(rule);
    }

    public boolean checkMove(Card lower, Card upper) {
        for (GameRule rule : ruleList) {
            if (!rule.validateMove(lower, upper)) {
                return false;
            }
        }
        return true;
    }
}
