package fr.emile.ddmanager.gestionFragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
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
        var ft=activity.supportFragmentManager.beginTransaction()
        //fragmentLauncher

        ft.finish(frag)
    }

    private fun FragmentTransaction.finish(frag:Fragment)
    {
        add(R.id.ecran,frag)
        addToBackStack(null)
        commit()
    }

    fun updateFrag()
    {
        (fragGenerated as? FragmentShowImageList)?.gridAdapterMonster?.notifyDataSetChanged()
    }

    /*fun updateAdapter(characterList: MutableList<out IShowImage>)
    {
        (fragGenerated as? FragmentShowImageList)?.gridAdapterMonster?.characterList=characterList
    }*/
}

