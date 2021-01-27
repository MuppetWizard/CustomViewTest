package com.muppet.customviewtest.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.muppet.customviewtest.R


/**
 * des：canvas绘制图形示例
 *
 * @author: Muppet
 * @date:   2021/1/18
 */
class CanvasDrawGraphics(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val mContext = context
    //创建画笔
    private var mPaint = Paint()

    init {
        //初始化画笔
        mPaint = getPaint(R.color.color_1)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec);      //取出宽度的确切数值
        val widthMode = MeasureSpec.getMode(widthMeasureSpec);      //取出宽度的测量模式
        val heightSize = MeasureSpec.getSize(heightMeasureSpec);    //取出高度的确切数值
        val heightMode = MeasureSpec.getMode(heightMeasureSpec);    //取出高度的测量模式
        //如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec);
        //要调用 setMeasuredDimension( widthsize, heightsize); 这个函数。
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //四个参数，分别为 宽度，高度，上一次宽度，上一次高度。
        //宽度(w), 高度(h) 这两个参数就是View最终的大小
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        //绘制画布底色
        canvas?.drawColor(ContextCompat.getColor(mContext, R.color.color_normal))

        //绘制点
        canvas?.drawPoint(50f, 50f, getPaint(R.color.color_1))
        canvas?.drawPoints(
            floatArrayOf(
                300f, 50f,
                400f, 50f,
                500f, 50f
            ), getPaint(R.color.color_1)
        )

        //绘制直线
        canvas?.drawLine(50f, 80f, 150f, 80f, getPaint(R.color.color_2))
        canvas?.drawLines(
            floatArrayOf(
                200f, 80f, 400f, 80f,
                500f, 80f, 700f, 80f,
            ), getPaint(R.color.color_2)
        )

        //绘制矩形
        //三种效果都是一样，二、三两种最大的区别就是精度不同
        //第一种方式
        canvas?.drawRect(100f, 100f, 300f, 200f, getPaint(R.color.color_3))
        //第二种方式
        val rect = Rect(100, 100, 300, 200)
        canvas?.drawRect(rect, getPaint(R.color.color_3))
        //第三种
        val rectF = RectF(100f, 100f, 300f, 200f)
        canvas?.drawRect(rectF, getPaint(R.color.color_3))

        //绘制圆角矩形
        //两种方式效果一样，但第二种方法在API21的时候才添加上，所以我们一般使用的都是第一种。
        //第一种
        val rectF_1 = RectF(350f, 100f, 500f, 200f)
        canvas?.drawRoundRect(rectF_1, 30f, 30f, getPaint(R.color.color_4))
        //第二种
        canvas?.drawRoundRect(350f, 100f, 500f, 200f, 30f, 30f, getPaint(R.color.color_4))

        //绘制椭圆
        //两种效果一样，但一般使用第一种，原理就是绘制矩形的内切圆
        //第一种方式
        val rectF_2 = RectF(50f, 220f, 200f, 320f)
        canvas?.drawOval(rectF_2, getPaint(R.color.color_5))
        //第二种
        canvas?.drawOval(50f,220f,200f,320f,getPaint(R.color.color_5))

        //绘制圆
        //绘制圆形有四个参数，前两个是圆心坐标，第三个是半径，最后一个是画笔。
        canvas?.drawCircle(280f,270f,50f,getPaint(R.color.color_6))

        //绘制圆弧
        val rectF_3 = RectF(50f,340f,150f,400f)

        //绘制背景 1
        canvas?.drawRect(rectF_3,getPaint(Color.GRAY))
        //startAngle 表示开始角度，sweepAngle 表示扫过角度， useCenter 是否使用中心
        //未使用中心点
        canvas?.drawArc(rectF_3,0f,90f,false,getPaint(R.color.color_7))
        //
        //使用中心点


    }

    /**
     * 画笔初始化设置,获取不同颜色的画笔
     */
    private fun getPaint(mColor: Int) : Paint {
        val paint = Paint()
        paint.apply {
            //设置画笔颜色
            color = ContextCompat.getColor(mContext, mColor)
            //设置画笔模式为填充
            style = Paint.Style.FILL
            //设置画笔笔触宽度为10px
            strokeWidth = 10f
        }
        return paint
    }
}