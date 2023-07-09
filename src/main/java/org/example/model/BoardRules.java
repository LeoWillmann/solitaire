package org.example.model;

/**
 * Rules for the board. Constitutes what a valid move is and the victory condition.
 */
public abstract class BoardRules {

    public abstract boolean validSuit();

    public abstract boolean validMove();
}
