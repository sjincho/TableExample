package com.gabepos.tableexample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TableCell extends TextView {

  private static final String TAG = "TableCell";

  public TableCell(Context context) {
    super(context);
  }

  public TableCell(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public TableCell(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public TableCell(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    Log.d(TAG, String.format("measure with width spec = %s, height spec = %s",
        MeasureSpec.toString(widthMeasureSpec), MeasureSpec.toString(heightMeasureSpec)));
  }
}
