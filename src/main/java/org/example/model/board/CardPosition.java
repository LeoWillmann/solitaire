package org.example.model.board;

import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;
import org.example.model.board.gameRules.RuleContainer;

import java.util.ArrayList;
import java.util.List;

public class CardPosition {
    private final Board board;
    private final RuleContainer placementRule;
    private final RuleContainer takeRule;
    private final List<Card> cards = new ArrayList<>();

    public CardPosition(Board board) {
        this.board = board;
        placementRule = new RuleContainer();
        takeRule = new RuleContainer();
    }

    public CardPosition(Board board, RuleContainer placementRule, RuleContainer takeRule) {
        this.board = board;
        this.placementRule = placementRule;
        this.takeRule = takeRule;
    }

    public void addTakeRule(GameRule rule) {
        takeRule.addRule(rule);
    }

    public void addPlacementRule(GameRule rule) {
        placementRule.addRule(rule);
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

    public Card getCard(int i) {
        if (i >= 0 && i < numberOfCards()) {
            return cards.get(i);
        } else {
            return null;
        }
    }

    public int numberOfCards() {
        return cards.size();
    }

    public int getIndexOfCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) == card) {
                return i;
            }
        }
        return -1;
    }

    public boolean isValidTake(Card card) {
        return takeRule.checkRules(this, card);
    }


    public void placeCard(Card card) {
        cards.add(card);
    }

    public void checkPlacement(Card card) {
        if (isValidPlacement(card)) {
            placeCard(card);
        }
    }

    public boolean isValidPlacement(Card newCard) {
        return placementRule.checkRules(this, newCard);
    }

}
