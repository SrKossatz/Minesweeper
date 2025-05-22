package com.kossatzdev.cm.model;

import com.kossatzdev.cm.excecao.ExplosionException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Board {

    private int rowsQuantity;
    private int columnsQuantity;
    private int minesQuantity;

    private final List<Field> fields = new ArrayList<>();

    public Board(int rowsQuantity, int columnsQuantity, int minesQuantity) {
        this.rowsQuantity = rowsQuantity;
        this.columnsQuantity = columnsQuantity;
        this.minesQuantity = minesQuantity;
        
        generateFields();
        associateNeighbors();
        shuffleMines();
    }

    public void openField(int row, int column){
        try {
            fields.parallelStream()
                    .filter(c-> c.getRow() == row && c.getColumn() == column)
                    .findFirst()
                    .ifPresent(c->c.open());
        } catch (ExplosionException e){
            fields.forEach(c->c.setOpened(true));
            throw e;
        }
    }
    public void toggleMarking(int row, int column){
        fields.parallelStream()
                .filter(c-> c.getRow() == row && c.getColumn() == column)
                .findFirst()
                .ifPresent(c->c.toggleMarking());
    }
    
    private void generateFields() {
        for (int row = 0; row < rowsQuantity; row++) {
            for (int column = 0; column < columnsQuantity; column++) {
                fields.add(new Field(row, column));
            }
            
        }
    }

    private void associateNeighbors() {
        for (Field c1 : fields){
            for (Field c2 : fields){
                c1.addNeighbor(c2);
            }
        }
    }

    private void shuffleMines() {
        long armedMines = 0;
        Predicate<Field> mined = c->c.isMined();
        do {
            int randomIndex = (int) (Math.random() * fields.size());
            fields.get(randomIndex).plantMine();
            armedMines = fields.stream().filter(mined).count();
        } while (armedMines < minesQuantity);
    }

    public boolean objectiveAchieved(){
       return fields.stream().allMatch(c-> c.objectiveAchieved());
    }

    public void reset(){
        fields.stream().forEach(Field::reset);
        shuffleMines();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int i = 0; i < columnsQuantity; i++) {
            sb.append(" ");
            sb.append(i);
            sb.append(" ");

        }
        sb.append("\n");

        int i = 0;
        for (int row = 0; row < rowsQuantity; row++) {
            sb.append(row);
            sb.append(" ");
            for (int column = 0; column < columnsQuantity; column++) {
                sb.append(" ");
                sb.append(fields.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
