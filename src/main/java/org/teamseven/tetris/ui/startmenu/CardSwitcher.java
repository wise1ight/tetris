package org.teamseven.tetris.ui.startmenu;

import java.awt.*;

public class CardSwitcher {
    CardLayout layout;
    Container container;

    public CardSwitcher(Container container, CardLayout layout) {
        this.layout = layout;
        this.container = container;
    }

    public void switchCard(String card) {
        layout.show(container, card);
    }
}