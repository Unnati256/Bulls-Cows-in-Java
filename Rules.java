import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rules extends JFrame {
	public static void rules()  {
		
		final JFrame frame = new JFrame();
		JPanel textPanel = new JPanel();
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("bnc.png"));	
		textPanel.add(label);
        frame.getContentPane().add(textPanel,BorderLayout.CENTER);
        
		frame.setSize(700, 430);
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - frame.getWidth()) / 2;
        int iCoordY = (objDimension.height - frame.getHeight()) / 2;
        frame.setLocation(iCoordX, iCoordY);
        frame.setVisible(true);
	}
}
