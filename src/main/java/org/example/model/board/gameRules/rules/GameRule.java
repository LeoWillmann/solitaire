package org.example.model.board.gameRules.rules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.ruleConsequence.RuleConsequence;
import org.example.model.board.gameRules.ruleContainers.RuleCheckable;

import java.util.List;

public abstract class GameRule implements RuleCheckable {

    public abstract boolean validateMove(CardPosition cardPosition, Card topCard, Card card);

    @Override
    public boolean checkRule(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        for (Card card : newCards) {
            if (!validateMove(cardPosition, nextCard, card)) {
                return false;
            }
            nextCard = card;
        }
        return true;
    }
}
