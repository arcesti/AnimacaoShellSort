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
    private int TL;
    AnchorPane pane;
    private Button[] vet;
    Button botao_inicio, dist, pos, gap;
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
            public void shellSort() throws InterruptedException {
                int dist = 1, aux, pos;
                while(dist < TL)
                    dist *= 2 + 1;
                dist = dist/2;
                while(dist > 0) {
                    for (int i = dist; i < TL; i++) {
                        aux = parseInt(vet[i].getText());
                        pos = i;
                        criarButtonAux(i, vet[i]);
                        marcaPos(pos);
                        marcaPosDist(pos-dist);
                        Thread.sleep(5000);
                        while(pos >= dist && aux < parseInt(vet[pos-dist].getText())) {
                            marcaPos(pos);
                            marcaPosDist(pos-dist);
                            trocaButton(pos, dist);
                            pos = pos - dist;
                            Thread.sleep(1000);
                        }
                    }
                }
            }

            public void criarButtonAux(int pos, Button btn) {
                Button aux = new Button(btn.getText());
                aux.setMinWidth(40);
                aux.setMinHeight(40);
                aux.setLayoutY(btn.getLayoutY());
                aux.setLayoutX(btn.getLayoutX());
                pane.getChildren().add(aux);
                marcaButtonAux(aux);
                moverAux(aux);
            }

            public void moverAux(Button aux) {
                for (int i = 0; i < 10; i++) {
                    Platform.runLater(() -> {
                        aux.setLayoutY(aux.getLayoutY() - 10);
                    });
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            public void trocaButton(int pos, int dist) {
                Platform.runLater(() -> {
                    vet[pos].setStyle("-fx-background-color: #0a88b2");
                    vet[pos - dist].setStyle("-fx-background-color: #0a88b2");
                });
            }

            public void marcaPos(int i) {
                Platform.runLater(() -> {
                    vet[i].setStyle("-fx-background-color: #b31212");
                });
            }

            public void marcaPosDist(int i) {
                Platform.runLater(() -> {
                    vet[i].setStyle("-fx-background-color: #fff033");
                });
            }

            public void marcaButtonAux(Button i) {
                Platform.runLater(() -> {
                    i.setStyle("-fx-background-color: #73b312");
                });
            }

            public void limparAux(int i) {
                Platform.runLater(() -> {
                    vet[i].setStyle("-fx-background-color: WHITE");
                });
            }
        };


        Thread thread = new Thread(task);
        thread.start();
    }
}