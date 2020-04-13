/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazyprimesdriver;

public class LazyPrimesDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize
        for(int i=9990; i< 10020; i++)
            System.out.println( i + " isPrime(): " + LazyPrime.isPrime(i) );
    }
    
}
