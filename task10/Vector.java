public class Vector implements IVector {
    private double[] vector = new double[2];
    private int lastIndex = -1;


    public double get(int index) {
        return vector[index];
    }

    public int size() {
        return lastIndex + 1;
    }


    public void add(double value) {
        if (vector.length == lastIndex + 1) {
            double[] newVector = new double[vector.length * 2];
            for (int i = 0; i < vector.length; i++) {
                newVector[i] = vector[i];
            }
            this.vector = newVector;
        }
        lastIndex ++;
        vector[lastIndex] = value;
    }
}
