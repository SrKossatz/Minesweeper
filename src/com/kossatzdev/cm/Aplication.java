package com.kossatzdev.cm;

import com.kossatzdev.cm.model.Board;
import com.kossatzdev.cm.view.Console;

public class Aplication {
    public static void main(String[] args) {
        Board board = new Board(6,6,3);

        new Console(board);


    }
}
