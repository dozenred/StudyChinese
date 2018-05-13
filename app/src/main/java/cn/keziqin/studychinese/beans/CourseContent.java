package cn.keziqin.studychinese.beans;

import java.util.ArrayList;

public class CourseContent {
    private int objectId;
    private String strcutImg;
    private String img;
    private String spell;
    private String grammatical;
    private String evolutionInterpretation;
    private String interpretation;
    private String question;
    private String answer;
    private int level;
    private int type;
    private int groupId;
    private String objectName;
    private String strokesImg;
    private String questionInterpretation;
    private String answerInterpretation;
    private ArrayList<SourceBean> sourceList;
    private ArrayList<SentenceBean> sentenceList;

    public CourseContent() {
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getStrcutImg() {
        return strcutImg;
    }

    public void setStrcutImg(String strcutImg) {
        this.strcutImg = strcutImg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getGrammatical() {
        return grammatical;
    }

    public void setGrammatical(String grammatical) {
        this.grammatical = grammatical;
    }

    public String getEvolutionInterpretation() {
        return evolutionInterpretation;
    }

    public void setEvolutionInterpretation(String evolutionInterpretation) {
        this.evolutionInterpretation = evolutionInterpretation;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getStrokesImg() {
        return strokesImg;
    }

    public void setStrokesImg(String strokesImg) {
        this.strokesImg = strokesImg;
    }

    public String getQuestionInterpretation() {
        return questionInterpretation;
    }

    public void setQuestionInterpretation(String questionInterpretation) {
        this.questionInterpretation = questionInterpretation;
    }

    public String getAnswerInterpretation() {
        return answerInterpretation;
    }

    public void setAnswerInterpretation(String answerInterpretation) {
        this.answerInterpretation = answerInterpretation;
    }

    public ArrayList<SourceBean> getSourceList() {
        return sourceList;
    }

    public void setSourceList(ArrayList<SourceBean> sourceList) {
        this.sourceList = sourceList;
    }

    public ArrayList<SentenceBean> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(ArrayList<SentenceBean> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
