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

public class SolvedByUser {

	public static final Random RANDOM = new Random();
	private int number;
	private int guesses_made;
	private boolean if_guessed;
	private JTextPane textPane;
	private JTextField textField;
	
	
	private void generateNumber() {
		do {
			number = RANDOM.nextInt(9000) + 1000;
		} while (hasDuplicateDigits (number));
	}

	private boolean hasDuplicateDigits(int number) {
		boolean[] digits = new boolean[10];
		while (number > 0) {
			int last_digit = number % 10;
			if (digits[last_digit]) return true;
			digits[last_digit] = true;
			number = number / 10;
		}
		return false;
	}

	private int feedbackBnC(int entered_num) {
		int bulls = 0;
		int cows = 0;

		String entered_numStr = String.valueOf(entered_num);
		String numberStr = String.valueOf(number);

		for (int i = 0; i < numberStr.length(); i++) {
			char c = entered_numStr.charAt(i);

			if (c == numberStr.charAt(i)) {
				bulls++;
			} else if (numberStr.contains(String.valueOf(c))) {
				cows++;
			}
		}
		return bulls * 10 + cows;
	}

	private void solve() {
		JFrame frame = new JFrame("Guess the number!");
		Container contentPane = frame.getContentPane();

		JPanel bottomPanel = new JPanel();

		JLabel inputLabel = new JLabel("Enter your guess: ");
		bottomPanel.add(inputLabel, BorderLayout.LINE_START);

		textField = new JTextField(15);
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				okButton();
			}
		});
		bottomPanel.add(textField, BorderLayout.LINE_START);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				okButton();
			}
		});

		bottomPanel.add(okButton, BorderLayout.LINE_START);

		contentPane.add(bottomPanel, BorderLayout.PAGE_END);

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

		init();
	}

	private void updateTextField(String txt) {
		textPane.setText(textPane.getText() + "\n" + txt);
	}

	private void okButton() {
		String userInput = textField.getText();
		int entered_num = -1;

		try {
			entered_num = Integer.parseInt(userInput);
		} catch(Exception e) {
			textField.setText("");
			JOptionPane.showMessageDialog(null, "You must enter a 4-digit number with no duplicates.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (hasDuplicateDigits(entered_num) || entered_num > 9876 || entered_num < 1234) {
			textField.setText("");
			JOptionPane.showMessageDialog(null, "You must enter a 4-digit number with no duplicates.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	
		guesses_made++;

		int answer = feedbackBnC(entered_num);
		if (answer / 10 == 4) {
			if_guessed = true;
		} else {
			updateTextField(entered_num + "\t\t\t\t" + answer / 10 + " Bull(s), " + answer % 10 + " Cow(s)");
		}

		if (if_guessed) {
			updateTextField("\n" + entered_num + "\n\nYou won after " + guesses_made + " guesses!");
		}

		textField.setText("");
	}

	private void init() {
		generateNumber();
		guesses_made = 0;
		if_guessed = false;
		textPane.setText("Guess the number!");
		textField.setText("");
	}

	public static void run() {
		SolvedByUser feedbackBnC = new SolvedByUser();
		feedbackBnC.solve();
	}

}