package fr.emile.ddmanager.gestionFragment.customFragment

import android.os.Bundle
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class CustomFragment : Fragment(),FragmentLauncher{

    abstract val idLayoutToInflate:Int

    //call in on createView
    abstract fun createView(inflater: LayoutInflater)

    //call in onActivityCreated
    abstract fun activityCreated()


    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        createView(inflater)
        return inflater.inflate(idLayoutToInflate, container, false)
    }

    final override fun onActivityCreated(savedInstanceState: Bundle?) {
        activityCreated()
        super.onActivityCreated(savedInstanceState)
    }


}