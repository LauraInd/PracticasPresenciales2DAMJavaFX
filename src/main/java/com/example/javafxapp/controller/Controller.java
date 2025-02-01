package com.example.javafxapp.controller;

import com.example.javafxapp.domain.Author;
import com.example.javafxapp.task.TaskManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;


public class Controller {


    @FXML
    private TableView<Author> authorTable;
    @FXML
    private TableColumn<Author, Integer> columnId;
    @FXML
    private TableColumn<Author, String> columnName;
    @FXML
    private TableColumn<Author, String> columnSurname;
    @FXML
    private TableColumn<Author, LocalDate> columnBirthDate;
    @FXML
    private TableColumn<Author, Boolean> columnActive;


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
            if (authors != null){
                columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
                columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
                columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
                columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
                columnActive.setCellValueFactory(new PropertyValueFactory<>("active"));

                authorTable.setItems(FXCollections.observableArrayList(authors));
            }

            }
    }





