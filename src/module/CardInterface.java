package module;

/**
 * Created by dukunwei on 6/4/17.
 */

interface CardInterface {
    boolean isTop();
    boolean isBottom();
    boolean isLeft();
    boolean isRight();
    void setTop(boolean top);
    void setBottom(boolean bottom);
    void setLeft(boolean left);
    void setRight(boolean right);
    double getOldX();
    double getOldY();

}
