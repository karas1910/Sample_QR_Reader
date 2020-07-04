package com.example.zxing_sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.camera_button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camera_button.setOnClickListener {
            IntentIntegrator(this).apply {
                captureActivity = QrReaderActivity::class.java
                setBeepEnabled(false)
            }.initiateScan()
        }
    }

    // 読み取り後に呼ばれるメソッド
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 結果の取得
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            // result.contents で取得した値を参照できる
            Toast.makeText(this, result.contents, Toast.LENGTH_LONG).show()
        }

        else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}