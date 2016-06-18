// spy_snippets

/*
 * Spy snippets
 * ============
 *
 * You've been recruited by the team building Spy4Rabbits, a highly
 * advanced search engine used to help fellow agents discover files
 * and intel needed to continue the operations against Dr. Boolean's
 * evil experiments. The team is known for recruiting only the brightest
 * rabbit engineers, so there's no surprise they brought you on board.
 * While you're elbow deep in some important encryption algorithm,
 * a high-ranking rabbit official requests a nice aesthetic feature
 * for the tool called "Snippet Search." While you really wanted to tell
 * him how such a feature is a waste of time in this intense, fast-paced
 * spy organization, you also wouldn't mind getting kudos from a leader.
 * How hard could it be, anyway?
 *
 * When someone makes a search, Spy4Rabbits shows the title of the page.
 * Your commander would also like it to show a short snippet of the page
 * containing the terms that were searched for.
 *
 * Write a function called answer(document, terms) which returns
 * the shortest snippet of the document, containing all of the given
 * search terms. The search terms can appear in any order.
 *
 * The length of a snippet is the number of words in the snippet.
 * For example, the length of the snippet "tastiest color of carrot" is 4.
 * (Who doesn't like a delicious snack!)
 *
 * The document will be a string consisting only of lower-case letters
 * [a-z] and spaces. Words in the string will be separated by a single
 * space. A word could appear multiple times in the document.
 *
 * terms will be a list of words, each word comprised only of
 * lower-case letters [a-z]. All the search terms will be distinct.
 *
 * Search terms must match words exactly, so "hop" does not match "hopping".
 *
 * Return the first sub-string if multiple sub-strings are shortest.
 * For example, if the document is "world there hello hello where world"
 * and the search terms are ["hello", "world"], you must return
 * "world there hello".
 *
 * The document will be guaranteed to contain all the search terms.
 *
 * The number of words in the document will be at least one, will not
 * exceed 500, and each word will be 1 to 10 letters long. Repeat words
 * in the document are considered distinct for counting purposes.
 *
 * The number of words in terms will be at least one, will not
 * exceed 100, and each word will not be more than 10 letters long.
 *
 * Test cases
 * ==========
 *
 * Inputs:
 *   (string) document = "many google employees can program"
 *   (string list) terms = ["google", "program"]
 * Output:
 *   (string) "google employees can program"
 *
 * Inputs:
 *   (string) document = "a b c d a"
 *   (string list) terms = ["a", "c", "d"]
 * Output:
 *   (string) "c d a"
 */

package com.google.challenges;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class Answer {

  /**
   * Represents the start and end of a slice that
   * contains all search terms.
   */
  static class Slice {

    public int start, end, size;

    /**
     * Takes the start and end positions of a slice. The
     * end position should be greater than the start position.
     * @param s The start position.
     * @param e The end position.
     */
    public Slice(int s, int e) {
      start = s;
      end = e;
      size = end - start;
    }
  }

  /**
   * Receives a "document" string with only lowercase words, separated by
   * spaces, and a "search terms" string array, where each string is
   * a unique word, in lower case letters.
   *
   * Returns the smallest slice (snippet) of the "document" string
   * that contains all search terms. If there are several smallest slices,
   * returns the first one. The search terms may appear in any order.
   * The slice can have repeated search terms.
   *
   * @param document The document to search the terms.
   * @param terms The terms to be searched in the document.
   * @return Smallest slice of the document containing all search terms.
   */
  public static String answer(String document, String[] searchTerms) {

    HashMap<Integer, String> termsPositions;
    HashSet<String> termsSet;
    ArrayList<Integer> positions;
    ArrayList<Slice> sliceCandidates;
    Comparator<Slice> smallestSlice;
    Slice slice;
    StringBuilder builder;
    String[] words;
    String term;
    int last;

    /*
     * If there's only one search term,
     * the snippet will be the term itself.
     */
    if (searchTerms.length == 1)
      return searchTerms[0];

    // Split the document in words for easier comparisons
    words = document.split(" ");

    /*
     * If the amount of words in the document is equal to the amount
     * of search terms, the snippet will be the document itself.
     */
    if (words.length == searchTerms.length)
      return document;

    termsPositions  = new HashMap<Integer, String>();
    positions       = new ArrayList<Integer>();
    sliceCandidates = new ArrayList<Slice>();
    termsSet        = new HashSet<String>();
    Collections.addAll(termsSet, searchTerms);

    /*
     * Save all terms positions of the document in a list, and save
     * every individual term position in a hash map.
     */
    for (int i = 0; i < words.length; i++) {
      if (termsSet.contains(words[i])) {
        positions.add(i);
        termsPositions.put(i,words[i]);
      }
    }

    termsSet.clear();
    last = 0;

    // While there's still more positions than search terms
    while (positions.size() >= searchTerms.length) {

      // For each position
      for (int position : positions) {
        /*
         * Add the term of that position to a term Set. It will
         * not contain duplicate terms.
         */
        term = termsPositions.get(position);
        termsSet.add(term);
        last = position;
        /*
         * When the term Set size is equal to the search terms amount,
         * we have found a slice. Save the slice start as the first
         * term position of the list, and the slice end as the last found
         * term position that completed the slice.
         */
        if (termsSet.size() == searchTerms.length) {
          slice = new Slice(positions.get(0),last);
          sliceCandidates.add(slice);
          break;
        }
      }

      /*
       * Just found a slice, so restart the slice search, but remove the
       * first position. This is necessary since slices can overlap.
       */
      termsSet.clear();
      positions.remove(0);
    }

    /*
     * Create a custom comparator that compares Slice objects
     * by their "size" property.
     */
    smallestSlice = new Comparator<Slice>() {
      public int compare(Slice s1, Slice s2) {
        return Integer.valueOf(s1.size).compareTo(s2.size);
      }
    };

    // Sort all found slices by size, and retrieve the smallest one.
    Collections.sort(sliceCandidates,smallestSlice);
    slice = sliceCandidates.get(0);

    /*
     * Using the slice start and end positions, build a string from the
     * words array. This string will be the snippet.
     */
    builder = new StringBuilder();
    builder.append(words[slice.start]);

    for (int i = slice.start + 1; i <= slice.end; i++) {
      builder.append(" ");
      builder.append(words[i]);
    }

    return builder.toString();
  }
}
