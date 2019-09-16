package fr.emile.ddmanager.gestionFragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import fr.emile.ddmanager.MainAcvtivity
import fr.emile.ddmanager.R
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment

class FragmentGenerate
{


    fun createFrag(frag:CustomFragment,activity: MainAcvtivity)
    {
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
}

