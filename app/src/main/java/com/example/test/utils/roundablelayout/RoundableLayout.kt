package com.example.test.utils.roundablelayout

import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.ColorInt
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.test.R

class RoundableLayout : ConstraintLayout {

    var path: Path? = null

    /** corner radius */
    var cornerLeftTop: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var cornerRightTop: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var cornerLeftBottom: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var cornerRightBottom: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    /**
     * if left side value existed
     */
    var cornerLeftSide: Float = 0F
        set(value) {
            field = value

            if (field != 0F) {
                cornerLeftTop = field
                cornerLeftBottom = field
            }

            postInvalidate()
        }

    var cornerRightSide: Float = 0F
        set(value) {
            field = value

            if (field != 0F) {
                cornerRightTop = field
                cornerRightBottom = field
            }

            postInvalidate()
        }


    /** background color */
    var backgroundColor: Int? = null
        set(@ColorInt value) {
            field = value
            postInvalidate()
        }

    override fun setBackgroundColor(color: Int) {
        backgroundColor = color
    }

    /** stroke & dash options */
    var strokeLineWidth: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var strokeLineColor = 0XFFFFFFFF.toInt()
        set(@ColorInt value) {
            field = value
            postInvalidate()
        }

    var dashLineGap: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var dashLineWidth: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        render(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        render(attrs)
    }

    constructor(context: Context) : super(context) {
        render(null)
    }

    private fun render(attrs: AttributeSet?) {
        attrs?.let {

            /** set corner radii */
            context.obtainStyledAttributes(it, R.styleable.RoundableLayout).apply {
                cornerLeftTop =
                    getDimensionPixelSize(R.styleable.RoundableLayout_roundable_cornerLeftTop, 0)
                        .toFloat()
                cornerRightTop =
                    getDimensionPixelSize(R.styleable.RoundableLayout_roundable_cornerRightTop, 0)
                        .toFloat()
                cornerLeftBottom =
                    getDimensionPixelSize(R.styleable.RoundableLayout_roundable_cornerLeftBottom, 0)
                        .toFloat()
                cornerRightBottom =
                    getDimensionPixelSize(
                        R.styleable.RoundableLayout_roundable_cornerRightBottom,
                        0
                    )
                        .toFloat()
                backgroundColor =
                    getColor(R.styleable.RoundableLayout_roundable_backgroundColor, Color.WHITE)
                strokeLineWidth =
                    getDimensionPixelSize(R.styleable.RoundableLayout_roundable_strokeLineWidth, 0)
                        .toFloat()
                strokeLineColor =
                    getColor(R.styleable.RoundableLayout_roundable_strokeLineColor, Color.BLACK)
                dashLineWidth =
                    getDimensionPixelSize(R.styleable.RoundableLayout_roundable_dashLineWidth, 0)
                        .toFloat()
                dashLineGap =
                    getDimensionPixelSize(
                        R.styleable.RoundableLayout_roundable_dashLineGap,
                        0
                    ).toFloat()
                cornerLeftSide =
                    getDimensionPixelSize(R.styleable.RoundableLayout_roundable_cornerLeftSide, 0)
                        .toFloat()
                cornerRightSide =
                    getDimensionPixelSize(R.styleable.RoundableLayout_roundable_cornerRightSide, 0)
                        .toFloat()
                if (getDimensionPixelSize(
                        R.styleable.RoundableLayout_roundable_cornerAll,
                        0
                    ) != 0
                ) {
                    val roundedSize =
                        getDimensionPixelSize(R.styleable.RoundableLayout_roundable_cornerAll, 0)
                            .toFloat()
                    cornerRightSide = roundedSize
                    cornerLeftSide = roundedSize
                }
            }.run {
                this.recycle()
            }
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        /** for outline remake whenenver draw */
        path = null

        if (path == null) {
            path = Path()
        }

        floatArrayOf(
            cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop, cornerRightBottom,
            cornerRightBottom, cornerLeftBottom, cornerLeftBottom
        ).apply {
            clipPathCanvas(canvas, this)
        }

        /** set drawable resource corner & background & stroke */
        GradientDrawable().apply {
            this.cornerRadii = floatArrayOf(
                cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop,
                cornerRightBottom, cornerRightBottom, cornerLeftBottom, cornerLeftBottom
            )

            if (strokeLineWidth != 0F && strokeLineColor != null)
                this.setStroke(
                    strokeLineWidth.toInt(),
                    strokeLineColor,
                    dashLineWidth,
                    dashLineGap
                )

            backgroundColor?.let {
                /** set background color */
                this.setColor(it)
            } ?: this.setColor(Color.WHITE)

            /** set background color default : WHITE */
            background = this
        }
        outlineProvider = getOutlineProvider()
        clipChildren = false
        super.dispatchDraw(canvas)
    }

    private fun clipPathCanvas(canvas: Canvas, floatArray: FloatArray) {
        path?.let {
            it.addRoundRect(
                RectF(0F, 0F, canvas.width.toFloat(), canvas.height.toFloat()),
                floatArray,
                Path.Direction.CW
            )
            canvas.clipPath(it)
        }
    }

    /** For not showing red underline */
    override fun setOutlineProvider(provider: ViewOutlineProvider?) {
        super.setOutlineProvider(provider)
    }

    /** For not showing red underline */
    override fun setElevation(elevation: Float) {
        super.setElevation(elevation)
    }

    /** For not showing red underline */
    override fun setTranslationZ(translationZ: Float) {
        super.setTranslationZ(translationZ)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getOutlineProvider(): ViewOutlineProvider {
        return object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                path?.let {
                    outline.setConvexPath(it)
                } ?: throw Exception()
            }
        }
    }


}