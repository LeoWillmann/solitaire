package org.example.model.innit;

import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.gameRules.RuleContainer;
import org.example.model.board.gameRules.cardPlacementRules.*;
import org.example.model.board.gameRules.cardTakeRules.MultiTakeRule;

public class InnitGameBoards {
    public static void makeSolitaire() {
        Board board = new Board(new CardDeck(1));
        RuleContainer placementRule = new RuleContainer();
        RuleContainer takeRule = new RuleContainer();
        placementRule.addRule(new DifferentSuitColorRule());
        placementRule.addRule(new DescendingCardsRule());
        placementRule.addRule(new CardIdRule(13));
        takeRule.addRule(new MultiTakeRule());
        for (int i = 0; i < 7; i++) {
            CardPosition cardPosition = new CardPosition(board, placementRule, takeRule);
            board.addCardPosition(cardPosition);
        }

        for (CardSuit suit : CardSuit.values()) {
            CardPosition cardPosition = new CardPosition(board);
            cardPosition.addPlacementRule(new CardIdRule(1));
            cardPosition.addPlacementRule(new CardSuitRule(suit));
            cardPosition.addPlacementRule(new AscendingCardsRule());
        }
    }
}
