package org.example.model.board.deck.deckBehavior;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.CardDeck;
import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.List;

public class NormalCardDeal extends DeckBehavior {
    private final CardPosition cardPosition;

    public NormalCardDeal(int deckProperty, CardPosition cardPosition) {
        super(deckProperty);
        this.cardPosition = cardPosition;
    }

    @Override
    public void dealCards(CardDeck deck) {
        List<Card> cardList = new ArrayList<>();
        for (int i = 0; i < deckProperty; i++) {
            if (deck.numberOfCardsInDeck() == 0) {
                deck.getCards().addAll(cardPosition.getCards());
                cardPosition.takeCards(cardPosition.getCards());
                if (deck.numberOfCardsInDeck() == 0) {
                    cardPosition.placeCards(cardList);
                    return;
                }
            }
            cardList.add(deck.takeCard());
        }
        cardPosition.placeCards(cardList);
    }
}
