public class Field extends VectorOperation {
    public int w, h;
    private double [] ball;
    private Vector ballMove;
    private double desk;
    private Vector deskMove;


    public Field(int w, int h) {
        this.w = w;
        this.h = h;
        setball();
        setdesk();
        play();
    }

    private void setball(){
        ball = new double[2];
        ball[0] = Math.floor(Math.random()*(w - 10) + 1);
        ball[1] = Math.floor(Math.random()*(h - 10) + 1);
        ballMove = new Vector();
        ballMove.add(Math.floor(0));
        ballMove.add(Math.floor(Math.random()*15));
    }

    private void setdesk(){
        deskMove = new Vector();
        desk = Math.floor(Math.random()*(w - 50) + 1);
        deskMove.add(Math.floor(Math.random()*10));
        deskMove.add(0);
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
        if ((ball[1] + ballMove.get(1)) + r >= h)
            return 4;

        return 0;
    }

    double r = 10.0;
    public void play() {
        System.out.println("ball: " + ball[0] + ' ' + ball[1]);
        System.out.println("desk: " + desk);
        System.out.println("ballMove: " + ballMove.get(0) + ' ' + ballMove.get(1));
        System.out.println("deskMove: " + deskMove.get(0));

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
                if ((((ball[0] + ballMove.get(0)) + r <= desk + deskMove.get(0)) | ((ball[0] + ballMove.get(0)) + 50 >= desk + deskMove.get(0) )) && (ball[1] + ballMove.get(1) + r <= h - 10)) {
                ball[0] += ballMove.get(0);
                ball[1] += ballMove.get(1);
            } else {
                    double x = incr(ballMove, 1 - (h + r - 10 - ball[1]) / Math.abs(ballMove.get(1))).get(0);
                    double y = -incr(ballMove, 1 - (h + r - 10 - ball[1]) / Math.abs(ballMove.get(1))).get(1);
                    ball[0] += (h + r - 10 - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(0);
                    ball[1] += (h + r - 10 - ball[1]) / Math.abs(ballMove.get(1)) * ballMove.get(1);
                    ballMove = new Vector();
                    ballMove.add(x);
                    ballMove.add(y);
                    ballMove = sum(ballMove, deskMove);
                }
        } else {
            if (check() == 3) {
                double x = incr(ballMove, 1 - ball[1] / Math.abs(ballMove.get(1))).get(0);
                double y = -incr(ballMove, 1 - ball[1] / Math.abs(ballMove.get(1))).get(1) ;
                ball[0] = ball[1] / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0];
                ball[1] = ball[1] / Math.abs(ballMove.get(1)) * ballMove.get(1) + ball[1];
                ballMove = new Vector();
                ballMove.add(x);
                ballMove.add(y);
            } else if (check() == 4) {
                System.out.println("Game Over");
            }

        }
        }else {
            if (check() == 1) {
                double x = -incr(ballMove, (1 - (w + r - ball[0]) / Math.abs(ballMove.get(0)))).get(0);
                double y = incr(ballMove, (1 - (w + r - ball[0]) / Math.abs(ballMove.get(0)))).get(1);
                ball[0] = (w + r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0] + x;
                ball[1] = (w + r - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] + y;
                Vector ballMove = new Vector();
                ballMove.add(x);
                ballMove.add(y);
            } else if (check() == 2) {
                double x = -incr(ballMove, 1 - ball[0] / Math.abs(ballMove.get(0))).get(0);
                double y = incr(ballMove, 1 - ball[0] / Math.abs(ballMove.get(0))).get(1);
                ball[0] = ball[0] / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0] + x;
                ball[1] = ball[0] / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] + y;
                Vector ballMove = new Vector();
                ballMove.add(x);
                ballMove.add(y);
            }


        }
        System.out.println("newBall: :" + ball[0] + ' ' + ball[1]);
        System.out.println("newDesk: " + desk);
        System.out.println("newBallMove: " + ballMove.get(0) + ' ' + ballMove.get(1));
        System.out.println("___________");
    }


    public double getDesk(){
        return desk;
    }
    public double [] getBall() {
        return ball;
    }


}

