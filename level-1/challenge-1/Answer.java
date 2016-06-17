// origins_and_order

/*
 * Origins and order
 * =================
 *
 * What do we know about Professor Boolean's past? It's mostly
 * rumor and conjecture, but a few things are known to be true.
 *
 * Mad Professor Boolean wasn't always a super villain. Early
 * in his career, he was an average paper pusher, working in
 * an office with some very backwards technology. One of his
 * primary jobs was to carry date cards between departments.
 * One morning, he tripped over a unicycle and dropped his
 * date cards on the floor. He hit his head - and hit upon
 * the idea of breeding an army of zombie rabbits to do his
 * bidding and manage simple tasks. But that comes later.
 * Before he could quit with an explosive YouTube video,
 * the professor had to get his cards back in order.
 *
 * Aha! It seems he recorded the details of this life-changing
 * event in his diary. Let's try to reproduce his methods:
 *
 * The goal is to get the date cards back in order. Each set of
 * date cards consists of 3 cards, each with a number written on
 * it. When arranged in some order, the numbers make up the
 * representation of a date, in the form month/day/year. However,
 * sometimes multiple representations will be possible. For
 * example, if the date cards read 1, 1, 99 it could only mean
 * 01/01/99, but if the date cards read 2, 30, 3, it could mean
 * any one of 02/03/30, 03/02/30, or 03/30/02.
 *
 * Write a function called answer(x, y, z) that takes as input
 * the 3 numbers on the date cards. You may assume that at least
 * one valid representation of a date can be constructed from the
 * cards.
 *
 * If there is only one valid representation, the function should
 * return it as a string, in the form MM/DD/YY. If there are
 * multiple valid representations, the function should return
 * the string "Ambiguous." Each of x, y, z will be between 1 to 99
 * inclusive. You may also assume that there are no leap years.
 *
 * Test cases
 * ==========
 *
 * Inputs:
 *   (int) x = 19
 *   (int) y = 19
 *   (int) z = 3
 * Output:
 *   (string) "03/19/19"
 *
 * Inputs:
 *   (int) x = 2
 *   (int) y = 30
 *   (int) z = 3
 * Output:
 *   (string) "Ambiguous"
 */

package com.google.challenges;

import java.util.Arrays;

public class Answer {

  /**
   * Receives 3 integers that can form a valid "mm/dd/yy" date.
   * each one can be from 1 to 99 inclusive. Returns a string with the
   * "mm/dd/yy" date if there is only one valid arrangement of the numbers,
   * otherwise returns "Ambiguous".
   *
   * @param x An integer ranging from 1 to 99 inclusive.
   * @param y An integer ranging from 1 to 99 inclusive.
   * @param z An integer ranging from 1 to 99 inclusive.
   * @return Date string in "mm/dd/yy" format, or "Ambiguous".
   */
  public static String answer(int x, int y, int z) {

    String mm, dd, yy, fail;
    int month, day, year;

    fail = "Ambiguous";

    /*
     * It is assumed that the 3 numbers will always be able to
     * form at least one valid date.
     *
     * The smallest number will be the month (maximum of 12).
     * The second smallest will be the day
     * (maximum of 28~31, depending on month).
     * The largest will be the year (maximum of 99).
     *
     * By storing the integers in an array and sorting it,
     * we will have a valid arrangement of numbers,
     * in order of month, day and year (mm/dd/yy).
     */
    int dateArr[] = {x, y, z};
    Arrays.sort(dateArr);

    month = dateArr[0];
    day   = dateArr[1];
    year  = dateArr[2];

    /*
     * First, we check if the "day" integer is smaller than 13.
     * If it is, it could also be a month, so the date is ambiguous.
     *
     * But if both "day" and "month" are equal, there's
     * no ambiguity.
     */
    if (day < 13 && month != day) {
      return fail;
    }

    /*
     * Next, we must check if the "year" integer is smaller than
     * the maximum number of days in the "month" integer.
     *
     * For that we must first know how many days each month has.
     * It is assumed that there are no leap years.
     */
    int daysInMonth[] = {
      31, // January
      28, // February (common year)
      31, // March
      30, // April
      31, // May
      30, // June
      31, // July
      31, // August
      30, // September
      31, // October
      30, // November
      31  // December
    };

    /*
     * If the "year" integer is equal or less than the number
     * of the days, then it's ambiguous, since the year could
     * also be a day.
     *
     * But if both "day" and "year" are equal, there's
     * no ambiguity.
     */
    if (year <= daysInMonth[month - 1] && day != year) {
      return fail;
    }

    /*
     * Finally, if the date isn't ambiguous, return it in
     * "mm/dd/yy" format.
     */
    mm = (month < 10) ? ("0" + month) : ("" + month);
    dd = (day < 10)   ? ("0" + day)   : ("" + day);
    yy = (year < 10)  ? ("0" + year)  : ("" + year);

    return (mm + "/" + dd + "/" + yy);
  }
}
