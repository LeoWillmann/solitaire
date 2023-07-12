package org.example.model.board.deck;

import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A deck of cards.
 */
public class CardDeck {
    private final List<Card> cards;

    public CardDeck(int numberOfDecks) {
        cards = new ArrayList<>();
        for (int i = 0; i < numberOfDecks; i++) {
            for (CardSuit suit : CardSuit.values()) {
                for (int j = 0; j < 13; j++) {
                    cards.add(new Card(j + 1, suit));
                }
            }
        }
    }

    private void shuffleDeck() {
        List<Card> deck = new ArrayList<>(cards);
        cards.clear();

        Random random = new Random();
        while (deck.size() > 0) {
            Card randomCard = deck.get(random.nextInt(deck.size()));
            deck.remove(randomCard);
            cards.add(randomCard);
        }
    }

    public Card takeCard() {
        if (cards.size() == 0) {
            return null;
        }
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}
