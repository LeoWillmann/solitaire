package org.example.model.board.deck.deckBehavior;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NapoleonBehavior extends DeckBehavior {
    private final List<CardPosition> commonPositions;
    private final List<CardPosition> ifPossiblePositions;
    private CardDeck deck;

    public NapoleonBehavior(List<CardPosition> commonPositions, List<CardPosition> ifPossiblePositions) {
        super(commonPositions.size());
        this.commonPositions = commonPositions;
        this.ifPossiblePositions = ifPossiblePositions;
    }

    @Override
    public void dealCards(CardDeck deck) {
        this.deck = deck;
        collectAllCards(deck);
        int i = 0;
        while (deck.numberOfCardsInDeck() > 0) {
            List<Card> cards = new ArrayList<>(Collections.singleton(deck.takeCard()));
            CardPosition cardPosition = ifPossibleToPlace(cards);
            commonPositions.get(i % deckProperty).placeCards(cards);
            i++;
            if (cardPosition != null) {
                checkPositions();
            }
        }
    }

    private void checkPositions() {
        for (int i = 0; i < deckProperty; i++) {
            if (i == 0 || i == deckProperty - 1) {
                edgeCase(i);
            } else {
                if (commonPositions.get(i).numberOfCards() == 1) {
                    standardCase(i);
                }
            }
        }
    }

    private void standardCase(int i) {
        if (checkCard(commonPositions.get(i).getTopCard())) {
            commonPositions.get(i).takeTopCard();
            checkPositions();
            commonPositions.get(i).placeCard(deck.takeCard());
        }
    }

    private void edgeCase(int i) {
        if (commonPositions.get(i).numberOfCards() > 0) {
            if (checkCard(commonPositions.get(i).getTopCard())) {
                commonPositions.get(i).takeTopCard();
                checkPositions();
                commonPositions.get(i).placeCard(deck.takeCard());
            }
        }
    }

    private boolean checkCard(Card card) {
        List<Card> cards = new ArrayList<>(Collections.singleton(card));
        CardPosition cardPosition = ifPossibleToPlace(cards);
        if (cardPosition != null) {
            cardPosition.placeCard(card);
            return true;
        }
        return false;
    }


    private CardPosition ifPossibleToPlace(List<Card> cards) {
        for (CardPosition cardPosition : ifPossiblePositions) {
            if (cardPosition.isValidPlacement(cardPosition.getTopCard(), cards)) {
                return cardPosition;
            }
        }
        return null;
    }

    private void collectAllCards(CardDeck deck) {
        for (CardPosition cardPosition : commonPositions) {
            deck.getCards().addAll(cardPosition.getCards());
            cardPosition.takeCards(cardPosition.getCards());
        }
    }
}
