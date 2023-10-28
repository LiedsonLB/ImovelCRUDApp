package LuxuriousBreeze;

import javax.swing.*;

import LuxuriousBreeze.pages.ImovelGUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImovelGUI gui = new ImovelGUI();
            gui.setVisible(true);
        });
    }
}
