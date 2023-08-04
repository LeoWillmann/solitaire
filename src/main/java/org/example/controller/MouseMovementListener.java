package org.example.controller;

import org.example.model.board.CardPosition;
import org.example.model.board.deck.card.Card;
import org.example.view.BoardView;
import org.example.view.objects.cardPositionView.CardPositionView;
import org.example.view.objects.cardView.CardView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static org.example.view.objects.cardPositionView.CardPositionView.CARD_VERTICAL_DISTANCE;

/**
 * Listens to mouse movement and reflects this on the boardView.
 */
public class MouseMovementListener extends MouseAdapter {

    private static final int CARDVIEW_DISTANCE_MAX = 60;
    private final BoardView boardView;
    private List<CardView> cardViewList = null;
    private int xOffset;
    private int yOffset;

    /**
     * Constructor.
     * @param boardView
     */
    public MouseMovementListener(BoardView boardView) {
        this.boardView = boardView;
    }

    /**
     * Takes the Card from the cardView and returns the list from that.
     * @param cardViews Cardviews to take cards from.
     * @return cards from cardView.
     */
    private static List<Card> reduceCardViewToCards(List<CardView> cardViews) {
        List<Card> cards = new ArrayList<>();
        for (CardView cardView : cardViews) {
            cards.add(cardView.getCard());
        }
        return cards;
    }

    /**
     * When mouse pressed. Check if mouse event is on a cardView, if so, check if you can take the cards.
     * If not check if clicked on deck.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        CardView cardView = getCardView(e);
        if (cardView != null) {
            cardViewList = cardView.getParent().getCardsAfterCard(cardView);
            List<Card> cards = reduceCardViewToCards(cardViewList);
            if (cardView.getParent().getCardPosition().isValidTake(cards)) {
                boardView.setCardViewsToTop(cardViewList);
                boardView.setSelectedCards(cardViewList);
                xOffset = (int) (e.getX() - cardView.getPoint().getX());
                yOffset = (int) (e.getY() - cardView.getPoint().getY());
            } else {
                cardViewList = null;
            }
        } else {
            if (isOnDeck(e)) {
                boardView.getDeckView().getDeck().performAction();
            }
        }
    }

    /**
     * Checks if the mouseEvent e is within the deck area.
     * @param e mouseEvent.
     * @return if mouseEvent e is within the deck area return true, otherwise false.
     */
    private boolean isOnDeck(MouseEvent e) {
        int x1 = (int) boardView.getDeckView().getPoint().getX();
        int y1 = (int) boardView.getDeckView().getPoint().getY();
        int x2 = x1 + CardView.CARD_WIDTH;
        int y2 = y1 + CardView.CARD_HEIGHT;
        if (e.getX() >= x1 && e.getX() <= x2 && e.getY() >= y1 && e.getY() <= y2) {
            return true;
        }
        return false;
    }

    /**
     * when dragging mouse, check if there is a selected cardViewList, update its location.
     *
     * @param e mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (cardViewList != null) {
            // move cards
            setPositionsOfCardList(e);
            boardView.notifyListener();
        }
    }

    /**
     * sets positions of the cardViews according to vertical positioning.
     * @param e mouse position.
     */
    private void setPositionsOfCardList(MouseEvent e) {
        int y = e.getY() - yOffset;
        for (CardView cardView : cardViewList) {
            cardView.setPosition(e.getX() - xOffset, y);
            y += CARD_VERTICAL_DISTANCE;
        }
    }

    /**
     * Checks for all cardViews if the mouseEvent is within the cardView.
     * returns the latest CardView in the list, if at all.
     * @param e MouseEvent.
     * @return CardView.
     */
    private CardView getCardView(MouseEvent e) {
        CardView cardClicked = null;
        for (CardView cardView : boardView.getCardViews()) {
            int x1 = (int) cardView.getPoint().getX();
            int y1 = (int) cardView.getPoint().getY();
            int x2 = x1 + CardView.CARD_WIDTH;
            int y2 = y1 + CardView.CARD_HEIGHT;
            if (e.getX() >= x1 && e.getX() <= x2 && e.getY() >= y1 && e.getY() <= y2) {
                cardClicked = cardView;
            }
        }
        return cardClicked;
    }

    /**
     * On mouse release, resolve card movement and move the selected cards to the supposed position.
     * @param e MouseEvent.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        boardView.clearSelectedCards();
        resolveCardMovement();

        cardViewList = null;
    }

    /**
     * Resolves card movement by moving cards back to original position if mouse released
     * more than CARDVIEW_DISTANCE_MAX pixels away or is an invalid placement.
     * Otherwise, move cards.
     */
    private void resolveCardMovement() {
        if (cardViewList == null) {
            return;
        }

        CardPositionView cardPositionView = getClosestCardPositionView(cardViewList.get(0));
        if (cardPositionView == null || !moveCardAttempt(cardPositionView, cardViewList)) {
            resetCardViewListPositions();
        }
    }

    /**
     * Attempts to move cards from CardPosition to CardPosition, returns true if successful, false otherwise.
     * @param cardPositionView move cards to this cardPositionView.
     * @param cardViews CardViews to move.
     * @return success status.
     */
    private boolean moveCardAttempt(CardPositionView cardPositionView, List<CardView> cardViews) {
        CardPosition placePosition = cardPositionView.getCardPosition();
        CardPosition takePosition = cardViews.get(0).getParent().getCardPosition();
        List<Card> cards = reduceCardViewToCards(cardViews);

        return placePosition.requestPlacements(takePosition, cards);
    }

    /**
     * Gets the closest card position view from the CardView. That is within CARDVIEW_DISTANCE_MAX and not its parent.
     * @param cardView CardView to compare to.
     * @return closest card position view from the CardView, null if not within CARDVIEW_DISTANCE_MAX.
     */
    private CardPositionView getClosestCardPositionView(CardView cardView) {
        int distance = Integer.MAX_VALUE;
        CardPositionView cpv = null;
        for (CardPositionView cardPositionView : boardView.getCardPositionViews()) {
            int temp = cardPositionView.placementDistanceFromPoint(cardView.getPoint());
            if (cardPositionView != cardView.getParent() && temp < distance) {
                distance = temp;
                cpv = cardPositionView;
            }
        }
        if (distance <= CARDVIEW_DISTANCE_MAX) {
            return cpv;
        } else {
            return null;
        }
    }

    /**
     * resets the card view locations.
     */
    private void resetCardViewListPositions() {
        if (cardViewList == null) {
            return;
        }
        for (CardView cardView : cardViewList) {
            cardView.getParent().setCardViewPos(cardView);
        }
        boardView.notifyListener();
    }

}
