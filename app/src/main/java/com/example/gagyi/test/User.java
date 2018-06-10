package com.example.gagyi.test;

import java.util.List;

/**
 * Created by gagyi on 2017-10-30.
 */

public class User {

    private String firstName;
    private String lastName;
    private String illness;
    private String problemToSolve;
    private List<String> observations;
    private String currentActivity;
    private String educator;
    private String parent;
    private List<String> diary; //List of Diary
    private List<String> tasks; //List of tasks
    private List<String> gamesToPlay; //List of games

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getEducator() {
        return educator;
    }

    public void setEducator(String educator) {
        this.educator = educator;
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public String getProblemToSolve() {
        return problemToSolve;
    }

    public void setProblemToSolve(String problemToSolve) {
        this.problemToSolve = problemToSolve;
    }

    public List<String> getObservations() {
        return observations;
    }

    public void setObservations(List<String> observations) {
        this.observations = observations;
    }

    public User (){

    }

    public User(String firstName, String lastName, String illness, String problemToSolve, List<String> observations, List<String> diary, List<String> tasks, List<String> gamesToPlay, String currentActivity, String educator , String parent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.illness = illness;
        this.problemToSolve = problemToSolve;
        this.observations = observations;
        this.diary = diary;
        this.tasks = tasks;
        this.gamesToPlay = gamesToPlay;
        this.currentActivity = currentActivity;
        this.educator = educator;
        this.parent = parent;
    }

    public List<String> getDiary() {
        return diary;
    }

    public void setDiary(List<String> diary) {
        this.diary = diary;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }

    public List<String> getGamesToPlay() {
        return gamesToPlay;
    }

    public void setGamesToPlay(List<String> gamesToPlay) {
        this.gamesToPlay = gamesToPlay;
    }
}
