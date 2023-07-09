package org.example;

import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.rules.gameRules.RedBlackSuitRule;

public class Main {
    public static void main(String[] args) {

//        CardDeck deck = new CardDeck(1);
//        Board board = new Board(new SolitaireLayout(), null);

        RedBlackSuitRule rule = new RedBlackSuitRule();
        System.out.println(rule.validateMove(new Card(5, CardSuit.HEARTS, true), new Card(2, CardSuit.SPADES, true)));

    }
}