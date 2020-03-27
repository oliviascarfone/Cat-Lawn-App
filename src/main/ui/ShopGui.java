//package ui;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import java.awt.*;
//import java.awt.event.KeyEvent;
//
//public class ShopGui extends JPanel  {
//    private JList shopList;
//    static String[] testItems = {"Kibble", "Spring"};
//
//    public ShopGui() {
//
//
//    }
//
//
////    //EFFECTS: Makes the components of the shop tab
////    public JComponent makeShop() {
////        JComponent shop = new JPanel();
////        shop.setLayout(new BorderLayout());
////        JLabel label = new JLabel("Welcome to the Shop!");
////        shopList = new JList<>(testItems);
////        shopList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
////        shopList.setLayoutOrientation(JList.VERTICAL_WRAP);
////        shopList.setVisibleRowCount(-1);
////        shopList.addListSelectionListener(this);
////        JButton shopButton = new JButton("Make Purchase");
//////        tabbedPane.addTab("Shop", cartIcon, shop,
//////                "What would you like to buy?");
//////        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
////        shop.add(label, BorderLayout.NORTH);
////        shop.add(shopList, BorderLayout.CENTER);
////        shop.add(shopButton, BorderLayout.SOUTH);
////        shopButton.addActionListener(new Gui.PurchaseItem());
////        return shop;
////
////    }
////
////    @Override
////    public void valueChanged(ListSelectionEvent e) {
////
////    }
////}
//}
