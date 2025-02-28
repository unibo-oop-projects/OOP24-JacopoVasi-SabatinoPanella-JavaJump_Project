package view;
import model.GameModel;
import model.GameModelObserver;
import java.awt.*;
public class ViewManager implements GameModelObserver {
    AbstractView currentView;
    GameView gameView;
    MenuView menuView;
    UI UI;
    GameModel model;
    GameFrame frame;
    public ViewManager(GameModel gameModel) {
        this.model = gameModel;
        this.gameView = new GameView(this.model);
        this.UI = new UI(this.model);
        this.menuView=new MenuView();
        this.frame = new GameFrame();
        frame.add(gameView);
        frame.add(UI);
        frame.add(menuView);
        this.currentView = this.menuView;
        currentView.toggleVisibility();

    }


    public void setCvMenu(){
        currentView.toggleVisibility();
        currentView=menuView;
        currentView.toggleVisibility();
    }
    public void setCvGame(){
        currentView.toggleVisibility();
        currentView=gameView;
        currentView.toggleVisibility();
    }
    public void draw(){
        currentView.updateG();

    }

    @Override
    public void onModelUpdate(GameModel model) {
        draw();
    }
}
