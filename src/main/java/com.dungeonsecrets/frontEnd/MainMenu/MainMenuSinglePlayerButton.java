package com.dungeonsecrets.frontEnd.MainMenu;

import com.dungeonsecrets.Main;
import com.dungeonsecrets.frontEnd.MainFrame.MainFrame;
import com.dungeonsecrets.sound.ButtonClickSound;
import com.dungeonsecrets.sound.MusicManager;
import com.dungeonsecrets.sound.MusicThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenuSinglePlayerButton extends JLabel implements MouseListener {

    public static boolean isSinglePlayerMenuOpen = false;

    //Взимане височината и дебелината на екрана
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();

    //Смятам началната позиция на бутона
    int buttonXPos = width / 2 - 100;
    int buttonYPos = height / 2 + 1;

    MainMenuSinglePlayerButton(){

        this.setForeground(new Color(111,0,0));
        this.setText("Singleplayer");
        this.setFont(new Font("Immortal",Font.BOLD,30));
        this.setBackground(Color.BLACK);
        this.setOpaque(false);
        this.setBounds(buttonXPos,buttonYPos,200,40);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //This has to be moved later to the button that starts the game. For now this is the right place
        MusicThread.stopMusic();
        MusicManager.music = new MusicThread();
        MusicManager.music.startMusic("soundResources/CalmOutdoors.wav");


        new ButtonClickSound();
        MainFrame.closeMainMenu();
//        com.dungeonsecrets.frontEnd.MainFrame.openSinglePlayerMenu();
//        GameLayout mainLayout = new GameLayout();
//        this.add(mainLayout);
        MainFrame.openMainLayout();
        this.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {



    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setFont(new Font("Immortal",Font.BOLD,31));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setFont(new Font("Immortal",Font.BOLD,30));
    }
}
