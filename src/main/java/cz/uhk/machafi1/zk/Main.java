package cz.uhk.machafi1.zk;

import cz.uhk.machafi1.zk.gui.MainFrame;
import cz.uhk.machafi1.zk.model.Data;

public class Main {

    public static void main(String[] args) {
        Data data = new Data();

        MainFrame mainFrame = new MainFrame(925, 630, data);
        mainFrame.setVisible(true);
    }
}
