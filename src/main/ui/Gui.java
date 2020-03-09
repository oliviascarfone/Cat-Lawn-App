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


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;



public class Gui extends JPanel {
    static String[] testItems = {"Kibble", "Spring"};
    private JTabbedPane tabbedPane;
    private ImageIcon shopIcon = createImageIcon("images/ragdoll.png");


    public Gui() {
        super(new GridLayout(1, 1));

        tabbedPane = new JTabbedPane();
        makeShop();

//        JComponent shop = makeTextPanel("Shop");
//        JList<String> myShop = new JList<String>(testItems);
//        myShop.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        myShop.setLayoutOrientation(JList.VERTICAL_WRAP);
//        myShop.setVisibleRowCount(-1);
//        tabbedPane.addTab("Shop", icon, shop,
//                "What would you like to buy?");
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//        shop.add(myShop);
//        //JButton shopButton = new JButton();

        JComponent inventory = makeTextPanel("Inventory");
        tabbedPane.addTab("Inventory", shopIcon, inventory,
                "Here is your inventory");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JComponent yard = makeTextPanel("Yard");
        JButton buttonCat = new JButton("See cats in yard");
        JButton buttonItems = new JButton("See items in yard");
        tabbedPane.addTab("Yard", shopIcon, yard,
                "Yard");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        yard.add(buttonCat);
        yard.add(buttonItems);

        JComponent options = makeTextPanel(
                "Options: save, load new");
        options.setPreferredSize(new Dimension(410, 50));
        tabbedPane.addTab("Options", shopIcon, options,
                "Does nothing at all");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Gui.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Cat Lawn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Gui(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void makeShop() {
        JComponent shop = makeTextPanel("Shop");
        JList<String> myShop = new JList<String>(testItems);
        myShop.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        myShop.setLayoutOrientation(JList.VERTICAL_WRAP);
        myShop.setVisibleRowCount(-1);
        tabbedPane.addTab("Shop", shopIcon, shop,
                "What would you like to buy?");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        shop.add(myShop);



    }

    public static void main(String[] args) {
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
}





