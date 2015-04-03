/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Дима
 */
public class PercolationStats3D {
    private double[] th;
    private int numberOfExperiments;
    private double mean;
    private double stddev;
    private double confidenceLo;
    

/** List of fractions. */

//private double confidenceHi;

    /**
     * List of fractions.
     * @param N
     * @param T
     */
    public PercolationStats3D(int N, int T) {
   // Exception must be called first before any reservation
   // because at this point N could have a wrong value
   if (N <= 0 || T <= 0) { throw new IllegalArgumentException(); }

   // Put here your reservation
   th = new double [T];
   int tot = N*N*N;
   numberOfExperiments = T;

   // i starts here at 0
   // this seems mode logical for me in order not to loose the first element of the array 
   for (int i = 0; i < T; i++) {
       Percolation3D p = new Percolation3D(N);
       // Init of the number of opening
       // This was to here in your code.
       int op = 0;

       while (!p.percolates())
       {   
           // I just change the name of the variables
           // because it is more readable but you can keep i1, and j if you prefer
           int row = StdRandom.uniform(1, N+1);
           int col = StdRandom.uniform(1, N+1);
           int depth = StdRandom.uniform(1, N+1);
           if (!p.isOpen(row, col, depth)) {
               p.open(row, col, depth);
               op++;
           }
           th[i] = (double) op / (double) tot;
        }


    }

    // Computing mean
    mean = 0;
    for (int i = 0; i < th.length; i++) {
        mean += th[i];
    }
    mean = mean / T;

    // Compute here the other values

    stddev = StdStats.stddev(th);
    
    confidenceLo = mean - ((1.96)*stddev)/Math.sqrt(T);
    

    
    
    

    
}

/** Fractions mean. */
public double mean() { return mean; };
/** Deviation. */
public double stddev() { return stddev; };
/** Lower bound confidence. */
public double confidenceLo() { return confidenceLo; };
/** Upper bound confidence. */
public double confidenceHi() { return mean + ((1.96)*stddev)/Math.sqrt(numberOfExperiments); };
    
    public static void main(String[] args) {
        int n, t;
        StdOut.print("Введите ширину поля" + "\n");
        n = StdIn.readInt();
        StdOut.print("Введите число экспериментов" + "\n");
        t = StdIn.readInt();
        PercolationStats3D ps = new PercolationStats3D(n, t);
        //PercolationStats ps=new PercolationStats(20,10);
        StdOut.print("mean\t=\t"+ps.mean()+"\n");
        StdOut.print("stddev\t=\t"+ps.stddev()+"\n");
        StdOut.print("95% confidence Interval\t=\t"+ps.confidenceLo()+",   "+ps.confidenceHi()+"\n");
      
}
}
