package chessbadger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import chessbadger.position.Position;

public class Client extends Application {

    private static final int PIXELS_PER_SQUARE = 64;
    private static final String welcomeMessage =
            "Moves can be made using algebraic notation;"
                    + " for example the command c2c3 would move the piece at c2 to c3.  \n";


    TextField commandField;
    TextArea logArea;

    BoardView boardView;
    ChessGame game;

    MoveMaker[] moveMaker;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");

        //set up new game
        game = new ChessGame();

        //build the board
        boardView = new BoardView(game, PIXELS_PER_SQUARE);

        //build the text area for giving log info to user
        logArea = new TextArea();
        //logArea.setPrefColumnCount(50);
        logArea.setPrefRowCount(5);
        logArea.setEditable(false);
        logArea.setWrapText(true);
        log(welcomeMessage);

        //build the command entry text field
        commandField = new TextField();

        //request focus on the command field after the ui is built,
        //to get a blinking cursor
        Platform.runLater(new Runnable() {
            public void run() {
                commandField.requestFocus();
            }
        });


        //Code for 3 game modes below: Player vs AI (hard), Player vs AI (easy), AI vs AI (easy vs hard)
        moveMaker = new MoveMaker[2];

        //Uncomment the next two lines to play against the Alpha Beta AI with a text field, AI set to hard
        moveMaker[Chess.BLACK] = new AIMoveMaker(new AlphaBetaAI(5, Chess.BLACK));
        moveMaker[Chess.WHITE] = new TextFieldMoveMaker();

        //Uncomment the next two lines to play against the Alpha Beta AI with a text field, AI set to easy
//        moveMaker[Chess.BLACK] = new AIMoveMaker(new AlphaBetaAI(2, Chess.BLACK));
//        moveMaker[Chess.WHITE] = new TextFieldMoveMaker();

        //Uncomment the next two lines to have the Alpha Beta AI play itself, easy vs hard. Used for testing AI settings
//        moveMaker[Chess.WHITE] = new AIMoveMaker(new AlphaBetaAI(2, Chess.WHITE));
//        moveMaker[Chess.BLACK]= new AIMoveMaker(new AlphaBetaAI(5, Chess.BLACK));


        VBox vb = new VBox();
        vb.getChildren().addAll(boardView, logArea, commandField);
        vb.setSpacing(10);
        vb.setPadding(new Insets(10, 10, 10, 10));

        // add everything to a root stackpane, and then to the main window
        StackPane root = new StackPane();
        root.getChildren().add(vb);
        primaryStage.setScene(new Scene(root)); //, boardView.getPreferredWidth(), 600));
        primaryStage.show();

        // sets the game world's game loop (Timeline)
        Timeline timeline = new Timeline(1.0);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(.05), new GameHandler()));
        timeline.playFromStart();
        timeline.playFromStart();

    }

    private void log(String logText) {
        logArea.appendText(logText + "\n");

    }

    // As time passes, handle the state of the game
    private class GameHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            // System.out.println("timer fired");
            // System.out.println(boardView.ready());
            // setting activeMoveSource to null will cause a new one to be
            // created:

            MoveMaker mover = moveMaker[game.position.getToPlay()];

            if (mover.getState() == Worker.State.READY) {
                mover.start(game.position);
            } else if (mover.getState() == Worker.State.SUCCEEDED
                    && boardView.ready()) {
                short move = mover.getMove();
                boardView.doMove(move);
                mover.reset();

                if (game.position.isMate()) {
                    if (game.position.getToPlay() == Chess.WHITE) {
                        System.out.println("Black wins!");
                        log("Black wins!");
                    } else {
                        System.out.println("White wins!");
                        log("White wins!");
                    }
                }

            }

            // System.out.println("activeMoveSource state " +
            // activeMoveSource.getState());

            // short move =
            // playerMoveSources[0].getMove(game.position.toString());
            // System.out.println(move);
            // boardView.doMove(move);
        }

    }

    private class TextFieldMoveMaker implements MoveMaker,
            EventHandler<ActionEvent> {

        private Worker.State state;
        short move;

        public TextFieldMoveMaker() {
            this.state = Worker.State.READY;
            commandField.setOnAction(this);
            move = 0;
        }

        @Override
        public void start(Position position) {
            //String[] players = {"WHITE", "BLACK"};
            //commandField.setPromptText("Your move," + players[position.getToPlay()] + ".");
        }

        @Override
        public void reset() {
            commandField.setText("");
            this.state = Worker.State.READY;

        }

        @Override
        public State getState() {
            return state;
        }

        @Override
        public short getMove() {
            return move;
        }

        @Override
        public void handle(ActionEvent e) {
            String text = commandField.getText();
            if (text != null & text != "") {
                int fromSqi = Chess.strToSqi(text.charAt(0), text.charAt(1));
                int toSqi = Chess.strToSqi(text.charAt(2), text.charAt(3));

                move = game.findMove(fromSqi, toSqi);
                this.state = Worker.State.SUCCEEDED;

            }

        }

    }

    private class AIMoveMaker implements MoveMaker {
        AI ai;
        AIMoveTask moveTask;

        public AIMoveMaker(AI ai) {
            super();
            this.ai = ai;
            this.moveTask = null;
        }

        public void start(Position position) {
            Position duplicate = new Position(position);
            moveTask = new AIMoveTask(ai, duplicate);
            new Thread(moveTask).start();
        }

        public Worker.State getState() {
            if (moveTask == null)
                return Worker.State.READY;
            if (moveTask.getState() == Worker.State.SUCCEEDED)
                return Worker.State.SUCCEEDED;
            return Worker.State.RUNNING;
        }

        public short getMove() {
            return moveTask.getValue();

        }

        public void reset() {
            this.moveTask = null;
        }


    }


}
