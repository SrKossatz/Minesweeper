package com.kossatzdev.cm.model;

import com.kossatzdev.cm.excecao.ExplosionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

   private Field field;

   @BeforeEach
   void iniciarCampo(){
   field = new Field(3,3);

   }

   @Test
   void testeVizinhoDistancia1Esquerda(){
       Field vizinho = new Field(3,2);
       boolean resultado = field.addNeighbor(vizinho);
       assertTrue(resultado);
   }
    @Test
    void testeVizinhoDistancia1Direita(){
        Field vizinho = new Field(3,4);
        boolean resultado = field.addNeighbor(vizinho);
        assertTrue(resultado);
    }
    @Test
    void testNaoVizinho(){
        Field vizinho = new Field(1,1);
        boolean resultado = field.addNeighbor(vizinho);
        assertFalse(resultado);
    }
    @Test
    void testValorPadraoAtributoMarcado(){
       assertFalse(field.isMarked());
    }
    @Test
    void testToggleMarking(){
        field.toggleMarking();
        assertTrue(field.isMarked());

    }
    @Test
    void testToggleMarkingDuasChamadas(){
        field.toggleMarking();
        field.toggleMarking();
        assertFalse(field.isMarked());

    }
    @Test
    void testabrirNaoMinadoNaoMarcado(){
        assertTrue(field.open());
    }
    @Test
    void testabrirNaoMinadoEMarcado(){
       field.toggleMarking();
        assertFalse(field.open());
    }
    @Test
    void testabrirMinadoEMarcado(){
       field.toggleMarking();
       field.plantMine();
       assertFalse(field.open());
    }
    @Test
    void testabrirMinadoENaoMarcado(){
       field.plantMine();
       assertThrows(ExplosionException.class, () -> {
           field.open();
       });

    }
    @Test
    void testOpenComVizinhos1(){
        Field field22 = new Field(2,2);
        Field field11 = new Field(1,1);

        field22.addNeighbor(field11);
        field.addNeighbor(field22);
        field.open();
        
        assertTrue(field22.isOpened() && field11.isOpened());
        };
   @Test
    void testOpenComVizinhos2(){
        Field field11 = new Field(1,1);
        Field field12 = new Field(1,1);
        field12.plantMine();

        Field field22 = new Field(2,2);
        field22.addNeighbor(field11);
        field22.addNeighbor(field12);

        field.addNeighbor(field22);
        field.open();

       assertTrue(field22.isOpened());
       assertTrue(field11.isClosed());
        };

    }

