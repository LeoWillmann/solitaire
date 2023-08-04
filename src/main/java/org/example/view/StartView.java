package org.example.view;

import org.example.view.objects.InnitBoardView;

import javax.swing.*;
import java.awt.*;

/**
 * Starts the view and creates a Swing JFrame.
 */
public class StartView {
    /**
     * Start UI function.
     */
    public void startUI() {
        JFrame frame = new JFrame("Game Frame"); // makes frame with name "Game frame"

        frame.setMinimumSize(new Dimension(500, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits application on closing of window
        frame.setResizable(true); // set resizable to true
        frame.setPreferredSize(new Dimension(1400, 800)); // dimensions of frame
        frame.pack();

        frame.requestFocusInWindow();
        frame.setLocationRelativeTo(null); // set no relative location
        frame.setVisible(true); // sets frame to visible

        JPanel boardView = InnitBoardView.napoleon();
        frame.add(boardView);
    }


}
