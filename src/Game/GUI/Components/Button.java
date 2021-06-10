package Game.GUI.Components;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    private String title;
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * This constructor is created to reduce the lines in BgPanel
     * @param title the title of the button
     * @param x the x position of the button
     * @param y the x position of the button
     * @param width the width of the button
     * @param height the height of the button
     */
    public Button(String title, int x, int y, int width, int height){
        this.title=title;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.setLayout(null);
        this.setText(title);
        this.setFont(new Font("Stencil",Font.PLAIN,20));
        this.setBounds(x,y,width,height);
    }
}
