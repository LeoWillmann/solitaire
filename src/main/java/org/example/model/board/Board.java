package org.example.model.board;

import org.example.model.board.boardLayout.BoardLayout;
import org.example.model.board.rules.BoardRules;

/**
 * Board class will be the game board for the solitaire game.
 */
public class Board {

    private BoardLayout layout;
    private BoardRules rules;

    public Board(BoardLayout layout, BoardRules rules) {
        this.layout = layout;
        this.rules = rules;
    }
}
