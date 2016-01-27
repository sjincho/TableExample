package com.gabepos.tableexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Space;

public class TableGrid extends GridLayout {

  /** Used to <code>setTag()</code> to {@link Space} children. */
  private static final Object TAG_SPACE = new Object();

  /** Table cell size in dps. */
  private int cellSize = 50;

  public TableGrid(Context context) {
    this(context, null);
  }

  public TableGrid(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TableGrid(Context context, AttributeSet attrs, int defStyleAttr) {
    this(context, attrs, defStyleAttr, 0);
  }

  public TableGrid(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  /** Get the current cell size in dps. */
  public int getCellSize() {
    return cellSize;
  }

  /** Set the new cell size (in dps), which also causes this table grid's size change. */
  public void setCellSize(int cellSize) {
    this.cellSize = cellSize;
    // Resize the Space children.
    for (int i = 0; i < getChildCount(); i++) {
      View view = getChildAt(i);
      if (view.getTag() == TAG_SPACE) {
        view.getLayoutParams().width = cellSize;
        view.getLayoutParams().height = cellSize;
      }
    }
    ViewGroup.LayoutParams params = getLayoutParams();
    params.width = cellSize * getColumnCount();
    params.height = cellSize * getRowCount();
    setLayoutParams(params);
  }

  private Space createSquareSpace(int row, int column) {
    Space space = new Space(getContext());
    GridLayout.LayoutParams params = new GridLayout.LayoutParams(
        GridLayout.spec(row, 1, 1f),
        GridLayout.spec(column, 1, 1f));
    params.setGravity(Gravity.FILL);
    space.setLayoutParams(params);
    space.setTag(TAG_SPACE);
    return space;
  }

  /** Clear all children and resize grid to the given number of columns and rows. */
  public void resetGrid(int columnCount, int rowCount) {
    removeAllViews();
    setColumnCount(columnCount);
    setRowCount(rowCount);
    // Add spacers to equally size each cell.
    for (int i = 0; i < columnCount; i++) {
      addView(createSquareSpace(0, i));
    }
    for (int i = 1; i < rowCount; i++) {
      addView(createSquareSpace(i, 0));
    }
  }

  /** Add a new cell (any view supported) at the given position. */
  public void addCell(View childView, int column, int row, int columnSpan, int rowSpan) {
    GridLayout.LayoutParams params = new GridLayout.LayoutParams(
        GridLayout.spec(row, rowSpan),
        GridLayout.spec(column, columnSpan));
    params.setGravity(Gravity.FILL);
    childView.setLayoutParams(params);
    addView(childView);
  }
}
