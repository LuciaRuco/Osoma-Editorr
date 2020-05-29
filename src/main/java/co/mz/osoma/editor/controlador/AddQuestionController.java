package co.mz.osoma.editor.controlador;

import co.mz.osoma.editor.modelo.QuestionMultiChoiceCaseStudy;
import co.mz.osoma.editor.modelo.QuestionType;
import co.mz.osoma.editor.service.TreeItemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.*;
import javafx.scene.web.HTMLEditor;
import co.mz.osoma.editor.modelo.Question;

import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable, TreeItemController {

    @FXML
    private Pane mainPane;

    private HTMLEditor taText = new HTMLEditor();
    private HTMLEditor taQuestion = new HTMLEditor();
    private  HTMLEditor taExplanation = new HTMLEditor();
    Label cLabel=new Label("case Of Study ");

    private MainGUIController mainGUIController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AnchorPane anchorPane = new AnchorPane();

        HBox hBox = new HBox();

        System.out.println("teste");
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);


        ObservableList<Tab> tabs = FXCollections.observableArrayList();

        Tab tabQA = new Tab("Question");

        Pane pane = new Pane();

        VBox vBoxPrincipal = new VBox();
        vBoxPrincipal.setSpacing(10);
        vBoxPrincipal.setPadding(new Insets(20));


        taText.setPrefHeight(0);
        taText.addEventHandler(InputEvent.ANY, new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                Question  selectedItemObject = getSelectedItemObject();
                selectedItemObject.questionProperty().set(taText.getHtmlText());
            }
        });

        vBoxPrincipal.getChildren().add(cLabel);
        vBoxPrincipal.getChildren().add(taText);
        taText.setVisible(false);
        cLabel.setVisible(false);
        Label qLabel=new Label("Question ");
        taQuestion.setPrefHeight(150);


        taQuestion.addEventHandler(InputEvent.ANY, new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                Question selectedItemObject = getSelectedItemObject();
                selectedItemObject.questionProperty().set(taQuestion.getHtmlText());
            }
        });

        vBoxPrincipal.getChildren().add(qLabel);
        vBoxPrincipal.getChildren().add(taQuestion);

        Label explainLabel=new Label("explain 1 ");
        vBoxPrincipal.getChildren().add(explainLabel);


        taExplanation.setPrefHeight(200);

        taExplanation.addEventHandler(InputEvent.ANY, new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                Question selectedItemObject = getSelectedItemObject();
                selectedItemObject.feedbackProperty().set(taExplanation.getHtmlText());
            }
        });

        vBoxPrincipal.getChildren().add(taExplanation);
        vBoxPrincipal.prefWidthProperty().bind(pane.widthProperty());

        pane.getChildren().add(vBoxPrincipal);
        pane.prefWidthProperty().bind(mainPane.widthProperty());


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPrefHeight(600);
        scrollPane.setContent(pane);

        tabQA.setContent(scrollPane);
        tabPane.getTabs().add(tabQA);
        hBox.getChildren().add(tabPane);


        anchorPane.getChildren().add(hBox);
        mainPane.getChildren().add(anchorPane);
    }
    public Question getSelectedItemObject(){
        TreeItem<Object> selectedItem = mainGUIController.getSeletedItem();
        System.out.println("teste1");
        return (Question) selectedItem.getValue();
    }

    public void fillForm(Object object){
        Question question = (Question) object;
            if ((question.getQtype()).equals(QuestionType.SIGLECASESTUDY)){
                taQuestion.setHtmlText(question.getQuestion());
                taExplanation.setHtmlText(question.getFeedback());
                taText.setVisible(true);
                cLabel.setVisible(true);
                taText.setPrefHeight(100);
                taText.setHtmlText(((QuestionMultiChoiceCaseStudy)question).getCaseOfStudy());
            }

    }

    @Override
    public void setMainGUIController(MainGUIController mainGUIController) {
        System.out.println("teste2");
            this.mainGUIController = mainGUIController;
    }
}
