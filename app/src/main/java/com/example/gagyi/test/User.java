package com.example.gagyi.test;

import java.util.List;

/**
 * Created by gagyi on 2017-10-30.
 */

public class User {

    public String firstName;
    public String lastName;
    public String illness;
    public String problemToSolve;
    public String observations;
    public String diary; //List of Diary
    public String tasks; //List of tasks
    public String gamesToPlay; //List of games

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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public User (){

    }

    public User(String firstName, String lastName, String illness, String problemToSolve, String observations, String diary, String tasks, String gamesToPlay) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.illness = illness;
        this.problemToSolve = problemToSolve;
        this.observations = observations;
        this.diary = diary;
        this.tasks = tasks;
        this.gamesToPlay = gamesToPlay;
    }

    public String getDiary() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary = diary;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    public String getGamesToPlay() {
        return gamesToPlay;
    }

    public void setGamesToPlay(String gamesToPlay) {
        this.gamesToPlay = gamesToPlay;
    }
}
