package org.teamseven.tetris.ui;

public class MainFrame extends BaseFrame {

    private int[] resolution = new int[2];           // frame resolution
    private int[] preferredResolution = new int[2];  // frame resolution - frame top border

    public MainFrame() {
        resolution[0] = 1600;
        resolution[1] = 900;
        int[] frameBorderSize = new int[2];       // frame top border
        frameBorderSize[0] = this.getInsets().left + this.getInsets().right;
        frameBorderSize[1] = this.getInsets().top + this.getInsets().bottom;
        preferredResolution[0] = resolution[0] - frameBorderSize[0];
        preferredResolution[1] = resolution[1] - frameBorderSize[1];

        this.setFrame("Tetris");
    }

    @Override
    public void setComp() {
        getContentPane().add(new GameMenuPane(preferredResolution));
    }

    @Override
    public void setDesign() {
        setSize(resolution[0], resolution[1]);
    }

    @Override
    public void setAction() {
    }

}

