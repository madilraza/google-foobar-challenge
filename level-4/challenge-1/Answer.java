// undercover_underground

/*
 * Undercover underground
 * ======================
 *
 * As you help the rabbits establish more and more resistance groups to fight
 * against Professor Boolean, you need a way to pass messages back and forth.
 *
 * Luckily there are abandoned tunnels between the warrens of the rabbits, and
 * you need to find the best way to use them. In some cases, Beta Rabbit wants
 * a high level of interconnectedness, especially when the groups show their
 * loyalty and worthiness. In other scenarios the groups should be less
 * intertwined, in case any are compromised by enemy agents or zombits.
 *
 * Every warren must be connected to every other warren somehow, and no two
 * warrens should ever have more than one tunnel between them. Your assignment:
 * count the number of ways to connect the resistance warrens.
 *
 * For example, with 3 warrens (denoted A, B, C) and 2 tunnels, there are three
 * distinct ways to connect them:
 *
 * A-B-C
 * A-C-B
 * C-A-B
 *
 * With 4 warrens and 6 tunnels, the only way to connect them is to connect
 * each warren to every other warren.
 *
 * Write a function answer(N, K) which returns the number of ways to connect N
 * distinctly labeled warrens with exactly K tunnels, so that there is a path
 * between any two warrens.
 *
 * The return value must be a string representation of the total number of ways
 * to do so, in base 10.
 * N will be at least 2 and at most 20.
 * K will be at least one less than N and at most (N * (N - 1)) / 2
 *
 *
 * Test cases
 * ==========
 *
 * Inputs:
 *   (int) N = 2
 *   (int) K = 1
 * Output:
 *   (string) "1"
 *
 * Inputs:
 *   (int) N = 4
 *   (int) K = 3
 * Output:
 *   (string) "16"
 */

package com.google.challenges;

import java.util.ArrayList;
import java.util.HashMap;

public class Answer {

  // Globals
  static HashMap<Tuple, Integer> binomialCache;
  static HashMap<Integer, Integer> factorialCache, mathMagicCache;

  // Static initializer
  static {

    binomialCache  = new HashMap<Tuple, Integer>();
    factorialCache = new HashMap<Integer, Integer>();
    mathMagicCache = new HashMap<Integer, Integer>();

    // Stores '1' as the value of '0!' (factorial of zero)
    factorialCache.put(0,1);
  }

  // Simple tuple implementation
  // TODO override equals
  static class Tuple {

    public int num1, num2;

    public Tuple (int a, int b) {
      num1 = a;
      num2 = b;
    }
  }

  /**
   * Takes a number of "warrens" (vertices) and "tunnels" (edges) and returns
   * the number of possible "ways" (simple connected graphs) that can
   * be made with those.
   *
   * @param N The number of vertices.
   * @param K The number of edges.
   * @return The number of graphs.
   */
  public static String answer(int N, int K) {

    // Returns the number of simple connected graphs, in string format.
    return Integer.toString(mathMagic(N, K));
  }

  /**
   * Returns the number of distinct, connected, labeled, undirected
   * graphs that can be formed using 'n' vertices and 'k' edges.
   *
   * @param n The number of vertices.
   * @param k The number of edges.
   * @return The number of graphs.
   */
  static int mathMagic(int n, int k) {
    // TODO
    int res, s, res1, lb, np, i, j;

    res = binomial(s, k);
    s = n * (n - 1) / 2;

    for (i = 0; i < (n - 1); i++) {

      res1 = 0;
      lb = Math.max(0, k - (i + 1) * i / 2);

      for (j = 0; j < (k - i + 1); j++) {

        np = (n - 1 - i) * (n - 2 - i) / 2;
        res1 += binomial(np, j) * mathMagic(i + 1, k - j);
      }
    }
  }

  /**
   * Calculates the binomial coefficient for n and k, using factorial
   * representation and memoization. This is equivalent to "n choose k".
   *
   * @param n Left term.
   * @param k Right term.
   * @return The binomial coefficient "n choose k".
   */
  static int binomial(int n, int k) {

    Tuple tuple;
    int binomial, diff;

    tuple = new Tuple(n, k);

    if (binomialCache.containsKey(tuple))
      return binomialCache.get(tuple);

    diff = n - k;

    if (diff < 0)
      return 0;

    binomial = factorial(n) / factorial(k) / factorial(diff);
    binomialCache.put(tuple, binomial);

    return binomial;
  }

  /**
   * Calculates the factorial of a positive number, using memoization.
   * Assumes the cache already has the value for 0! (factorial of zero).
   *
   * @param num A number.
   * @return The factorial.
   */
  static int factorial(int num) {

    int factorial;

    if (factorialCache.containsKey(num))
      return factorialCache.get(num);

    factorial = num * factorial(num - 1);
    factorialCache.put(num, factorial);

    return factorial;
  }
}