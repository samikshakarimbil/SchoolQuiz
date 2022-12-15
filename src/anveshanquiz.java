import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

public class anveshanquiz implements  ActionListener{
    // initialisation of global values and items that are part of the GUI.
    static int windowWidth = 1375;
    static int messageWindowWidth = 470;

    static int buttonWidth = 600;
    static int buttonSpace = 50;
//    static int buttonHeight = 350;
    static int windowHeight = 875;
    static int panelHeight = 150;
    static int fontSize = 30;
    static String fontStyle = "TimesRoman";

    static Color buttonColor = null;

    static int maxQuestions = 150;
    static int totalQuestions = 5;

    int scoreValueAmount = 0;
    int currentQuestion = 0;
    static int startAgain = 0;
    static float percent = 0;

    static String quest[] = new String[maxQuestions];
    static String options[][]=new String[maxQuestions][4];
    static String anss[]=new String[maxQuestions];
    static String topic[] = new String[maxQuestions];

    static ImageIcon icon;

    JPanel scorePanel, questionPanel, welcomePanel, buttonPanel,resetPanel, messagePanel, percentPanel, nextPanel;
    JLabel questionLabel, scoreLabel, topicLabel, scoreValue, logo, percentLabel, messageLabel, names;
    JButton buttonA, buttonB, buttonC, buttonD, resetButton,startButton, nextButton;
    private JFrame welcome;
    private static JFrame message;
    private static JFrame quizWindow;

    public anveshanquiz() {
        welcome = new JFrame("WELCOME");
        quizWindow = new JFrame("Anveshan Quiz!!");
        message = new JFrame("Result");
    }

    public JPanel createWelcomeWindow(){
        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        // Creation of a Panel to contain the welcome screen
        welcomePanel = new JPanel();
        welcomePanel.setLayout(null);
        welcomePanel.setLocation(10, 0);
        welcomePanel.setSize(windowWidth, windowHeight);
        totalGUI.add(welcomePanel);


        logo = new JLabel();
        logo.setLocation(10,0);
        logo.setSize(windowWidth, panelHeight);
        logo.setHorizontalAlignment(0);
        logo.setIcon(icon);
        logo.setSize(windowWidth,300);
        welcomePanel.add(logo);

        startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("TimesRoman", Font.ITALIC, fontSize*2));
        startButton.setLocation(7, 350);
        startButton.setSize(windowWidth-50, windowHeight-500);
        startButton.addActionListener(this);
        welcomePanel.add(startButton);

        names = new JLabel("Created by Samiksha GK - X-C, Anaya Pillai - IX-A, Aadya Das - IX-A");
        names.setFont(new Font(fontStyle, Font.ITALIC, (fontSize-10)));
        names.setLocation(0, 700);
        names.setSize(windowWidth, panelHeight);
        names.setHorizontalAlignment(0);
        names.setForeground(Color.black);
        welcomePanel.add(names);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        // Creation of a Panel to contain the score labels
        scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setLocation(0, 0);
        scorePanel.setSize(windowWidth, panelHeight);
        totalGUI.add(scorePanel);

        scoreLabel = new JLabel("Your score:");
        scoreLabel.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        scoreLabel.setLocation(windowWidth-300, 0);
        scoreLabel.setSize(200, panelHeight);
        scoreLabel.setHorizontalAlignment(0);
        scoreLabel.setForeground(Color.red);
        scorePanel.add(scoreLabel);

        scoreValue = new JLabel(""+scoreValueAmount);
        scoreValue.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        scoreValue.setLocation(windowWidth-100, 0);
        scoreValue.setSize(100, panelHeight);
        scoreValue.setHorizontalAlignment(0);
        scoreValue.setForeground(Color.blue);
        scorePanel.add(scoreValue);

        topicLabel = new JLabel(topic[0]);
        topicLabel.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        topicLabel.setLocation(0, 0);
        topicLabel.setSize(700, panelHeight);
        topicLabel.setHorizontalAlignment(0);
        topicLabel.setForeground(Color.blue);
        scorePanel.add(topicLabel);

        //Questions
        // Creation of a Panel to contain the questions
        questionPanel = new JPanel();
        questionPanel.setLayout(null);
        questionPanel.setLocation(10, panelHeight);
        questionPanel.setSize(windowWidth, panelHeight);
        totalGUI.add(questionPanel);

        questionLabel = new JLabel(quest[0]);
        questionLabel.setFont(new Font(fontStyle, Font.BOLD, fontSize-1));
        questionLabel.setLocation(0, 0);
        questionLabel.setSize(windowWidth, panelHeight);
        questionLabel.setHorizontalAlignment(0);
        questionLabel.setForeground(Color.black);
        questionPanel.add(questionLabel);


        // Creation of a Panel to contain all the options.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(10, 2*panelHeight);
        buttonPanel.setSize(windowWidth, 2*panelHeight+buttonSpace);
        totalGUI.add(buttonPanel);

        // We create a button and manipulate it using the syntax we have
        // used before. Now each button has an ActionListener which posts
        // it's action out when the button is pressed.

        buttonA = new JButton(options[0][0]);
        buttonA.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        buttonA.setLocation(buttonSpace, 0);
        buttonA.setSize(buttonWidth, panelHeight);
        buttonA.setBackground(buttonColor);
        buttonA.addActionListener(this);
        buttonPanel.add(buttonA);

        buttonB = new JButton(options[0][1]);
        buttonB.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        buttonB.setLocation(buttonWidth+2*buttonSpace, 0);
        buttonB.setSize(buttonWidth, panelHeight);
        buttonB.setBackground(buttonColor);
        buttonB.addActionListener(this);
        buttonPanel.add(buttonB);

        buttonC = new JButton(options[0][2]);
        buttonC.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        buttonC.setLocation(buttonSpace, panelHeight+buttonSpace);
        buttonC.setSize(buttonWidth, panelHeight);
        buttonC.setBackground(buttonColor);
        buttonC.addActionListener(this);
        buttonPanel.add(buttonC);

        buttonD = new JButton(options[0][3]);
        buttonD.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        buttonD.setLocation(buttonWidth+2*buttonSpace, panelHeight+buttonSpace);
        buttonD.setSize(buttonWidth, panelHeight);
        buttonD.setBackground(buttonColor);
        buttonD.addActionListener(this);
        buttonPanel.add(buttonD);

        // Creation of a Panel to contain the reset button.
        resetPanel = new JPanel();
        resetPanel.setLayout(null);
        resetPanel.setLocation(10, 4*panelHeight+2*buttonSpace);
        resetPanel.setSize(windowWidth, panelHeight);
        totalGUI.add(resetPanel);

        resetButton = new JButton("Reset");
        resetButton.setFont(new Font(fontStyle, Font.BOLD, fontSize));
        resetButton.setLocation(buttonSpace, 0);
        resetButton.setSize((buttonSpace+2*buttonWidth), panelHeight-buttonSpace-10);
        resetButton.addActionListener(this);
        resetPanel.add(resetButton);

        totalGUI.setOpaque(true);
        return totalGUI;
    }

    public JPanel createMessageWindow(){
        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        // Creation of a Panel to contain the meessage screen
        messagePanel = new JPanel();
        messagePanel.setLayout(null);
        messagePanel.setLocation(10, 0);
        messagePanel.setSize(messageWindowWidth, 70);
        totalGUI.add(messagePanel);

        messageLabel = new JLabel(" ");
        messageLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
        messageLabel.setLocation(5, 0);
        messageLabel.setSize(messageWindowWidth, 70);
        messagePanel.add(messageLabel);

        percentPanel = new JPanel();
        percentPanel.setLayout(null);
        percentPanel.setLocation(10, 75);
        percentPanel.setSize(messageWindowWidth, 70);
        totalGUI.add(percentPanel);

        percentLabel = new JLabel(" ");
        percentLabel.setFont(new Font("TimesRoman", Font.BOLD, 20));
        percentLabel.setLocation(5, 0);
        percentLabel.setSize(messageWindowWidth, 40);
        percentPanel.add(percentLabel);

        nextPanel = new JPanel();
        nextPanel.setLayout(null);
        nextPanel.setLocation(0, 150);
        nextPanel.setSize(messageWindowWidth,50 );
        totalGUI.add(nextPanel);

        nextButton = new JButton("Next");
        nextButton.setFont(new Font(fontStyle, Font.BOLD, 15));
        nextButton.setLocation(165, 0);
        nextButton.setSize(120, 30);
        nextButton.addActionListener(this);
        nextPanel.add(nextButton);

        totalGUI.setOpaque(true);
        return totalGUI;
    }
    // creation of functions
    //function to update the questions and options and score after each question
    public void updateQuestionOptionsScore(){
        currentQuestion = currentQuestion + 1;
        if (currentQuestion>totalQuestions-1){
            scoreValue.setText(""+scoreValueAmount);
            currentQuestion = 0;
        }

        questionLabel.setText(quest[currentQuestion]);
        topicLabel.setText(topic[currentQuestion]);

        buttonA.setText(options[currentQuestion][0]);
        buttonA.setBackground(buttonColor);
        buttonA.setEnabled(true);

        buttonB.setText(options[currentQuestion][1]);
        buttonB.setBackground(buttonColor);
        buttonB.setEnabled(true);

        buttonC.setText(options[currentQuestion][2]);
        buttonC.setBackground(buttonColor);
        buttonC.setEnabled(true);

        buttonD.setText(options[currentQuestion][3]);
        buttonD.setBackground(buttonColor);
        buttonD.setEnabled(true);

        scoreValue.setText(""+scoreValueAmount);

    }
    // if the answers correct
    public void correct(){
        createMessageScreen();
        messageLabel.setText("Correct answer :-)");
    }
    // if the answer is incorrect
    public void incorrect(){
        createMessageScreen();
        messageLabel.setText("Wrong answer :-( The correct answer is "+anss[currentQuestion]);
    }
    // at the end of the quiz
    public void endgame() {
        createMessageScreen();
        percent = (scoreValueAmount * 100) / totalQuestions;
        messageLabel.setText("Thank you for playing! Your final score is " + scoreValueAmount + "/" + totalQuestions);
        if (percent <= 50.0) {
            percentLabel.setText("You scored " + percent + "%. Better luck next time!");
        }
        if ((percent > 50.0)&&(percent != 100.0)) {
            percentLabel.setText("You scored " + percent + "%. Good job!");
        }
        if (percent == 100.0) {
            percentLabel.setText("You scored " + percent + "%. Congratulations!!!");
        }
        nextButton.setText("OK");
        quizWindow.dispose();
        scoreValueAmount = 0;
    }
    // gets the option
    public void optionGetter(String correctOption,JButton button){
        if(anss[currentQuestion].equalsIgnoreCase(correctOption)){
            scoreValueAmount += 1;
            button.setBackground(Color.green);
            button.setEnabled(false);
            correct();
        }
        else{
            button.setBackground(Color.red);
            if(anss[currentQuestion].equalsIgnoreCase("a")){
                buttonA.setBackground(Color.green);
                buttonA.setEnabled(false);
            }
            else if(anss[currentQuestion].equalsIgnoreCase("b")){
                buttonB.setBackground(Color.green);
                buttonB.setEnabled(false);
            }
            else if(anss[currentQuestion].equalsIgnoreCase("c")){
                buttonC.setBackground(Color.green);
                buttonC.setEnabled(false);
            }
            else{
                buttonD.setBackground(Color.green);
                buttonD.setEnabled(false);
            }
            incorrect();
        }

    }
    // when button is clicked
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonA)
        {
            optionGetter("a",buttonA);
        }
        else if(e.getSource() == buttonB)
        {
            optionGetter("b",buttonB);
        }
        else if(e.getSource() == buttonC)
        {
            optionGetter("c",buttonC);
        }
        else if(e.getSource() == buttonD)
        {
            optionGetter("d",buttonD);
        }
        else if(e.getSource() == resetButton)
        {
            scoreValueAmount = 0;
            scoreValue.setText(""+scoreValueAmount);
            currentQuestion=-1;
            updateQuestionOptionsScore();
            createWelcomeScreen();
            quizWindow.dispose();
        }
        else if(e.getSource()== startButton){
            createAndShowQuiz();
            welcome.dispose();
        }
        else if(e.getSource()== nextButton){
            message.dispose();
            updateQuestionOptionsScore();
            if(startAgain==1){
                createWelcomeScreen();
                startAgain = 0;
            }
            if(currentQuestion==0){
                endgame();
                startAgain = 1;
            }
        }
    }

    private static void createAndShowQuiz() {
        //Create and set up the content pane.
        anveshanquiz demo = new anveshanquiz();
        quizWindow.setContentPane(demo.createContentPane());
        quizWindow.setLocation(0,0);
        quizWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizWindow.setSize(windowWidth, windowHeight);
        quizWindow.setVisible(true);
    }
     public void createWelcomeScreen(){
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setLocation(0,0);
        welcome.setContentPane(this.createWelcomeWindow());
        welcome.setSize(windowWidth,windowHeight);
        welcome.setVisible(true);
     }
    public void createMessageScreen(){
        message.setContentPane(this.createMessageWindow());
        message.setSize(messageWindowWidth,250);
        message.setLocation(500,300);
        message.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        // reading the questions from the file
        File file = new File("src\\Questions.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        BufferedImage img = ImageIO.read(new File("src\\logo.png"));
        icon = new ImageIcon(img);

        String st;
        int questNo=0;
        int opNo=-1;
        String sub="";
        int flag=0;
        while ((st = br.readLine()) != null) {
            sub=st.substring(0,2);
            if(opNo>=0){
                options[questNo-1][opNo]=st;
                opNo+=1;
            }
            if(opNo==4){
                opNo=-1;
            }
            if(flag==1){
                quest[questNo]=st;
                questNo+=1;
                flag = 0;
                opNo = 0;
            }
            if (sub.equalsIgnoreCase("qu")) {
                flag=1;
                topic[questNo] = st.substring(10);
            }
            if(st.substring(0,6).equalsIgnoreCase("answer")){
                anss[questNo-1]=st.substring(8);
            }
        }
        for(int i=0;i<maxQuestions;i++){
            if (quest[i]==null){
                totalQuestions = i;
                break;
            }
        }
        anveshanquiz obj = new anveshanquiz();
        obj.createWelcomeScreen();
    }
}
