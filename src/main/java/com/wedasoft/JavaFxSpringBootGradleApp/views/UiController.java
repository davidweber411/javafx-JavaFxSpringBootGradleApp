package com.wedasoft.JavaFxSpringBootGradleApp.views;

import com.wedasoft.JavaFxSpringBootGradleApp.persistence.todo.Todo;
import com.wedasoft.JavaFxSpringBootGradleApp.persistence.todo.TodoRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class UiController implements Initializable {

    private final TodoRepository todoRepository;

    @FXML
    public ListView<Todo> todosListView;
    @FXML
    public TextField todoTf;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        todosListView.setItems(FXCollections.observableArrayList(todoRepository.findAll()));
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    public void onAddTodoBtnClick() {
        todoRepository.save(new Todo(null, todoTf.getText()));
        updateTodosListView();
    }

    public void onDeleteAllTodosBtnClick() {
        todoRepository.deleteAll();
        updateTodosListView();
    }

    private void updateTodosListView() {
        todosListView.setItems(FXCollections.observableArrayList(todoRepository.findAll()));
    }

}
