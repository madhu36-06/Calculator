import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private String operator = "";
    private double num1, num2, result;

    public Calculator() {
        setTitle("Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        JButton[] btn = new JButton[buttons.length];

        for (int i = 0; i < buttons.length; i++) {
            btn[i] = new JButton(buttons[i]);
            btn[i].setFont(new Font("Arial", Font.BOLD, 20));
            btn[i].addActionListener(this);
            panel.add(btn[i]);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9' || cmd.equals(".")) {
            display.setText(display.getText() + cmd);
        } else if (cmd.equals("C")) {
            display.setText("");
            operator = "";
            num1 = num2 = result = 0;
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
