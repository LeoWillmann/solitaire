package org.example.model.board;

import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;
import org.example.model.board.gameRules.RuleContainer;

import java.util.ArrayList;
import java.util.List;

public class CardPosition {
    private final RuleContainer movementRule = new RuleContainer();
    private final RuleContainer emptyPositionRule = new RuleContainer();
    private final List<Card> cards = new ArrayList<>();

    public void addEmptyPositionRule(GameRule rule) {
        emptyPositionRule.addRule(rule);
    }

    public void addMovementRule(GameRule rule) {
        movementRule.addRule(rule);
    }

    public boolean isCardStackEmpty() {
        return cards.size() == 0;
    }

    public Card getTopCard() {
        if (!isCardStackEmpty()) {
            return cards.get(cards.size() - 1);
        } else {
            return null;
        }
    }

    public void move(Card card) {
        cards.add(card);
    }

    public void checkMove(Card card) {
        if (isValidMove(card)) {
            move(card);
        }
    }

    public boolean isValidMove(Card newCard) {
        Card topCard = getTopCard();
        if (topCard == null) {
            return emptyPositionRule.checkRules(null, newCard);
        } else {
            return movementRule.checkRules(topCard, newCard);
        }
    }

}
