import java.util.ArrayList;
import java.util.List;

public class Field extends VectorOperation {
    private int w, h;
    private double velocity;
    private double [] ball;
    private Vector ballMove;
    private double desk;
    private Vector deskMove;
    private Vector blocks;
    private List<Listener> listeners = new ArrayList<>();


    public Field(int w, int h) {
        this.w = w;
        this.h = h;
        setball();
        setdesk();
        setBlocks();
        play();
    }

    public Vector getDeskMove() {
        Vector a = new Vector();
        for (int i = 0; i < deskMove.size(); i++){
            a.add(deskMove.get(i));
        }
        return a;
    }
    public double getDesk(){
        return desk;
    }
    public double [] getBall() {
        return ball;
    }
    public int getH() {
        return h;
    }
    public int getW() {
        return w;
    }
    public int getR() {
        return r;
    }
    public Vector getBlocks(){
        return blocks;
    }


    public void setDesk(double x){
        desk = x;
    }
    public void setDeskMove(double x) {
        deskMove = new Vector();
        deskMove.add(x);
        deskMove.add(0);
    }
    private void setball(){
        ball = new double[2];
        ball[0] = (w + r) / 2/*Math.floor(Math.random()*(w - 10) + 1)*/;
        ball[1] = (h + r) / 2/*Math.floor(Math.random()*(h - 10) + 1)*/;
        ballMove = new Vector();
        ballMove.add(0/*Math.floor(0)*/);
        ballMove.add(10/*Math.floor(Math.random()*15)*/);
        velocity = nor(ballMove);
    }
    private void setdesk(){
        deskMove = new Vector();
        desk = (w - 50) / 2/*Math.floor(Math.random()*(w - 50) + 1)*/;
        deskMove.add(0/*Math.floor(Math.random()*10)*/);
        deskMove.add(0);
    }

    private void setBlocks(){
        blocks = new Vector();
        blocks.add(w - 400);
    }

    private void rightCrash(int a) {
        double x = -incr(ballMove, (1 - (a - r - ball[0]) / Math.abs(ballMove.get(0)))).get(0);
        double y = incr(ballMove, (1 - (a - r - ball[0]) / Math.abs(ballMove.get(0)))).get(1);
        ball[0] = (a - r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0];
        ball[1] = (a - r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1];
        ballMove = new Vector();
        ballMove.add(x);
        ballMove.add(y);
        ballMove = incr(ballMove, velocity / nor(ballMove));
    }

    private void leftCrash(int a) {
        double x = -incr(ballMove, 1 - (ball[0] - a) / Math.abs(ballMove.get(0))).get(0);
        double y = incr(ballMove, 1 - (ball[0] - a) / Math.abs(ballMove.get(0))).get(1);
        ball[0] = (ball[0] - a) / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0];
        ball[1] = (ball[0] - a) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1];
        ballMove = new Vector();
        ballMove.add(x);
        ballMove.add(y);
        ballMove = incr(ballMove, velocity / nor(ballMove));
    }

    private void crashBottom(int a) {
        double x = incr(ballMove, 1 - (a - r - ball[1]) / Math.abs(ballMove.get(1))).get(0);
        double y = -incr(ballMove, 1 - (a - r - ball[1]) / Math.abs(ballMove.get(1))).get(1);
        ball[0] += (a - r - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(0);
        ball[1] += (a - r - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(1);
        ballMove = new Vector();
        ballMove.add(x);
        ballMove.add(y);
        ballMove = incr(ballMove, velocity / nor(ballMove));
    }

    private void crashTop(int a) {
        double x = incr(ballMove, 1 - (ball[1] - a) / Math.abs(ballMove.get(1))).get(0);
        double y = -incr(ballMove, 1 - (ball[1] - a) / Math.abs(ballMove.get(1))).get(1) ;
        ball[0] = (ball[1] - a) / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0];
        ball[1] = (ball[1] - a) / Math.abs(ballMove.get(1)) * ballMove.get(1) + ball[1];
        ballMove = new Vector();
        ballMove.add(x);
        ballMove.add(y);
        ballMove = incr(ballMove, velocity / nor(ballMove));
    }


    private int check() {
        if ((((ball[0] + ballMove.get(0)) + r > w) | ((ball[0] + ballMove.get(0)) < 0)) && (((ball[1] + ballMove.get(1)) + r > h) | ((ball[1] + ballMove.get(1)) < 0)))
            return 5;
        if ((ball[0] + ballMove.get(0)) + r > w)
            return 1;
        if ((ball[0] + ballMove.get(0)) < 0)
            return 2;
        if ((ball[1] + ballMove.get(1)) < 0)
            return 3;
        if ((ball[1] + ballMove.get(1)) + r > h)
            return 4;

        return 0;
    }

    private int checkBlocksCrash (int a) {
        if (((ball[1] - a) / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0] <= blocks.get(0) + 25) &&
                        ((ball[1] - a) / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0] >= blocks.get(0)) &&
                        ((ball[1] - a) / Math.abs(ballMove.get(1)) * ballMove.get(1) + ball[1] == blocks.get(1) + 25))
            return 1; //top
        if (((a - r - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0] <= blocks.get(0) + 25) &&
                        ((a - r - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0] >= blocks.get(0)) &&
                        ((a - r - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(1) + ball[1] == blocks.get(1)))
            return 2; //bottom
        if (((a - r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] <= blocks.get(1) + 25) &&
                ((a - r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] >= blocks.get(1)) &&
                ((a - r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0] == blocks.get(0)))
            return 3; // right
        if (((ball[0] - a) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] <= blocks.get(1) + 25) &&
                ((ball[0] - a) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] >= blocks.get(1)) &&
                ((ball[0] - a) / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0] == blocks.get(0) + 25))
            return 4; // left

        return 0;
    }

    int r = 10;
    public void play() {
        if (((ball[1] + ballMove.get(1)) + r <= h) && (0 <= (ball[1] + ballMove.get(1)))) {
            if (((ball[0] + ballMove.get(0)) + r <= w) && (0 <= (ball[0] + ballMove.get(0)))) {
                if ((((ball[0] + ballMove.get(0)) + r <= desk + deskMove.get(0)) || ((ball[0] + ballMove.get(0)) >= desk + deskMove.get(0) + 50 )) || (ball[1] + ballMove.get(1) + r <= h - 10)) {
                    if ((ball[0] + ballMove.get(0) >= blocks.get(0))&&(ball[0] + ballMove.get(0) <= blocks.get(0)+25)&&(ball[1] + ballMove.get(1) <= h)) {
                            leftCrash((int) blocks.get(0) + 25);
                    }
                ball[0] += ballMove.get(0);
                ball[1] += ballMove.get(1);
            } else {
                    double x = incr(ballMove, 1 - (h - r - 10 - ball[1]) / Math.abs(ballMove.get(1))).get(0);
                    double y = -incr(ballMove, 1 - (h - r - 10 - ball[1]) / Math.abs(ballMove.get(1))).get(1);
                    ball[0] += (h - r - 10 - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(0);
                    ball[1] += (h - r - 10 - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(1);
                    ballMove = new Vector();
                    ballMove.add(x);
                    ballMove.add(y);
                    ballMove = sum(ballMove, deskMove);
                    ballMove = incr(ballMove, velocity / nor(ballMove));
            }
        } else {
                if (check() == 1) {
                    rightCrash(w);
                } else if (check() == 2) {
                    leftCrash(0);
                } // отскок от стенок
        }
        }else {
            if (check() == 3) {
                crashTop(0);
            } else if (check() == 4) {
                crashBottom(h);
            } // Отскок от пола и потолка
        }
        for (Listener listener : listeners) {
            listener.modelChanged();
        }
    }



    public void addListener(Listener listener) {
        listeners.add(listener);
    }
}

