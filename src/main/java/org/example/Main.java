package org.example;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.example.view.StartView;

import javax.swing.*;

/**
 * Main class.
 */
public class Main {
    /**
     * Main function.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        FlatDarculaLaf.setup(); // Dark mode

        StartView startView = new StartView();

        SwingUtilities.invokeLater(() -> {
            startView.startUI(); // start gui
        });
    }
}