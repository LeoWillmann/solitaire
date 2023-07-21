package org.example.model.board.gameRules.ruleContainers;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

import java.util.List;

public interface RuleCheckable {
    boolean checkRule(CardPosition cardPosition, Card nextCard, List<Card> newCards);
}
