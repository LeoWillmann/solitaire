package org.example.model.board.gameRules;

import org.example.model.board.deck.card.Card;

import java.util.ArrayList;
import java.util.List;

public class CardIdRule extends GameRule {
    private final List<Integer> allowedCardIds = new ArrayList<>();

    public CardIdRule(int id) {
        allowedCardIds.add(id);
    }

    public CardIdRule(List<Integer> ids) {
        allowedCardIds.addAll(ids);
    }

    public void addAllowedCardId(int id) {
        allowedCardIds.add(id);
    }

    public void addAllowedCardId(List<Integer> ids) {
        allowedCardIds.addAll(ids);
    }

    @Override
    public boolean validateMove(Card bottom, Card top) {
        for (Integer id : allowedCardIds) {
            if (id == top.cardValue()) {
                return true;
            }
        }
        return false;
    }
}
