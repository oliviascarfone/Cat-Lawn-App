package ui;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//https://docs.oracle.com/javase/tutorial/uiswing/components/splitpane.html
//https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
//https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
//https://stackoverflow.com/questions/42381633/refreshing-a-jlabel
//https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
//https://stackoverflow.com/questions/15840857/java-createimageicon-not-recognized-in-code/15840895
//https://stackoverflow.com/questions/20811728/adding-music-sound-to-java-programs
//https://mkyong.com/swing/java-swing-how-to-make-a-simple-dialog/
//music from Kazumi Totaka, Wii menu music

//creates the gui
import model.*;

import persistance.GameJsonParser;
import persistance.JsonWriterInventory;
import persistance.JsonWriterYard;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;


public class Gui extends JPanel implements ActionListener, ListSelectionListener {
    private static final String YARD_FILE = "./data/yard.json";
    private static final String INVENTORY_FILE = "./data/inventory.json";
    static String[] itemString = {"Kibble", "Spring"};
    private String[] catStrings = { "Moki", "Sesame", "Zeus", "Sushi", "Evie", "Luna", "Nala",
            "Milo", "Mimi", "Roddick", "Kiwi" };
    JTextArea yardLabel;
    JLabel picture;
    private DefaultListModel listModelInventory;
    private JList inventoryList;
    private JList shopList;
    private JButton placeInYardButton;
    private JTabbedPane tabbedPane;
    private ImageIcon yardIcon = new ImageIcon("data/yard.png", "yard");
    private ImageIcon kittyIcon = new ImageIcon("data/ragdoll.png", "cat");
    private ImageIcon cartIcon = new ImageIcon("data/cart.png", "cart");
    private ImageIcon backpackIcon = new ImageIcon("data/backpack.png", "inventory");
    private ImageIcon saveIcon = new ImageIcon("data/save.png", "save");
    private ImageIcon cat;
    Yard yard;
    Inventory inventory;
    GameJsonParser parser = new GameJsonParser(this);


    //EFFECTS: Creates and initializes JFrame and Tab components
    public Gui() {
        super(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();
        loadGame(YARD_FILE, INVENTORY_FILE);
        makeShop();
        makeInventoryPanel();
        makeYard();
        makeOptions();
        addMusic();
        updateInventory();
        makeGallery();
        setBorder(BorderFactory.createEmptyBorder(20,10,20,10));


        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */

    //EFFECTS: creates GUI and displays
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Cat Lawn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Gui(), BorderLayout.CENTER);

        //Display the window.
        frame.setPreferredSize(new Dimension(500, 500));
        frame.pack();
        frame.setVisible(true);

    }

    //EFFECTS: Adds background music to game
    public void addMusic() {
        AudioPlayer audioPlayer = AudioPlayer.player;
        AudioStream audioStream;
        ContinuousAudioDataStream audioLoop = null;
        try {
            InputStream musicFile = new FileInputStream("data/music/01 Main Menu.wav");
            audioStream = new AudioStream(musicFile);
            audioPlayer.start(audioStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        audioPlayer.start(audioLoop);

    }

    //EFFECTS: Makes the components of the shop tab
    public void makeShop() {
        JComponent shop = new JPanel();
        shop.setLayout(new BorderLayout());
        JLabel label = new JLabel("Welcome to the Shop!");
        shopList = new JList<String>(itemString);
        shopList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        shopList.setLayoutOrientation(JList.VERTICAL_WRAP);
        shopList.setVisibleRowCount(-1);
        shopList.addListSelectionListener(this);
        JButton shopButton = new JButton("Make Purchase");

        tabbedPane.addTab("Shop", cartIcon, shop,
                "What would you like to buy?");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        shop.add(label, BorderLayout.NORTH);
        shop.add(shopList, BorderLayout.CENTER);
        shop.add(shopButton, BorderLayout.SOUTH);
        shopButton.addActionListener(new PurchaseItem());

    }


    //EFECTS: Makes the Inventory tab
    public void makeInventoryPanel() {
        JComponent inventoryPanel = new JPanel();
        listModelInventory = new DefaultListModel();
        makeInventoryInit();

        inventoryList = new JList(listModelInventory);
        inventoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inventoryList.setSelectedIndex(0);
        inventoryList.addListSelectionListener(this);
        inventoryList.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(inventoryList);

        tabbedPane.addTab("Inventory", backpackIcon, inventoryPanel,
                "Here is your inventoryPanel");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        inventoryPanel.add(listScrollPane);
        inventoryPanel.add(placeInYardButton = new JButton("Place in Yard"));
        placeInYardButton.addActionListener(new PlaceInYard());

    }

    //EFFECTS: Creates initial inventory JList
    public void makeInventoryInit() {
        for (InventoryEntry i : inventory.inventoryList) {
            String itemName = i.getItem().getName();
            listModelInventory.addElement(itemName);
        }
    }

    //EFFECTS: updates the inventory JList to match inventory changes
    public void updateInventory() {
        listModelInventory.removeAllElements();
        for (InventoryEntry i : inventory.inventoryList) {
            String itemName = i.getItem().getName();
            listModelInventory.addElement(itemName);
        }
    }

    //EFFECTS: Makes yard tab
    public void makeYard() {
        JPanel yardPanel = new JPanel();
        Dimension minimumSize = new Dimension(400, 300);
        yardLabel = new JTextArea("Please select an option");
        yardLabel.setEditable(false);
        yardLabel.setLineWrap(true);

        JComponent buttonsMenu = new JPanel();
        JButton buttonCat = new JButton("See cats in yard");
        JButton buttonFood = new JButton("See food in yard");
        JButton buttonToys = new JButton("See toys in yard");
        tabbedPane.addTab("Yard", yardIcon, yardPanel,
                "Yard");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        buttonCat.addActionListener(this);
        buttonFood.addActionListener(this);
        buttonToys.addActionListener(this);
        buttonsMenu.add(buttonCat);
        buttonsMenu.add(buttonFood);
        buttonsMenu.add(buttonToys);
        yardPanel.add(buttonsMenu);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonsMenu, yardLabel);

        yardPanel.add(splitPane);

        buttonsMenu.setMinimumSize(minimumSize);

    }

    //EFFECTS: creates cat gallery tab
    public void makeGallery() {
        ActionListener galleryListener;
        JPanel galleryTab = new JPanel();
        JPanel gallery = new JPanel(new BorderLayout());
        picture = new JLabel();
        JComboBox catList = new JComboBox(catStrings);

        gallery.add(catList, BorderLayout.PAGE_START);
        gallery.add(picture, BorderLayout.PAGE_END);
        galleryTab.add(gallery);
        catList.setSelectedIndex(0);
        galleryListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox selection = (JComboBox)e.getSource();
                String catSelection = (String)selection.getSelectedItem();
                catPicture(catSelection);
            }
        };
        catList.addActionListener(galleryListener);
        catList.addActionListener(this);
        tabbedPane.addTab("Gallery", kittyIcon, galleryTab,
                "Look at cute cats");
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_4);
    }

    public void catPicture(String selection) {
        String selectedCat = yard.getCatPic(selection);
        cat = new ImageIcon(selectedCat);
        picture.setHorizontalAlignment(JLabel.CENTER);
        picture.setIcon(cat);


    }






    //EFFECTS: Makes Options tab
    public void makeOptions() {
        JComponent options = new JPanel();
        options.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Options", saveIcon, options,
                "Create new game, save game, or quit");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        JButton buttonSave = new JButton("Save Game");
        buttonSave.addActionListener(this);
        JButton buttonNew = new JButton("New Game");
        buttonNew.addActionListener(this);
        JButton buttonQuit = new JButton("Quit Game");
        buttonQuit.addActionListener(this);
        options.add(buttonNew);
        options.add(buttonSave);
        options.add(buttonQuit);

    }

    //EFFECTS: runs the Gui - called from Main
    public static void start() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

    //EFFECTS: Action Listener for all buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action == "See cats in yard") {
            yard.addCatToYard();
            String catsInYard = yard.catsInYard();
            updateLabel(catsInYard);
        } else if (action == "See food in yard") {
            String foodInYard = yard.itemsInYard(yard.food);
            updateLabel(foodInYard);
        } else if (action == "See toys in yard") {
            String toysInYard = yard.itemsInYard(yard.toys);
            updateLabel(toysInYard);
        } else if (action == "Quit Game") {
            System.exit(0);
        } else if (action == "New Game") {
            inventory.clearInventory(inventory);
            updateInventory();
            yard.clearYard(yard);
        } else if (action == "Save Game") {
            JsonWriterYard.saveGame(yard, YARD_FILE);
            JsonWriterInventory.saveGame(inventory, INVENTORY_FILE);

        }

    }

    //EFFECTS: Updates the YardTab test status when called
    public void updateLabel(String status) {
        yardLabel.setText(status);

    }

    //EFFECTS: Stops the place in yard button if there are no more items in inventory
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (inventoryList.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                placeInYardButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                placeInYardButton.setEnabled(true);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: makes yard from loaded file data
    public void loadedGameYard(ArrayList<Cat> listOfCats, ArrayList<Item> listOfFood, ArrayList<Item> listOfToy) {
        yard = new Yard(listOfCats, listOfFood, listOfToy);
    }

    //MODIFIES: this
    //EFFECTS: makes inventory from loaded file data
    public void loadedGameInventory(Inventory inventoryFromFile) {
        inventory = inventoryFromFile;

    }

    //MODIFIES: this
    //EFFECTS: makes a new empty inventory
    public void emptyInventory() {
        inventory = new Inventory();

    }

    //Class to give functionality to 'Place in Yard' button in Inventory tab
    class PlaceInYard implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = inventoryList.getSelectedIndex();
            //takes the selected item and places it in the yard
            placeItem((String) listModelInventory.get(index));
            listModelInventory.remove(index);

            int size = listModelInventory.getSize();

            if (size == 0) {
                placeInYardButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModelInventory.getSize()) {
                    //removed item in last position
                    index--;
                }

                inventoryList.setSelectedIndex(index);
                inventoryList.ensureIndexIsVisible(index);
            }
        }
    }

    //EFFECTS: Takes selected Inventory item and places it into the yard, also removes selection
    // from the inventory
    public void placeItem(String item) {
        yard.addItemToYard(item);
        inventory.removeItemFromInventory(item, inventory);
    }


    //class to help with the 'Purchase Item' functionality
    class PurchaseItem implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //int index = shopList.getSelectedIndex();
            buyItemFromShopTab((String) shopList.getSelectedValue());

            //int size = listModelInventory.getSize();
        }
    }


    //EFFECTS: buys selected item form the store and moves it into the inventory
    public void buyItemFromShopTab(String selection) {
        inventory.buyItem(selection);
        updateInventory();
        confirmPurchase(selection);

    }

    //EFFECTS: creates the pop-up with an image of the purchased item
    public void confirmPurchase(String itemType) {
        JOptionPane popup = new JOptionPane();
        ImageIcon kibbleIcon = new ImageIcon("data/kibblepic.png");
        ImageIcon springIcon = new ImageIcon("data/Spring.png");
        if (itemType.equals("Kibble")) {
            popup.showMessageDialog(null, "Bought Kibble!", "Purchase Confirmation",
                    JOptionPane.INFORMATION_MESSAGE, kibbleIcon);
        } else if (itemType.equals("Spring")) {
            popup.showMessageDialog(null, "Bought a Spring!", "Purchase Confirmation",
                    JOptionPane.INFORMATION_MESSAGE, springIcon);
        }
    }

    //MODIFIES: this, YardJsonParser
    //EFFECTS: loads the game data
    public boolean loadGame(String yardFile, String inventoryFile) {
        return parser.loadGame(yardFile, inventoryFile);
    }

    //MODIFIES: this
    //EFFECTS: constructs an empty yard
    public void emptyYard() {
        yard = new Yard();
    }

}













