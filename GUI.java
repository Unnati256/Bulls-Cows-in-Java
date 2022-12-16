import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {
    public static void main() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 480);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - frame.getWidth()) / 2;
        int iCoordY = (objDimension.height - frame.getHeight()) / 2;
        frame.setLocation(iCoordX, iCoordY);

        JButton btn1 = new JButton("Solve it yourself!");
        btn1.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                    	SolvedByUser.run();
                    }
                });

        JButton btn2 = new JButton("Play against computer");
        btn2.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                    	SolvedByComputer.run();
                    }
                });
        JButton btn3 = new JButton("Instructions");
        btn3.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                    	Rules.rules();
                    }
                });
        
        JButton btn4 = new JButton("Exit");
        btn4.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                    	 frame.dispose();
                    	 System.exit(0);
                    }
                });

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("introBnC.png"));
        JPanel buttonPanel = new JPanel( );
        buttonPanel.add(label);
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        buttonPanel.add(btn4);
        
        frame.getContentPane( ).add(buttonPanel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}