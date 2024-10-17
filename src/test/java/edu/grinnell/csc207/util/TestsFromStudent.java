package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * A place for you to put your own tests (beyond the shared repo). This is for fall24 csc207 class
 * 
 * @author Tiffany Yan
 */
public class TestsFromStudent {
  /**
   * A test for the get, set, height, and width methods.
   * 
   */
  @Test
  public void general() {
    Integer i0 = Integer.valueOf(0);
    Integer i1 = Integer.valueOf(1);
    Integer i2 = Integer.valueOf(2);
    Integer i3 = Integer.valueOf(3);
    Integer i4 = Integer.valueOf(4);
    Integer i5 = Integer.valueOf(5);

    // test that the constructor works properly
    Matrix<Integer> test1 = new MatrixV0<Integer>(2, 3);
    assertMatrixEquals(new Integer[][] {{null, null}, {null, null}, {null, null}}, test1,
        "2 * 3 matrix of null");

    Matrix<Integer> test2 = new MatrixV0<Integer>(2, 3, 0);
    assertMatrixEquals(new Integer[][] {{0, 0}, {0, 0}, {0, 0}}, test2, "2 * 3 matrix of 0");

    // test for the set method
    test1.set(0, 0, i0);
    test1.set(1, 1, i4);
    assertMatrixEquals(new Integer[][] {{0, null}, {null, 4}, {null, null}}, test1,
        "check for the functionality of set()");

    // test for the get method
    assertEquals(i4, test1.get(1, 1), "checks for the functionality of get()");

    // test for the height method
    assertEquals(3, test2.height(), "Test for the functionality of height()");

    // test for the width method
    assertEquals(2, test2.width(), "Test for the functionality of width()");

  } // general()

  /*
   * A test for the insert functions, including inserting Row and Col
   */
  @Test
  public void insert() {
    Integer i0 = Integer.valueOf(0);
    Integer i1 = Integer.valueOf(1);
    Integer i2 = Integer.valueOf(2);
    Integer i3 = Integer.valueOf(3);
    Integer i4 = Integer.valueOf(4);
    Integer i5 = Integer.valueOf(5);

    // test that the constructor works properly
    Matrix<Integer> test1 = new MatrixV0<Integer>(2, 3);
    assertMatrixEquals(new Integer[][] {{null, null}, {null, null}, {null, null}}, test1,
        "2 * 3 matrix of null");
    Matrix<Integer> test2 = new MatrixV0<Integer>(2, 3, 0);
    assertMatrixEquals(new Integer[][] {{0, 0}, {0, 0}, {0, 0}}, test2, "2 * 3 matrix of 0");


    // Test for insertRow
    test1.insertRow(1);
    assertMatrixEquals(new Integer[][] {{null, null}, {null, null}, {null, null}, {null, null}},
        test1, "new matrix after new col being inserted");

    // Test for insertCol
    test1.insertCol(1);
    assertMatrixEquals(new Integer[][] {{null, null, null}, {null, null, null}, {null, null, null},
        {null, null, null}}, test1, "new matrix after new col being inserted");
  } //insert()

  /*
   * Test cases for testing the delet methods
   */
  @Test
  public void delet() {

    // test that the constructor works properly
    Matrix<Integer> test1 = new MatrixV0<Integer>(2, 3);
    assertMatrixEquals(new Integer[][] {{null, null}, {null, null}, {null, null}}, test1,
        "2 * 3 matrix of null");

    // Test for deleteRow
    test1.deleteRow(1);
    assertMatrixEquals(new Integer[][] {{null, null}, {null, null}}, test1,
        "new matrix after one row deleted");

    // Test for deleteCol
    test1.deleteCol(1);
    assertMatrixEquals(new Integer[][] {{null}, {null}}, test1, "new matrix after one col deleted");
  } //delet()

  /*
   * Test for fillRegion and Filline
   */
  @Test
  public void Fill() {

    Matrix<String> strings = new MatrixV0(4, 2, " ");
    assertMatrixEquals(new String[][] {{" ", " ", " ", " "}, {" ", " ", " ", " "}}, strings,
        "original matrix");

    strings.fillRegion(0, 0, 2, 4, "T");
    assertMatrixEquals(new String[][] {{"T", "T", "T", "T"}, {"T", "T", "T", "T"}}, strings,
        "filling the whole matrix");

    strings.fillLine(0, 0, 1, 1, 2, 2, "c");
    assertMatrixEquals(new String[][] {{"c", "T", "T", "T"}, {"T", "c", "T", "T"}}, strings,
        "After inserting diagonal line");
  } //Fill()

  /*
   * Test for Equals() and clone()
   */
  @Test
  public void Eqv() {
    Matrix<String> matrix0 = new MatrixV0<String>(4, 3, "X");

    Matrix<String> matrix1 = new MatrixV0<String>(3, 3, "X");
    assertEquals(false, matrix0.equals(matrix1), "test for the equals method when false");
    matrix1.insertCol(1);

    Matrix<String> matrix2 = matrix1.clone();
    // Tests for clone
    assertEquals(true, matrix2.equals(matrix1), "test for the equals method when true and cloned");

    assertEquals(true, matrix0.equals(matrix1), "test for the equals method when true");
  } // Eqv()
} // class TestsFromStudent
