public class Field extends VectorOperation {
    private int w, h;
    private double velocity;
    private double [] ball;
    private Vector ballMove;
    private double desk;
    public Vector deskMove;


    public Field(int w, int h) {
        this.w = w;
        this.h = h;
        setball();
        setdesk();
        play();
    }

    public Vector getDeskMove() {
        Vector a = new Vector();
        for (int i = 0; i<deskMove.size (); i++){
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

    private void setball(){
        ball = new double[2];
        ball[0] = (w + r) / 2/*Math.floor(Math.random()*(w - 10) + 1)*/;
        ball[1] = (h + r) / 2/*Math.floor(Math.random()*(h - 10) + 1)*/;
        ballMove = new Vector();
        ballMove.add(0/*Math.floor(0)*/);
        ballMove.add(10/*Math.floor(Math.random()*15)*/);
        velocity = nor(ballMove);
    }

    public void setdesk(){
        deskMove = new Vector();
        desk = (w + 50) / 2/*Math.floor(Math.random()*(w - 50) + 1)*/;
        deskMove.add(20/*Math.floor(Math.random()*10)*/);
        deskMove.add(0);
    }

    private int check() {
        if ((((ball[0] + ballMove.get(0)) + r > w) | ((ball[0] + ballMove.get(0)) < 0)) && (((ball[1] + ballMove.get(1)) + r > h) | ((ball[1] + ballMove.get(1)) < 0)))
            return 5;
        if ((ball[0] + ballMove.get(0)) + r > w) {
            System.out.println(ballMove.get(0));
            return 1;
        }
        if ((ball[0] + ballMove.get(0)) < 0)
            return 2;
        if ((ball[1] + ballMove.get(1)) < 0)
            return 3;
        if ((ball[1] + ballMove.get(1)) + r > h)
            return 4;

        return 0;
    }

    int r = 10;
    public void play() {
     /*   System.out.println("ball: " + ball[0] + ' ' + ball[1]);
        System.out.println("desk: " + desk);
        System.out.println("ballMove: " + ballMove.get(0) + ' ' + ballMove.get(1));
        System.out.println("deskMove: " + deskMove.get(0)); */

        if ((desk + deskMove.get(0) + 50) > w) {
            double a = -deskMove.get(0);
            deskMove = new Vector();
            deskMove.add(a);
            deskMove.add(0);
        }

        if ((desk + deskMove.get(0)) < 0) {
            double a = -deskMove.get(0);
            deskMove = new Vector();
            deskMove.add(a);
            deskMove.add(0);
        }
        desk += deskMove.get(0);


        if (((ball[1] + ballMove.get(1)) + r <= h) && (0 <= (ball[1] + ballMove.get(1)))) {
            if (((ball[0] + ballMove.get(0)) + r <= w) && (0 <= (ball[0] + ballMove.get(0)))) {
                if ((((ball[0] + ballMove.get(0)) + r <= desk + deskMove.get(0)) || ((ball[0] + ballMove.get(0)) >= desk + deskMove.get(0) + 50 )) || (ball[1] + ballMove.get(1) + r <= h - 10)) {
                ball[0] += ballMove.get(0);
                ball[1] += ballMove.get(1);
            } else {System.out.println("RRRRRRRRR");
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
                    double x = -incr(ballMove, (1 - (w - r - ball[0]) / Math.abs(ballMove.get(0)))).get(0);
                    double y = incr(ballMove, (1 - (w - r - ball[0]) / Math.abs(ballMove.get(0)))).get(1);
                    ball[0] = (w - r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0];
                    ball[1] = (w - r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1];
                    ballMove = new Vector();
                    ballMove.add(x);
                    ballMove.add(y);
                    ballMove = incr(ballMove, velocity / nor(ballMove));
                } else if (check() == 2) {
                    double x = -incr(ballMove, 1 - ball[0] / Math.abs(ballMove.get(0))).get(0);
                    double y = incr(ballMove, 1 - ball[0] / Math.abs(ballMove.get(0))).get(1);
                    ball[0] = ball[0] / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0];
                    ball[1] = ball[0] / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1];
                    ballMove = new Vector();
                    ballMove.add(x);
                    ballMove.add(y);
                    ballMove = incr(ballMove, velocity / nor(ballMove));
                }
        }
        }else {
            if (check() == 3) {
                double x = incr(ballMove, 1 - ball[1] / Math.abs(ballMove.get(1))).get(0);
                double y = -incr(ballMove, 1 - ball[1] / Math.abs(ballMove.get(1))).get(1) ;
                ball[0] = ball[1] / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0];
                ball[1] = ball[1] / Math.abs(ballMove.get(1)) * ballMove.get(1) + ball[1];
                ballMove = new Vector();
                ballMove.add(x);
                ballMove.add(y);
                ballMove = incr(ballMove, velocity / nor(ballMove));
            } else if (check() == 4) {
                double x = incr(ballMove, 1 - (h - r - ball[1]) / Math.abs(ballMove.get(1))).get(0);
                double y = -incr(ballMove, 1 - (h - r - ball[1]) / Math.abs(ballMove.get(1))).get(1);
                ball[0] += (h - r - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(0);
                ball[1] += (h - r - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(1);
                ballMove = new Vector();
                ballMove.add(x);
                ballMove.add(y);
                ballMove = incr(ballMove, velocity / nor(ballMove));


            } // Отскок от пола и потолка
        }
       /* System.out.println("newBall: :" + ball[0] + ' ' + ball[1]);
        System.out.println("newDesk: " + desk);
        System.out.println("newBallMove: " + ballMove.get(0) + ' ' + ballMove.get(1));
        System.out.println("___________");*/
    }
}

