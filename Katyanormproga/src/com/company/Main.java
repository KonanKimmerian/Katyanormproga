package com.company;






import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
//import java.net.URL;
//import java.nio.channels.Channels;
//import java.nio.channels.ReadableByteChannel;
//import java.nio.file.Files;
import java.util.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
//import java.util.logging.Level;
//import java.util.logging.Logger;



public class Main extends JTextField{

    static final String emptyStroke="";
    static final String filler="filler";

    public static void clear(JTextField jtf){
        jtf.setText(emptyStroke);
    }


    //for 1.2



    static ArrayList<String>questionsList;
    static HashMap<String,ArrayList<String>>questionsWithAnswers;
    static {
        questionsList = new ArrayList<>();
        questionsWithAnswers = new HashMap<>();
    }

    static HashMap<String,String>answersOfQuestions;
    static{
        answersOfQuestions=new HashMap<>();
    }



    static HashMap<String,Integer> countOfVaries;
    static{
        countOfVaries=new HashMap<>();
    }
    //текст вопроса - количество вариантов ответа на этот вопрос


    static int questionsPerOptionValue;
    static int optionsIntoValue;
    static int optionsOutValue;
    static String fileNameValue;
    static JFrame gotovo=new JFrame();
    static JFrame frame=new JFrame();
    static {
        gotovo.setTitle("Готовый текст");
        gotovo.setUndecorated(false);
        gotovo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gotovo.setSize(1080, 960);
        gotovo.setLocation(0, 0);
        gotovo.setLayout(null);
    }




    static int [] i;
    static void newvariants(int questionsOutValue, int questionsPerOptionValue,String fileNameValue) {
        Collections.shuffle(questionsList);
//        StringBuilder sb = new StringBuilder();
//        String str="";
        ArrayList<String>noname=new ArrayList<>();

        /*
        .
        .
        .
        .
        v.1.1 code. may not work
        .
        .
        .
        .
        */

        ArrayList<String> answerText=new ArrayList();

        for (int i = 0; i < questionsOutValue; i++) {
            noname.add("Вариант"+" "+(i+1)+"\n");
            answerText.add("Вариант"+" "+(i+1)+"\n");

            Collections.shuffle(questionsList);
            for (int j = 0; j < questionsPerOptionValue; j++) {
                noname.add("Вопрос"+" "+(j+1)+"\n");
                noname.add(questionsList.get(j)+"\n");

                answerText.add("Вопрос"+" "+(j+1)+"\n");

                Collections.shuffle(questionsWithAnswers.get(questionsList.get(j)));
                for (int l = 0; l < countOfVaries.get(questionsList.get(j)); l++) {
//                    sb.append((l + 1) + "." + voprosi.get(arg.get(j)).get(l) + "\n");
//                    str=str+(l+1)+"."+voprosi.get(arg.get(j)).get(l)+"\n";
                    noname.add((l+1)+"."+questionsWithAnswers.get(questionsList.get(j)).get(l)+"\n");

                    if(answersOfQuestions.get(questionsList.get(j)).equals(questionsWithAnswers.get(questionsList.get(j)).get(l))){
                        answerText.add(Integer.toString(l+1)+"\n");
                    }

                }
            }
        }



        saveLevel(fileNameValue,noname);

        saveLevel(fileNameValue+"(answers)",answerText);

//        JLabel whereIsFile=new JLabel();
//        whereIsFile.setBounds(0,0,100,50);
//        whereIsFile.setText("Файл записан в: D:\\"+fileNameValue+".txt.");
//        System.out.println("Файл записан в: D:\\"+fileNameValue+".txt.");
//        gotovo.add(whereIsFile);

//        JTextArea textArea=new JTextArea("Файл записан в: D:\\"+fileNameValue+".txt.");
//        gotovo.add(textArea);
//        gotovo.setLayout(new GridLayout(2,2));


//        gotovo.setVisible(true);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }

        JOptionPane.showMessageDialog(frame,new String[]{"текст теста записан в: D:\\"+fileNameValue+".txt.","текст ответов записан в: D:\\"+fileNameValue+"(answers).txt."},"Расположение текста теста",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setTitle("Моя супер программа");
        jf.setUndecorated(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1080, 960);
        jf.setLocation(0, 0);
        jf.setLayout(null);
        jf.setVisible(true);

        JTextField questionPerOption = new JTextField();
        questionPerOption.setBounds(0, 0, 540, 100);
        jf.add(questionPerOption);



        JLabel questionPerOptionLabel=new JLabel();
        questionPerOptionLabel.setBounds(540,0,540,100);
        questionPerOptionLabel.setText("Сюда количество вопросов на вариант");
        jf.add(questionPerOptionLabel);

        JTextField optionsInto = new JTextField();
        optionsInto.setBounds(0, 100, 540, 100);
        jf.add(optionsInto);



        JLabel optionsIntoLabel=new JLabel();
        optionsIntoLabel.setBounds(540,100,540,100);
        optionsIntoLabel.setText("Сюда количество вводных вариантов");
        jf.add(optionsIntoLabel);



        JTextField optionsOut = new JTextField();
        optionsOut.setBounds(0, 200, 540, 100);
        jf.add(optionsOut);



        JLabel optionsOutLabel=new JLabel();
        optionsOutLabel.setBounds(540,200,540,100);
        optionsOutLabel.setText("Сюда количество вариантов на выход");
        jf.add(optionsOutLabel);


        JTextField fileName=new JTextField();
        fileName.setBounds(0,300, 540,100);
        jf.add(fileName);

        JLabel fileNameLabel=new JLabel();
        fileNameLabel.setText("Запишите имя вашего файла");
        fileNameLabel.setBounds(540,300,540,100);
        jf.add(fileNameLabel);



        JLabel errorTextLabel=new JLabel();
        errorTextLabel.setBounds(0,400,540,100);
        errorTextLabel.setText(" ");
        jf.add(errorTextLabel);

        JButton entering = new JButton("Ввод");
        entering.setBounds(0, 500, 100, 100);
        jf.add(entering);
        entering.addActionListener(e -> {
            questionsPerOptionValue = strtoint(questionPerOption.getText());
            optionsIntoValue = strtoint(optionsInto.getText());
            optionsOutValue = strtoint(optionsOut.getText());
            fileNameValue=fileName.getText();
            if (questionsPerOptionValue > 0 && optionsIntoValue > 0 && optionsOutValue > 0) {
                jf.setVisible(false);
//                     frame.setVisible(true);
                nextStep();
            }else {
//                        l.add("Что-то пошло не так. Проверьте входные данные");
                errorTextLabel.setText("Что-то пошло не так. Проверьте входные данные");
            }
        });


    }

    static int strtoint(String str) {
        try {
//            a=Integer.parseInt(str);
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }


    public static void saveLevel(String name,ArrayList<String>noname) {
        File Platforms = new File(("C:\\" + name + ".txt"));//создали файл
//        System.out.println(Platforms.getName());

        try {
            Platforms.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        File Bebra=Platforms;

        saveStringToFile(noname,Platforms);//производим сохранение

    }


    public static void appendStrToFile(File file, String str) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("D:\\"+file.getName(), true));
            out.write(str);
            out.newLine();
            out.close();
        } catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }


    public static void saveStringToFile(ArrayList<String>noname, File file) {
        for (String string:noname){
            appendStrToFile(file, string);//для всех строчек результата производим записывание в файл
        }
    }


    public static void nextStep(){



        frame.setTitle("Спасибо Шен");
        frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 600);
        frame.setLocation(0, 0);
        frame.setLayout(null);
        frame.setVisible(true);

        JTextField question = new JTextField();
        question.setBounds(0, 0, 540, 100);
        frame.add(question);


        JLabel questionLabel=new JLabel();
        questionLabel.setBounds(540,0,540,100);
        questionLabel.setText("Вопрос");
        frame.add(questionLabel);

        //add buttons (they r not there)

        int[] position=new int[]{0};
        ArrayList<String> intermediate = new ArrayList<>();
        intermediate.add(filler);

        JTextField answer1 = new JTextField();
        answer1.setBounds(150, 100, 400, 100);
        frame.add(answer1);

        answer1.addCaretListener((CaretEvent e) -> {
            if(!answer1.getText().equals(emptyStroke))
                intermediate.set(position[0],answer1.getText());
        });

        JLabel answer1Label=new JLabel();
        answer1Label.setBounds(550,100,600,100);
        answer1Label.setText("Вариант ответа №"+(position[0]+1));
        frame.add(answer1Label);


        //достаточно этого окна



        JLabel toPreviousTextLabel=new JLabel();
        toPreviousTextLabel.setBounds(0,210,540,100);
        toPreviousTextLabel.setText("Предыдущий вариант ответа");
        frame.add(toPreviousTextLabel);


        JLabel toNextTextLabel=new JLabel();
        toNextTextLabel.setBounds(700,210,540,100);
        toNextTextLabel.setText("Предыдущий вариант ответа");
        frame.add(toNextTextLabel);

//        JTextField answer2 = new JTextField();
//        answer2.setBounds(0, 200, 540, 100);
//        frame.add(answer2);
//
//
//        JLabel answer2Label=new JLabel();
//        answer2Label.setText("Второй варинат ответа");
//        answer2Label.setBounds(540,200,540,100);
//        frame.add(answer2Label);
//
//        JTextField answer3 = new JTextField();
//        answer3.setBounds(0, 300, 540, 100);
//        frame.add(answer3);
//
//
//
//        JLabel answer3Label=new JLabel();
//        answer3Label.setBounds(540,300,540,100);
//        answer3Label.setText("Третий вариант ответа");
//        frame.add(answer3Label);
//
//        JTextField answer4 = new JTextField();
//        answer4.setBounds(0, 400, 540, 100);
//        frame.add(answer4);
//
//
//
//
//        JLabel answer4Label=new JLabel();
//        answer4Label.setBounds(540,400,540,100);
//        answer4Label.setText("Четвертый варинт ответа");
//        frame.add(answer4Label);
//


        JTextField trueAnswer=new JTextField();
        trueAnswer.setBounds(0,500,540,100);
        frame.add(trueAnswer);

        JLabel trueAnswerLabel=new JLabel();
        trueAnswerLabel.setBounds(540,500,540,100);
        trueAnswerLabel.setText("Введите правильный ответ");
        frame.add(trueAnswerLabel);





        java.awt.List questionsLeft = new java.awt.List(5);
        questionsLeft.setBounds(0, 600, 1080, 100);
        frame.add(questionsLeft);
        i = new int[]{questionsPerOptionValue * optionsIntoValue};

        questionsLeft.add("Введите вопросы. Осталось:" + " " + (i[0]));

        JButton addQuestion = new JButton("Добавить вопрос");
        addQuestion.setBounds(0, 700, 100, 100);
        frame.add(addQuestion);


        //my territory





        JButton deleteLast=new JButton();
        deleteLast.setBounds(0,300,150,150);
        frame.add(deleteLast);

        deleteLast.addActionListener(e->{
            if(intermediate.size()!=2){
                intermediate.remove(intermediate.size()-1);
            }

            if(position[0]>=intermediate.size()){
                position[0]=intermediate.size()-1;
                answer1Label.setText("Вариант ответа №"+(position[0]+1));

                if(!intermediate.get(position[0]).equals(filler))
                    answer1.setText(intermediate.get(position[0]));
            }



        });

        JLabel deleteLastLabel=new JLabel();
        deleteLastLabel.setBounds(200,300,700,100);
        deleteLastLabel.setText("Удалить последний вариант ответа");
        frame.add(deleteLastLabel);



        JButton previousAnswerOption=new JButton();
        previousAnswerOption.setBounds(0,100,100,100);
        frame.add(previousAnswerOption);

        previousAnswerOption.addActionListener((ActionEvent e)->{
            if(position[0]!=0){
                intermediate.set(position[0],answer1.getText());
                clear(answer1);
                position[0]--;
                answer1Label.setText("Вариант ответа №"+(position[0]+1));

                if(!intermediate.get(position[0]).equals(filler))
                    answer1.setText(intermediate.get(position[0]));


            }
        });


        JButton nextAnswerOption=new JButton();
        nextAnswerOption.setBounds(700,100,100,100);
        frame.add(nextAnswerOption);

        nextAnswerOption.addActionListener((ActionEvent e) -> {
            if(position[0]!=intermediate.size()-1){
                intermediate.set(position[0], answer1.getText());
                clear(answer1);
                position[0]++;
                answer1Label.setText("Вариант ответа №"+(position[0]+1));
            }else{
                intermediate.add(filler);
                position[0]++;
                answer1Label.setText("Вариант ответа №"+(position[0]+1));
                clear(answer1);
            }

            if(!intermediate.get(position[0]).equals(filler))
                answer1.setText(intermediate.get(position[0]));
        });


//        Здесь фигня. Пофиксить

        addQuestion.addActionListener(e -> {
            questionsLeft.clear();
            if (i[0] == 1) {
                newvariants(optionsOutValue, questionsPerOptionValue,fileNameValue);

//                void actionPerformed(ActionEvent e) {
                // Включение в интерфейс иконки
//               JOptionPane.showMessageDialog(this,
//                    "Использование изображения в окне сообщений",
//                    TITLE_message, JOptionPane.INFORMATION_MESSAGE,
//                    icon);


            }else{
                questionsList.add(question.getText());


//            zadanie.removeAll();

                questionsWithAnswers.put(question.getText(),(ArrayList<String>)intermediate.clone());
                ArrayList <String> arr=questionsWithAnswers.get(question.getText());

                countOfVaries.put(question.getText(),arr.size());

                answersOfQuestions.put(question.getText(),trueAnswer.getText());
                clear(trueAnswer);

                clear(question);
                i[0]--;
                questionsLeft.add("Введите вопросы. Осталось:"+" "+i[0]);
                intermediate.set(position[0], answer1.getText());
                intermediate.clear();
                intermediate.add(filler);
                position[0]=0;
                answer1Label.setText("Вариант ответа №"+(position[0]+1));
                clear(answer1);
            }
        });
    }
}