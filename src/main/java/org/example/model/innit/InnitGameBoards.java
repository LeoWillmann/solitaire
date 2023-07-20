package org.example.model.innit;

import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.deckBehavior.NapoleonBehavior;
import org.example.model.board.deck.deckBehavior.NormalCardDeal;
import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.gameRules.FalseRule;
import org.example.model.board.gameRules.OrRule;
import org.example.model.board.gameRules.RuleContainer;
import org.example.model.board.gameRules.cardPlacementRules.*;
import org.example.model.board.gameRules.cardPlacementRules.firstCard.FirstCardIdRule;
import org.example.model.board.gameRules.cardTakeRules.MultiTakeRule;
import org.example.model.board.gameRules.cardTakeRules.SingleTakeRule;
import org.example.view.objects.cardPositionView.CardPositionView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InnitGameBoards {
    public static Board makeSolitaire() {
        Board board = new Board();
        CardDeck cardDeck = new CardDeck(1, new NormalCardDeal(3, board.getCardPool()));
        cardDeck.shuffleDeck();
        board.setDeck(cardDeck);
        innitSolitairePositions(board);
        return board;
    }

    private static void dealCards(Board board, CardPosition cardPosition, int cards) {
        for (int i = 0; i < cards; i++) {
            cardPosition.placeCard(board.getDeck().takeCard());
        }
    }

    private static void innitSolitairePositions(Board board) {
        RuleContainer placementRule = new RuleContainer();
        RuleContainer takeRule = new RuleContainer();
        placementRule.addRule(new DifferentSuitColorRule());
        placementRule.addRule(new DescendingCardsRule());
        placementRule.addRule(new FirstCardIdRule(13));
        takeRule.addRule(new MultiTakeRule());
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
            cardPosition.addTakeRule(new SingleTakeRule());
            board.addCardPosition(cardPosition);
        }
    }

    public static Board makeGrandNapoleon(int columns) {
        Board board = new Board();
        innitNapoleonPositions(board, columns);
        CardDeck cardDeck = new CardDeck(2, new NapoleonBehavior(
                board.getCardPositions().subList(0, columns), board.getCardPositions().subList(columns, columns + 8)));
        cardDeck.shuffleDeck();
        board.setDeck(cardDeck);
        return board;
    }

    private static void innitNapoleonPositions(Board board, int columns) {
        RuleContainer placementRule = new RuleContainer();
        RuleContainer takeRule = new RuleContainer();
        placementRule.addRule(new SameSuitRule());
        placementRule.addRule(new OrRule(new DescendingCardsRule(), new AscendingCardsRule()));
        takeRule.addRule(new SingleTakeRule());
        for (int i = 0; i < columns; i++) {
            CardPosition cardPosition = new CardPosition(placementRule, takeRule);
            board.addCardPosition(cardPosition);
        }

        for (CardSuit suit : CardSuit.values()) {
            CardPosition cardPosition = new CardPosition();
            cardPosition.addPlacementRule(new FirstCardIdRule(1));
            cardPosition.addPlacementRule(new CardSuitRule(suit));
            cardPosition.addPlacementRule(new AscendingCardsRule());
            cardPosition.addTakeRule(new FalseRule());
            board.addCardPosition(cardPosition);
        }

        for (int i = CardSuit.values().length - 1; i >= 0; i--) {
            CardPosition cardPosition = new CardPosition();
            cardPosition.addPlacementRule(new FirstCardIdRule(13));
            cardPosition.addPlacementRule(new CardSuitRule(CardSuit.values()[i]));
            cardPosition.addPlacementRule(new DescendingCardsRule());
            cardPosition.addTakeRule(new FalseRule());
            board.addCardPosition(cardPosition);
        }
    }

}
