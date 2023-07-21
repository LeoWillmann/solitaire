package org.example.model.board;

import org.example.model.board.deck.CardDeck;
import org.example.model.board.gameRules.FalseRule;
import org.example.model.board.gameRules.cardTakeRules.SingleTakeRule;
import org.example.model.board.gameRules.ruleContainers.AndCheckable;
import org.example.model.board.gameRules.ruleContainers.AtomicCheckable;

import java.util.ArrayList;
import java.util.List;

/**
 * Board class will be the game board for the solitaire game.
 */
public class Board {
    private final List<CardPosition> cardPositions = new ArrayList<>();
    private final CardPosition cardPool;
    private CardDeck deck;

    public Board() {
        AndCheckable placementRule = new AndCheckable();
        AndCheckable takeRule = new AndCheckable();
        placementRule.addRule(new AtomicCheckable(new FalseRule()));
        takeRule.addRule(new AtomicCheckable(new SingleTakeRule()));
        
        cardPool = new CardPosition(placementRule, takeRule);

    }

    public void addCardPosition(CardPosition cardPosition) {
        cardPositions.add(cardPosition);
    }

    public List<CardPosition> getCardPositions() {
        return cardPositions;
    }

    public CardDeck getDeck() {
        return deck;
    }

    public void setDeck(CardDeck deck) {
        this.deck = deck;
    }

    public CardPosition getCardPool() {
        return cardPool;
    }
}
