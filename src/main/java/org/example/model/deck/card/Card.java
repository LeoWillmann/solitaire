package org.example.model.deck.card;

public record Card(int cardValue, CardSuit suit) {

    public void printCard() {
        String valueName = switch (cardValue) {
            case 1 -> "ace";
            case 11 -> "jack";
            case 12 -> "queen";
            case 13 -> "king";
            default -> String.valueOf(cardValue);
        };
        System.out.println(valueName + " of " + String.valueOf(suit).toLowerCase());
    }
}
