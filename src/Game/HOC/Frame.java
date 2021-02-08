package Game.HOC;

import city.cs.engine.UserView;

import javax.swing.*;

public class Frame extends JFrame{
    private final JFrame frame;
    public Frame(UserView view){
        frame = new JFrame("The Game");
        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
    }
}
