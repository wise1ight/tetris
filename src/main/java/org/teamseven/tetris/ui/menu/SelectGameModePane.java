package org.teamseven.tetris.ui.menu;

import org.teamseven.tetris.Pipeline;
import org.teamseven.tetris.handler.*;
import org.teamseven.tetris.ui.BasePane;
import org.teamseven.tetris.ui.CustomButton;
import org.teamseven.tetris.ui.game.TetrisPane;
import org.teamseven.tetris.ui.game.TwoPlayerModeTetrisPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectGameModePane extends BasePane {
    private Label title;
    private Panel gameModePanel, buttonPanel, titlePanel;
    private CustomButton normalMode, itemMode, twoPlayerMode_Normal, twoPlayerMode_Item, twoPlayerMode_Time, home;

    @Override
    public void setComp() {
        gameModePanel = new Panel();

        titlePanel = new Panel();

        title = new Label("Select Game Mode");
        titlePanel.add(title);

        buttonPanel = new Panel();

        normalMode = new CustomButton("Normal Mode");
        itemMode = new CustomButton("Item Mode");
        twoPlayerMode_Normal = new CustomButton("Two Player Mode : Normal");
        twoPlayerMode_Item = new CustomButton("Two Player Mode : Item");
        twoPlayerMode_Time = new CustomButton("Two Player Mode : Time");
        home = new CustomButton("Back to Menu");

        buttonPanel.add(normalMode);
        buttonPanel.add(itemMode);
        buttonPanel.add(twoPlayerMode_Normal);
        buttonPanel.add(twoPlayerMode_Item);
        buttonPanel.add(twoPlayerMode_Time);
        buttonPanel.add(home);

        gameModePanel.add(buttonPanel);
        gameModePanel.add(titlePanel);

        this.add(gameModePanel);
    }

    @Override
    public void setDesign() {
        gameModePanel.setSize(Pipeline.getScreenResolutionX(), Pipeline.getScreenResolutionY());
        gameModePanel.setBackground(Color.black);
        gameModePanel.setLayout(null);
        gameModePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 8));

        titlePanel.setBounds(0, sizeInt * 50, Pipeline.getScreenResolutionX(), sizeInt * 40);
        titlePanel.setFont(new Font("Dialog", Font.PLAIN, sizeInt * 25));

        title.setForeground(Color.RED);

        buttonPanel.setBounds(sizeInt * 50, sizeInt * 110, Pipeline.getScreenResolutionX() - sizeInt * 50 * 2, sizeInt * 150);
        buttonPanel.setLayout(new GridLayout(3, 5));
    }

    public void setAction() {
        setFocusTraversal();

        home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new MainMenuPane());
            }
        });

        normalMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new TetrisPane(new GameHandler()));
            }
        });

        itemMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pipeline.replacePane(new TetrisPane(new ItemModeHandler()));
            }
        });

        twoPlayerMode_Normal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MatchModeHandler aGameHandler = new MatchModeHandler();
                MatchModeHandler bGameHandler = new MatchModeHandler();
                Pipeline.replacePane(new TwoPlayerModeTetrisPane(new MatchModeBridge(aGameHandler, bGameHandler)));
            }
        });

        twoPlayerMode_Item.addActionListener(e -> {
            MatchModeHandler aGameHandler = new ItemMatchModeHandler();
            MatchModeHandler bGameHandler = new ItemMatchModeHandler();
            Pipeline.replacePane(new TwoPlayerModeTetrisPane(new MatchModeBridge(aGameHandler, bGameHandler)));
        });
    }

}
