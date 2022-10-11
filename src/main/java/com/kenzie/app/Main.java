package com.kenzie.app;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// import necessary libraries


public class Main {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!

     */
    static final String GET_URL = "https://jservice.kenzie.academy/api/clues";
    static int totalPoints = 0;
    static ArrayList<Integer> idOfQuestionsAsked = new ArrayList<>();

    private static String ruleSet = "You will have the chance to answer 10 trivia questions. For each question" +
            " you get right, you will get 1 point. Wrong answers don't affect your score. To quit, type \"exit\" at" +
            "any time. Good luck!";


    public static String formatUrl(String GET_URL, int questionId){
        String urlId = String.valueOf(questionId);

        return GET_URL + "/" + urlId;
    }

    public static QuestionsDTO.Clues deserializeCluesJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        QuestionsDTO.Clues clues = objectMapper.readValue(json, QuestionsDTO.Clues.class);

        return clues;
    }

    public static int[] selectTenQuestionIds(){
        Random random = new Random();
        int[] questionIds = new int[10];

        int questionsGathered = 0;

        while(questionsGathered < 10) {
            int randomNumber = random.nextInt(100) + 1;

            //check if ArrayList contains the chosen number. If so, select a new number until no match is found.
            while(idOfQuestionsAsked.contains(randomNumber)){
                randomNumber = random.nextInt();
            }
            //if no match is found: add the number to the ArrayList, so it can't be chosen again.
            idOfQuestionsAsked.add(randomNumber);

            //add number to array (this array will be used to ask questions).
            questionIds[questionsGathered] = randomNumber;

            questionsGathered++;
            }

        return questionIds;
    }

    public static String cleanUserInput (String userInput){
        String trimmedAnswer = userInput.trim()
                .replaceAll(" +", " ");

        return trimmedAnswer;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Trivia Night!");
        System.out.println(ruleSet);
        System.out.println("Get ready!");

        Scanner scanner = new Scanner(System.in);

        boolean exitGame = false;
        int[] questionIds = selectTenQuestionIds();
        int questionNumber = 1;

        while(!exitGame){

            try {
                for (int i = 0; i < questionIds.length; i++) {
                    String url = formatUrl(GET_URL, questionIds[i]);
                    String json = CustomHttpClient.sendGET(url);
                    QuestionsDTO.Clues question = deserializeCluesJson(json);

                    System.out.println("Your category is \"" + question.getCategory().getTitle() + "\"");


                    System.out.println("Here's question " + questionNumber);
                    System.out.println(question.getQuestion());

                    String userInput = scanner.nextLine();
                    String cleanInput = cleanUserInput(userInput);

                    while(cleanInput.equals("") || cleanInput.equals(" ")){
                        System.out.println("Looks like you didn't enter anything. Try again");
                        System.out.println(question.getQuestion());

                        userInput = scanner.nextLine();
                        cleanInput = cleanUserInput(userInput);
                    }

                    if(cleanInput.equalsIgnoreCase("exit")){
                        exitGame = true;
                        break;
                    } else if (cleanInput.equalsIgnoreCase(question.getAnswer())) {
                        System.out.println("That's correct!");
                        totalPoints++;
                    } else {
                        System.out.println("So sorry! That is incorrect. The correct answer was: " + question.getAnswer());
                    }
                    System.out.println("Total Points: " + totalPoints);
                    System.out.print("\n");

                    questionNumber++;
                }
            }
            catch (MissingFormatArgumentException e){
                System.out.println("Sorry, there was an error with this question.");
            }

            System.out.print("\n");
            System.out.println("That's all for now.");
            System.out.println("You scored a total of " + totalPoints + " points.");
            System.out.println("Would you like to play again? Y or N");

            String userInput = scanner.nextLine();

            if(userInput.equalsIgnoreCase("y")){
                questionNumber = 1;
                totalPoints = 0;
                questionIds = selectTenQuestionIds();
            } else {
                System.out.println("Thanks so much for playing!");
                exitGame = true;
            }

        }
    }
}

