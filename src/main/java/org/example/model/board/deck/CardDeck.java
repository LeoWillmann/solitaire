package org.example.model.board.deck;

import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.deck.deckBehavior.DeckBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A deck of cards.
 */
public class CardDeck {
    private static final int MAX_CARD_VALUE = 13;
    private final List<Card> cards;
    private DeckBehavior deckBehavior;

    public CardDeck(int numberOfDecks) {
        cards = new ArrayList<>();
        for (int i = 0; i < numberOfDecks; i++) {
            for (CardSuit suit : CardSuit.values()) {
                for (int j = 0; j < MAX_CARD_VALUE; j++) {
                    cards.add(new Card(j + 1, suit, true));
                }
            }
        }
    }

    public int getMaxCardValue() {
        return MAX_CARD_VALUE;
    }

    public List<Card> getCards() {
        return cards;
    }

    public DeckBehavior getDeckBehavior() {
        return deckBehavior;
    }

    public void setDeckBehavior(DeckBehavior deckBehavior) {
        this.deckBehavior = deckBehavior;
    }

    public int numberOfCardsInDeck() {
        return cards.size();
    }


    public void shuffleDeck() {
        List<Card> deck = new ArrayList<>(cards);
        cards.clear();

        Random random = new Random();
        random.setSeed(12344);
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

    public void performAction() {
        if (deckBehavior != null) {
            deckBehavior.dealCards(this);
        }
    }
}
