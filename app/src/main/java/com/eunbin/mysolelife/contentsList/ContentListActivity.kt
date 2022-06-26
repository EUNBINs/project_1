package com.eunbin.mysolelife.contentsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eunbin.mysolelife.R
import com.eunbin.mysolelife.utils.FBAuth
import com.eunbin.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ContentListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference     // lateinit : 타입은 정해놓고, 값은 나중에 넣겠다

    val bookmarkIdList = mutableListOf<String>()

    lateinit var rvAdapter : ContentRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_list)

        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        rvAdapter = ContentRVAdapter(baseContext, items, itemKeyList, bookmarkIdList)       //어댑터에 context,items,itemkeylist 넘겨주기 -> 받는부분에서도 선언하기

        val database = Firebase.database

        val category = intent.getStringExtra("category")

        // 카테고리1에 컨텐츠데이터 넣어주기
        if (category == "category1") {
            myRef = database.getReference("contents")


            // 카테고리2에 컨텐츠2 데이터 넣어주기
        } else if (category == "category2") {

            myRef = database.getReference("contents2")

        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    Log.d("ContentListActivity",dataSnapshot.toString())
                    Log.d("ContentListActivity", dataModel.key.toString())          //content ID
                    val item = dataModel.getValue(ContentModel::class.java)
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("ContentListActivity", items.toString())

            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }

        myRef.addValueEventListener(postListener)

        val rv : RecyclerView = findViewById(R.id.rv)

        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(this,2)

        getBookmarkData()



    }

    private fun getBookmarkData() {

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // 북마크리스트의 기존리스트들이 변경될 때 클리어하고 다시 실행 --> 북마크 클릭 후 해제 정상작동
                bookmarkIdList.clear()
                // 북마크 리스트들
                for (dataModel in dataSnapshot.children) {
                    bookmarkIdList.add(dataModel.key.toString())
                }
                Log.d("Bookmark : ", bookmarkIdList.toString())
                rvAdapter.notifyDataSetChanged()

            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        // 데이터베이스에서 bookmarkRef 데이터모델 가져오기
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)


    }
}
