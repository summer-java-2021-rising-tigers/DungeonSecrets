package com.dungeonsecrets.backEnd.gameGridObjects;

import com.dungeonsecrets.backEnd.enums.MoveDirection;
import com.dungeonsecrets.backEnd.processors.APIConnect;
import com.dungeonsecrets.backEnd.utility.ScreenResolution;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Monster extends GameObject {

    private String name;
    private String type;
    private int row;
    private int col;
    private int armor_class;
    private int hit_points;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private String url;


    private static MoveDirection orientation = MoveDirection.UP;
    private Image iconToShow;

    private Image iconUpp       = new ImageIcon("src/main/resources/imgs/enemyOneUp.png").getImage();
    private Image iconDownn     = new ImageIcon("src/main/resources/imgs/enemyOneDown.png").getImage();
    private Image iconLeftt     = new ImageIcon("src/main/resources/imgs/enemyOneLeft.png").getImage();
    private Image iconRightt    = new ImageIcon("src/main/resources/imgs/enemyOneRight.png").getImage();
    int maxHP = 1000;
    int currentHp = 1000;

    public Monster(int row, int col) {
        super(row, col);
        monsterInit(row, col);
    }
    public void render(Graphics g) {
        int mapWidth    = (int)((ScreenResolution.getScreenWidth())*0.8);
        int mapHeight   = ScreenResolution.getScreenHeight();

        int tileWidth   = mapWidth/31;
        int tileHeight  = mapHeight/21;

        int tileX = this.col * tileWidth;
        int tileY = this.row * tileHeight;

        g.drawImage(iconUpp, tileX, tileY, tileWidth, tileHeight, null);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setOrientation(MoveDirection moveToDirection){
        if(moveToDirection.equals(MoveDirection.UP)){
            iconToShow = iconUpp;
        }
        if(moveToDirection.equals(MoveDirection.DOWN)){
            iconToShow = iconDownn;
        }
        if(moveToDirection.equals(MoveDirection.LEFT)){
            iconToShow = iconLeftt;
        }
        if(moveToDirection.equals(MoveDirection.RIGHT)){
            iconToShow = iconRightt;
        }
    }

    private String getIndex(){

        JSONArray indexes = APIConnect.getMonstersIndexList();

        int randomMonsterIndex = new Random().nextInt((indexes.length()) + 1);
        JSONObject innerObj = indexes.getJSONObject(randomMonsterIndex);

        return innerObj.getString("index");
    }

    private void monsterInit(int row, int col){
        String index = getIndex();
        JSONObject monster = APIConnect.getMonster(index);

        this.row = row;
        this.col = col;

        this.name           = monster.getString("name");
        this.type           = monster.getString("type");
        this.armor_class    = monster.getInt("armor_class");
        this.hit_points     = monster.getInt("hit_points");
        this.strength       = monster.getInt("strength");
        this.dexterity      = monster.getInt("dexterity");
        this.constitution   = monster.getInt("constitution");
        this.intelligence   = monster.getInt("intelligence");
        this.wisdom         = monster.getInt("wisdom");
        this.charisma       = monster.getInt("charisma");
        this.url            = monster.getString("url");


    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public void setRow(int row){
        super.row = row;
    }
    public void setCol(int col){
        super.col = col;
    }

}