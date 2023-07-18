package org.example.model.innit;

import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.gameRules.RuleContainer;
import org.example.model.board.gameRules.cardPlacementRules.*;
import org.example.model.board.gameRules.cardPlacementRules.firstCard.FirstCardIdRule;

import java.util.ArrayList;
import java.util.Collections;

public class InnitGameBoards {
    public static Board makeSolitaire() {
        CardDeck cardDeck = new CardDeck(1);
        cardDeck.shuffleDeck();
        Board board = new Board(cardDeck);
        innitPositions(board);
        return board;
    }

    private static void dealCards(Board board, CardPosition cardPosition, int cards) {
        for (int i = 0; i < cards; i++) {
            cardPosition.placeCards(new ArrayList<Card>(Collections.singleton(board.getDeck().takeCard())));
        }
    }

    private static void innitPositions(Board board) {
        RuleContainer placementRule = new RuleContainer();
        RuleContainer takeRule = new RuleContainer();
        placementRule.addRule(new DifferentSuitColorRule());
        placementRule.addRule(new DescendingCardsRule());
        placementRule.addRule(new FirstCardIdRule(13));
        for (int i = 0; i < 7; i++) {
            CardPosition cardPosition = new CardPosition(placementRule, takeRule);
            board.addCardPosition(cardPosition);
            dealCards(board, cardPosition, i + 1);
        }

        for (CardSuit suit : CardSuit.values()) {
            CardPosition cardPosition = new CardPosition();
            cardPosition.addPlacementRule(new FirstCardIdRule(1));
            cardPosition.addPlacementRule(new CardSuitRule(suit));
            cardPosition.addPlacementRule(new AscendingCardsRule());
            board.addCardPosition(cardPosition);
        }
    }
}
