package org.example.model.board;

import org.example.model.board.deck.card.Card;
import org.example.model.board.gameRules.ruleContainers.RuleCheckable;
import org.example.view.objects.cardPositionView.CardMovementListener;

import java.util.ArrayList;
import java.util.List;

public class CardPosition {
    private final List<Card> cards = new ArrayList<>();
    private RuleCheckable placementRule;
    private RuleCheckable takeRule;
    private CardMovementListener cardMovementListener;

    public CardPosition() {
    }

    public CardPosition(RuleCheckable placementRule, RuleCheckable takeRule) {
        this.placementRule = placementRule;
        this.takeRule = takeRule;
    }

    public void setPlacementRule(RuleCheckable placementRule) {
        this.placementRule = placementRule;
    }

    public void setTakeRule(RuleCheckable takeRule) {
        this.takeRule = takeRule;
    }

    public void setCardMovementListener(CardMovementListener cardMovementListener) {
        this.cardMovementListener = cardMovementListener;
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

    public void takeTopCard() {
        cards.remove(cards.size() - 1);
        notifyCardMovementListener();
    }

    public void takeTopCards(int i) {
        for (int j = 0; j < i; j++) {
            takeTopCard();
        }
    }

    public void takeCards(List<Card> cardList) {
        cards.removeAll(cardList);
        notifyCardMovementListener();
    }

    public boolean isValidTake(List<Card> cardsToTake) {
        return takeRule.checkRule(this, null, cardsToTake);
    }

    public void placeCards(List<Card> cardList) {
        for (Card card : cardList) {
            placeCard(card);
        }
    }

    public void placeCard(Card card) {
        if (card != null) {
            cards.add(card);
            notifyCardMovementListener();
        }
    }

    public boolean requestPlacements(CardPosition takePosition, List<Card> cardList) {
        if (takePosition.isValidTake(cardList) && this.isValidPlacement(getTopCard(), cardList)) {
            takePosition.takeTopCards(cardList.size());
            this.placeCards(cardList);
            return true;
        }
        return false;
    }

    public boolean isValidPlacement(Card nextCard, List<Card> newCards) {
        return placementRule.checkRule(this, nextCard, newCards);


//        for (Card card : newCards) {
//            if (!placementRule.checkRules(this, nextCard, card)) {
//                return false;
//            }
//            nextCard = card;
//        }
//        return true;
    }

    private void notifyCardMovementListener() {
        if (cardMovementListener != null) {
            cardMovementListener.updateView();
        }
    }
}
