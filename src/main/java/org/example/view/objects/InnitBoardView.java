package org.example.view.objects;

import org.example.model.board.Board;
import org.example.model.innit.InnitGameBoards;
import org.example.view.BoardView;
import org.example.view.objects.cardPositionView.CardPositionView;
import org.example.view.objects.cardView.CardView;

public class InnitBoardView {
    public static BoardView solitaire() {
        BoardView boardView = new BoardView(InnitGameBoards.makeSolitaire());
        innitSolitaire(boardView);

        return boardView;
    }

    public static void innitSolitaire(BoardView boardView) {
        Board board = boardView.getBoard();
        int j = 0;
        for (int i = 0; i < 7; i++) {
            CardPositionView cardPositionView = new CardPositionView(board.getCardPositions().get(j), -1, true, boardView);
            cardPositionView.setPosition((int) (boardView.getPoint().getX() + j * (CardView.CARD_WIDTH + BoardView.HORIZONTAL_COLUMN_DISTANCE)), (int) (boardView.getPoint().getY() + CardView.CARD_HEIGHT + BoardView.VERTICAL_COLUMN_DISTANCE));
            boardView.getCardPositionViews().add(cardPositionView);
            j++;
        }

        for (int i = 0; i < 4; i++) {
            CardPositionView cardPositionView = new CardPositionView(board.getCardPositions().get(j), 1, true, boardView);
            cardPositionView.setPosition((int) (boardView.getPoint().getX() + i * (CardView.CARD_WIDTH + BoardView.HORIZONTAL_COLUMN_DISTANCE) + 400), (int) boardView.getPoint().getY());
            boardView.getCardPositionViews().add(cardPositionView);
            j++;
        }
    }

    public static BoardView napoleon() {
        int columns = 11;
        BoardView boardView = new BoardView(InnitGameBoards.makeGrandNapoleon(columns));
        innitNapoleon(boardView, columns);

        return boardView;
    }

    private static void innitNapoleon(BoardView boardView, int columns) {
        Board board = boardView.getBoard();
        int j = 0;
        for (int i = 0; i < columns; i++) {
            CardPositionView cardPositionView = new CardPositionView(board.getCardPositions().get(j), -1, true, boardView);
            cardPositionView.setPosition((int) (boardView.getPoint().getX() + j * (CardView.CARD_WIDTH + BoardView.HORIZONTAL_COLUMN_DISTANCE)), (int) (boardView.getPoint().getY() + CardView.CARD_HEIGHT + BoardView.VERTICAL_COLUMN_DISTANCE));
            boardView.getCardPositionViews().add(cardPositionView);
            j++;
        }

        for (int i = 0; i < 8; i++) {
            CardPositionView cardPositionView = new CardPositionView(board.getCardPositions().get(j), 1, true, boardView);
            cardPositionView.setPosition((int) (boardView.getPoint().getX() + i * (CardView.CARD_WIDTH + BoardView.HORIZONTAL_COLUMN_DISTANCE) + 400), (int) boardView.getPoint().getY());
            boardView.getCardPositionViews().add(cardPositionView);
            j++;
        }
    }
}
