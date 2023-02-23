package objects;

import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Objects.*;
import static utilz.Constants.Objects.GetSpriteAmount;

public class GameObjects {
    protected int x, y, objType;
    protected Rectangle2D.Float hitBox;
    protected boolean doAnimation, active = true;
    protected int aniTick, aniIndex;
    protected int ANI_SPEED;
    protected int xDrawOffset, yDrawOffset;

    public GameObjects(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    protected void updateAnimationTick() {
        aniTick++;
        int startAni = objType;

        if (objType == ARCHER_ATTACK_LEFT || objType == ARCHER_ATTACK_RIGHT) {
            ANI_SPEED = 60;
        } else {
            ANI_SPEED = 25;
        }
            if (aniTick >= ANI_SPEED) {
                aniTick = 0;
                aniIndex++;
                if (aniIndex >= GetSpriteAmount(objType)) {
                    aniIndex = 0;

                    switch (objType) {
                        case BOX:
                            doAnimation = false;
                            active = false;
                            break;
                        case ARCHER_DEATH_LEFT, ARCHER_DEATH_RIGHT:
                            active = false;
                    }
                }
            }
        if (startAni != objType) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniIndex = 0;
        aniTick = 0;
    }

    public void reset() {
        aniIndex = 0;
        aniTick = 0;

        if (objType == BOX) {
            doAnimation = false;
            active = true;
        }
        else if (objType == RED_POTION || objType == BLUE_POTION){
            doAnimation = true;
            active = false;
        }
        else if (objType == ARCHER_DEATH_LEFT) {
            objType = ARCHER_ATTACK_LEFT;
            active = true;
        }
        else if (objType == ARCHER_DEATH_RIGHT) {
            objType = ARCHER_ATTACK_RIGHT;
            active = true;
        }
    }

    protected void initHitbox( int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitBox.x - xLvlOffset, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitBox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public int getAniTick() {
        return aniTick;
    }

}
