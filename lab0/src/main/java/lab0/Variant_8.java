package lab0;

public class Variant_8 {

    /**
     *
     * @param k is number >= 10 && <= 99
     * @return permutation of two digits of number
     */

    public int integerTask(int k) {
        assert k >= 10 && k <= 99 : "method accept number >= 10 && <= 99";

        return k%10*10+k/10;
    }

    /**
     *
     * @param A is integer number
     * @param B is integer number
     * @return true, if both numbers is odd
     */
    public boolean booleanTask(int A, int B) {

        return A%2 != 0 && B%2 != 0;
    }


    /**
     *
     * @param A is double number
     * @param B is double number
     * @return Array that includes two doubles: 1 - greater and 2 - lesser
     */
    public double[] ifTask(double A, double B) {

        if(A > B)
            return new double[]{A,B};
        else
            return new double[]{B,A};
    }


    /**
     *
     * @param M is integer between 1 and 12 that represent month
     * @param D is integer that represent correct day of month number M
     * @return array that includes number of previous date
     */
    public int[] switchTask(int D, int M) {
        assert M >= 1 && M <= 12 : "M is integer between 1 and 12 that represent month";
        int curMonthCountOfDays = 0;
        switch (M) {
            case 1, 3, 5, 7, 8, 10, 12 -> curMonthCountOfDays = 31;
            case 2 -> curMonthCountOfDays = 28;
            case 4, 6, 9, 11 -> curMonthCountOfDays = 30;
        }
        assert D >= 1 && D <= curMonthCountOfDays : "D is integer that represent correct day of month number M";
        if(D >= 2)
            return new int[]{D-1,M};
        int nD = 0;
        int nM = (M == 1 ? 12 : M - 1);
        switch (nM) {
            case 1, 3, 5, 7, 8, 10, 12 -> nD = 31;
            case 2 -> nD = 28;
            case 4, 6, 9, 11 -> nD = 30;
        }

        return new int[]{nD,nM};
    }


    /**
     *
     * @param A is integer number
     * @param B is integer number > A
     * @return product all integers between A and B
     */
    public int forTask(int A, int B) {
        assert A < B: "Arguments are integers A < B";
        int product = 1;
        for(int i = A;i <= B;i++)
            product *= i;

        return product;
    }


    /**
     *
     * @param N is integer > 0
     * @return K max integer that K*K <= N
     */
    public int whileTask(int N) {
        assert N > 0: "N is integer > 0";
        int l = 1, r = N;
        while(r-l > 1)
        {
            int mid = (l+r)/2;
            if(mid*mid < N)
                l = mid;
            else
                r = mid;
        }
        return r*r < N ? r : l;
    }

    /**
     *
     * @param array is array of integers
     * @return array of odd integers are ordered by index
     */
    public int[] arrayTask(int[] array) {
        int sizeOddArray = 0;
        for (int v : array)
            if (v % 2 == 1)
                sizeOddArray++;
        int[] oddArray = new int[sizeOddArray];
        int i = 0;
        for (int v : array)
            if (v % 2 == 1)
                oddArray[i++] = v;
        return oddArray;
    }

    /**
     *
     * @param matrix is matrix of double numbers
     * @param K is column index
     * @return array that represent column number K
     */
    public double[]  twoDimensionArrayTask(double[][] matrix, int K) {
        int minRowSize = -1;
        for (double[] arr : matrix)
            if(minRowSize == -1 || arr.length < minRowSize)
                minRowSize = arr.length;
        assert 0 <= K && K <= minRowSize : "out of range";
        double[] column = new double[matrix.length];
        int i = 0;
        for (double[] arr : matrix)
            column[i++] = arr[K];

        return column;
    }

}

