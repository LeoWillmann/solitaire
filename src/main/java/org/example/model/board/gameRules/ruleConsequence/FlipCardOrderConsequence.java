package org.example.model.board.gameRules.ruleConsequence;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.List;

public class FlipCardOrderConsequence extends RuleConsequence {
    @Override
    public void enactConsequence(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        List<Card> cards = new ArrayList<>();
        while (newCards.size() > 0) {
            cards.add(newCards.get(newCards.size() - 1));
        }
        newCards.clear();
        newCards.addAll(cards);
    }
}
