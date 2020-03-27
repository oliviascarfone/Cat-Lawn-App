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
//https://docs.oracle.com/javase/tutorial/uiswing/components/button.html
//https://stackoverflow.com/questions/42381633/refreshing-a-jlabel
//https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
//https://stackoverflow.com/questions/15840857/java-createimageicon-not-recognized-in-code/15840895
//https://stackoverflow.com/questions/20811728/adding-music-sound-to-java-programs
//https://mkyong.com/swing/java-swing-how-to-make-a-simple-dialog/
//music from Kazumi Totaka, Wii menu music

//creates the gui
import model.*;
import persistance.JsonWriter;
import persistance.YardJsonParser;
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
    static String[] testItems = {"Kibble", "Spring"};
    JTextArea yardLabel;
    private DefaultListModel listModelInventory;
    private JList inventoryList;
    private JList shopList;
    private JLabel yardImage;
    private JButton placeInYardButton;
    private JTabbedPane tabbedPane;
    private ImageIcon kittyIcon = new ImageIcon("data/ragdoll.png", "cat");
    private ImageIcon cartIcon = new ImageIcon("data/cart.png", "cart");
    private ImageIcon backpackIcon = new ImageIcon("data/backpack.png", "inventory");
    private ImageIcon saveIcon = new ImageIcon("data/save.png", "save");
    Yard yard;
    Inventory inventory;
    YardJsonParser parser = new YardJsonParser(this);


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

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }


//    protected JComponent makeTextPanel(String text) {
//        JPanel panel = new JPanel(false);
//        JLabel filler = new JLabel(text);
//        filler.setHorizontalAlignment(JLabel.CENTER);
//        panel.setLayout(new GridLayout(1, 1));
//        panel.add(filler);
//        return panel;
//    }

//    /**
//     * Returns an ImageIcon, or null if the path was invalid.
//     */
//    protected static ImageIcon createImageIcon(String path, String description) {
//        java.net.URL imgURL = Gui.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL, description);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }

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
        shopList = new JList<String>(testItems);
        shopList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        shopList.setLayoutOrientation(JList.VERTICAL_WRAP);
        shopList.setVisibleRowCount(-1);
        shopList.addListSelectionListener(this);
        //JScrollPane listScroller = new JScrollPane(myShop);
        JButton shopButton = new JButton("Make Purchase");
        //JList.setPreferredSize(new Dimension(250, 80));
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
        //updateInventory();

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
        //listModelInventory = new DefaultListModel();
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
        //yardLabel = new JLabel("Please select an option");
        //yardLabel.setMinimumSize(minimumSize);
        JComponent buttonsMenu = new JPanel();
        JButton buttonCat = new JButton("See cats in yard");
        JButton buttonFood = new JButton("See food in yard");
        JButton buttonToys = new JButton("See toys in yard");
        tabbedPane.addTab("Yard", kittyIcon, yardPanel,
                "Yard");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        buttonCat.addActionListener(this);
        buttonFood.addActionListener(this);
        buttonToys.addActionListener(this);
        buttonsMenu.add(buttonCat);
        buttonsMenu.add(buttonFood);
        buttonsMenu.add(buttonToys);
        yardPanel.add(buttonsMenu);
        //makeYardImage();
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonsMenu, yardLabel);
        //JSplitPane totalPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPane, yardImage);
        yardPanel.add(splitPane);
        //yard.add(totalPane);
        buttonsMenu.setMinimumSize(minimumSize);

    }

//    public void makeYardImage() {
//        try {
//            BufferedImage picture = ImageIO.read(new File("data/cats/moki.png"));
//            yardImage = new JLabel(new ImageIcon(picture));
//        } catch (IOException e) {
//           //
//        }





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
            inventory.inventoryList.clear();
            updateInventory();
            yard.cats.clear();
            yard.food.clear();
            yard.toys.clear();
        } else if (action == "Save Game") {
            JsonWriter.saveGame(yard, inventory, YARD_FILE, INVENTORY_FILE);
        }

    }




    //EFFECTS: Updates the Yard status when called
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
        //updateInventory();
    }

    //MODIFIES: this
    //EFFECTS: makes a new empty inventory
    public void emptyInventory() {
        inventory = new Inventory();
        //updateInventory();
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
        if (item.equals("Kibble")) {
            yard.addItemToYard(new Food("Kibble", 0));
            removeItemFromInventory("Kibble");
            //updateInventory();
        } else if (item.equals("Spring")) {
            yard.addItemToYard(new Toy("Spring", 0));
            removeItemFromInventory("Spring");
            //updateInventory();
        }
    }

    //EFFECTS: removes item from inventory has the same name as the given string
    public void removeItemFromInventory(String selection) {
        for (InventoryEntry e : inventory.inventoryList) {
            String itemName = e.getItem().getName();
            if (itemName == selection) {
                inventory.inventoryList.remove(e);
                break;

            }
        }
    }

    //class to help with the 'Purchase Item' functionality
    class PurchaseItem implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //int index = shopList.getSelectedIndex();
            buyItem((String) shopList.getSelectedValue());

            //int size = listModelInventory.getSize();
        }
    }




    //EFFECTS: buys selected item form the store and moves it into the inventory
    public void buyItem(String selection) {
        if (selection == "Kibble") {
            inventory.inventoryList.add(new InventoryEntry(new Food("Kibble", 0), 1));
        } else if (selection == "Spring") {
            inventory.inventoryList.add(new InventoryEntry(new Toy("Spring", 0), 1));
        }
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













