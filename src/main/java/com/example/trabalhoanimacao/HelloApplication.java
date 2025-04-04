package com.example.trabalhoanimacao;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.util.Random;

import static java.lang.Integer.parseInt;

public class HelloApplication extends Application {
    private int TL, posAux;
    AnchorPane pane;
    private Button[] vet;
    Button botao_inicio, auxBt, clone = null, subClone;
    private Text[] indice;

    public static void main(String[] args) {
        launch(args);
    }

    public void gerarBotoes(int TL) {
        vet = new Button[TL];
        indice = new Text[TL];
        Random aleatorio = new Random();

        for (int i = 0; i < TL; i++) {
            double buttonX = 50 * (i + 1);
            indice[i] = new Text("" + i);
            indice[i].setStyle("-fx-fill: #F8F8F2");
            indice[i].setFont(new Font(14));
            double textWidth = indice[i].getLayoutBounds().getWidth();
            double textX = buttonX + (40 - textWidth) / 2;
            indice[i].setLayoutX(textX);
            indice[i].setLayoutY(260);
            indice[i].setFont(Font.font(14));

            pane.getChildren().add(indice[i]);
        }

        for (int i = 0; i < TL; i++) {
            double buttonX = 50 * (i + 1);
            vet[i] = new Button("" + aleatorio.nextInt(i, 30));
            vet[i].setMinWidth(40);
            vet[i].setMinHeight(40);
            vet[i].setFont(new Font(14));
            vet[i].setLayoutX(buttonX);
            vet[i].setLayoutY(200);
            pane.getChildren().add(vet[i]);
        }
        /*double buttonX = 50 * (0 + 1);
        vet[0] = new Button("" + 10);
        vet[0].setMinWidth(40);
        vet[0].setMinHeight(40);
        vet[0].setFont(new Font(14));
        vet[0].setLayoutX(buttonX);
        vet[0].setLayoutY(200);
        pane.getChildren().add(vet[0]);

        buttonX = 50 * (1 + 1);
        vet[1] = new Button("" + 3);
        vet[1].setMinWidth(40);
        vet[1].setMinHeight(40);
        vet[1].setFont(new Font(14));
        vet[1].setLayoutX(buttonX);
        vet[1].setLayoutY(200);
        pane.getChildren().add(vet[1]);

        buttonX = 50 * (2 + 1);
        vet[2] = new Button("" + 5);
        vet[2].setMinWidth(40);
        vet[2].setMinHeight(40);
        vet[2].setFont(new Font(14));
        vet[2].setLayoutX(buttonX);
        vet[2].setLayoutY(200);
        pane.getChildren().add(vet[2]);

        buttonX = 50 * (3 + 1);
        vet[3] = new Button("" + 2);
        vet[3].setMinWidth(40);
        vet[3].setMinHeight(40);
        vet[3].setFont(new Font(14));
        vet[3].setLayoutX(buttonX);
        vet[3].setLayoutY(200);
        pane.getChildren().add(vet[3]);

        buttonX = 50 * (4 + 1);
        vet[4] = new Button("" + 1);
        vet[4].setMinWidth(40);
        vet[4].setMinHeight(40);
        vet[4].setFont(new Font(14));
        vet[4].setLayoutX(buttonX);
        vet[4].setLayoutY(200);
        pane.getChildren().add(vet[4]);

        buttonX = 50 * (5 + 1);
        vet[5] = new Button("" + 7);
        vet[5].setMinWidth(40);
        vet[5].setMinHeight(40);
        vet[5].setFont(new Font(14));
        vet[5].setLayoutX(buttonX);
        vet[5].setLayoutY(200);
        pane.getChildren().add(vet[5]);

        buttonX = 50 * (6 + 1);
        vet[6] = new Button("" + 6);
        vet[6].setMinWidth(40);
        vet[6].setMinHeight(40);
        vet[6].setFont(new Font(14));
        vet[6].setLayoutX(buttonX);
        vet[6].setLayoutY(200);
        pane.getChildren().add(vet[6]);

        buttonX = 50 * (7 + 1);
        vet[7] = new Button("" + 8);
        vet[7].setMinWidth(40);
        vet[7].setMinHeight(40);
        vet[7].setFont(new Font(14));
        vet[7].setLayoutX(buttonX);
        vet[7].setLayoutY(200);
        pane.getChildren().add(vet[7]);

        buttonX = 50 * (8 + 1);
        vet[8] = new Button("" + 9);
        vet[8].setMinWidth(40);
        vet[8].setMinHeight(40);
        vet[8].setFont(new Font(14));
        vet[8].setLayoutX(buttonX);
        vet[8].setLayoutY(200);
        pane.getChildren().add(vet[8]);

        buttonX = 50 * (9 + 1);
        vet[9] = new Button("" + 4);
        vet[9].setMinWidth(40);
        vet[9].setMinHeight(40);
        vet[9].setFont(new Font(14));
        vet[9].setLayoutX(buttonX);
        vet[9].setLayoutY(200);
        pane.getChildren().add(vet[9]);*/
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pesquisa e Ordenacao");
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: #3d3d3f");
        gerarBotoes(15);
        TL = 15;
        botao_inicio = new Button();
        botao_inicio.setLayoutX(10);
        botao_inicio.setLayoutY(100);
        botao_inicio.setText("Inicia...");
        botao_inicio.setOnAction(e -> {
            move_botoes();
        });
        pane.getChildren().add(botao_inicio);
        Scene scene = new Scene(pane, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void move_botoes() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws InterruptedException {
                shellSort();
                return null;
                //permutação na tela
                /*for (int i = 0; i < 10; i++) {
                    Platform.runLater(() -> vet[0].setLayoutY(vet[0].getLayoutY() + 5));
                    Platform.runLater(() -> vet[1].setLayoutY(vet[1].getLayoutY() - 5));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 16; i++) {
                    Platform.runLater(() -> vet[0].setLayoutX(vet[0].getLayoutX() + 5));
                    Platform.runLater(() -> vet[1].setLayoutX(vet[1].getLayoutX() - 5));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    Platform.runLater(() -> vet[0].setLayoutY(vet[0].getLayoutY() - 5));
                    Platform.runLater(() -> vet[1].setLayoutY(vet[1].getLayoutY() + 5));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //permutação na memória
                Button aux = vet[0];
                vet[0] = vet[1];
                vet[1] = aux;
                return null;*/
            }
            // ESTA DANDO ERRO NA HORA QUE ELE TEM QUE TROCAR O POS DIST ANDA E TROCA COM OUTRO
            public void shellSort() {
                int dist = 1, indiAux, aux, pos;
                while (dist < TL) {
                    dist = dist * 2 + 1;
                }
                dist = dist / 2;
                while(dist > 0) {
                    for (int i = dist; i < TL; i++) {
                        aux = parseInt(vet[i].getText());
                        pos = i;
                        posAux = i;
                        marcarAux(pos);
                        boolean flag = false;
                        while(pos >= dist && aux < parseInt(vet[pos-dist].getText())) {
                                marcarPosDist(pos-dist);
                                posAux = movimentarParaPos(pos-dist, pos, flag);
                                desmarcaPosDist(pos-dist);
                                pos = pos-dist;
                                flag = true;
                        }
                        colocaAux(posAux);
                    }
                    dist = dist/2;
                }
            }

            public void desmarcaPosDist(int posDist) {
                vet[posDist].setStyle("-fx-background-color: #c5c3c3");
            }

            public void colocaAux(int posAux) {
                int tam = (int)auxBt.getLayoutY();
                for (int i = (int)auxBt.getLayoutY(); i < tam + 100; i++) {
                    auxBt.setLayoutY(i);
                    sleep();
                }
                if(clone!= null) {
                    tam = (int) clone.getLayoutX();
                    for (int i = (int) auxBt.getLayoutX(); i > tam; i--) {
                        Platform.runLater(() -> auxBt.setLayoutX(auxBt.getLayoutX()-1));
                        sleep();
                    }
                    tam = (int) clone.getLayoutY();
                    removeClone();
                    for (int i = (int) auxBt.getLayoutY(); i < tam; i++) {
                        Platform.runLater(() -> auxBt.setLayoutY(auxBt.getLayoutY()+1));
                        sleep();
                    }
                }
                else {
                    tam = (int)auxBt.getLayoutY()+50;
                    for (int i = (int)auxBt.getLayoutY(); i < tam; i++) {
                        Platform.runLater(() -> auxBt.setLayoutY(auxBt.getLayoutY()+1));
                        sleep();
                    }
                }
                desmarcaAux();
                vet[posAux] = auxBt;
                auxBt = null;
            }

            public void desmarcaAux() {
                auxBt.setStyle("-fx-background-color: #c5c3c3");
            }

            public int movimentarParaPos(int posDist, int pos, boolean flag) {
                int tam = (int)vet[posDist].getLayoutY();
                int x = (int)vet[posDist].getLayoutX();
                int y = (int)vet[posDist].getLayoutY();
                int x2 = 0;
                if(clone != null) {
                    subClone = clone;
                    x2 = (int) clone.getLayoutX();
                }
                criaClone(x, y, vet[posDist].getText());
                for (int i = (int)vet[posDist].getLayoutY(); i > tam-80; i--) {
                    Platform.runLater(() -> vet[posDist].setLayoutY(vet[posDist].getLayoutY()-1));
                    //vet[posDist].setLayoutY(i);
                    sleep();
                }
                // Se ja tem um clone, vai para a posicao do clone, se nao, vai para pos
                if(flag) {
                    tam = x2;
                }
                else {
                    tam = (int)vet[pos].getLayoutX();
                }
                for (int i = (int)vet[posDist].getLayoutX(); i < tam; i++) {
                    Platform.runLater(() -> vet[posDist].setLayoutX(vet[posDist].getLayoutX()+1));
                    //vet[posDist].setLayoutX(i);
                    sleep();
                }
                tam = (int)vet[posDist].getLayoutY()+80;
                if(flag) {
                    removeSubClone();
                }
                for (int i = (int)vet[posDist].getLayoutY(); i < tam; i++) {
                    Platform.runLater(() -> vet[posDist].setLayoutY(vet[posDist].getLayoutY()+1));
                    //vet[posDist].setLayoutY(i);
                    sleep();
                }
                if(!flag) {
                    vet[pos] = vet[posDist];
                    posAux = posDist;
                }
                else {
                    vet[pos] = vet[posDist];
                    posAux = posDist;
                }
                return posAux;
            }

            public void removeSubClone() {
                Platform.runLater(() -> {
                    pane.getChildren().remove(subClone);
                    subClone = null;
                });
            }

            public void removeClone() {
                Platform.runLater(() -> {
                    pane.getChildren().remove(clone);
                    clone = null;
                });
            }

            public void criaClone(int x, int y, String text) {
                clone = new Button(text);
                clone.setStyle(clone.getStyle()+" -fx-background-color: #ff3333");
                clone.setPrefHeight(40);
                clone.setPrefWidth(40);
                clone.setLayoutX(x);
                clone.setLayoutY(y);
                Platform.runLater(() -> {
                    pane.getChildren().add(clone);
                });
            }

            public void marcarPosDist(int pos) {
                vet[pos].setStyle("-fx-background-color: #18ab18");
            }

            public void marcarAux(int pos) {
                auxBt = new Button(vet[pos].getText());
                estilizaAux(auxBt, pos);
                int tam = (int)vet[pos].getLayoutY() - 150;
                for (int i = (int)vet[pos].getLayoutY(); i > tam; i--) {
                    Platform.runLater(() -> auxBt.setLayoutY(auxBt.getLayoutY()-1));
                    //auxBt.setLayoutY(i);
                    sleep();
                }
            }

            public void estilizaAux(Button auxBt, int pos) {
                auxBt.setStyle("-fx-background-color: #3385ff");
                auxBt.setPrefHeight(40);
                auxBt.setPrefWidth(40);
                auxBt.setLayoutX(vet[pos].getLayoutX());
                auxBt.setLayoutY(vet[pos].getLayoutY());
                Platform.runLater(() -> {
                    pane.getChildren().remove(vet[pos]);
                    pane.getChildren().add(auxBt);
                });
            }

            public void sleep() {
                try {
                    Thread.sleep(7);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread thread = new Thread(task);
        thread.start();
    }
}