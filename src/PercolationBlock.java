
public class PercolationBlock {

    private int openSiteCount;
    private final int[] id;
    private final int[] vacancy;
    private final int[] size;
    private final int n;

    public PercolationBlock(int n) {
        this.n = n;
        id = new int[(n*n) + 2];
        vacancy = new int[id.length];
        size = new int[id.length];
        openSiteCount = 0;

        for(int i = 0; i < id.length; i++) {
            id[i] = i;
            vacancy[i] = 0;
            size[i] = 1;
        }

        for(int i = 0; i < n ; i++) {

            union(i , id.length - 2);
            union((id.length - 3 - i) , id.length - 1);
        }

    }

    private int root(int index) {
        while (index != id[index]) {
            // path compression
            id[index] = id[id[index]];

            index = id[index];
        }
        return index;
    }

    public void union(int index1 , int index2) {
        int r1 = root(index1);
        int r2 = root(index2);

        // weighting & union
        if(size[r1] > size[r2])  {
            id[r2] = r1;
            size[r1] += size[r2];
        } else {
            id[r1] = r2;
            size[r2] += size[r1];
        }
    }

    public boolean connected(int index1 , int index2) {
        return root(index1) == root(index2);
    }

    public void open(int index) {

        if(vacancy[index] != 1) {
            openSiteCount++;
        }

        vacancy[index] = 1;

        // left check and connect

        if(index % n != 0 && vacancy[index - 1] == 1) {
            union(index , index - 1);
        }

        // right check and connect

        if((index + 1) % n != 0 && vacancy[index + 1] == 1) {
            union(index , index + 1);
        }

        // up  check and connect

        if(index >= n && vacancy[index - n] == 1) {
            union(index , index - n);
        }

        // down check and connect

        if(index < (n*n) - n && vacancy[index + n] == 1) {
            union(index , index + n);
        }


    }

    public boolean percolates() {
        return connected(id.length - 2 , id.length - 1);
    }

    public int numberOfOpenSites() {
        return openSiteCount;
    }
}
