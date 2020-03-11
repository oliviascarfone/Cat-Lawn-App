package ui;

import model.Yard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class YardGui extends JPanel implements ActionListener {
    JLabel yardLabel;
    Yard yard;

    public YardGui(Yard yardObj) {
        makeYard();
        yard = yardObj;
    }

    public void makeYard() {
        JPanel yard = new JPanel();
        yardLabel = new JLabel("Please select an option");
        JComponent buttonsMenu = new JPanel();
        JButton buttonCat = new JButton("See cats in yard");
        JButton buttonFood = new JButton("See food in yard");
        JButton buttonToys = new JButton("See toys in yard");
        //tabbedPane.addTab("Yard", shopIcon, yard,
        //        "Yard");
        //tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        buttonCat.addActionListener(this);
        buttonFood.addActionListener(this);
        buttonToys.addActionListener(this);
        buttonsMenu.add(buttonCat);
        buttonsMenu.add(buttonFood);
        buttonsMenu.add(buttonToys);
        yard.add(buttonsMenu);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonsMenu, yardLabel);
        yard.add(splitPane);
        Dimension minimumSize = new Dimension(400, 300);
        buttonsMenu.setMinimumSize(minimumSize);
        yardLabel.setMinimumSize(minimumSize);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action == "See cats in yard") {
            updateLabel(yard.catsInYard());
        } else if (action == "See food in yard") {
            updateLabel(yard.itemsInYard(yard.food));
        } else if (action == "See toys in yard") {
            updateLabel(yard.itemsInYard(yard.toys));
        }

    }

    public void updateLabel(String status) {
        yardLabel.setText(status);

    }


}
