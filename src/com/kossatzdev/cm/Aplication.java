package com.kossatzdev.cm;

import com.kossatzdev.cm.excecao.ExitException;
import com.kossatzdev.cm.model.Board;
import com.kossatzdev.cm.view.Console;

public class Aplication {
    public static void main(String[] args) {

        Board board = new Board(6,6,3);

        try {
            new Console(board);
        } catch (ExitException e) {
            System.out.println("Jogo encerrado pelo usuário.");
        }



    }
}
