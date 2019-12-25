package id.ac.ui.cs.mobileprogramming.tyagitalarasati.welist.view.opengl

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class SplashScreenGLRenderer(val context: Context) : GLSurfaceView.Renderer {
    private lateinit var mTriangle_1: DrawShape
    private lateinit var mTriangle_2: DrawShape
    private lateinit var mTriangle_3: DrawShape
    private lateinit var mTriangle_4: DrawShape
    private val mViewMatrix = FloatArray(16)
    private val mMVPMatrix = FloatArray(16)
    private val mProjectionMatrix = FloatArray(16)
    private val mRotationMatrix = FloatArray(16)
    private var mTempMatrix = FloatArray(16)
    private val mModelMatrix = FloatArray(16)
    private var dx = 0f

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        GLES20.glClearColor(0.10196078431f, 0.10196078431f, 0.10196078431f, 0f)

        val triangleCoordinate = floatArrayOf(
            0f, 0.8f, 0f,
            -0.8f, 0f, 0f,
            0f, 0f, 0f,
            0f, 0f, 0f
        )

        val triangleCoordinate_2 = floatArrayOf(
            0f, 0.4f, 0f,
            -0.4f, 0f, 0f,
            0f, 0f, 0f,
            0f, 0f, 0f
        )
        val color_1 = floatArrayOf(0.65098039215f, 0.24705882352f, 0f, 0.00156862745f)
        val color_2 = floatArrayOf(0f, 0.40392156862f, 0.65098039215f, 0f)

        mTriangle_1 = DrawShape(triangleCoordinate, color_1)
        mTriangle_2 = DrawShape(triangleCoordinate_2, color_2)
        mTriangle_3 = DrawShape(triangleCoordinate, color_1)
        mTriangle_4 = DrawShape(triangleCoordinate_2, color_2)
    }

    override fun onDrawFrame(unused: GL10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        rotateTriangle(mTriangle_1, 1)
        rotateTriangle(mTriangle_2, 1)
        rotateTriangle(mTriangle_3, -1)
        rotateTriangle(mTriangle_4, -1)
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        val ratio = width.toFloat() / height

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

    private fun rotateTriangle(triangle: DrawShape, direction: Int) {
        Matrix.setIdentityM(mModelMatrix, 0)
        Matrix.translateM(mModelMatrix, 0, direction * dx, 0f, 0f)
        dx += 0.000032f

        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)

        val time = SystemClock.uptimeMillis() % 4000L
        val angle = direction * 0.090f * time.toInt()
        Matrix.setRotateM(mRotationMatrix, 0, angle, 0f, 0f, -1.0f)

        mTempMatrix = mModelMatrix.clone()
        Matrix.multiplyMM(mModelMatrix, 0, mTempMatrix, 0, mRotationMatrix, 0)
        mTempMatrix = mMVPMatrix.clone()
        Matrix.multiplyMM(mMVPMatrix, 0, mTempMatrix, 0, mModelMatrix, 0)
        triangle.draw(mMVPMatrix)

    }
}