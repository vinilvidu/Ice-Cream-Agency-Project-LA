package lk.ijse.agencyla.controller.util;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class Validate {
    public static Object validation(LinkedHashMap<TextField, Pattern> map) {
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (pattern.matcher(key.getText()).matches()) {
                removeError(key);
                return key;
            } else {
                addError(key);
            }
        }
        return true;
    }

    private static void addError(TextField key) {
        key.setStyle("-fx-border-color: red; ");
    }

    private static void removeError(TextField key) {
        key.setStyle("-fx-border-color: green;");
    }

    public static void vibrateTextField(TextField textField) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(textField.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(100), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(150), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(200), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(250), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(300), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(350), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(400), new KeyValue(textField.translateXProperty(), 0))

        );

        textField.setStyle("-fx-border-color: red;");
        timeline.play();

        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(3), new KeyValue(textField.styleProperty(), "-fx-border-color: #bde0fe;"))
        );

        timeline1.play();
    }
}
