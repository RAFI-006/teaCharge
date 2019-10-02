package com.teashop.teacharge.utils;

import android.content.Context;
import android.util.TypedValue;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class GridAutofitLayoutManager extends GridLayoutManager {
    private Context context;
    private float mColumnWidth;

    private float currentColumnWidth = -1;
    private int currentWidth = -1;
    private int currentHeight = -1;


    public GridAutofitLayoutManager(Context context) {
        /* Initially set spanCount to 1, will be changed automatically later. */
        super(context, 1);
        this.context = context;
        setColumnWidthByResource(-1);
    }

    public GridAutofitLayoutManager(Context context, int resource) {
        this(context);
        this.context = context;
        setColumnWidthByResource(resource);
    }

    public GridAutofitLayoutManager(Context context, int resource, int orientation, boolean reverseLayout) {
        /* Initially set spanCount to 1, will be changed automatically later. */
        super(context, 1, orientation, reverseLayout);
        this.context = context;
        setColumnWidthByResource(resource);
    }

    public void setColumnWidthByResource(int resource) {
        if (resource >= 0) {
            mColumnWidth = context.getResources().getDimension(resource);
        } else {
            /* Set default columnWidth value (48dp here). It is better to move this constant
            to static constant on top, but we need context to convert it to dp, so can't really
            do so. */
            mColumnWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48,
                    context.getResources().getDisplayMetrics());
        }
    }

    public void setColumnWidth(float newColumnWidth) {
        mColumnWidth = newColumnWidth;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        recalculateSpanCount();
        super.onLayoutChildren(recycler, state);
    }

    public void recalculateSpanCount() {
        int width = getWidth();
        if (width <= 0) return;
        int height = getHeight();
        if (height <= 0) return;
        if (mColumnWidth <= 0) return;
        if ((width != currentWidth) || (height != currentHeight) || (mColumnWidth != currentColumnWidth)) {
            int totalSpace;
            if (getOrientation() == RecyclerView.VERTICAL) {
                totalSpace = width - getPaddingRight() - getPaddingLeft();
            } else {
                totalSpace = height - getPaddingTop() - getPaddingBottom();
            }
            int spanCount = (int) Math.max(1, Math.floor(totalSpace / mColumnWidth));
            setSpanCount(spanCount);
            currentColumnWidth = mColumnWidth;
            currentWidth = width;
            currentHeight = height;
        }
    }
}