package com.fine.boll.hiruproject

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.fine.boll.hiruproject.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    // 전역 변수로 바인딩 객체 선언
    private var mBinding : ActivityMainBinding? = null
    // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // 자동 생성된 뷰 바인딩 클래스에서의 inflate라는 메서드를 활용해서
        // 액티비티에서 사용할 바인딩 클래스의 인스턴스 생성
        mBinding = ActivityMainBinding.inflate(layoutInflater)

        // getRoot 메서드로 레이아웃 내부의 최상위 위치 뷰의
        // 인스턴스를 활용하여 생성된 뷰를 액티비티에 표시 합니다
        setContentView(binding.root)

        MobileAds.initialize(this@MainActivity, resources.getString(R.string.ADMOB_ID))
        binding.admobView.loadAd(AdRequest.Builder().build())

        binding.title.setTypeface(Typeface.createFromAsset(this.assets, "fonts/ChosunGs.ttf"))
        binding.title.setOnClickListener{
            binding.titleLayout.visibility = GONE
            binding.bodyLayout.visibility = VISIBLE
            binding.imgRotation.visibility = VISIBLE
        }

        binding.body.setTypeface(Typeface.createFromAsset(this.assets, "fonts/ChosunGs.ttf"))
        binding.bodyBtnTextView.setTypeface(Typeface.createFromAsset(this.assets, "fonts/ChosunGs.ttf"))
        binding.bodyButton.setOnClickListener{
            if (binding.bodyLayout.visibility == VISIBLE && binding.bodyButton.visibility == VISIBLE) {
                binding.bodyButton.visibility = GONE
            }
        }

//        binding.title.setOnTouchListener(object : View.OnTouchListener {
//            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                when(event?.action) {
//                    MotionEvent.ACTION_DOWN -> {
//
//                    }
//                }
//                return true
//            }
//        })

//        binding.title.setOnTouchListener{ v, event ->
//            when(event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    Log.e(TAG, "dyhwang *** ACTION_DOWN")
//                    v?.elevation = 0f}
//                MotionEvent.ACTION_UP -> {
//                    Log.e(TAG, "dyhwang *** ACTION_UP")
//                    v?.elevation = 10f}
//            }
//            true
//        }
    }

    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다
        mBinding = null
        super.onDestroy()
    }
}