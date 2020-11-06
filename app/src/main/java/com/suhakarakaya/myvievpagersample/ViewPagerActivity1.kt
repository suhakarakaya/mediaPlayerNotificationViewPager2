package com.suhakarakaya.myvievpagersample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.suhakarakaya.myvievpagersample.ViewPager2.AnotherActivity
import com.suhakarakaya.myvievpagersample.ViewPager2.IntroSlide
import com.suhakarakaya.myvievpagersample.ViewPager2.IntroSliderAdapter
import kotlinx.android.synthetic.main.activity_main.*

class ViewPagerActivity1 : AppCompatActivity() {

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "buselik",
                "buselik makamı",
                R.drawable.buselik
            ),
            IntroSlide(
                "buzurg",
                "buzurg makamı",
                R.drawable.buzurg
            ),
            IntroSlide(
                "hicaz",
                "hicaz makamı",
                R.drawable.hicaz
            ),
            IntroSlide(
                "huseyni",
                "huseyni makamı",
                R.drawable.huseyni
            ),
            IntroSlide(
                "irak",
                "irak makamı",
                R.drawable.irak
            ),
            IntroSlide(
                "isfahan",
                "isfahan makamı",
                R.drawable.isfahan
            ),
            IntroSlide(
                "neva",
                "neva makamı",
                R.drawable.buselik
            ),
            IntroSlide(
                "rast",
                "rast makamı",
                R.drawable.rast
            ),
            IntroSlide(
                "rehavi",
                "rahavi makamı",
                R.drawable.rehavi
            ),
            IntroSlide(
                "saba",
                "saba makamı",
                R.drawable.saba
            ),
            IntroSlide(
                "ussak",
                "ussak makamı",
                R.drawable.ussak
            ),
            IntroSlide(
                "zergule",
                "zergule makamı",
                R.drawable.zergule
            ),
            IntroSlide(
                "ziref",
                "ziref makamı",
                R.drawable.ziref
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        buttonNext.setOnClickListener {
            if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem += 1
            } else {
                Intent(applicationContext, AnotherActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        texttSkipIntro.setOnClickListener {
            Intent(applicationContext, AnotherActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_inactive)
                )
                this?.layoutParams = layoutParams
            }
            indicatorsConrainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        var childCount = indicatorsConrainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsConrainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}