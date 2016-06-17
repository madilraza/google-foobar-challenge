// access_codes

/*
 * Access codes
 * ============
 *
 * You've discovered the evil laboratory of Dr. Boolean,
 * and you've found that the vile doctor is transforming your
 * fellow rabbit kin into terrifying rabbit-zombies! Naturally,
 * you're less-than-happy about this turn of events.
 *
 * Of course where there's a will, there's a way. Your top-notch
 * assistant, Beta Rabbit, managed to sneak in and steal some
 * access codes for Dr. Boolean's lab in the hopes that the two of
 * you could then return and rescue some of the undead rabbits.
 * Unfortunately, once an access code is used it cannot be used again.
 * Worse still, if an access code is used, then that code backwards
 * won't work either! Who wrote this security system?
 *
 * Your job is to compare a list of the access codes and find
 * the number of distinct codes, where two codes are considered to
 * be "identical" (not distinct) if they're exactly the same, or the
 * same but reversed. The access codes only contain the letters a-z,
 * are all lowercase, and have at most 10 characters. Each set of
 * access codes provided will have at most 5000 codes in them.
 *
 * For example, the access code "abc" is identical to "cba" as
 * well as "abc." The code "cba" is identical to "abc" as well
 * as "cba." The list ["abc," "cba," "bac"] has 2 distinct access
 * codes in it.
 *
 * Write a function answer(x) which takes a list of access code
 * strings, x, and returns the number of distinct access code
 * strings using this definition of identical.
 *
 * Test cases
 * ==========
 *
 * Inputs:
 *   (string list) x = ["foo", "bar", "oof", "bar"]
 * Output:
 *   (int) 2
 * Inputs:
 *   (string list) x = ["x", "y", "xy", "yy", "", "yx"]
 * Output:
 *   (int) 5
 *
 */

package com.google.challenges;

import java.util.HashMap;
import java.lang.StringBuilder;

public class Answer {

  /**
   * Receives an Array of Strings, where all are lowercase letters
   * with a maximum of 10 characters each, with a maximum total of 5000.
   * Returns a integer representing the amount of "distinct" strings.
   *
   * The "distinct" definition is that it does not count strings that are
   * equal to any other, and equal to any other reversed.
   * For example, those strings would be considered "equal":
   * "abc" == "abc"
   * "abc" == "cba"
   *
   * @param x Array of Strings codes, all lowercase letters.
   * @return Integer representing the total "distinct" codes.
   */
  public static int answer(String[] x) {

    /*
     * This hash map will have codes as keys, and the
     * reversed codes as values.
     */
    HashMap<String, String> map = new HashMap<String, String>();

    for (int i = 0; i < x.length ; i++) {

      /*
       * If the given code already exists in the map either as
       * a key or as a reversed value, skip it.
       */
      if (map.containsKey(x[i]) || map.containsValue(x[i])) continue;

      /*
       * Put the current code as a new key in the map,
       * with the value being the reversed code.
       */
      map.put( x[i], new StringBuilder(x[i]).reverse().toString() );
    }

    return map.size();
  }
}
