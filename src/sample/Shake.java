package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;
    public Shake(Node node){//объект окна
        tt = new TranslateTransition(Duration.millis(70), node);//продолжительность
        tt.setFromX(0f);
        tt.setByX(10f);
        tt.setCycleCount(4);//количество
        tt.setAutoReverse(true);//возвращение назад после тряски
    }
    public void playAnim(){
        tt.playFromStart();
    }
}
