package Game.UI.Buttons;

import javax.swing.*;

public class Button extends JButton {
    public Button (String title) {
        JButton button = new JButton(title);
        button.setVisible(true);
        button.setBounds(50,100,100,40);
    }
}
