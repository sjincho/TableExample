package com.gabepos.tableexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    final TableGrid tableGrid = (TableGrid) findViewById(R.id.tableGrid);
    tableGrid.resetGrid(10, 10);
    tableGrid.setCellSize(50);
    // Numbers are: x, y, w, h
    tableGrid.addCell(createTableCell(1), 0, 0, 1, 2);
    tableGrid.addCell(createTableCell(2), 3, 0, 2, 1);
    tableGrid.addCell(createTableCell(3), 1, 5, 1, 2);
    tableGrid.addCell(createTableCell(4), 2, 6, 1, 1);
    tableGrid.addCell(createTableCell(5), 8, 9, 1, 1);
    tableGrid.addCell(createTableCell(6), 9, 9, 1, 1);
    tableGrid.addCell(createTableCell(7), 1, 1, 1, 1);

    Button increaseSizeButton = (Button) findViewById(R.id.increaseSizeButton);
    increaseSizeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int newCellSize = (int) Math.ceil(tableGrid.getCellSize() * 1.1f);
        tableGrid.setCellSize(newCellSize);
      }
    });

    Button decreaseSizeButton = (Button) findViewById(R.id.decreaseSizeButton);
    decreaseSizeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int newCellSize = (int) Math.ceil(tableGrid.getCellSize() * 0.9f);
        tableGrid.setCellSize(newCellSize);
      }
    });

    // Bidirectional scroll view from http://stackoverflow.com/a/14256246 with slight modifications
    // to grab intercept touch event.
    final HorizontalScrollView hScroll =
        (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
    final NonScrollView vScroll = (NonScrollView) findViewById(R.id.verticalScrollView);
    hScroll.setOnTouchListener(new View.OnTouchListener() {  // Outer scroll listener
      private float mx, my, curX, curY;
      private boolean started = false;

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        curX = event.getX();
        curY = event.getY();
        int dx = (int) (mx - curX);
        int dy = (int) (my - curY);
        switch (event.getAction()) {
          case MotionEvent.ACTION_MOVE:
            if (started) {
              vScroll.scrollBy(0, dy);
              hScroll.scrollBy(dx, 0);
            } else {
              started = true;
            }
            mx = curX;
            my = curY;
            break;
          case MotionEvent.ACTION_UP:
            vScroll.scrollBy(0, dy);
            hScroll.scrollBy(dx, 0);
            started = false;
            break;
        }
        return false;
      }
    });
  }

  private TextView createTableCell(int label) {
    TextView textView = new TextView(this);
    textView.setText(Integer.toString(label));
    textView.setBackgroundResource(R.color.colorPrimary);
    return textView;
  }
}
