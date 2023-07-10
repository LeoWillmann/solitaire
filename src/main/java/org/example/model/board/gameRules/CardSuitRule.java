package org.example.model.board.gameRules;

import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;

import java.util.ArrayList;
import java.util.List;

public class CardSuitRule extends GameRule {

    private final List<CardSuit> allowedSuits = new ArrayList<>();

    public CardSuitRule(CardSuit suit) {
        allowedSuits.add(suit);
    }

    public CardSuitRule(List<CardSuit> suits) {
        allowedSuits.addAll(suits);
    }

    public void addAllowedSuits(CardSuit suit) {
        allowedSuits.add(suit);
    }

    public void addAllowedSuits(List<CardSuit> suits) {
        allowedSuits.addAll(suits);
    }

    @Override
    public boolean validateMove(Card bottom, Card top) {
        for (CardSuit suit : allowedSuits) {
            if (suit == top.suit()) {
                return true;
            }
        }
        return false;
    }
}
