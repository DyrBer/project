public class Field extends VectorOperation {
    private static int w, h;
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
        ball[0] = Math.floor(Math.random()*w);
        ball[1] = Math.floor(Math.random()*h);
        ballMove = new Vector();
        ballMove.add(Math.floor(Math.random()*10));
        ballMove.add(Math.floor(Math.random()*15));
    }

    private void setdesk(){
        deskMove = new Vector();
        desk = 5 + Math.floor(Math.random()*(w-10));
        deskMove.add(Math.floor(Math.random()*10));
        deskMove.add(0);
    }

    private int check() {
        if ((((ball[0] + ballMove.get(0)) > w) | ((ball[0] + ballMove.get(0)) < 0)) && (((ball[1] + ballMove.get(1)) > h) | ((ball[1] + ballMove.get(1)) < 0)))
            return 3;
        if ((ball[0] + ballMove.get(0)) > w)
            return 1;
        if ((ball[0] + ballMove.get(0)) < 0)
            return 2;
        if ((ball[1] + ballMove.get(1)) > h)
            return 3;
        if ((ball[1] + ballMove.get(1)) < 0)
            return 4;

        return 0;
    }

    double r = 5.0;
    private void play() {
        System.out.println("ball: " + ball[0] + ' ' + ball[1]);
        System.out.println("desk: " + desk);
        System.out.println("ballMove: " + ballMove.get(0) + ' ' + ballMove.get(1));
        desk += deskMove.get(0);
        if ( ((ball[1] + ballMove.get(1)) - r < h) && (0 < (ball[1] + ballMove.get(1)) + r) ) {
            if ( ((ball[0] + ballMove.get(0)) - r < w) && (0 < (ball[0] + ballMove.get(0)) + r)  ) {
                if (((ball[1] + ballMove.get(1)) - r < desk + deskMove.get(0) - 5) | ((ball[1] + ballMove.get(1)) + 5 > desk + deskMove.get(0) + 5)) {
                    ball[0] += ballMove.get(0);
                    ball[1] += ballMove.get(1);
                } else {
                    double x = incr(ballMove, 1 - ball[1] / Math.abs(ballMove.get(1))).get(0);
                    double y = -incr(ballMove, 1 - ball[1] / Math.abs(ballMove.get(1))).get(1) ;
                    ball[0] = ball[1] / Math.abs(ballMove.get(1)) * ballMove.get(0) + ball[0];
                    ball[1] = ball[1] / Math.abs(ballMove.get(1)) * ballMove.get(1) + ball[1];
                    ballMove = new Vector();
                    ballMove.add(x);
                    ballMove.add(y);
                    ballMove = sum(ballMove, deskMove);
                    ball[0] += ballMove.get(0);
                    ball[1] += ballMove.get(1);
                }
            }else {
                if (check() == 3) {
                    double x = incr(ballMove, 1 - (h - ball[1]) / Math.abs(ballMove.get(1))).get(0);
                    double y = -incr(ballMove, 1 - (h - ball[1]) / Math.abs(ballMove.get(1))).get(1);
                    ball[0] = (ball[1] / Math.abs(ballMove.get(1))) * ballMove.get(0) + ball[0] + x;
                    ball[1] = (ball[1] / Math.abs(ballMove.get(1))) * ballMove.get(1) + ball[1] + y;
                    ballMove = new Vector();
                    ballMove.add(x);
                    ballMove.add(y);
                }else if (check() == 4) {
                    System.out.println("Game Over");
                }else {
                    ball[0] += 2 * ballMove.get(0);
                    ballMove = new Vector();
                    ballMove.add(ball[0] + deskMove.get(0));
                    ballMove.add(ball[1]);
                }
            }
        }else {
            if (check() == 1) {
                double x = -incr(ballMove, (1 - (w - ball[0]) / Math.abs(ballMove.get(0)))).get(0);
                double y = incr(ballMove, (1 - (w - ball[0]) / Math.abs(ballMove.get(0)))).get(1);
                ball[0] = (w - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0] + x;
                ball[1] = (w - ball[0]) / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] + y;
                Vector ballMove = new Vector();
                ballMove.add(x);
                ballMove.add(y);
            }else if (check() == 2) {
                double x = -incr(ballMove, 1 - ball[0] / Math.abs(ballMove.get(0))).get(0);
                double y = incr(ballMove, 1 - ball[0] / Math.abs(ballMove.get(0))).get(1);
                ball[0] = ball[0] / Math.abs(ballMove.get(0)) * ballMove.get(0) + ball[0] + x;
                ball[1] = ball[0] / Math.abs(ballMove.get(0)) * ballMove.get(1) + ball[1] + y;
                Vector ballMove = new Vector();
                ballMove.add(x);
                ballMove.add(y);
            }else {
                ball[1] += 2 * ballMove.get(1);
                ballMove = new Vector();
                ballMove.add(ball[0]);
                ballMove.add(ball[1]);
            }
        }
        System.out.println("newBall: :" + ball[0] + ' ' + ball[1]);
        System.out.println("newDesk: " + desk);
        System.out.println("newBallMove: " + ballMove.get(0) + ' ' + ballMove.get(1));
    }

}
