package flashlight.flashlightapp.ledlight.torch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import flashlight.flashlightapp.ledlight.torch.databinding.ActivitySplashBinding
import flashlight.flashlightapp.ledlight.torch.uitl.Utils
import java.util.Timer
import kotlin.concurrent.schedule


class SplashActivity : AppCompatActivity(),AnimationListener {

    private lateinit var binding: ActivitySplashBinding

    private lateinit var alphaAnimation: AlphaAnimation

    private lateinit var alphaAnimation1: AlphaAnimation

    private lateinit var translateAnimation: TranslateAnimation

    private lateinit var translateAnimation1: TranslateAnimation

    private lateinit var translateAnimation2: TranslateAnimation

    private lateinit var setAnim: AnimationSet

    private lateinit var setAnim1: AnimationSet

    private lateinit var setAnim2: AnimationSet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initbgAnimation()
        binding.mainSlBackIv.startAnimation(translateAnimation)
        binding.mainSlFrontIv.startAnimation(setAnim)
        binding.mainSlTitle1.startAnimation(setAnim1)
        binding.mainSlTitle2.startAnimation(setAnim2)
        translateAnimation.setAnimationListener(this)
        Timer().schedule(6000){
            toMainActivity()
        }

    }

    fun toMainActivity(){
        intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun initbgAnimation(){
        //光束透明度
        alphaAnimation=AlphaAnimation(0.6f,1.0f)
        alphaAnimation.duration=3000
        alphaAnimation.fillAfter=true
        //设置text1光束透明度
        alphaAnimation1=AlphaAnimation(0.3f,1.0f)
        alphaAnimation1.duration=1000
        alphaAnimation1.fillAfter=true
        //向左平移125dp
        translateAnimation=TranslateAnimation(
            1.0f,-125f,1.0f,1.0f
        )
        translateAnimation.duration=3000
        translateAnimation.fillAfter=true
        //向下平移20dp
        translateAnimation1=TranslateAnimation(
            1.0f,1.0f,Utils.dip2px(this,-20f).toFloat(),1.0f
        )
        translateAnimation1.duration=1000
        translateAnimation1.fillAfter=true
        //向上平移20dp
        translateAnimation2=TranslateAnimation(
            1.0f,1.0f,1.0f,Utils.dip2px(this,-20f).toFloat()
        )
        translateAnimation2.duration=1000
        translateAnimation2.fillAfter=true
        //设置集合动画
        setAnim=AnimationSet(true)
        setAnim.addAnimation(alphaAnimation)
        setAnim.addAnimation(translateAnimation)
        setAnim.fillAfter=true
        setAnim1=AnimationSet(true)
        setAnim1.addAnimation(alphaAnimation1)
        setAnim1.addAnimation(translateAnimation1)
        setAnim1.fillAfter=true
        setAnim2=AnimationSet(true)
        setAnim2.addAnimation(alphaAnimation1)
        setAnim2.addAnimation(translateAnimation2)
        setAnim2.fillAfter=true
    }

    override fun onAnimationStart(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
        if (translateAnimation==p0){
            val translateAnimation3=TranslateAnimation(
                -125f,1.0f,1.0f,1.0f
            )
            translateAnimation3.duration=3000
            translateAnimation3.fillAfter=true
            binding.mainSlFrontIv.startAnimation(translateAnimation3)
            binding.mainSlBackIv.startAnimation(translateAnimation3)
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }
}