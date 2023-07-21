package org.example.model.innit;

import org.example.model.board.Board;
import org.example.model.board.CardPosition;
import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.deckBehavior.NapoleonBehavior;
import org.example.model.board.deck.deckBehavior.NormalCardDeal;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.gameRules.FalseRule;
import org.example.model.board.gameRules.OrRule;
import org.example.model.board.gameRules.ruleContainers.AndCheckable;
import org.example.model.board.gameRules.ruleContainers.AtomicCheckable;
import org.example.model.board.gameRules.ruleContainers.OrCheckable;
import org.example.model.board.gameRules.ruleContainers.trash.AndRuleOperation;
import org.example.model.board.gameRules.ruleContainers.trash.RuleContainer;
import org.example.model.board.gameRules.cardPlacementRules.*;
import org.example.model.board.gameRules.cardPlacementRules.firstCard.FirstCardIdRule;
import org.example.model.board.gameRules.cardTakeRules.HasEmptySpaceTakeRule;
import org.example.model.board.gameRules.cardTakeRules.MultiTakeRule;
import org.example.model.board.gameRules.cardTakeRules.SingleTakeRule;
import org.example.model.board.gameRules.ruleContainers.trash.RuleOperation;

public class InnitGameBoards {
    public static Board makeSolitaire() {
        Board board = new Board();
        CardDeck cardDeck = new CardDeck(1);
        cardDeck.setDeckBehavior(new NormalCardDeal(3, board.getCardPool()));
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
        AndCheckable placementRule = new AndCheckable();
        AndCheckable takeRule = new AndCheckable();

        placementRule.addRule(new AtomicCheckable(new DifferentSuitColorRule()));
        placementRule.addRule(new AtomicCheckable(new DescendingCardsRule()));
        placementRule.addRule(new AtomicCheckable(new FirstCardIdRule(board.getDeck().getMaxCardValue())));

        takeRule.addRule(new AtomicCheckable(new MultiTakeRule()));

        for (int i = 0; i < 7; i++) {
            CardPosition cardPosition = new CardPosition(placementRule, takeRule);
            board.addCardPosition(cardPosition);
            dealCards(board, cardPosition, i + 1);
        }

        innitAcePositions(board);
    }

    public static Board makeGrandNapoleon(int columns) {
        Board board = new Board();
        CardDeck cardDeck = new CardDeck(2);
        board.setDeck(cardDeck);
        cardDeck.shuffleDeck();
        innitNapoleonPositions(board, columns);
        cardDeck.setDeckBehavior(new NapoleonBehavior(board.getCardPositions().subList(0, columns), board.getCardPositions().subList(columns, columns + 8)));
        return board;
    }

    private static void innitNapoleonPositions(Board board, int columns) {
        AndCheckable placementRule = new AndCheckable();
        AndCheckable takeRule = new AndCheckable();

        placementRule.addRule(new AtomicCheckable(new SameSuitRule()));
        {
            OrCheckable orCheckable = new OrCheckable();
            OrRule orRule = new OrRule();
            orRule.addRule(new DescendingCardsRule());
            orRule.addRule(new AscendingCardsRule());
            orRule.addRule(new CustomPlaceIdRule(board.getDeck().getMaxCardValue(), 1));
            orRule.addRule(new CustomPlaceIdRule(1, board.getDeck().getMaxCardValue()));
            orCheckable.addRule(new AtomicCheckable(orRule));
//            orCheckable.addRule(new AtomicCheckable(new DescendingCardsRule()));
//            orCheckable.addRule(new AtomicCheckable(new AscendingCardsRule()));
//            orCheckable.addRule(new AtomicCheckable(new CustomPlaceIdRule(1, board.getDeck().getMaxCardValue())));
//            orCheckable.addRule(new AtomicCheckable(new CustomPlaceIdRule(board.getDeck().getMaxCardValue(), 1)));
            placementRule.addRule(orCheckable);
        }

        HasEmptySpaceTakeRule emptySpaceTakeRule = new HasEmptySpaceTakeRule();
        {
            OrCheckable orCheckable = new OrCheckable();
            orCheckable.addRule(new AtomicCheckable(new SingleTakeRule()));

            AndCheckable andCheckable = new AndCheckable();
            andCheckable.addRule(new AtomicCheckable(emptySpaceTakeRule));
            andCheckable.addRule(new AtomicCheckable(new MultiTakeRule()));

            orCheckable.addRule(andCheckable);
            takeRule.addRule(orCheckable);
        }

        for (int i = 0; i < columns; i++) {
            CardPosition cardPosition = new CardPosition(placementRule, takeRule);
            emptySpaceTakeRule.addPosition(cardPosition);
            board.addCardPosition(cardPosition);
        }

        innitAcePositions(board);
        innitKingPositions(board);
    }

    private static void innitKingPositions(Board board) {
        for (int i = CardSuit.values().length - 1; i >= 0; i--) {
            AndCheckable placementRule = new AndCheckable();
            AndCheckable takeRule = new AndCheckable();

            placementRule.addRule(new AtomicCheckable(new FirstCardIdRule(board.getDeck().getMaxCardValue())));
            placementRule.addRule(new AtomicCheckable(new CardSuitRule(CardSuit.values()[i])));
            placementRule.addRule(new AtomicCheckable(new DescendingCardsRule()));

            takeRule.addRule(new AtomicCheckable(new FalseRule()));

            CardPosition cardPosition = new CardPosition(placementRule, takeRule);
            board.addCardPosition(cardPosition);
        }
    }

    private static void innitAcePositions(Board board) {
        for (CardSuit suit : CardSuit.values()) {
            AndCheckable placementRule = new AndCheckable();
            AndCheckable takeRule = new AndCheckable();

            placementRule.addRule(new AtomicCheckable(new FirstCardIdRule(1)));
            placementRule.addRule(new AtomicCheckable(new CardSuitRule(suit)));
            placementRule.addRule(new AtomicCheckable(new AscendingCardsRule()));

            takeRule.addRule(new AtomicCheckable(new FalseRule()));

            CardPosition cardPosition = new CardPosition(placementRule, takeRule);
            board.addCardPosition(cardPosition);
        }
    }
}
