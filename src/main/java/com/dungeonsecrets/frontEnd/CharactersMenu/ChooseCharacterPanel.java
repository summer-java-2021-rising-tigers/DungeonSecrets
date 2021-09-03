package com.dungeonsecrets.frontEnd.CharactersMenu;

import com.dungeonsecrets.backEnd.GameInfo.CurrentUser;
import com.dungeonsecrets.backEnd.gameGridObjects.Character;
import com.dungeonsecrets.backEnd.objects.characterListItem;
//import com.dungeonsecrets.backEnd.processors.Character;
import com.dungeonsecrets.backEnd.processors.GetHeroList;
import com.dungeonsecrets.backEnd.utility.ScreenResolution;
import com.dungeonsecrets.frontEnd.BackgroundWithoutLogo;
import com.dungeonsecrets.frontEnd.MainFrame.MainFrame;
import com.dungeonsecrets.frontEnd.MainMenuPanel;
import com.dungeonsecrets.sound.ButtonClickSound;
import com.dungeonsecrets.sound.MusicManager;
import com.dungeonsecrets.sound.MusicThread;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChooseCharacterPanel extends JPanel {

    BackgroundWithoutLogo background = new BackgroundWithoutLogo();

//    String username = LoginMenu.getUsername();
    public static JList<String> characterList                   = new JList<>();
    public static DefaultListModel<String> characterModel;
    JButton selectButton                                        = new JButton("Select");
    JButton createButton                                        = new JButton("Create New Character");
    JButton backButton                                          = new JButton("Back");
    JButton deleteButton                                        = new JButton("Delete");
    JPanel characterInfoPanel                                   = new JPanel();
    JLabel characterInfoLabel                                   = new JLabel();
    JSplitPane characterSplitPane                               = new JSplitPane();
    JScrollPane characterScrollPane                             = new JScrollPane(characterList);
    private static boolean isHeroSelected                       = false;
//    private ArrayList<String> heroes                            = GetHeroList.getHeroes();

    public ChooseCharacterPanel(){
        ArrayList<characterListItem> heroes                            = GetHeroList.getHeroes();
        characterModel       = new DefaultListModel<>();

        for (int i = 0; i < heroes.size(); i++)
        {
            characterModel.addElement(heroes.get(i).getCharacterName());
        }

        characterList.setModel(characterModel);
        characterList.setOpaque(false);
        characterList.setBackground(new Color(196,153,80));
        characterList.setForeground(new Color(111,0,0));
        characterList.setBorder(BorderFactory.createLineBorder(new Color(196,153,80)));
        characterList.setFont(new Font("Immortal",Font.BOLD,30));

        characterList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                characterListItem chosenCharacter = getCharacter(characterList.getSelectedValue(), heroes);

                characterInfoLabel.setText("Name: " + CurrentUser.getInstance().getUsername()+ " ID: " + CurrentUser.getInstance().getUserId());
                Character.getInstance().setHero(chosenCharacter);
                isHeroSelected = true;
                //put the select button outside in order to fix the "double tap" bug
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ButtonClickSound();
                if(isHeroSelected) {
                    MusicThread.stopMusic();
                    MusicManager.music = new MusicThread();
                    MusicManager.music.startMusic("soundResources/CalmOutdoors.wav");

                    MainFrame.closeChooseCharacterMenu();
                    MainFrame.openMainLayout();

                }
            }
        });


        backButton.setBounds((int)(ScreenResolution.getScreenWidth()*0.60),(int)(ScreenResolution.getScreenHeight()*0.955),(int)(ScreenResolution.getScreenWidth()*0.10),(int)(ScreenResolution.getScreenHeight()*0.045));
        backButton.setForeground(new Color(111,0,0));
        backButton.setBackground(new Color(196,153,80));
        backButton.setFocusable(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.closeChooseCharacterMenu();
                MainFrame.openMainMenu();
            }
        });

        deleteButton.setBounds((int)(ScreenResolution.getScreenWidth()*0.70),(int)(ScreenResolution.getScreenHeight()*0.955),(int)(ScreenResolution.getScreenWidth()*0.10),(int)(ScreenResolution.getScreenHeight()*0.045));
        deleteButton.setForeground(new Color(111,0,0));
        deleteButton.setBackground(new Color(196,153,80));
        deleteButton.setFocusable(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ButtonClickSound();
                if(e.getSource() == deleteButton){

                }
            }
        });

        selectButton.setBounds((int)(ScreenResolution.getScreenWidth()*0.90),(int)(ScreenResolution.getScreenHeight()*0.955),(int)(ScreenResolution.getScreenWidth()*0.10),(int)(ScreenResolution.getScreenHeight()*0.045));
        selectButton.setForeground(new Color(111,0,0));
        selectButton.setBackground(new Color(196,153,80));
        selectButton.setFocusable(false);

        if(!MainMenuPanel.isSinglePlayerSelectedOrCharacters){
            selectButton.setVisible(false);
        }

        createButton.setBounds((int)(ScreenResolution.getScreenWidth()*0.80),(int)(ScreenResolution.getScreenHeight()*0.955),(int)(ScreenResolution.getScreenWidth()*0.10),(int)(ScreenResolution.getScreenHeight()*0.045));
        createButton.setForeground(new Color(111,0,0));
        createButton.setBackground(new Color(196,153,80));
        createButton.setFocusable(false);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ButtonClickSound();
                if(e.getSource() == createButton){
                    MainFrame.closeChooseCharacterMenu();
                    MainFrame.openCreateCharactersMenu();
                }
            }
        });

        characterInfoPanel.setOpaque(false);
        characterInfoPanel.setLayout(new FlowLayout());

        characterInfoLabel.setOpaque(false);
        characterInfoLabel.setForeground(new Color(111,0,0));
        characterInfoLabel.setFont(new Font("Immortal",Font.BOLD,30));

        characterScrollPane.setOpaque(false);
        characterScrollPane.getViewport().setOpaque(false);
        characterScrollPane.setBorder(BorderFactory.createLineBorder(new Color(196,153,80)));

        characterSplitPane.setBounds(0,0, ScreenResolution.getScreenWidth(),ScreenResolution.getScreenHeight());
        characterSplitPane.setForeground(new Color(111,0,0));
        characterSplitPane.setFont(new Font("Immortal",Font.BOLD,30));
        characterSplitPane.setBorder(BorderFactory.createLineBorder(new Color(196,153,80)));
        characterSplitPane.setLeftComponent(characterScrollPane);
        characterSplitPane.setDividerLocation(300);
        characterSplitPane.setOpaque(false);
        characterInfoPanel.add(characterInfoLabel);
        characterSplitPane.setRightComponent(characterInfoPanel);

        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLayout(null);

        this.add(deleteButton);
        this.add(backButton);
        this.add(createButton);
        this.add(selectButton);
        this.add(characterSplitPane);
        this.add(background);

    }

    private characterListItem getCharacter(String character_name, ArrayList<characterListItem> heroes){
        for(characterListItem character : heroes){
            if(character.getCharacterName().equals(character_name)) return character;
        }
        return heroes.get(1);
    }


}
