package com.example.javafxapp.controller;

import com.example.javafxapp.domain.Author;
import com.example.javafxapp.task.TaskFilter;
import com.example.javafxapp.task.TaskManager;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
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
    private TableColumn<Author, LocalDate> columnBirthdate;
    @FXML
    private TableColumn<Author, Boolean> columnActive;
    @FXML
    private ImageView imageV;
    @FXML
    private Button buttonFilter;

    private BufferedImage bufferedImage;


    private List<Author> authors;

    public Controller() {

    }

    @FXML
    private void initialize() {
        showImage();
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
                columnBirthdate.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
                columnActive.setCellValueFactory(new PropertyValueFactory<>("active"));

                authorTable.setItems(FXCollections.observableArrayList(authors));
                authorTable.refresh();

            } else {
                System.out.println("Authors is null");
            }

        }
        private void showImage(){
            String imageUrl = "https://media.istockphoto.com/id/96379220/es/foto/libro-abierto.jpg?s=612x612&w=0&k=20&c=elAwIgMGhzHAIPDVhSkMVHi51l7CYCe5RnpOv4MvfH4=";
            Image image = new Image(imageUrl, true);
            imageV.setImage(image);

        }

        @FXML
        private void applyFilter() {
            Image image = imageV.getImage();
            bufferedImage = SwingFXUtils.fromFXImage(image, null);
            TaskFilter taskFilter = new TaskFilter(bufferedImage);


            taskFilter.setOnSucceeded(event ->{
                Image image2 = SwingFXUtils.toFXImage(bufferedImage, null);
            imageV.setImage(image2);
            });

            new Thread(taskFilter).start();

        }
    }





