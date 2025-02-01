package com.example.javafxapp;

import com.example.javafxapp.domain.Author;
import com.example.javafxapp.task.TaskManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.util.List;


public class Controller {


    @FXML
    private TextArea txArea;

    private List<Author> authors;

    public Controller() {

    }

    @FXML
    private void initialize() {
        TaskManager taskManager = new TaskManager();

        taskManager.setOnSucceeded(event -> {
            authors = taskManager.getValue();
            showAuthors();
        });
        new Thread(taskManager).start();


        }
        private void showAuthors(){
            if (authors == null){

            }
    }





}