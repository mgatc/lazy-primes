LazyPrime is a class that stores a list of prime numbers in a static data member.

Each instance of LazyPrime will get its primes from the static list, in ascending order.

When an instance reaches the end of the list, the list is extended.

The static nature of the prime number list ensures that duplicates are not generated for programs that require multiple instances of sequential primes.