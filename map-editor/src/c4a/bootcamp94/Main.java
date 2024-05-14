package c4a.bootcamp94;

import c4a.bootcamp94.utils.KeyboardControl;

public class Main {
    public static void main(String[] args) {
        MapEditor mapEditor = new MapEditor(20,20);
        new KeyboardControl(mapEditor);
    }
}