package org.example.model.board.deck;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.model.board.deck.card.CardSuit;
import org.example.model.board.gameRules.GameRule;
import org.example.model.board.gameRules.RuleContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A deck of cards.
 */
public class CardDeck {
    private final List<Card> cards;
    private final RuleContainer deckRules = new RuleContainer();
    private int drawNumberOfCards = 1;

    public CardDeck(int numberOfDecks) {
        cards = new ArrayList<>();
        for (int i = 0; i < numberOfDecks; i++) {
            for (CardSuit suit : CardSuit.values()) {
                for (int j = 0; j < 13; j++) {
                    cards.add(new Card(j + 1, suit, true));
                }
            }
        }
    }

    public int getDrawNumberOfCards() {
        return drawNumberOfCards;
    }

    public void setDrawNumberOfCards(int drawNumberOfCards) {
        this.drawNumberOfCards = drawNumberOfCards;
    }

    public void addDeckRule(GameRule rule) {
        deckRules.addRule(rule);
    }

    public void shuffleDeck() {
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

    public void dealCardsToCardPool(CardPosition cardPosition) {
        List<Card> dealCards = new ArrayList<>();
        for (int i = 0; i < drawNumberOfCards; i++) {
            if (cards.size() == 0) {
                cards.addAll(cardPosition.getCards());
                cardPosition.takeCards(cardPosition.getCards());
                if (cards.size() == 0) {
                    cardPosition.placeCards(dealCards);
                    return;
                }
            }
            dealCards.add(takeCard());
        }
        cardPosition.placeCards(dealCards);
    }
}
