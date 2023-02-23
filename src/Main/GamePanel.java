package Main;
import Inputs.KeyBoard;
import Inputs.Mouse;
import javax.swing.*;
import java.awt.*;
import static Main.Game.GAME_WIDTH;
import static Main.Game.GAME_HEIGHT;


public class GamePanel extends JPanel {
    private Mouse mouseInputs;
    private Game game;
    public GamePanel(Game game) {
        this.game = game;
        setPanelSize();
        mouseInputs = new Mouse(this);
        addKeyListener(new KeyBoard(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public void updateGame() {}

    public Game getGame() {
        return game;
    }
}
