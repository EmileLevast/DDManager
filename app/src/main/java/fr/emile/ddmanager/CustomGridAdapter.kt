package fr.emile.ddmanager

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.LinearLayout
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RelativeLayout


class CustomGridAdapter(private var activity: Activity, private var characterList: MutableList<Character>) : ArrayAdapter<Int>(activity, 0, characterList.getOnlyIdImage()) {
    //private val characterList: ArrayList<Character>

    private val marginBetweenPictures = 10//pixel
    val nbrColumnGridView=4

    private var widthImg = 0
    private var heightImg = 0


    /*init {
        this.characterList = ArrayList(characterList)
        widthImg = 0
    }*/

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var listItem: View? = convertView

        //we create our viewHolder to save our view
        var viewHolder = ViewHolder()
        //Log.d(TAG,"Image counter: " + position + " ConvertView: " + convertView);
        //if we are at the beginning convertView is null , we have to inflate it
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.layout_grid_view, parent, false)
            val imageView:ImageView = listItem.findViewById(R.id.imageViewAdapterFragment)
            //val textLegend = context.findViewById(R.id.textLegend)

            widthImg = (WIDTH_SCREEN!! - 2 * marginBetweenPictures * nbrColumnGridView) / nbrColumnGridView
            val param = RelativeLayout.LayoutParams(widthImg, widthImg)
            param.setMargins(marginBetweenPictures, marginBetweenPictures, marginBetweenPictures, marginBetweenPictures)
            imageView.layoutParams = param

            viewHolder.imageView = imageView
            //viewHolder.textXpCost = textXpCost
            listItem.tag = viewHolder
        } else {
            //we get the views previously registered
            viewHolder = listItem.tag as ViewHolder
        }

        //we get the currently image selected
        val currentImg = characterList[position]

        if (viewHolder.imageView!!.getDrawable() != null)
            (viewHolder.imageView!!.getDrawable() as BitmapDrawable).bitmap.recycle()

        //viewHolder.getImageView().setImageResource(currentImg);
        viewHolder.imageView!!.setImageBitmap(decodeSampledBitmapFromResource(currentImg.imgId, widthImg, widthImg))
        //viewHolder.textXpCost!!.setText(currentImg.xpCost)

        return listItem
    }

    fun setImg(img: List<Character>) {
        this.characterList.clear()
        this.characterList.addAll(img)
    }

    // class to save view
    inner class ViewHolder {
        var imageView: ImageView? = null
        //var textXpCost: TextView? = null
    }

    /*companion object {
        private val TAG = "CustomVIew"
*/

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
    //}
}