package org.example.model.board.gameRules.rules.miscRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.rules.GameRule;

import java.util.ArrayList;
import java.util.List;

public class AndCardRule extends GameRule {
    private final List<GameRule> rules = new ArrayList<>();

    public void addRule(GameRule rule) {
        rules.add(rule);
    }

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {

        for (GameRule rule : rules) {
            if (!rule.validateMove(cardPosition, topCard, card)) {
                return false;
            }
        }
        return true;
    }
}
