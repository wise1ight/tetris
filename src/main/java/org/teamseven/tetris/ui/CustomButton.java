package org.teamseven.tetris.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomButton extends JButton {
    private final Font font = this.getFont();

    public CustomButton() {
        // set Inputkey(focus)
        this.getInputMap().getParent().remove(KeyStroke.getKeyStroke("pressed SPACE"));
        this.getInputMap().getParent().remove(KeyStroke.getKeyStroke("released SPACE"));
        this.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("pressed ENTER"), "pressed");
        this.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("released ENTER"), "released");

        // set Listners
        this.addFocusListener(focusListener);

        CustomButton.this.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 5));
    }

    public CustomButton(String text) {
        this();
        this.setText(text);
    }

    //focus animation
    private FocusListener focusListener = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            CustomButton.this.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 15));
        }

        @Override
        public void focusLost(FocusEvent e) {
            CustomButton.this.setFont(new Font(font.getName(), font.getStyle(), font.getSize() + 5));
        }
    };

}
