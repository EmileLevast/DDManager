package fr.emile.ddmanager

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListAdapterPowerHero(activity: Activity, private var listPower: MutableList<Power>) : ArrayAdapter<Int>(activity, 0, listPower.getOnlyIdImage()) {

    //private val marginBetweenPictures = 10//pixel
    //val nbrColumnGridView=4

    private var widthImg = 0
    private var heightImg = 0


    /*init {
        this.listPower = ArrayList(listPower)
        widthImg = 0
    }*/

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var listItem: View? = convertView

        //we create our viewHolder to save our view
        var viewHolder = ViewHolder()


        val currentPower = listPower[position]

        //if we are at the beginning convertView is null , we have to inflate it
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.layout_list_view, parent, false)
            val imageView: ImageView = listItem.findViewById(R.id.imageViewPower)
            val textDescriptionPower: TextView = listItem.findViewById(R.id.textPowerDescription)

            widthImg = (HEIGHT_SCREEN!! * (1 - RAPPORT_IMAGE_ECRAN_Y)).toInt()
            heightImg = widthImg//(widthImg/calculateRatioWidthHeigth(currentPower.imgId)).toInt()

            val param = LinearLayout.LayoutParams(widthImg, heightImg)
            //param.setMargins(marginBetweenPictures, marginBetweenPictures, marginBetweenPictures, marginBetweenPictures)
            imageView.layoutParams = param

            viewHolder.imageView = imageView
            viewHolder.textPowerDescription = textDescriptionPower
            listItem.tag = viewHolder
        } else {
            //we get the views previously registered
            viewHolder = listItem.tag as ViewHolder
        }

        //we get the currently image selected

        if (viewHolder.imageView!!.drawable != null)
            (viewHolder.imageView!!.drawable as BitmapDrawable).bitmap.recycle()

        //viewHolder.getImageView().setImageResource(currentImg);
        viewHolder.imageView!!.setImageBitmap(decodeSampledBitmapFromResource(currentPower.idImg, widthImg, heightImg))
        viewHolder.textPowerDescription!!.text = currentPower.textExplanation

        //set Typeface
        val typeface= Typeface.createFromAsset((context as Activity).assets,"font/hobbitonbrushhand.ttf")
        viewHolder.textPowerDescription!!.typeface=typeface

        return listItem
    }

    fun setImg(img: List<Power>) {
        this.listPower.clear()
        this.listPower.addAll(img)
    }

    /*companion object {
        private val TAG = "CustomVIew"
*/

    fun calculateRatioWidthHeigth(imgId: Int): Float {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, imgId, options)

        return options.outWidth.toFloat() / options.outHeight.toFloat()
    }

    fun decodeSampledBitmapFromResource(
            resId: Int, reqWidth: Int, reqHeight: Int): Bitmap {

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, resId, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(context.resources, resId, options)
    }

    fun calculateInSampleSize(options: BitmapFactory.Options,
                              reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = if (heightRatio < widthRatio) widthRatio else heightRatio
        }

        return inSampleSize
    }

    // class to save view
    inner class ViewHolder {
        var imageView: ImageView? = null
        var textPowerDescription: TextView? = null
    }

}