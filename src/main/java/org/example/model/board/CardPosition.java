package org.example.model.board;

import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.GameRule;
import org.example.model.board.gameRules.RuleContainer;
import org.example.view.objects.CardMovementListener;

import java.util.ArrayList;
import java.util.List;

public class CardPosition {
    private final RuleContainer placementRule;
    private final RuleContainer takeRule;
    private final List<Card> cards = new ArrayList<>();
    private CardMovementListener cardMovementListener;

    public CardPosition() {
        placementRule = new RuleContainer();
        takeRule = new RuleContainer();
    }

    public CardPosition(RuleContainer placementRule, RuleContainer takeRule) {
        this.placementRule = placementRule;
        this.takeRule = takeRule;
    }

    public void addTakeRule(GameRule rule) {
        takeRule.addRule(rule);
    }

    public void setCardMovementListener(CardMovementListener cardMovementListener) {
        this.cardMovementListener = cardMovementListener;
    }

    public void addPlacementRule(GameRule rule) {
        placementRule.addRule(rule);
    }

    public boolean isCardStackEmpty() {
        return cards.size() == 0;
    }

    public Card getTopCard() {
        if (!isCardStackEmpty()) {
            return cards.get(cards.size() - 1);
        } else {
            return null;
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public int numberOfCards() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.size() == 0;
    }

    public int getIndexOfCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) == card) {
                return i;
            }
        }
        return -1;
    }

    private void takeCards(List<Card> cardList) {
        cards.removeAll(cardList);
//        cardMovementListener.takeCards(this, cardList.size());
        notifyCardMovementListener();
    }

    public boolean isValidTake(List<Card> cardsToTake) {
        for (Card card : cardsToTake) {
            if (!takeRule.checkRules(this, null, card)) {
                return false;
            }
        }
        return true;
    }

    public void placeCards(List<Card> cardList) {
        cards.addAll(cardList);
        notifyCardMovementListener();
//        notifyCardMovementListener(this, cardList);
    }

    public boolean requestPlacements(CardPosition takePosition, List<Card> cardList) {
        if (takePosition.isValidTake(cardList) && this.isValidPlacement(getTopCard(), cardList)) {
            takePosition.takeCards(cardList);
            this.placeCards(cardList);
            return true;
        }
        return false;
    }

    public boolean isValidPlacement(Card nextCard, List<Card> newCards) {
        for (Card card : newCards) {
            if (!placementRule.checkRules(this, nextCard, card)) {
                return false;
            }
            nextCard = card;
        }
        return true;
    }

    private void notifyCardMovementListener() {
        if (cardMovementListener != null) {
            cardMovementListener.updateView();
        }
    }
}
