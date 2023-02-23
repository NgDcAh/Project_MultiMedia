package levels;

import Main.Game;
import entities.AssassinEnemy;
import entities.Boss;
import entities.SpearEnemy;
import objects.Archer;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;
import utilz.CheckMethods;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.CheckMethods.*;

public class Level {
    private BufferedImage img;
    private ArrayList<AssassinEnemy> assassinEnemies;
    private ArrayList<SpearEnemy> spearmanEnemies;
    private Boss boss;
    private ArrayList<Spike> spikes;
    private ArrayList<GameContainer> containers;
    private ArrayList<Potion> potions;
    private ArrayList<Archer> archers;
    private int[][] lvlData;
    private int lvlTilesWide;
    private int maxTileOffset;
    private int maxLvlOffsetX;
    private Point playerSpawn;

    public Level(BufferedImage img){
        this.img = img;
        createLevelData();
        createEnemies();
        createSpikes();
        createContainers();
        createPotions();
        createArchers();
        calcLvlOffsets();
        calcPlayerSpawn();
    }

    private void createArchers() {
        archers = CheckMethods.GetArchers(img);
    }

    private void createSpikes() {
        spikes = CheckMethods.getSpikes(img);
    }

    private void createContainers() {
        containers = CheckMethods.GetContainers(img);
    }

    private void createPotions() {
        potions = CheckMethods.GetPotions(img);
    }

    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTileOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTileOffset;
    }

    private void createEnemies() {
        assassinEnemies = GetAssassins(img);
        spearmanEnemies = GetSpearman(img);
        boss = GetBoss(img);
    }

    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLvlData() {
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    public ArrayList<AssassinEnemy> getAssassinEnemies() {
        return assassinEnemies;
    }

    public ArrayList<SpearEnemy> getSpearmanEnemies() {
        return spearmanEnemies;
    }

    public Boss getBoss() {
        return boss;
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<GameContainer> getContainers() {
        return containers;
    }
    public ArrayList<Archer> getArchers() {
        return archers;
    }
}
