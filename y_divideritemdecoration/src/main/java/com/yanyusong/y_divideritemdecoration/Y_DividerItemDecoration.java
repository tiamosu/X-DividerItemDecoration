package com.yanyusong.y_divideritemdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public abstract class Y_DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    private Context context;

    public Y_DividerItemDecoration(Context context) {
        this.context = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //left, top, right, bottom
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (!(child.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
                return;
            }
            final int itemPosition = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();
            final Y_Divider divider = getDivider(itemPosition);
            if (divider == null) {
                return;
            }

            if (divider.getLeftSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getLeftSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getLeftSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getLeftSideLine().getEndPaddingDp());
                drawChildLeftVertical(child, c, divider.getLeftSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getTopSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getTopSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getTopSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getTopSideLine().getEndPaddingDp());
                drawChildTopHorizontal(child, c, divider.topSideLine.getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getRightSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getRightSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getRightSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getRightSideLine().getEndPaddingDp());
                drawChildRightVertical(child, c, divider.getRightSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
            if (divider.getBottomSideLine().isHave()) {
                int lineWidthPx = Dp2Px.convert(context, divider.getBottomSideLine().getWidthDp());
                int startPaddingPx = Dp2Px.convert(context, divider.getBottomSideLine().getStartPaddingDp());
                int endPaddingPx = Dp2Px.convert(context, divider.getBottomSideLine().getEndPaddingDp());
                drawChildBottomHorizontal(child, c, divider.getBottomSideLine().getColor(), lineWidthPx, startPaddingPx, endPaddingPx);
            }
        }
    }

    private void drawChildBottomHorizontal(View child, Canvas c, @ColorInt int color,
                                           int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        if (!(child.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
            return;
        }
        int leftPadding;
        int rightPadding;
        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }

        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int left = child.getLeft() - params.leftMargin + leftPadding;
        final int right = child.getRight() + params.rightMargin + rightPadding;
        final int top = child.getBottom() + params.bottomMargin;
        final int bottom = top + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }

    private void drawChildTopHorizontal(View child, Canvas c, @ColorInt int color,
                                        int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        if (!(child.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
            return;
        }
        int leftPadding;
        int rightPadding;
        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            leftPadding = -lineWidthPx;
        } else {
            leftPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            rightPadding = lineWidthPx;
        } else {
            rightPadding = -endPaddingPx;
        }

        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int left = child.getLeft() - params.leftMargin + leftPadding;
        final int right = child.getRight() + params.rightMargin + rightPadding;
        final int bottom = child.getTop() - params.topMargin;
        final int top = bottom - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }

    private void drawChildLeftVertical(View child, Canvas c, @ColorInt int color,
                                       int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        if (!(child.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
            return;
        }
        int topPadding;
        int bottomPadding;
        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getTop() - params.topMargin + topPadding;
        final int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        final int right = child.getLeft() - params.leftMargin;
        final int left = right - lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }

    private void drawChildRightVertical(View child, Canvas c, @ColorInt int color,
                                        int lineWidthPx, int startPaddingPx, int endPaddingPx) {

        if (!(child.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
            return;
        }
        int topPadding;
        int bottomPadding;
        if (startPaddingPx <= 0) {
            //padding<0当作==0处理
            //上下左右默认分割线的两头都出头一个分割线的宽度，避免十字交叉的时候，交叉点是空白
            topPadding = -lineWidthPx;
        } else {
            topPadding = startPaddingPx;
        }
        if (endPaddingPx <= 0) {
            bottomPadding = lineWidthPx;
        } else {
            bottomPadding = -endPaddingPx;
        }

        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getTop() - params.topMargin + topPadding;
        final int bottom = child.getBottom() + params.bottomMargin + bottomPadding;
        final int left = child.getRight() + params.rightMargin;
        final int right = left + lineWidthPx;
        mPaint.setColor(color);

        c.drawRect(left, top, right, bottom, mPaint);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //outRect 看源码可知这里只是把Rect类型的outRect作为一个封装了left,right,top,bottom的数据结构,
        //作为传递left,right,top,bottom的偏移值来用的

        final int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();

        Y_Divider divider = getDivider(itemPosition);
        if (divider == null) {
            divider = new Y_DividerBuilder().create();
        }

        final int left = divider.getLeftSideLine().isHave() ? Dp2Px.convert(context, divider.getLeftSideLine().getWidthDp()) : 0;
        final int top = divider.getTopSideLine().isHave() ? Dp2Px.convert(context, divider.getTopSideLine().getWidthDp()) : 0;
        final int right = divider.getRightSideLine().isHave() ? Dp2Px.convert(context, divider.getRightSideLine().getWidthDp()) : 0;
        final int bottom = divider.getBottomSideLine().isHave() ? Dp2Px.convert(context, divider.getBottomSideLine().getWidthDp()) : 0;

        outRect.set(left, top, right, bottom);
    }

    public abstract @Nullable
    Y_Divider getDivider(int itemPosition);
}

















