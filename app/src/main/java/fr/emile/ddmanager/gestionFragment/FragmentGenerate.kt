package fr.emile.ddmanager.gestionFragment

import android.app.AlertDialog
import android.content.DialogInterface
import fr.emile.ddmanager.IShowImage
import fr.emile.ddmanager.MainAcvtivity
import fr.emile.ddmanager.R
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment
import fr.emile.ddmanager.gestionFragment.monsterFragment.FragmentShowImageList

class FragmentGenerate
{

    lateinit var fragGenerated:CustomFragment

    fun createFrag(frag:CustomFragment,activity: MainAcvtivity)
    {
        fragGenerated=frag
        val ft=activity.supportFragmentManager.beginTransaction()
        //fragmentLauncher

//        ft.finish(frag)
        ft.add(R.id.ecran,frag)
        ft.addToBackStack(null)
        ft.commit()
    }

    /*private fun FragmentTransaction.finish(frag:Fragment)
    {
        add(R.id.ecran,frag)
        addToBackStack(null)
        commit()
    }*/

    fun updateFrag()
    {
        (fragGenerated as? FragmentShowImageList)?.gridAdapterMonster?.notifyDataSetChanged()
    }

    fun createAlertDialog(activity: MainAcvtivity,yesAction:()->Unit,noAction:()->Unit) {
        //val alertDialog: AlertDialog? =
            val builder = AlertDialog.Builder(activity)
            builder.apply {
                setPositiveButton("Yes"
                ) { _, _ ->
                    yesAction()
                }
                setNegativeButton("Cancel"
                ) { _, _ ->
                    noAction()
                }
            }

            builder.setMessage("Sure you want to delete this card ?")
            // Create the AlertDialog
            builder.create().show()


    }
    /*fun updateAdapter(characterList: MutableList<out IShowImage>)
    {
        (fragGenerated as? FragmentShowImageList)?.gridAdapterMonster?.characterList=characterList
    }*/
}

