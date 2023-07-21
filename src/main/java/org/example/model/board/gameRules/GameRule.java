package org.example.model.board.gameRules;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;

import java.util.List;

public abstract class GameRule {

    protected abstract boolean validateMove(CardPosition cardPosition, Card topCard, Card card);

    public boolean isValidMove(CardPosition cardPosition, Card nextCard, List<Card> newCards) {
        for (Card card : newCards) {
            if (!validateMove(cardPosition, nextCard, card)) {
                return false;
            }
            nextCard = card;
        }
        return true;
    }
}
