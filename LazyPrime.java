package lazyprimesdriver;

import java.util.*;

public class LazyPrime {
    // Static fields
    public static final int N = 100; // starting ArrayList size
    public static int n = LazyPrime.N; // ArrayList size
    // Sieve of Eratosthenes
    public static ArrayList<Boolean> primes = new ArrayList<>( 
        Collections.nCopies( LazyPrime.N, true ) 
    );
    // Instance fields
    public int j = 2;
    public int max;
    
    // Constructors
    public LazyPrime( int max ) {
        if( !LazyPrime.isInitialized() )
            LazyPrime.init();
        this.max = max;
    }
    public LazyPrime() {
        this( 1000 );
    }
    
    // Static methods
    public static void init() {
        if( !isInitialized() ) {
            primes.set( 0, false ); // initialized flag
        }
    }
    public static boolean isInitialized() {
        // During initialization we set 0 and 1 to false
        return !primes.get(0);
    }
    // Return next prime using the Sieve of Eratosthenes, -1 for error
    public static int sieve( int f ) {
        // set all elements with indices divisable by f (current prime) to false
        for( int j=f+f; j<n; j+=f ) {
            if( primes.get(j) ) {
                //System.out.println( "  " +j + " " + f);
                primes.set( j, false );
                //System.out.println("remov:"+j);
            }
        }
        //System.out.println(primes.get(f));
        f++; // go to next element
        while( f<n && !primes.get(f) )
            f++;//System.out.println("notpr:"+ f++);
        if( f<n )
            return f;
        else // We've reached capacity
            return -1;
    }
    protected static boolean increaseCapacity() {
        int j = 2;
        try {
            primes.addAll( Collections.nCopies( n, true ) ); // fill new candidates w/ true
            n *= 2; // double max prime
        } catch( Exception e ) {
            return false;
        }
        while( 1 < j && j < n/2 ) {
            j = LazyPrime.sieve( j );
            //System.out.println(j);
        }
        return j > 1;
    }
    public static int next( int current ) {
        if( !LazyPrime.isInitialized() )
            LazyPrime.init();
        if( current > 1 )
            current = LazyPrime.sieve( current );
        return current;
    }
    public static boolean hasNext( int current, int max ) {
        if( !LazyPrime.isInitialized() )
            LazyPrime.init();
        int next = next( current );
        if( next > 1 && next < max )
            return true;
        else
            while( LazyPrime.n < current )
                LazyPrime.increaseCapacity();
        
        next = next( current );
        if( next > 1 && next < max )
            return true;
        else
            return false;
    }
    public static boolean isPrime( int num ) {
        LazyPrime p;
        if( num < 2 )
            return false;
        if( num == 2 )
            return true;
        if( num > LazyPrime.n ) {
            p = new LazyPrime(num+1);
            while( p.hasNext() )
                if( p.next() == num )
                    return true;
            return false;
        } // need to loop through existing primes in sieve
        return false;
    }
    // Instance methods
    public boolean hasNext() {
        if( this.j < this.max-1 ) 
            return hasNext( this.j, this.max );
        else
            return false;
    }
    public int next() {
        if( primes.get(1) ) { // If 1 is true, we haven't used 2 yet
            primes.set( 1, false ); // set 1 to false
            return 2;               // return f (2)
        }
        if( j > 1 )
            this.j = LazyPrime.sieve( this.j );
        return this.j;
    }
}