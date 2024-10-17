package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * This is for the CSC207 class of fall 24.
 *
 * @author Tiffany Yan
 * @author Samuel A. Rebelsky
 *
 * @param <T> The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * The width of 2D array.
   */
  private int width;

  /**
   * The height of 2D array.
   */
  private int height;

  /**
   * The defacult value of the 2D array.
   */
  private T def;

  /**
   * The contents of the 2D array.
   */
  private T[] contents;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the given value as the default.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   * @param def The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } // if
    this.width = width;
    this.height = height;
    this.def = def;
    this.contents = (T[]) new Object[height * width];
    for (int i = 0; i < this.contents.length; i++) {
      this.contents[i] = def;
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with null as the default value.
   *
   * @param width The width of the matrix.
   * @param height The height of the matrix.
   *
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height) throws NegativeArraySizeException {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } // if
    this.width = width;
    this.height = height;
    this.def = null;
    this.contents = (T[]) new Object[height * width];
    for (int i = 0; i < this.contents.length; i++) {
      this.contents[i] = null;
    } // for
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    if (row >= this.height || col >= this.width || row < 0 || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if
    int location = this.width * row + col;
    return this.contents[location];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   * @param val The value to set.
   *
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row >= this.height || col >= this.width || row < 0 || col < 0) {
      throw new IndexOutOfBoundsException();
    } // if
    int location = this.width * row + col;
    this.contents[location] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException();
    } // if

    T[] newrecord = (T[]) new Object[(1 + this.height) * this.width];

    int location = row * this.width;

    for (int i = 0; i < location; i++) {
      newrecord[i] = this.contents[i];
    } // for

    for (int i = location; i < location + this.width; i++) {
      newrecord[i] = this.def;
    } // for

    int oldloc = location;

    for (int i = location + this.width; i < newrecord.length; i++) {
      newrecord[i] = this.contents[oldloc++];
    } // for
    this.height++;
    this.contents = newrecord;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row The number of the row to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   * @throws ArraySizeException If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row < 0 || row > this.height) {
      throw new IndexOutOfBoundsException();
    } // if
    if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if

    T[] newrecord = (T[]) new Object[(1 + this.height) * this.width];

    int location = row * this.width;

    for (int i = 0; i < location; i++) {
      newrecord[i] = this.contents[i];
    } // for


    int oldloc = 0;
    for (int i = location; i < location + this.width; i++) {
      newrecord[i] = vals[oldloc++];
    } // for

    oldloc = location;

    for (int i = location + this.width; i < newrecord.length; i++) {
      newrecord[i] = this.contents[oldloc++];
    } // for

    this.height++;
    this.contents = newrecord;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException();
    } // if

    T[] newrecord = (T[]) new Object[this.height * (this.width + 1)];
    for (int i = 0; i < this.height; i++) {
      for (int z = 0; z < this.width + 1; z++) {
        if (z < col) {
          newrecord[i * (1 + this.width) + z] = this.contents[i * this.width + z];
        } else if (z == col) {
          newrecord[i * (1 + this.width) + z] = this.def;
        } else {
          newrecord[i * (1 + this.width) + z] = this.contents[i * this.width + z - 1];
        } // if
      } // for
    } // for
    this.width++;
    this.contents = newrecord;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col The number of the column to insert.
   * @param vals The values to insert.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   * @throws ArraySizeException If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col < 0 || col > this.width) {
      throw new IndexOutOfBoundsException();
    } // if

    if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if

    T[] newrecord = (T[]) new Object[this.height * (this.width + 1)];

    int count = 0;
    for (int i = 0; i < this.height; i++) {
      for (int z = 0; z < this.width + 1; z++) {
        if (z < col) {
          newrecord[i * (1 + this.width) + z] = this.contents[i * this.width + z];
        } else if (z == col) {
          newrecord[i * (1 + this.width) + z] = vals[count++];
        } else {
          newrecord[i * (1 + this.width) + z] = this.contents[i * this.width + z - 1];
        } // if
      } // for
    } // for
    this.width++;
    this.contents = newrecord;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException If the row is negative or greater than or equal to the
   *         height.
   */
  public void deleteRow(int row) {
    if (row < 0 || row >= this.height) {
      throw new IndexOutOfBoundsException();
    } // if

    T[] newrecord = (T[]) new Object[(this.height - 1) * this.width];

    int location = row * this.width;

    int oldloc = location;

    for (int i = 0; i < location; i++) {
      newrecord[i] = this.contents[i];
    } // for

    oldloc += this.width;
    for (int i = location; i < newrecord.length; i++) {
      newrecord[i] = this.contents[oldloc++];
    } // for

    this.height--;
    this.contents = newrecord;

  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException If the column is negative or greater than or equal to the
   *         width.
   */
  public void deleteCol(int col) {
    if (col < 0 || col >= width) {
      throw new IndexOutOfBoundsException();
    } // if

    T[] newrecord = (T[]) new Object[this.height * (this.width - 1)];

    for (int i = 0; i < this.height; i++) {
      for (int z = 0; z < this.width - 1; z++) {
        if (z < col) {
          newrecord[i * (this.width - 1) + z] = this.contents[i * this.width + z];
        } else {
          newrecord[i * (this.width - 1) + z] = this.contents[i * this.width + z + 1];
        } // if
      } // for
    } // for
    this.width--;
    this.contents = newrecord;

  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow The top edge / row to start with (inclusive).
   * @param startCol The left edge / column to start with (inclusive).
   * @param endRow The bottom edge / row to stop with (exclusive).
   * @param endCol The right edge / column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol, T val) {
    if (endRow < startRow || startRow < 0 || endRow > this.height || endCol < startCol
        || startCol < 0 || endCol > this.width) {
      throw new IndexOutOfBoundsException();
    } // if
    for (int i = startRow; i < endRow; i++) {
      for (int z = startCol; z < endCol; z++) {
        this.set(i, z, val);
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow The row to start with (inclusive).
   * @param startCol The column to start with (inclusive).
   * @param deltaRow How much to change the row in each step.
   * @param deltaCol How much to change the column in each step.
   * @param endRow The row to stop with (exclusive).
   * @param endCol The column to stop with (exclusive).
   * @param val The value to store.
   *
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol, int endRow,
      int endCol, T val) {
    if (endRow < startRow || startRow < 0 || endRow > this.height || endCol < startCol
        || startCol < 0 || endCol > this.width) {
      throw new IndexOutOfBoundsException();
    } // if

    while (startRow != endRow && startCol != endCol) {
      set(startRow, startCol, val);
      startRow += deltaRow;
      startCol += deltaCol;
    } // while

  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual elements are mutable,
   * mutating them in one matrix may affect the other matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> cloned = new MatrixV0<>(this.width, this.height, this.def);
    System.arraycopy(this.contents, 0, cloned.contents, 0, this.contents.length);
    return cloned;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other The object to compare.
   *
   * @return true if the other object is a matrix with the same width, height, and equal elements;
   *         false otherwise.
   */

  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      Matrix otherMatrix = (Matrix) other;
      if (!(this.width == (otherMatrix.width()) && this.height == (otherMatrix.height()))) {
        return false;
      } // if
      for (int i = 0; i < this.height; i++) {
        for (int z = 0; z < this.width; z++) {
          if (!(this.get(i, z).equals(otherMatrix.get(i, z)))) {
            return false;
          } // if
        } // for
      } //for
    } else {
      // If it's not a matrix, it's not equal.
      return false;
    } //if
    return true;
  } // equals(Object)


  /**
   * Compute a hash code for this matrix. Included because any object that implements `equals` is
   * expected to implement `hashCode` and ensure that the hash codes for two equal objects are the
   * same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
