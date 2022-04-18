package org.teamseven.tetris.ui;

import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame implements IDesign {
    public void setFrame(String title) {
        this.setTitle(title);                                //set title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application using the System exit method
        this.pack();                                         // set window size to fit layouts
        this.setLocationRelativeTo(null);                    // set window pos middle
        this.setResizable(false);                            // is resize by user

        setComp();
        setDesign();
        setAction();

        this.setVisible(true);                               // is visible
    }

}
