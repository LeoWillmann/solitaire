package org.example.model.board.gameRules.ruleConsequence;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.ruleContainers.RuleCheckable;

import java.util.List;

public abstract class RuleConsequence {
    public abstract void enactConsequence(CardPosition cardPosition, Card nextCard, List<Card> newCards);
}
