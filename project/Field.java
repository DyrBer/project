import java.util.ArrayList;
import java.util.List;

public class Field extends VectorOperation {
    private int w, h;
    private double velocity;
    private double[] ball;
    private Vector ballMove;
    private double desk;
    private Vector deskMove;
    private Vector blocks;
    private List<Listener> listeners = new ArrayList<>();
    private Vector[] block;


    public Field(int w, int h) {
        this.w = w;
        this.h = h;
        setball();
        setdesk();
        setBlock();
        play();
    }

    public Vector getDeskMove() {
        Vector a = new Vector();
        for (int i = 0; i < deskMove.size(); i++) {
            a.add(deskMove.get(i));
        }
        return a;
    }

    public Vector getBallMove() {
        Vector a = new Vector();
        for (int i = 0; i < ballMove.size(); i++) {
            a.add(ballMove.get(i));
        }
        return a;
    }

    public double getDesk() {
        return desk;
    }

    public double[] getBall() {
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

    public Vector[] getBlocks() {
        return block;
    }

    public int getBlockH() {
        return bH;
    }

    public int getBlockW() {
        return bW;
    }

    public int getDeskW () {
        return deskW;
    }




    public void setDesk(double x) {
        desk = x;
    }

    public void setDeskMove(double x) {
        deskMove = new Vector();
        deskMove.add(x);
        deskMove.add(0);
    }

    private void setball() {
        ball = new double[2];
        ball[0] = (w - r ) / 2;
        ball[1] = h - 10 - r ;
        ball[0] += r/2;

        ballMove = new Vector();
        ballMove.add(0);
        ballMove.add(-4);
        velocity = nor(ballMove);
    }

    private void setdesk() {
        deskMove = new Vector();
        desk = (w - deskW) / 2;
        deskMove.add(0);
        deskMove.add(0);
    }

    private void setBlock() {
        block = new Vector[56];
        for (int i = 0; i <= 7; i++) {
            block[i] = new Vector();
            block[i].add(100 + i * 50);
            block[i].add(20);
        }
        for (int i = 8; i <= 15; i++) {
            block[i] = new Vector();
            block[i].add(100 + (i-8) * 50);
            block[i].add(40);
        }
        for (int i = 16; i <= 23; i++) {
            block[i] = new Vector();
            block[i].add(100 + (i-16) * 50);
            block[i].add(60);
        }
        for (int i = 24; i <= 31; i++) {
            block[i] = new Vector();
            block[i].add(100 + (i-24) * 50);
            block[i].add(80);
        }
        for (int i = 32; i <= 39; i++) {
            block[i] = new Vector();
            block[i].add(100 + (i-32) * 50);
            block[i].add(100);
        }
        for (int i = 40; i <= 47; i++) {
            block[i] = new Vector();
            block[i].add(100 + (i-40) * 50);
            block[i].add(120);
        }
        for (int i = 48; i <= 55; i++) {
            block[i] = new Vector();
            block[i].add(100 + (i-48) * 50);
            block[i].add(140);
        }
    }

    private void rightCrash(int a) {
        if ( ballMove.get(1) >= 0) {
            ball[1] = ball[1] + (ballMove.get(1)) / (ballMove.get(0)) * (a - ball[0]) ;
        }else {
            ball[1] = ball[1] + (ballMove.get(1)) / (ballMove.get(0)) * (a - ball[0]) ;
        }
        ball[0]=a-r;
        double x = ballMove.get(0);
        double y = ballMove.get(1);
        ballMove = new Vector();
        ballMove.add(-x);
        ballMove.add(y);
    }

    private void leftCrash(int a) {
        ball[1]=ball[1]+(ballMove.get(1))/(ballMove.get(0))*(a - ball[0]);
        ball[0]=a;
        double x = ballMove.get(0);
        double y = ballMove.get(1);
        ballMove = new Vector();
        ballMove.add(-x);
        ballMove.add(y);
    }

    private void crashBottom(int a) {
        if ( ballMove.get(0) >= 0) {
            ball[0] = ball[0] + (ballMove.get(0)) / (ballMove.get(1)) * (a - ball[1]) - r;
        }else {
            ball[0] = ball[0] + (ballMove.get(0)) / (ballMove.get(1)) * (a - ball[1]) + r;
        }
        ball[1]=a - r;
        double x = ballMove.get(0);
        double y = ballMove.get(1);
        ballMove = new Vector();
        ballMove.add(x);
        ballMove.add(-y);
    }

    private void crashTop(int a) {
        ball[0]=ball[0]+(ballMove.get(0))/(ballMove.get(1))*(a - ball[1]);
        ball[1]=a;
        double x = ballMove.get(0);
        double y = ballMove.get(1);
        ballMove = new Vector();
        ballMove.add(x);
        ballMove.add(-y);
    }


    private int check() {
        if ((((ball[0] + ballMove.get(0)) + r > w) | ((ball[0] + ballMove.get(0)) < 0)) && (((ball[1] + ballMove.get(1)) + r > h) | ((ball[1] + ballMove.get(1)) < 0)))
            return 5;
        if ((ball[0] + ballMove.get(0)) + r > w)
            return 1;
        if ((ball[0] + ballMove.get(0)) < 0)
            return 2;
        if ((ball[1] + ballMove.get(1)) <= 0)
            return 3;
        if ((ball[1] + ballMove.get(1)) + r > h)
            return 4;

        return 0;
    }



    private double y(double x) {
        if (ballMove.get(0) != 0)
            return (ballMove.get(1) / ballMove.get(0) * (x - ball[0]) + ball[1]);
        else if (ballMove.get(1) > 0)
            return -100;
        else if (ballMove.get(1) < 0){
            return -100;

        }

        return 0;
    }

    private int checkBlocksCrash(int i) {
        double vx = ballMove.get(0);
        double vy = ballMove.get(1);
        if ((vy <= 0 ) && (vx >= 0)) {
            if ((y(block[i].get(0)) >= block[i].get(1)) && (y(block[i].get(0)) <= block[i].get(1) + bH)) {
                return 1;
            }
            else
                return 2;
        }
        if ((vy <= 0 ) && (vx <= 0)) {
            if ((y(block[i].get(0) + bW) >= block[i].get(1)) && (y(block[i].get(0) + bW) <= block[i].get(1) + bH))
                return 3;
            else
                return 4;
        }
        if ((vy >= 0 ) && (vx <= 0)) {
            if ((y(block[i].get(0) + bW) >= block[i].get(1)) && (y(block[i].get(0) + bW) <= block[i].get(1) + bH))
                return 5;
            else
                return 6;
        }
        if ((vy >= 0 ) && (vx >= 0)) {
            if ((y(block[i].get(0)) >= block[i].get(1)) && (y(block[i].get(0)) <= block[i].get(1) + bH))
                return 7;
            else
                return 8;
        }
        return 0;
    }

    int r = 10;
    int bH = 20;
    int bW = 50;
    int deskW = 100;

    public void play() {
        if (((ball[1] + ballMove.get(1)) + r <= h) && (0 <= (ball[1] + ballMove.get(1)))) {
            if (((ball[0] + ballMove.get(0)) + r <= w) && (0 <= (ball[0] + ballMove.get(0)))) {
                if ((((ball[0] + ballMove.get(0)) + r <= desk + deskMove.get(0)) || ((ball[0] + ballMove.get(0)) >= desk + deskMove.get(0) + deskW)) || (ball[1] + ballMove.get(1) + r <= h - 10)) {
                    int p = 0;
                    for (int i = 0; i <= 55; i++) {
                        if (block[i] != null) {
                        if ((ball[0] + ballMove.get(0) >= block[i].get(0)) && (ball[0] + ballMove.get(0) <= block[i].get(0) + bW) &&
                                (ball[1] + ballMove.get(1) <= block[i].get(1) + bH) && (ball[1] + ballMove.get(1) >= block[i].get(1))) {
                            int k = checkBlocksCrash(i);
                            p = 1;
                            if (k == 1) {
                                rightCrash((int) block[i].get(0));

                                break;
                            } else if (k == 2) {
                                crashTop((int) block[i].get(1) + bH);
                                block[i]=null;
                                break;
                            } else if (k == 3) {
                                leftCrash((int) block[i].get(0) + bW);
                                block[i]=null;
                                break;
                            } else if (k == 4) {
                                crashTop((int) block[i].get(1) + bH);
                                block[i]=null;
                                break;
                            } else if (k == 5) {
                                leftCrash((int) block[i].get(0) + bW);
                                block[i]=null;
                                break;
                            } else if (k == 6) {
                                crashBottom((int) block[i].get(1));
                                block[i]=null;
                                break;
                            } else if (k == 7) {
                                rightCrash((int) block[i].get(0));
                                block[i]=null;
                                break;
                            } else if (k == 8) {
                                crashBottom((int) block[i].get(1));
                                block[i]=null;
                                break;
                            }

                        }
                        }

                    }
                    if (p == 0) {
                        ball[0] += ballMove.get(0);
                        ball[1] += ballMove.get(1);
                    }


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
            } else {
                if (check() == 3) {
                    crashTop(0);
                } else if (check() == 4) {
                    ballMove = new Vector();
                    ballMove.add(0);
                    ballMove.add(0);

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


