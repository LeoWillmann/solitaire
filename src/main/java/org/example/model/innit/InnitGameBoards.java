package org.example.model.innit;

import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.gameRules.cardPlacementRules.CardIdRule;
import org.example.model.board.gameRules.cardPlacementRules.CardSuitRule;
import org.example.model.board.gameRules.cardPlacementRules.DescendingCardsRule;
import org.example.model.board.gameRules.cardPlacementRules.DifferentSuitColorRule;

public class InnitGameBoards {
    public static void makeSolitaire() {
        Board board = new Board(new CardDeck(1));
        for (int i = 0; i < 7; i++) {
            CardPosition cardPosition = new CardPosition(board);
            cardPosition.addPlacementRule(new DifferentSuitColorRule());
            cardPosition.addPlacementRule(new DescendingCardsRule());
            cardPosition.addEmptyPositionRule(new CardIdRule(13));
            board.addCardPosition(cardPosition);
        }

        for (CardSuit suit : CardSuit.values()) {
            CardPosition cardPosition = new CardPosition(board);
            cardPosition.addEmptyPositionRule(new CardIdRule(1));
            cardPosition.addEmptyPositionRule(new CardSuitRule(suit));
        }
    }
}
