public class VectorOperation extends Vector {
    public Vector vector1;
    public Vector vector2;
    public static Vector resultVector;
    public double[] point;


    public static Vector sum(Vector vector1, Vector vector2) {
        resultVector = new Vector();
        for (int i = 0; i < vector1.size(); i++) {
            resultVector.add(vector1.get(i) + vector2.get(i));
        }
        return resultVector;
    }

    public static Vector div(Vector vector1, Vector vector2) {
        resultVector = new Vector();
        for (int i = 0; i < vector1.size(); i++) {
            resultVector.add(vector1.get(i) - vector2.get(i));
        }
        return resultVector;
    }

    public static Vector incr(Vector vector1, double k) {
        resultVector = new Vector();
        for (int i = 0; i < vector1.size(); i++) {
            resultVector.add(k * vector1.get(i));
        }
        return resultVector;
    }

    public static double nor(Vector vector1) {
        double k = 0;
        for (int i = 0; i < vector1.size(); i++) {
            k = k + (vector1.get(i) * vector1.get(i));
        }
        return Math.sqrt(k);
    }

    public static double skalar(Vector vector1, Vector vector2) {
        double k = 0;
        for (int i = 0; i < vector1.size(); i++) {
            k = k + vector1.get(i) * vector2.get(i);
        }
        return k;
    }
}
