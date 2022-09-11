package lab0;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lab0.variant_8;

public class testVariant_8 {

        public static double EPS = 0.0000001;
        @Test(dataProvider = "integerProvider")
        public void integerTest(int p1, int p3) {
            assertEquals(new variant_8().integerTask(p1), p3);
        }

        @DataProvider
        public Object[][] integerProvider() {
            return new Object[][] { { 69, 96 }, { 12, 21 }, { 88, 88 } };
        }

        @Test(expectedExceptions = AssertionError.class)
        public void negativeIntegerTest() {
            new variant_8().integerTask(-2);
        }

        ////////////////////////////////////////////////

        @Test(dataProvider = "ifProvider")
        public void ifTest(double p1,double p2, double p3,double p4) {
            assertEquals(new variant_8().ifTask(p1,p2), new double[]{p3, p4},EPS);
        }

        @DataProvider
        public Object[][] ifProvider() {
            return new Object[][] { { 7, 8, 8, 7 }, { 10, 6, 10, 6 }, { 0, 0, 0, 0} };
        }

        //////////////////////////////////////////////////

        @Test(dataProvider = "booleanProvider")
        public void booleanTest(int p1, int p2, boolean p3) {
            assertEquals(new variant_8().booleanTask(p1, p2), p3);
        }

        @DataProvider
        public Object[][] booleanProvider() {
            return new Object[][] { { 5, 4, false }, { 1, 7, true }, { 2, 2, false } };
        }

        //////////////////////////////////////////////////

        @Test(dataProvider = "switchProvider")
        public void switchTest(int p1, int p2, int p3, int p4) {
            assertEquals(new variant_8().switchTask(p1, p2), new int[]{p3,p4});
        }

        @DataProvider
        public Object[][] switchProvider() {
            return new Object[][] { { 1, 3 , 28, 2 }, { 2, 1, 1, 1 }, { 1, 1, 31, 12 }};
        }

        @Test(expectedExceptions = AssertionError.class)
        public void switchNegativeTest() {
            new variant_8().switchTask(29,2);
        }

        ///////////////////////////////////////////////////

        @Test(dataProvider = "forProvider")
        public void forTest(int p1, int p2, int p3) {
            assertEquals(new variant_8().forTask(p1,p2), p3);
        }

        @DataProvider
        public Object[][] forProvider() {
            return new Object[][] { { 3, 4, 12 }, { 1, 10, 3628800 }, { -1,1, 0 } };
        }

        ///////////////////////////////////////////////////

        //////////////////////////////////////////

        @Test(dataProvider = "whileProvider")
        public void whileTest(int p1, int p2) {
            assertEquals(new variant_8().whileTask(p1), p2);
        }

        @DataProvider
        public Object[][] whileProvider() {
            return new Object[][] { { 42, 6 }, { 50, 7 }, { 97, 9 }, { 149, 12 } };
        }

        @Test(expectedExceptions = AssertionError.class, dataProvider = "negativeWhileProvider")
        public void negativeWhileTest(int a) {
            new variant_8().whileTask(a);
        }

        @DataProvider
        public Object[][] negativeWhileProvider() {
            return new Object[][] { { -1 }, { 0 }, { -99 } };
        }

        //////////////////////////////////////////
        @Test(dataProvider = "arrayProvider")
        public void arrayTest(int[] array, int[] value) {
            assertEquals(new variant_8().arrayTask(array), value);
        }

        @DataProvider
        public Object[][] arrayProvider() {
            return new Object[][] { { new int[] { 10, 2, 3, 1, 7 }, new int[] { 3, 1, 7 } },
                    { new int[] { 11, 23, 8 }, new int[] { 11, 23 } },
                    { new int[] { 4, 3, 5, -4, 9, 2 }, new int[] { 3, 5, 9 } } };
        }



        //////////////////////////////////////////

        @Test(dataProvider = "matrixProvider")
        public void twoDimensionArrayTest(double[][] input, int K, double[] output) {
            assertEquals(new variant_8().twoDimensionArrayTask(input, K), output);
        }

        @DataProvider
        public Object[][] matrixProvider() {
            double[][] inputMat1 = {{2, 3, 6, 9, -9},
                    {34, 98, -9, 2, 1},
                    {-4, 2, 1, 6, 1},
                    {-98, 8, 1, 5, 3}};
            int inputK1 = 2;
            double[] outputArr1 = {6, -9, 1, 1};

            double[][] inputMat2 = {{2, 3, 6, 9, -9},
                    {-4, 2, 1, 6, 1},
                    {34, 98, -9, 2, 1},
                    {-98, 8, 1, 5, 3}};
            int inputK2 = 0;
            double[] outputArr2 = {2, -4, 34, -98};


            return new Object[][] { {inputMat1, inputK1, outputArr1}, {inputMat2, inputK2, outputArr2} };

        }

    }
