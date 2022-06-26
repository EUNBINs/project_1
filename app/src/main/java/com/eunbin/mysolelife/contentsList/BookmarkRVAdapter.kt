package com.eunbin.mysolelife.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eunbin.mysolelife.R
import com.eunbin.mysolelife.utils.FBAuth
import com.eunbin.mysolelife.utils.FBRef

class BookmarkRVAdapter(val context : Context,
                       val items : ArrayList<ContentModel>,
                       val keyList : ArrayList<String>,
                       val bookmarkIdList : MutableList<String>)
    : RecyclerView.Adapter<BookmarkRVAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)

        Log.d("BookmarkRVAdapter", keyList.toString())
        Log.d("BookmarkRVAdapter", bookmarkIdList.toString())
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: BookmarkRVAdapter.Viewholder, position: Int) {
        holder.bindItems(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: ContentModel, key: String) {

            // 카테고리 영역텍스트에서 아이템 클릭하게 하는 코드
            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()
                // 액티비티 이동
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            // keyList가 bookmark 를 포함하고 있을때의 조건문
            if(bookmarkIdList.contains(key)) {
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

            // 북마크 클릭하는 코드
            // 북마크 한 컨텐츠만 가져오기위해 지워줘야함
//            bookmarkArea.setOnClickListener {
//                Log.d("BookmarkRVAdapter", FBAuth.getUid())
//                Toast.makeText(context, key, Toast.LENGTH_SHORT).show()
//
//
//                if(bookmarkIdList.contains(key)) {
//                    // 북마크가 있을 때 다시 누르면 데이터베이스에서 삭제
//                    //bookmarkIdList.remove(key)      // 북마크표시색깔도 해제
//
//                    FBRef.bookmarkRef
//                        .child(FBAuth.getUid())
//                        .child(key)
//                        .removeValue()
//
//                } else {
//                    // 북마크가 없을 때
//                    //북마크 데이터모델 파이어베이스에 집어넣어주기
//                    FBRef.bookmarkRef
//                        .child(FBAuth.getUid())
//                        .child(key)
//                        .setValue(BookmarkModel(true))
//
//                }
//            }

            contentTitle.text = item.title

            // 그전에 위의 class에서 context 변수 선언하기기
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)         // imageurl을 읽어와서 imageViewArea에 집어넣겠다

        }
    }
}