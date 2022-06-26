package com.eunbin.mysolelife.board

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.eunbin.mysolelife.R
import com.eunbin.mysolelife.utils.FBAuth

class BoardListLVAdapter(val boardList : MutableList<BoardModel>) : BaseAdapter() {

    override fun getCount(): Int {
        return boardList.size
    }

    override fun getItem(position: Int): Any {
        return boardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
//            if(view == null) { // view 를 재활용하면서 생기는 이슈
        view = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent,false)
//        }

        val itemLinearLayoutView = view?.findViewById<LinearLayout>(R.id.itemView)

        // 작성한 타이틀, 컨텐츠, 시간을 가져오는 코드
        val title = view?.findViewById<TextView>(R.id.titleArea)
        val content = view?.findViewById<TextView>(R.id.contentArea)
        val time = view?.findViewById<TextView>(R.id.timeArea)

        //내가 쓴 게시물은 따로 게시물이 회색으로 보이도록 하기
        if(boardList[position].uid.equals(FBAuth.getUid())) {
            itemLinearLayoutView?.setBackgroundColor(Color.parseColor("#ffa500"))
        }

        title!!.text = boardList[position].title        // 작성한 타이틀내용물 가져오기 코드
        content!!.text = boardList[position].content
        time!!.text = boardList[position].time

        return view!!
    }
}