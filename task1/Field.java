public class Field {
    private static int w, h;
    private static double [] ball;
    private static double [] ballMove;
    private static double desk;
    private static double deskMove;


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
        ballMove = new double[2];
        ballMove[0] = Math.floor(Math.random()*10);
        ballMove[1] = Math.floor(Math.random()*15);

    }

    private void setdesk(){
        desk = 5 + Math.floor(Math.random()*(w-10));
        deskMove = Math.floor(Math.random()*10);
    }

    double r = 5.0;
    private void play() {
        if ( ((ball[1] + ballMove[1]) - r < h) && (0 < (ball[1] + ballMove[1]) + r) ) {
            if ( ((ball[0] + ballMove[0]) - r < w) && (0 < (ball[0] + ballMove[0]) + r)  ) {
                if (((ball[1] + ballMove[1]) - r < desk + deskMove - 5) | ((ball[1] + ballMove[1]) + 5 > desk + deskMove + 5)) {
                    ball[0] += ballMove[0];
                    ball[1] += ballMove[1];
                } else {
                    ball[0] += 2 * ballMove[0];
                    ballMove[0] = ball[0] + deskMove;
                    ballMove[1] = ball[1];
                }
            }else {
                ball[1] += 2 * ballMove[1];
                ballMove[1] = ball[1];
                ballMove[0] = ball[0];
            }
        }else {
            ball[0] += 2 * ballMove[0];
            ballMove[0] = ball[0] + deskMove;
            ballMove[1] = ball[1];

        }

        desk += deskMove;


    }


}
