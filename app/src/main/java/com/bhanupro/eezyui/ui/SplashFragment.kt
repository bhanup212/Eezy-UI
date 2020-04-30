package com.bhanupro.eezyui.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.bhanupro.eezyui.R

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    private val handler: Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashScreenDelay()
    }
    private fun splashScreenDelay(){
        handler.postDelayed({ navigateToDoublePlan() }, SPLASH_SCREEN_DELAY)
    }
    private fun navigateToDoublePlan(){
        findNavController().navigate(R.id.action_splashFragment_to_doublePlanFragment)
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(null)
    }
    companion object{
        const val SPLASH_SCREEN_DELAY:Long = 2000
    }

}
