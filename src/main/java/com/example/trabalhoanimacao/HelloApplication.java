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
    Button botao_inicio, auxBt, clone = null, subClone, tlBt, distBt, auxBtPrint, btPos, btPosDist;
    private Text[] indice, codigo;

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
            indice[i].setLayoutY(320);
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
            vet[i].setLayoutY(260);
            pane.getChildren().add(vet[i]);
        }
    }

    public void gerarEstiloPane(double larguraPane) {
        Text text = new Text("Arcesti Giglio Ricci - ShellSort");
        text.setStyle("-fx-font-weight: 900; -fx-fill: #fff;");
        text.setFont(Font.font(20));

        double larguraTexto = text.getLayoutBounds().getWidth();

        text.setLayoutX((larguraPane - larguraTexto) / 2);
        text.setLayoutY(35);

        pane.getChildren().add(text);
    }

    public void adicionarLinha(String linha, int y, int i) {
            codigo[i] = new Text(linha);
            codigo[i].setStyle("-fx-fill: #F8F8F2;");
            codigo[i].setFont(new Font(14));
            codigo[i].setLayoutX(15);
            codigo[i].setLayoutY(y);
            pane.getChildren().add(codigo[i]);
    }

    public void geraCodigo() {
        codigo = new Text[18];
        int i = 0, j = 340;
        adicionarLinha("public void shellSort() {", j+=25, i++);
        adicionarLinha("|\tdist = 1;", j+=25, i++);
        adicionarLinha("|\twhile(dist < TL)", j+=25, i++);
        adicionarLinha("|\t\tdist = dist * 2;", j+=25, i++);
        adicionarLinha("|\tdist = dist / 2;", j+=25, i++);
        adicionarLinha("|\twhile(dist > 0) {", j+=25, i++);
        adicionarLinha("|\t|\tfor(i = dist; i < TL; i++) {", j+=25, i++);
        adicionarLinha("|\t|\t|\taux = vet[i];", j+=25, i++);
        adicionarLinha("|\t|\t|\tpos = i;", j+=25, i++);
        adicionarLinha("|\t|\t|\twhile(pos >= dist && aux < vet[pos - dist]) {", j+=25, i++);
        adicionarLinha("|\t|\t|\t|\tvet[pos] = vet[pos - dist];", j+=25, i++);
        adicionarLinha("|\t|\t|\t|\tpos = pos - dist;", j+=25, i++);
        adicionarLinha("|\t|\t|\t}", j+=25, i++);
        adicionarLinha("|\t|\t|\tvet[pos] = aux;", j+=25, i++);
        adicionarLinha("|\t|\t}", j+=25, i++);
        adicionarLinha("|\t}", j+=25, i++);
        adicionarLinha("|\tdist = dist / 2;", j+=25, i++);
        adicionarLinha("}", j+=25, i++);
    }

    public void geraBotaoIni() {
        botao_inicio = new Button();
        botao_inicio.setLayoutX(10);
        botao_inicio.setLayoutY(260);
        botao_inicio.setText("Iniciar");
        botao_inicio.setStyle("-fx-text-fill: #4a4a4d; -fx-background-color: #38dc6c; -fx-font-size: 14px; -fx-font-weight: 900");
        botao_inicio.setPrefHeight(40);
        botao_inicio.setOnAction(e -> {
            move_botoes();
        });
        gerarEstiloPane(1200);
        pane.getChildren().add(botao_inicio);
    }

    public void geraVariaveis() {
            int x = 500, y = 420;
            tlBt = new Button(); distBt = new Button(); auxBtPrint = new Button(); btPosDist = new Button(); btPos = new Button();
            tlBt.setText("TL: " + TL); tlBt.setPrefHeight(40); tlBt.setLayoutY(y); tlBt.setLayoutX(x);  tlBt.setPrefWidth(120);
            y+=50;
            distBt.setText("Dist: "); distBt.setPrefHeight(40); distBt.setLayoutY(y); distBt.setLayoutX(x); distBt.setPrefWidth(120);
            y+=50;
            auxBtPrint.setText("Aux: "); auxBtPrint.setPrefHeight(40); auxBtPrint.setLayoutY(y); auxBtPrint.setLayoutX(x); auxBtPrint.setPrefWidth(120);
            y+=50;
            btPos.setText("Pos: "); btPos.setPrefHeight(40); btPos.setLayoutY(y); btPos.setLayoutX(x); btPos.setPrefWidth(120);
            pane.getChildren().add(tlBt);
            pane.getChildren().add(distBt);
            pane.getChildren().add(auxBtPrint);
            pane.getChildren().add(btPosDist);
            pane.getChildren().add(btPos);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Pesquisa e Ordenacao: ShellSort");
        pane = new AnchorPane();
        TL = 15;
        pane.setStyle("-fx-background-color: #4a4a4d");
        gerarBotoes(15, 1200);
        geraCodigo();
        geraVariaveis();
        geraBotaoIni();
        Scene scene = new Scene(pane, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

    private void grifarLinha(int linha) {
        Platform.runLater(() -> {
            codigo[linha - 1].setStyle("-fx-fill: #F8F8F2");
            codigo[linha].setStyle("-fx-fill: #FF5555");
        });
    }
    private void grifarLinhaSemDesm(int linha) {
        Platform.runLater(() -> {
            codigo[linha].setStyle("-fx-fill: #FF5555");
        });
    }

    public void atualizaDist(int dist) {
        Platform.runLater(() -> {
            distBt.setText("Dist: " + dist);
        });
    }

    public void atualizaPos(int pos) {
        Platform.runLater(() -> {
            btPos.setText("Pos: " + pos);
        });
    }

    public void atualizaAux(int aux) {
        Platform.runLater(() -> {
            auxBtPrint.setText("Aux: " + aux);
        });
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
                grifarLinha(1);
                sleepLinha();
                grifarLinha(2);
                sleepLinha();
                while (dist < TL) {
                    dist = dist * 2 + 1;
                    grifarLinha(3);
                    grifarLinha(2);
                    atualizaDist(dist);
                    sleepLinha();
                    limpaLinha(3);
                    sleepLinha();
                }
                limpaLinha(2);

                dist = dist / 2;
                atualizaDist(dist);
                grifarLinha(4);
                sleepLinha();
                while(dist > 0) {
                    grifarLinha(5);
                    sleepLinha();
                    for (int i = dist; i < TL; i++) {
                        grifarLinha(6);
                        sleepLinha();

                        aux = parseInt(vet[i].getText());
                        pos = i;
                        atualizaPos(pos);
                        posAux = i;
                        grifarLinha(7);
                        atualizaAux(Integer.parseInt(vet[pos].getText()));
                        marcarAux(pos);
                        grifarLinha(8);
                        sleepLinha();
                        grifarLinha(9);
                        boolean flag = false;
                        while(pos >= dist && aux < parseInt(vet[pos-dist].getText())) {
                                grifarLinha(9);
                                sleepLinha();
                                marcarPosDist(pos-dist);
                                grifarLinhaSemDesm(10);
                                posAux = movimentarParaPos(pos-dist, pos, flag);
                                desmarcaPosDist(pos-dist);
                                pos = pos-dist;
                                atualizaPos(pos);
                                grifarLinha(11);
                                sleepLinha();
                                limpaLinha(11);
                                flag = true;
                        }
                        limpaLinha(10);
                        grifarLinha(9);
                        sleepLinha();
                        limpaLinha(9);
                        grifarLinha(13);
                        colocaAux(posAux);
                        grifarLinha(14);
                        limpaLinha(14);
                        alinhaBotao();
                    }
                    dist = dist/2;
                    grifarLinha(16);
                    sleepLinha();
                    atualizaDist(dist);
                    limpaLinha(16);
                }
            }

            public void limpaLinha(int i) {
                Platform.runLater(() -> codigo[i].setStyle("-fx-fill: #F8F8F2"));
            }

            public void alinhaBotao() {
                for (int i = 0; i < TL; i++) {
                    vet[i].setLayoutY(260);
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

            public void sleepLinha() {
                try {
                    Thread.sleep(400);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            public void sleep() {
                try {
                    Thread.sleep(10);
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