package org.example.model.board.gameRules.cardPlacementRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.gameRules.GameRule;

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
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        return allowedSuits.contains(card.suit());
    }
}
