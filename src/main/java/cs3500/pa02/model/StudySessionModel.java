package cs3500.pa02.model;

import cs3500.pa01.questionWriting.Difficulty;
import cs3500.pa01.questionWriting.Question;
import cs3500.pa01.questionWriting.QuestionSet;
import cs3500.pa01.questionWriting.StudySessionWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the model for a study session
 */
public class StudySessionModel implements Model {
  private final Path srFile;
  private ArrayList<Question> loadedArrayList = new ArrayList<>();
  private ArrayList<Question> questionsToAsk = new ArrayList<>();
  private final ArrayList<Question> questionHard = new ArrayList<>();
  private final ArrayList<Question> questionEasy = new ArrayList<>();
  private final ArrayList<String> seen = new ArrayList<>();
  private final ArrayList<String> seenFinal = new ArrayList<>();
  private int beginningQuestions;
  private int wentEasy;
  private int wentHard;
  private final Random random = new Random();

  /**
   * creates a model based off a given path to the srfile
   *
   * @param filePath path to srfile
   */
  public StudySessionModel(Path filePath) {
    srFile = filePath;
  }

  /**
   * loads the questions in from sr file and divide them into two lists of hard and easy questions
   */
  private void setQuestionList() {
    QuestionSet qs = new QuestionSet(srFile);
    qs.readFile();
    loadedArrayList = qs.getFinalizedSet();
    beginningQuestions = loadedArrayList.size();
    for (Question q : loadedArrayList) {
      if (q.getType().equals(Difficulty.HARD)) {
        questionHard.add(q);
      } else {
        questionEasy.add(q);
      }
    }
  }

  /**
   * gets the finalized questions that are to be asked to the user
   *
   * @return an arraylist of questions
   */
  public ArrayList<Question> getQuestionsToAsk() {
    return questionsToAsk;
  }

  /**
   * generates the final list of questions (mixed with hard and easy) based on how many the user
   * wants to study
   *
   * @param studySessionLength a number of how many questions to study
   */
  public void generateFinalizedQuestionList(int studySessionLength) {
    setQuestionList();
    int questionsFromHard;
    int questionsFromEasy;

    if (studySessionLength <= 0) {
      throw new IllegalArgumentException("can't wanna study no questions");
    }

    if (studySessionLength > loadedArrayList.size()) {
      questionsToAsk = loadedArrayList;
    } else if (studySessionLength <= questionHard.size()) {
      questionsFromHard = studySessionLength;
      while (questionsFromHard > 0) {
        questionsToAsk.add(getNextQuestionFrom(questionHard));
        questionsFromHard--;
      }
    } else if (questionHard.size() == 0) {
      questionsFromEasy = studySessionLength;
      while (questionsFromEasy > 0) {
        questionsToAsk.add(getNextQuestionFrom(questionEasy));
        questionsFromEasy--;
      }
    } else {
      questionsFromHard = questionHard.size();
      questionsFromEasy = studySessionLength - questionsFromHard;

      while (questionsFromHard > 0) {
        questionsToAsk.add(getNextQuestionFrom(questionHard));
        questionsFromHard--;
      }
      while (questionsFromEasy > 0) {
        questionsToAsk.add(getNextQuestionFrom(questionEasy));
        questionsFromEasy--;
      }
    }
  }

  /**
   * helps return the next question in the final list of questions and makes sure it's not a repeat
   *
   * @return a question object that represents the next question
   */
  public Question getNextQuestion() {
    Question question = questionsToAsk.get(random.nextInt(questionsToAsk.size()));
    while (seenFinal.contains(question.getQuestion())) {
      question = questionsToAsk.get(random.nextInt(questionsToAsk.size()));
    }
    seenFinal.add(question.getQuestion());
    return question;
  }

  /**
   * gets the next random question from an arraylist of questions
   *
   * @param inputList an arraylist of questions
   * @return a question to be later asked
   */
  public Question getNextQuestionFrom(ArrayList<Question> inputList) {

    Question question = inputList.get(random.nextInt(inputList.size()));
    while (seen.contains(question.getQuestion())) {
      question = inputList.get(random.nextInt(inputList.size()));
    }
    seen.add(question.getQuestion());
    return question;
  }

  /**
   * returns the answer of a given question
   *
   * @param q a given question
   * @return a string of the answer
   */
  public String getAnswer(Question q) {
    return q.getAnswer();
  }

  /**
   * sets the status of a question in the list to Easy
   *
   * @param q a question object
   */
  public void toEasy(Question q) {
    int indexQ = questionsToAsk.indexOf(q);
    questionsToAsk.get(indexQ).setType(Difficulty.EASY);
    increment(Difficulty.EASY);
  }


  /**
   * sets the difficulty of a question in the list to Hard
   *
   * @param q a question object
   */
  public void toHard(Question q) {
    int indexQ = questionsToAsk.indexOf(q);
    questionsToAsk.get(indexQ).setType(Difficulty.HARD);
    increment(Difficulty.HARD);
  }

  /**
   * increments the correct counter based on an enum
   *
   * @param d an enumeration of the difficulty of a question
   */
  private void increment(Difficulty d) {
    if (d.equals(Difficulty.HARD)) {
      wentHard++;
    } else if (d.equals(Difficulty.EASY)) {
      wentEasy++;
    }
  }

  /**
   * gets the amount of questions that changed to Easy
   *
   * @return number of switches to easy
   */
  public int getCounterEasy() {
    return wentEasy;
  }

  /**
   * gets the amount of questions that changed to Hard
   *
   * @return number of switches to Hard
   */
  public int getCounterHard() {
    return wentHard;
  }

  /**
   * gets the total amount of questions that can be studied in one session
   *
   * @return a number of the size of the list of questions
   */
  public int totalQuestions() {
    return beginningQuestions;
  }

  /**
   * gets the number of questions asked in a study session
   *
   * @return the number of questions completed
   */
  public int getAsked() {
    return seenFinal.size();
  }

  /**
   * gets the final number of hard questions in the sr file
   *
   * @return an int of hard questions
   */
  public int getFinalHard() {
    int counter = 0;
    for (Question q : questionsToAsk) {
      if (q.getType().equals(Difficulty.HARD)) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * gets the final number of easy questions in the sr file
   *
   * @return an int of easy questions
   */
  public int getFinalEasy() {
    int counter = 0;
    for (Question q : questionsToAsk) {
      if (q.getType().equals(Difficulty.EASY)) {
        counter++;
      }
    }
    return counter;
  }

  /**
   * gets the stats of a study session
   *
   * @return the string of all the stats
   */
  public String getStats() {
    return "Nice! you studied " + getAsked() + " questions out of " + totalQuestions()
        + ". " + getCounterEasy() + " of these questions went from Hard to Easy and "
        + getCounterHard() + " of these questions went from Easy to Hard."
        + " There are now " + getFinalHard() + " hard questions and " + getFinalEasy()
        + " easy questions";
  }

  /**
   * gets the list of all questions that were asked and changed and ones which were unchanged
   * and not asked
   *
   * @return an arraylist of questions to be written
   */
  private ArrayList<Question> getListToWrite() {
    ArrayList<Question> result = new ArrayList<>();
    for (Question finalQ : questionsToAsk) {
      boolean isNotAsked = true;
      for (Question beginQ : loadedArrayList) {
        if (beginQ.getQuestion().equals(finalQ.getQuestion())) {
          isNotAsked = false;
          break;
        }
      }
      if (isNotAsked) {
        result.add(finalQ);
      }
    }
    result.addAll(loadedArrayList);
    return result;
  }

  /**
   * updates the sr file by writing newly changed questions to it
   */
  public void updateSr() {
    ArrayList<ArrayList<Question>> newList = new ArrayList<>();
    newList.add(getListToWrite());
    StudySessionWriter writer = new StudySessionWriter(srFile, newList);
    writer.writeQuestions();
  }
}
