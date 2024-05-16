package org.example.graafikaga; //klass, et saaks lisada korraga nii pilt kui ka checkbox

import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;

public class DiceContainer {
    private final ImageView imageView;
    private final CheckBox checkBox;

    public DiceContainer(ImageView imageView, CheckBox checkBox) {
        this.imageView = imageView;
        this.checkBox = checkBox;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}