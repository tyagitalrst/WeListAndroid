package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.opengl

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

class SplashScreenGLSurfaceView(context: Context, attrs: AttributeSet) : GLSurfaceView(context, attrs) {

    private val renderer: SplashScreenGLRenderer

    init {

        setEGLContextClientVersion(2)
        renderer = SplashScreenGLRenderer(context)
        setRenderer(renderer)
    }
}