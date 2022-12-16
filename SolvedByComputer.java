import java.util.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class SolvedByComputer {
	static ArrayList<String> possible_guesses = new ArrayList<String>();
	private static JFrame frame;
	private static JTextPane textPane;
	private static JTextField text_field_bulls;
	private static JTextField text_field_cows;
	static int guesses_made;
	static String guess;

	
	private void play() {
		frame = new JFrame("Let me guess your number!");
		Container contentPane = frame.getContentPane();

		JPanel buttonsPanel = new JPanel();

		JLabel inputLabel_b = new JLabel("No. of Bulls: ");
		buttonsPanel.add(inputLabel_b, BorderLayout.LINE_START);

		text_field_bulls = new JTextField(5);

		text_field_bulls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okButton();
			}
		});
		buttonsPanel.add(text_field_bulls, BorderLayout.LINE_START);

		JLabel inputLabel_c = new JLabel("No. of Cows: ");
		buttonsPanel.add(inputLabel_c, BorderLayout.LINE_START);

		text_field_cows = new JTextField(5);

		text_field_cows.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okButton();
			}
		});
		buttonsPanel.add(text_field_cows, BorderLayout.LINE_START);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				okButton();
			}
		});

		buttonsPanel.add(okButton, BorderLayout.LINE_START);

		JButton finalbutton = new JButton("That's my number!");
		finalbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					thatsMyNum();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		buttonsPanel.add(finalbutton, BorderLayout.LINE_START);
		
		contentPane.add(buttonsPanel, BorderLayout.PAGE_END);

		textPane = new JTextPane();
		textPane.setEditable(false);
		JScrollPane jscrollpane = new JScrollPane(textPane);

		SimpleAttributeSet bSet = new SimpleAttributeSet();
		StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(bSet, 20);
		StyledDocument doc = textPane.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), bSet, false);

		contentPane.add(jscrollpane, BorderLayout.CENTER);
		frame.setMinimumSize(new Dimension(800, 600));

		Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int iCoordX = (objDimension.width - frame.getWidth()) / 2;
		int iCoordY = (objDimension.height - frame.getHeight()) / 2;
		frame.setLocation(iCoordX, iCoordY);

		frame.pack();
		frame.setVisible(true);

		guess = possible_guesses.get((int)(Math.random() * possible_guesses.size()));
		updateText("Guess no " + guesses_made + ": " + guess +" ");

	}
	
	public static void thatsMyNum() throws InterruptedException {
		updateText("Yayy I guessed right!");
		frame.dispose();
	}
	
    public static void addGuesses() {
    	String my_num;
        for (int a = 1; a <= 9; a++) { 
            for (int b = 1; b <= 9; b++) {
                if (b == a) continue;
                for (int c = 1; c <= 9; c++) {
                    if (c == b || c == a) continue;
                    for (int d = 1; d <= 9; d++) {
                        if (d == a || d == b || d == c) continue;
                        my_num = ""+a+b+c+d;
                        possible_guesses.add(my_num);
                    }
                }
            }
        }
    }
    
    static boolean check(String num, String guess, int cows, int bulls) {
        for (int i = 0; i < guess.length(); i++) {
            int index = num.indexOf(guess.charAt(i));
            if (index == i) bulls--;
            else if (index >= 0) cows--;
        }
        return (cows == 0) && (bulls == 0);
    }
    
    public static void gameByComp (int bulls, int cows) {
            for (int j = 0; j < possible_guesses.size(); j++)
                if (!check(possible_guesses.get(j),guess,cows,bulls)) {
                    possible_guesses.remove(j);
                    j--;
                }
    }
    
	  public static void okButton() {
		  String bulls_str = text_field_bulls.getText();
		  String cows_str = text_field_cows.getText();
		  int bulls = -1;
		  int cows = -1;
		  bulls =  Integer.parseInt(bulls_str);
		  cows =  Integer.parseInt(cows_str);
		  
		  if (bulls + cows > 4) {
		      text_field_bulls.setText("");
		      text_field_cows.setText("");
		      JOptionPane.showMessageDialog(null, "Please enter the number of bulls and cows correctly!", "Error", JOptionPane.ERROR_MESSAGE);
		      return;
		    }
		  else {
			  text_field_bulls.setText("");
		      text_field_cows.setText("");
		  }
		  gameByComp(bulls, cows);
		  if (possible_guesses.size() == 1) {
        	  updateText("Your number: " + possible_guesses.get(0));
          }
		  else {
			  guess = possible_guesses.get((int)(Math.random() * possible_guesses.size()));
			  updateText("Guess no " + guesses_made + ": " + guess +" ");
			  guesses_made++;
		  }
	  }
	  
	  private static void updateText(String txt) {
		  if (textPane.getText() == "") textPane.setText(txt);
		  else textPane.setText(textPane.getText() + "\n" + txt);
	  }

	  public static void run() {
	        SolvedByComputer guessNum = new SolvedByComputer();
	        addGuesses();
	        guesses_made = 1;
	        guessNum.play();
	      }
}
