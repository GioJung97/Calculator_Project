package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private JTextField expressionTextField = new JTextField();
    private JPanel buttonPanel = new JPanel();
    private String inputExpression = "";

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
        "7", "8", "9", "+",
        "4", "5", "6", "-", 
        "1", "2", "3", "*", 
        "0", "(", ")", "/",
        "C", "CE", "=", "^"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    //stores all the references to the buttons
    private JButton[] buttons = new JButton[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.expressionTextField.setPreferredSize(new Dimension(600, 50));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 24));
        this.expressionTextField.setHorizontalAlignment(JTextField.RIGHT);
        this.expressionTextField.setText("0");

        add(expressionTextField, BorderLayout.NORTH);
        expressionTextField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        JButton tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new JButton(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 24));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param actionEventObject Event object generated when a
     *                    button is pressed.
     */
    //inspect and use action object

    public void actionPerformed(ActionEvent actionEventObject) {
        String buttonClicked = actionEventObject.getActionCommand();

        if(buttonClicked == "C") {
            inputExpression = "";
            this.expressionTextField.setText("");
        }else if(buttonClicked == "CE") {
            eraseLastDigit(inputExpression);
            this.expressionTextField.setText(eraseLastDigit(inputExpression));
        }else if (buttonClicked.equals("=")){
                Evaluator currExpression = new Evaluator ();
                try {
                    int result = currExpression.evaluateExpression(inputExpression);
                    inputExpression = Integer.toString(result);
                } catch (InvalidTokenException e) {
                    expressionTextField.setText("Error");
                    throw new RuntimeException(e);
                }

                expressionTextField.setText(inputExpression);

//                try {
//                    expressionTextField.setText(String.valueOf(currExpression.evaluateExpression(inputExpression)));
//                } catch (InvalidTokenException e) {
//                    expressionTextField.setText("Error");
//                    inputExpression = "";
//                    throw new RuntimeException(e);
//                }
        }else{
            inputExpression += buttonClicked;
            expressionTextField.setText(inputExpression);
        }


    }
    public static String eraseLastDigit(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, str.length() - 1);
    }
//    public String operatorButton (JPanel buttonClicked){
//        switch (buttonClicked){
//            case "+": this.expressionTextField.setText("");
//                return "+";
//            case "-": this.expressionTextField.setText("");
//                return "-";
//            case "*": this.expressionTextField.setText("");
//                return "*";
//            case "/": this.expressionTextField.setText("");
//                return "/";
//            case "^": this.expressionTextField.setText("");
//                return "^";
//        }
//
//        return this.expressionTextField.setText(buttonClicked);
//    }
}
