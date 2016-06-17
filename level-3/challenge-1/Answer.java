// save_beta_rabbit

/*
 * Save Beta Rabbit
 * ================
 *
 * Oh no! The mad Professor Boolean has trapped Beta Rabbit in an
 * NxN grid of rooms. In the center of each room (except for
 * the top left room) is a hungry zombie. In order to be freed,
 * and to avoid being eaten, Beta Rabbit must move through this
 * grid and feed the zombies.
 *
 * Beta Rabbit starts at the top left room of the grid. For each
 * room in the grid, there is a door to the room above, below,
 * left, and right. There is no door in cases where there is no
 * room in that direction. However, the doors are locked in such
 * a way that Beta Rabbit can only ever move to the room below
 * or to the right. Once Beta Rabbit enters a room, the zombie
 * immediately starts crawling towards him, and he must feed the
 * zombie until it is full to ward it off. Thankfully, Beta
 * Rabbit took a class about zombies and knows how many units of
 * food each zombie needs be full.
 *
 * To be freed, Beta Rabbit needs to make his way to the bottom
 * right room (which also has a hungry zombie) and have used most
 * of the limited food he has. He decides to take the path through
 * the grid such that he ends up with as little food as possible at
 * the end.
 *
 * Write a function answer(food, grid) that returns the number
 * of units of food Beta Rabbit will have at the end, given that
 * he takes a route using up as much food as possible without him
 * being eaten, and ends at the bottom right room. If there does
 * not exist a route in which Beta Rabbit will not be eaten, then
 * return -1.
 *
 * food is the amount of food Beta Rabbit starts with, and will
 * be a positive integer no larger than 200.
 *
 * grid will be a list of N elements. Each element of grid will
 * itself be a list of N integers each, denoting a single row
 * of N rooms. The first element of grid will be the list
 * denoting the top row, the second element will be the list
 * denoting second row from the top, and so on until the last
 * element, which is the list denoting the bottom row. In the
 * list denoting a single row, the first element will be the
 * amount of food the zombie in the left-most room in that row
 * needs, the second element will be the amount the zombie in
 * the room to its immediate right needs and so on. The top
 * left room will always contain the integer 0, to indicate
 * that there is no zombie there
 *
 * The number of rows N will not exceed 20, and the amount
 * of food each zombie requires will be a positive integer
 * not exceeding 10.
 *
 * Test cases
 * ==========
 *
 * Inputs:
 *   (int) food = 7
 *   (int) grid = [[0, 2, 5], [1, 1, 3], [2, 1, 1]]
 * Output:
 *   (int) 0
 *
 * Inputs:
 *  (int) food = 12
 *  (int) grid = [[0, 2, 5], [1, 1, 3], [2, 1, 1]]
 * Output:
 *  (int) 1
 */

package com.google.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Answer {

  static List<List<Set<Integer>>> foodNeededMap;
  static int[][] map;
  static int mapSize;

  /**
   * Takes a "food" amount and a "grid" map filled with zombies.
   * Returns the smallest possible food remaining by traveling
   * to the bottom right of the grid, feeding the zombies in
   * the way. Return -1 if it's not possible to get to the end
   * without running out of food in the middle of the map and
   * get mauled to death by a zombie.
   *
   * Takes in consideration that it's only possible to move
   * to the left or bottom of each map cell.
   *
   * @param food The initial food amount.
   * @param grid The 2D, square map.
   * @return The remaining food amount, or -1 (RIP Beta Rabbit).
   */
  public static int answer(int food, int[][] grid) {

    /*
     * Considerations:
     *
     * Traditional methods won't work since there's a time
     * constraint. Thus memoization is required.
     *
     *
     * Procedure:
     *
     * Create a "food needed" map, with the same dimensions
     * as the grid, and store in each cell all possible combinations
     * of food needed to get there. To achieve this, each cell will
     * receive the equivalent food requirements from the grid, and
     * it will be added to every previously calculated requirements
     * from the top and right cells.
     *
     * The calculated requirements of each cell will be stored
     * inside a Set of integers. This will prevent duplicate values
     * from appearing.
     *
     * The very first cell of the food map will have a value of zero.
     * Each cell from the first row will only add the food
     * requirements from the left cells. Same for the first column,
     * but only the top cells. Treat those cases separately, as
     * to avoid constant checking if there is a cell above or to the
     * left when calculating food requirements.
     */

    setGlobals(grid);
    setFoodNeededMap();
    return getRemainingFood(food);
  }

  /**
   * Set some global variables, so there's no need to juggle
   * them around in function parameters.
   *
   * @param grid The given 2D square map, filled with zombies.
   */
  static void setGlobals(int[][] grid) {
    map = grid;
    mapSize = grid.length;
    foodNeededMap = new ArrayList<>();
  }

  /**
   * Creates the food map.
   */
  static void setFoodNeededMap() {
    setFirstCell();
    setFirstRow();
    setFirstColumn();
    setRemainingCells();
  }

  /**
   * Set the top left cell of the food map to zero.
   */
  static void setFirstCell() {

    List<Set<Integer>> foodNeededRow;
    Set<Integer> foodNeededCell;

    foodNeededRow = new ArrayList<>();
    foodNeededCell = new HashSet<>();

    foodNeededCell.add(0);
    foodNeededRow.add(foodNeededCell);
    foodNeededMap.add(foodNeededRow);
  }

  /**
   * Set the first row of the food map,
   * only adding to the current cell the left cell value.
   */
  static void setFirstRow() {

    List<Set<Integer>> foodNeededRow;
    Set<Integer> foodNeededCell;
    int col, thisCellFood;

    foodNeededRow = foodNeededMap.get(0);

    for (col = 1; col < mapSize; col++) {

      foodNeededCell = new HashSet<>();
      thisCellFood = map[0][col];

      for (int previous : foodNeededRow.get(col - 1))
        foodNeededCell.add(previous + thisCellFood);

      foodNeededRow.add(foodNeededCell);
    }
  }

  /**
   * Set the first column of the food map,
   * only adding to the current cell the top cell value.
   */
  static void setFirstColumn() {

    List<Set<Integer>> foodNeededRow;
    Set<Integer> foodNeededCell;
    int row, thisCellFood;

    for (row = 1; row < mapSize; row++) {

      foodNeededRow = new ArrayList<>();
      foodNeededCell = new HashSet<>();
      thisCellFood = map[row][0];

      for (int previous : foodNeededMap.get(row - 1).get(0))
        foodNeededCell.add(previous + thisCellFood);

      foodNeededRow.add(foodNeededCell);
      foodNeededMap.add(foodNeededRow);
    }
  }

  /**
   * Set the remaining cells of the food map,
   * starting at row and column two.
   */
  static void setRemainingCells() {

    List<Set<Integer>> foodNeededRow;
    int row;

    for (row = 1; row < mapSize; row++) {
      foodNeededRow = foodNeededMap.get(row);
      setRowCells(foodNeededRow,row);
    }
  }

  /**
   * Set the cells of a row, starting from column two.
   *
   * @param foodNeededRow The row to be set.
   * @param row The row position.
   */
  static void setRowCells(
      List<Set<Integer>> foodNeededRow, int row) {

    Set<Integer> foodNeededCell;
    int col, thisCellFood;

    for (col = 1; col < mapSize; col++) {

      foodNeededCell = new HashSet<>();
      thisCellFood = map[row][col];

      for (int leftCellFood: foodNeededMap.get(row).get(col-1) )
        foodNeededCell.add(leftCellFood + thisCellFood);

      for (int topCellFood: foodNeededMap.get(row-1).get(col) )
        foodNeededCell.add(topCellFood + thisCellFood);

      foodNeededRow.add(foodNeededCell);
    }
  }

  /**
   * Returns the smallest possible remaining food after traveling
   * the grid, or -1 if Beta Rabbit is dead.
   *
   * @param food The initial food amount.
   * @return The remaining food, or a dead rabbit (-1).
   */
  static int getRemainingFood(int food) {

    List<Integer> foodNeededValues;
    int foodNeeded, amount;

    foodNeededValues = getLastCellValuesSorted();
    amount = foodNeededValues.size() - 1;

    /*
     * The values are sorted in ascending order,
     * so start iterating in reverse from the last value.
     * This way, the first value that is smaller or equal
     * to the initial food amount is the answer.
     */
    for (int i = amount; i >= 0; i--) {
      foodNeeded = foodNeededValues.get(i);
      if (foodNeeded <= food)
        return (food - foodNeeded);
    }

    // Beta Rabbit never made it...
    return -1;
  }

  /**
   * @return The bottom right cell food values from the food map,
   * sorted in ascending order.
   */
  static List<Integer> getLastCellValuesSorted() {

    Set<Integer> foodNeededLastCell;
    List<Integer> foodNeededList;

    foodNeededLastCell = foodNeededMap.get(mapSize - 1).get(mapSize - 1);
    foodNeededList = new ArrayList<>(foodNeededLastCell);
    Collections.sort(foodNeededList);

    return foodNeededList;
  }
}
