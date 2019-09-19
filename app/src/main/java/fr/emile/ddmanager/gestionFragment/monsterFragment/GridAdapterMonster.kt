package fr.emile.ddmanager.gestionFragment.monsterFragment

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import fr.emile.ddmanager.R
import fr.emile.ddmanager.IShowImage
import fr.emile.ddmanager.gestionAffichage.WIDTH_SCREEN
import fr.emile.ddmanager.calculateRatioWidthHeigth
import fr.emile.ddmanager.getOnlyIdImage
import fr.emile.ddmanager.mainClass.StuffCard


class GridAdapterMonster(activity: Activity, var characterList: MutableList<out IShowImage>) : ArrayAdapter<Int>(activity, 0, characterList.getOnlyIdImage()) {
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


        val currentImg = characterList[position]

        //if we are at the beginning convertView is null , we have to inflate it
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.layout_grid_view, parent, false)
            val imageView:ImageView = listItem.findViewById(R.id.imageViewAdapterFragment)
            //val textLegend = context.findViewById(R.id.textLegend)

            widthImg = (WIDTH_SCREEN!! - 2 * marginBetweenPictures * nbrColumnGridView) / nbrColumnGridView
            heightImg= (widthImg/calculateRatioWidthHeigth(currentImg.imgId,context)).toInt()

            val param = RelativeLayout.LayoutParams(widthImg, heightImg)
            param.setMargins(marginBetweenPictures, marginBetweenPictures, marginBetweenPictures, marginBetweenPictures)
            imageView.layoutParams = param

            viewHolder.imageView = imageView
            //viewHolder.textPowerDescription = textPowerDescription
            listItem.tag = viewHolder
        } else {
            //we get the views previously registered
            viewHolder = listItem.tag as ViewHolder
        }

        //we get the currently image selected

        if (viewHolder.imageView!!.drawable != null)
            (viewHolder.imageView!!.drawable as BitmapDrawable).bitmap.recycle()

        //viewHolder.getImageView().setImageResource(currentImg);
        if((currentImg !is StuffCard)  || !currentImg.isDeleted)
        {
            viewHolder.imageView!!.setImageBitmap(decodeSampledBitmapFromResource(currentImg.imgId, widthImg, heightImg))
        }
        else
        {
            //TODO verifier ce que fait cette ligne (elle efface les objets deleted mais est- ce qu'on est sur
            viewHolder.imageView!!.setImageResource(0)//R.color.transparent)
        }
        //viewHolder.textPowerDescription!!.setText(currentImg.xpCost)

        //if the image is corresponding to a stuffCard we color his background depending on it isUsed attribute
        if (currentImg is StuffCard && !currentImg.isDeleted)
            viewHolder.imageView!!.setBackgroundResource(
                    when (currentImg.isUsed)
                    {
                        true->R.color.strokeButton
                        else->R.color.transparent
                    }
            )

        return listItem
    }

    /*fun setImg(img: List<Monster>) {
        this.characterList.clear()
        this.characterList.addAll(img)
    }*/

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

// class to save view
    inner class ViewHolder {
        var imageView: ImageView? = null
        //var textPowerDescription: TextView? = null
    }
}
