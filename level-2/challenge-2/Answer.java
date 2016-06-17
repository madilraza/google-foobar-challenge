// peculiar_balance

/*
 * Peculiar balance
 *
 * Can we save them? Beta Rabbit is trying to break into a lab
 * that contains the only known zombie cure - but there's
 * an obstacle. The door will only open if a challenge is
 * solved correctly. The future of the zombified rabbit
 * population is at stake, so Beta reads the challenge:
 * There is a scale with an object on the left-hand side,
 * whose mass is given in some number of units. Predictably,
 * the task is to balance the two sides. But there is a catch:
 * You only have this peculiar weight set, having masses
 * 1, 3, 9, 27, ... units. That is, one for each power of 3.
 * Being a brilliant mathematician, Beta Rabbit quickly discovers
 * that any number of units of mass can be balanced exactly using
 * this set.
 *
 * To help Beta get into the room, write a method called answer(x),
 * which outputs a list of strings representing where the weights
 * should be placed, in order for the two sides to be balanced,
 * assuming that weight on the left has mass x units.
 *
 * The first element of the output list should correspond to
 * the 1-unit weight, the second element to the 3-unit weight,
 * and so on. Each string is one of:
 *
 *   "L" : put weight on left-hand side
 *   "R" : put weight on right-hand side
 *   "-" : do not use weight
 *
 * To ensure that the output is the smallest possible,
 * the last element of the list must not be "-".
 *
 * x will always be a positive integer, no larger than 1000000000.
 * Test cases
 *
 * Inputs: (int) x = 2
 * Output: (string list) ["L", "R"]
 *
 * Inputs: (int) x = 8
 * Output: (string list) ["L", "-", "R"]
 */

package com.google.challenges;

import java.util.List;
import java.util.ArrayList;

public class Answer {

  /**
   * Takes an integer "x" that represents the weight of an object
   * in the left side of a balance. Returns an Array of Strings,
   * representing where weights should be placed, in order for the two
   * sides to be balanced.
   *
   * @param x The weight of the object on the left side of the balance.
   * @return List of where each weight should be placed.
   */
  public static String[] answer(int x) {

    /*
     * This problem can be solved by converting the decimal number x to
     * balanced ternary, but using "LR-" instead of "-+0", respectively.
     */

    /*
     * If the initial weight is zero,
     * return an empty array.
     */
    if (x == 0) {
      String[] a = {};
      return a;
    }

    List<String> ternary = new ArrayList<String>();

    /*
     * Keep dividing the number by 3 until it's zero.
     * The remainder of the division by 3 will be our
     * balanced ternary for each iteration.
     */
    while (x > 0) {

      switch(x%3) {
        case 2:
          ternary.add("L");
          break;
        case 1:
          ternary.add("R");
          break;
        case 0:
          ternary.add("-");
          break;
      }

      /*
       * Adding 1 to x before diving is necessary for the
       * next remainder to be correct.
       */
      x = (x+1)/3;
    }

    return ternary.toArray(new String[ternary.size()]);
  }

}
