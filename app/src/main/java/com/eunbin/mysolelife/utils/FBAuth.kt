package com.eunbin.mysolelife.utils

import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.*

class FBAuth {

    companion object {

        // 북마크 클릭시 사용자 uid값 가져오는 코드
        private lateinit var auth : FirebaseAuth
        fun getUid() : String {

            auth = FirebaseAuth.getInstance()

            return auth.currentUser?.uid.toString()
        }

        fun getTime() : String {
            val currentDataTime = Calendar.getInstance().time
            val dataFormat = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDataTime)
            // Locale 이란 사용자 언어 국가 및 장소 설정

            return dataFormat
        }
    }
}