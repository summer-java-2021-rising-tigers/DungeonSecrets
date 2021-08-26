package com.dungeonsecrets.frontEnd;

import com.dungeonsecrets.Chapter1.Chapter1;
import com.dungeonsecrets.backEnd.gameGridObjects.Enemy;
import com.dungeonsecrets.backEnd.gameGridObjects.GameObject;
import com.dungeonsecrets.backEnd.gameGridObjects.Hero;
import com.dungeonsecrets.backEnd.gameGridObjects.Tile;

import javax.swing.*;
import java.awt.*;

public class GameGrid extends JPanel{

    private static GameGrid instance;
    private int gameGridRows = 21;
    private int gameGridCols = 32;
    private GameObject[][] grid;
    private GameObject hero;

    public GameGrid(){

        this.setOpaque(false);
        this.bootstrap();
        instance = this;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 0; row < gameGridRows; row++) {
            for(int col = 0; col < gameGridCols; col++) {
                grid[row][col].render(g);
            }
        }
    }
    public static GameGrid getInstance(){ 
        return instance;
    }

    public GameObject[][] getGrid(){
        return grid;
    }

    public GameObject getHero(){
        return hero;
    }

    private void bootstrap(){
        this.generateGrid();
    }

    private GameObject[][] generateGrid(){
        grid = new GameObject[gameGridRows][gameGridCols];

        for(int row = 0; row < gameGridRows; row++) {
            for(int col = 0; col < gameGridCols; col++) {
                grid[row][col] = new Tile(row, col);
            }
        }
        spawnHero();
        spawnEnemy();
        return grid;
    }

    private void spawnHero(){
        hero = new Hero(15, 7);
        grid[15][7] = hero;

    }

    private void spawnEnemy(){
//        enemy = new Enemy(7, 7);
//        grid[7][7] = enemy;
//        enemy2 = new Enemy(8, 7);
//        grid[8][7] = enemy2;
        ArrayList<GameObject> monsters = chapter.getMonsters();
        for(GameObject monster : monsters){
            int mRow = monster.getRow();
            int mCol = monster.getCol();
            grid[mRow][mCol] = monster;
        }

    }
}
