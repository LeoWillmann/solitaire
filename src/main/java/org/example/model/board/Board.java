package org.example.model.board;

import org.example.model.board.deck.CardDeck;
import org.example.model.board.gameRules.GameRule;
import org.example.model.board.gameRules.RuleContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Board class will be the game board for the solitaire game.
 */
public class Board {
    private final List<CardPosition> cardPositions = new ArrayList<>();
    private final CardDeck deck;

    public Board(CardDeck deck) {
        this.deck = deck;
    }

    public void addCardPosition(CardPosition cardPosition) {
        cardPositions.add(cardPosition);
    }
}
