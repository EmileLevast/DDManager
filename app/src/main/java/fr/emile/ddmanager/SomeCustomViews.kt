package fr.emile.ddmanager

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.GridLayout
import android.widget.TextView

/**
 * Created by emile on 10/03/2019.
 */
class PointGrid(context: Context?,
                private val drawableFillBackgroundId:Int,
                private val colorEmptyTextId:Int,
                val value:Int,
                var isEmpty:Boolean
                ) : TextView(context) {


    fun empty()
    {
        isEmpty=true
        setBackgroundResource(R.drawable.emptypointgrid)
        setTextColor(ContextCompat.getColor(context,colorEmptyTextId))
    }

    fun fill()
    {
        isEmpty=false
        setBackgroundResource(drawableFillBackgroundId)
        setTextColor(ContextCompat.getColor(context,R.color.black))
    }
}

class GridCustomPoint:GridLayout {

    var currentNumber:Int = 0
    var numberReferenceInit:Int=0

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun initValueToRefer(valueToPoint:Int,valueReferenceInit:Int)
    {
        currentNumber=valueToPoint
        numberReferenceInit=valueReferenceInit
    }

}

//some functions for manage view size
fun calculateRatioWidthHeigth(imgId:Int, context: Context):Float
{
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(context.resources, imgId, options)

    return options.outWidth.toFloat() / options.outHeight.toFloat()
}