/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Дима
 */
public class Percolation2D {

    /**
     * @param args the command line arguments
     */
    private byte[][] sites;
    private int top = 0;
    private int bottom;
    private int size;
    private WeightedQuickUnionUF uf, uf2;
       
    /*Constructor
    
    * @param N Size of grid (N*N)
    
    */

    public Percolation2D(int N) {
        
        if (N <= 0) { throw new IllegalArgumentException(); }
        
        size = N;
        
        bottom = N*N+1;
        
        
        
        
        
        uf = new WeightedQuickUnionUF(N * N + 2);
        
        uf2 = new WeightedQuickUnionUF(N * N + 2);
        
        sites = new byte[N][N];
        
        /*
        sites = new byte[N][N][N];
        */
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = 1;
                
            }
            
        }
        
/*        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++){
                sites[i][j][k] = 1;
                }

            }

        }
*/
        
        
    }
    
    
    /*
    
     * @param i row
    
     * @param j column
    
    */
    
    private int calcIndex(int i, int j) {
        
        return size * (i - 1) + j;
        

        
    }
    
    /*
        private int calcIndex(int i, int j, int k) {
        
        return size * size * (k - 1) + size * (i - 1) + j

    }*/
    
    
    public boolean isOpen(int i, int j) {
        
        if (i > 0 && i <= size && j <= size && j > 0) {
            return sites[i-1][j-1] == 0;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
        
        /*
         if (i > 0 && i <= size && j <= size && j > 0 && k > 0 && k <= size) {
         return sites[i-1][j-1][k-1] == 0;
         }
         else {
         throw new IndexOutOfBoundsException();
         }
        */
        
        
    }
    
    public boolean isFull(int i, int j) {
        
        if (i > 0 && i <= size && j <= size && j > 0)
          
            return uf2.connected(top, calcIndex(i, j));
        
        else
            
            throw new IndexOutOfBoundsException();
        
        /*
                if (i > 0 && i <= size && j <= size && j > 0 && k > 0 && k <= size)
          
            return uf2.connected(top, calcIndex(i, j, k));
        
        else
            
            throw new IndexOutOfBoundsException();
        
        
        */
        
    }
        
    public void open(int i, int j) {
        
        
        //i = StdRandom.uniform(size)+1;
        //j = StdRandom.uniform(size)+1;
        
        if (i > 0 && i <= size && j <= size && j > 0) {
            sites[i - 1][j - 1] = 0;
            
     // open for uf
            
         //   if(i == 1) uf.union(calcIndex(i,j), top);
                    
           // if(i == size) uf.union(calcIndex(i,j), bottom);
            
            if (i == 1) {
                uf.union(top, calcIndex(i, j));
            }

            if (i == size) {
                uf.union(bottom, calcIndex(i, j));
            }

            if (j > 1 && isOpen(i, j - 1)) {
                uf.union(calcIndex(i, j), calcIndex(i, j - 1));
            }

            if (j < size && isOpen(i, j + 1)) {
                uf.union(calcIndex(i, j), calcIndex(i, j + 1));
            }

            if (i > 1 && isOpen(i - 1, j)) {
                uf.union(calcIndex(i, j), calcIndex(i - 1, j));
            }

            if (i < size && isOpen(i + 1, j)) {
                uf.union(calcIndex(i, j), calcIndex(i + 1, j));
            }
            
            
            
       //open for uf2
            


                if (i == 1) {
                    uf2.union(top, calcIndex(i, j));
                }



                if (j > 1 && isOpen(i, j - 1)) {
                    uf2.union(calcIndex(i, j), calcIndex(i, j - 1));
                }

                if (j < size && isOpen(i, j + 1)) {
                    uf2.union(calcIndex(i, j), calcIndex(i, j + 1));
                }

                if (i > 1 && isOpen(i - 1, j)) {
                    uf2.union(calcIndex(i, j), calcIndex(i - 1, j));
                }

                if (i < size && isOpen(i + 1, j)) {
                    uf2.union(calcIndex(i, j), calcIndex(i + 1, j));
                
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        
    }
    
    
    public boolean percolates() {

        return uf.connected(top, bottom);
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Percolation2D test;
        test = new Percolation2D(4);
        PercolationStats2D test1;
        test1 =new  PercolationStats2D(100, 100);
        double probability = test1.mean();
        StdOut.print(probability + "\n");

    }
    
}
