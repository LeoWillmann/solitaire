package org.example.model.board.deck.card;

public record Card(int cardValue, CardSuit suit, boolean isVisible) {

    public Card flipVisibility() {
        return new Card(cardValue, suit, !isVisible);
    }

    public String cardDescription() {
        String valueName = switch (cardValue) {
            case 1 -> "ace";
            case 11 -> "jack";
            case 12 -> "queen";
            case 13 -> "king";
            default -> String.valueOf(cardValue);
        };
        return valueName + " of " + String.valueOf(suit).toLowerCase();
    }
}
