package com.example.trabalhoanimacao;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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

    public void gerarBotoes(int TL, double largura) {
        vet = new Button[TL];
        indice = new Text[TL];
        Random aleatorio = new Random();

        double espacoEntreBotoes = 50;
        double larguraBotao = 40;
        double larguraTotal = TL * espacoEntreBotoes;
        double startX = (largura - larguraTotal) / 2;

        for (int i = 0; i < TL; i++) {
            double buttonX = startX + espacoEntreBotoes * i;

            indice[i] = new Text("" + i);
            indice[i].setStyle("-fx-fill: #F8F8F2");
            indice[i].setFont(new Font(14));

            double textWidth = indice[i].getLayoutBounds().getWidth();
            double textX = buttonX + (larguraBotao - textWidth) / 2;

            indice[i].setLayoutX(textX);
            indice[i].setLayoutY(360);
            pane.getChildren().add(indice[i]);
        }

        for (int i = 0; i < TL; i++) {
            double buttonX = startX + espacoEntreBotoes * i;

            DropShadow glow = new DropShadow();
            glow.setRadius(15);

            vet[i] = new Button("" + aleatorio.nextInt(i, 35));
            vet[i].setStyle("-fx-background-color: #c5c3c3");
            vet[i].setMinWidth(larguraBotao);
            vet[i].setMinHeight(40);
            vet[i].setFont(new Font(14));
            vet[i].setLayoutX(buttonX);
            vet[i].setLayoutY(300);
            pane.getChildren().add(vet[i]);
        }
    }

    public void gerarEstiloPane(double larguraPane) {
        Text text = new Text("Arcesti Giglio Ricci - ShellSort");
        text.setFont(Font.font(20));
        text.setStyle("-fx-fill: #fff");

        double larguraTexto = text.getLayoutBounds().getWidth();

        text.setLayoutX((larguraPane - larguraTexto) / 2);
        text.setLayoutY(35);

        pane.getChildren().add(text);
    }

    public void geraBotaoIni() {
        botao_inicio = new Button();
        botao_inicio.setLayoutX(10);
        botao_inicio.setLayoutY(300);
        botao_inicio.setText("Iniciar");
        botao_inicio.setStyle("-fx-text-fill: #8d8b8b; -fx-background-color: #38dc6c;");
        botao_inicio.setPrefHeight(40);
        botao_inicio.setOnAction(e -> {
            move_botoes();
        });
        gerarEstiloPane(1200);
        pane.getChildren().add(botao_inicio);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pesquisa e Ordenacao: ShellSort");
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: #4a4a4d");
        gerarBotoes(15, 1200);
        TL = 15;
        geraBotaoIni();
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
            }

            public void shellSort() {
                int dist = 1, aux, pos;
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
                        alinhaBotao();
                    }
                    dist = dist/2;
                }
            }

            public void alinhaBotao() {
                for (int i = 0; i < TL; i++) {
                    vet[i].setLayoutY(300);
                }
            }

            public void desmarcaPosDist(int posDist) {
                Platform.runLater(() -> vet[posDist].setStyle("-fx-background-color: #c5c3c3"));
            }

            public void colocaAux(int posAux) {
                int tam = (int)auxBt.getLayoutY();
                for (int i = (int)auxBt.getLayoutY(); i < tam + 100; i++) {
                    Platform.runLater(() -> auxBt.setLayoutY(Math.round(auxBt.getLayoutY()+1)));
                    sleep();
                }
                if(clone!= null) {
                    tam = (int) clone.getLayoutX();
                    for (int i = (int) auxBt.getLayoutX(); i > tam; i--) {
                        Platform.runLater(() -> auxBt.setLayoutX(Math.round(auxBt.getLayoutX()-1)));
                        sleep();
                    }

                    removeClone();
                    if (auxBt != null) {
                        Button botaoAux = auxBt;
                        int inicioY = (int) botaoAux.getLayoutY();
                        int fimY = inicioY + 50;

                        for (int i = inicioY; i < fimY; i++) {
                            Platform.runLater(() -> botaoAux.setLayoutY(botaoAux.getLayoutY() + 1));
                            sleep();
                        }
                    }
                }
                else {
                    Button botaoAux = auxBt;
                    if (botaoAux != null) {
                        int inicioY = (int) botaoAux.getLayoutY();
                        int fimY = inicioY + 50;

                        for (int i = inicioY; i < fimY; i++) {
                            Platform.runLater(() -> botaoAux.setLayoutY(botaoAux.getLayoutY() + 1));
                            sleep();
                        }
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
                    Platform.runLater(() -> vet[posDist].setLayoutX(Math.round(vet[posDist].getLayoutX()+1)));
                    sleep();
                }
                tam = (int)vet[posDist].getLayoutY()+80;
                if(flag) {
                    removeSubClone();
                }
                for (int i = (int)vet[posDist].getLayoutY(); i < tam; i++) {
                    Platform.runLater(() -> vet[posDist].setLayoutY(Math.round(vet[posDist].getLayoutY()+1)));
                    sleep();
                }
                vet[pos] = vet[posDist];
                posAux = posDist;
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
                Platform.runLater(() -> {
                    if (text != null) {
                        clone = new Button(text);
                        clone.setStyle("-fx-background-color: #ff3333");
                        clone.setPrefHeight(40);
                        clone.setPrefWidth(40);
                        clone.setLayoutX(x);
                        clone.setLayoutY(y);
                        pane.getChildren().add(clone);
                    }
                });
            }

            public void marcarPosDist(int pos) {
                Platform.runLater(() -> vet[pos].setStyle("-fx-background-color: #18ab18"));
            }

            public void marcarAux(int pos) {
                Platform.runLater(() -> {
                    auxBt = new Button(vet[pos].getText());
                    estilizaAux(auxBt, pos);
                });
                int tam = (int)vet[pos].getLayoutY() - 150;
                for (int i = (int)vet[pos].getLayoutY(); i > tam; i--) {
                    Platform.runLater(() -> auxBt.setLayoutY(auxBt.getLayoutY()-1));
                    sleep();
                }
            }

            public void estilizaAux(Button auxBt, int pos) {
                Platform.runLater(() -> {
                    auxBt.setStyle("-fx-background-color: #3385ff");
                    auxBt.setPrefHeight(40);
                    auxBt.setPrefWidth(40);
                });
                Platform.runLater(() -> {
                    auxBt.setLayoutX(vet[pos].getLayoutX());
                    auxBt.setLayoutY(vet[pos].getLayoutY());
                });
                Platform.runLater(() -> {
                    pane.getChildren().remove(vet[pos]);
                    pane.getChildren().add(auxBt);
                });
            }

            public void sleep() {
                try {
                    Thread.sleep(1);
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