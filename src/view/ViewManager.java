package view;

import model.GameModel;
import model.GameModelObserver;

public class ViewManager implements GameModelObserver {
    AbstractView currentView;
    AbstractView gameView;
    AbstractView menuView;
    AbstractView UI;
    public ViewManager(AbstractView gameView,AbstractView menuView, AbstractView UI) {
        this.gameView = gameView;
        this.menuView = menuView;
        this.currentView = this.menuView;
        this.UI = UI;
    }


    public void setCvMenu(){
        currentView=menuView;
    }
    public void setCvGame(){
        currentView=gameView;
    }
    public void draw(){
        currentView.repaint();
    }

    @Override
    public void onModelUpdate(GameModel model) {
        draw();
    }
}
