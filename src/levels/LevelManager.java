package levels;

import Gamestate.GameState;
import Main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelManager {
    private Game game;
    private BufferedImage levelSprites[];
    private ArrayList<Level> levels;
    private int lvlIndex = 0;

    public  LevelManager(Game game) {
        this.game = game;
        importOutSideSprites();
        levels = new ArrayList<>();
        buildAllLevels();
    };

    public void loadNextLevel() {
        lvlIndex++;
        if (lvlIndex >= levels.size()) {
            lvlIndex = 0;
            System.out.println("No more levels! Game Completed!");
            GameState.state = GameState.MENU;
        }

        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLvlData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadSave.GetAllLevels();
        for (BufferedImage img : allLevels)
            levels.add(new Level(img));
    }

    private void importOutSideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprites = new BufferedImage[150];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 25; j++){
                int index = i * 25 + j;
                levelSprites[index] = img.getSubimage(j * 8, i * 8, 8, 8);
            }
        }
    }

    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLvlData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprites[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }


    public void update () {

    }

    public Level getCurrentLevel() {
        return levels.get(lvlIndex);
    }
    public Level getLastLevel() {
        return levels.get(levels.size() - 1);
    }

    public int getAmountOfLevels() {
        return levels.size();
    }
}
