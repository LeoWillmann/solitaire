package org.example.model.board.gameRules.rules.miscRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.rules.GameRule;

import java.util.List;

public class MoreThanXCardsRule extends GameRule {
    private int bound;

    public MoreThanXCardsRule(int bound) {
        this.bound = bound;
    }

    @Override
    public boolean validateMove(CardPosition cardPosition, Card topCard, Card card) {
        System.out.println("Should not access heree");
        return true;
    }

    @Override
    public boolean checkRule(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        return newCards.size() > bound;
    }
}
