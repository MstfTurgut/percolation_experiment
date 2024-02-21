
import java.util.Random;

public class PercolationTest {


    public static void main(String[] args) {

        double sum = 0;

        int experimentNumber = 10000;

        for(int i = 0 ; i < experimentNumber ; i++) {
            sum += testPercolation(100);
            System.out.println("Testing : %" + (i / (double)experimentNumber) * 100);
        }

        System.out.println("Result : " +  sum  / experimentNumber);

    }


    public static double testPercolation(int n) {
        Random random = new Random();
        int length = n*n;
        PercolationBlock block = new PercolationBlock(n);

        int indexToOpen;

        while (!block.percolates()) {

            indexToOpen = random.nextInt(length);

            block.open(indexToOpen);

        }

        return ((double)block.numberOfOpenSites() / length);
    }

}
