package com.eunbin.mysolelife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.eunbin.mysolelife.R
import com.eunbin.mysolelife.board.BoardInsideActivity
import com.eunbin.mysolelife.board.BoardListLVAdapter
import com.eunbin.mysolelife.board.BoardModel
import com.eunbin.mysolelife.board.BoardWriteActivity
import com.eunbin.mysolelife.databinding.FragmentTalkBinding
import com.eunbin.mysolelife.utils.FBRef
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class TalkFragment : Fragment() {

    private lateinit var binding : FragmentTalkBinding

    private val boardDataList = mutableListOf<BoardModel>()
    private val boardKeyList = mutableListOf<String>()

    private val TAG = TalkFragment::class.java.simpleName

    private lateinit var boardRVAdapter : BoardListLVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false)

        boardRVAdapter = BoardListLVAdapter(boardDataList)      //boardList에서 변경 / /val boardRVAdapter 였지만 위에서 선언했기에 val 제거
        binding.boardListView.adapter = boardRVAdapter

        // boardListview 클릭시 boardinside액티비티로 넘어가는 코드
        binding.boardListView.setOnItemClickListener { parent, view, position, id ->

            // 첫번째 방법 : listview에 있는 데이터 title,content,time 다 다른 액티비티로 전달해줘서 만들기
//            intent.putExtra("title", boardDataList[position].title)
//            intent.putExtra("content", boardDataList[position].content)
//            intent.putExtra("time", boardDataList[position].time)
//            startActivity(intent)

            // 두번째 방법 : Firebase에 있는 board에 대한 데이터의 id를 기반으로 다시 데이터를 받아오는 방법
            val intent = Intent(context, BoardInsideActivity::class.java)
            intent.putExtra("key", boardKeyList[position])
            startActivity(intent)

        }

        binding.writeBtn.setOnClickListener {
            val intent = Intent(context, BoardWriteActivity::class.java)
            startActivity(intent)
        }
        binding.hometap.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_homeFragment)
        }
        binding.bookmark.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_bookmarkFragment)
        }
        binding.store.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_storeFragment)
        }
        binding.goodtip.setOnClickListener {
            it.findNavController().navigate(R.id.action_talkFragment_to_tipFragment)
        }

        getFBBoardData()            // 데이터를 얻어내는 코드

        return binding.root
    }

    // 파이어베이스에서 boarddata 불러오는 코드
    private fun getFBBoardData() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                boardDataList.clear()                               // 파이어베이스 특징상 게시물이 변경되면 똑같은 내용물이 두번 생김. 때문에 clear로 정상작동 시켜주기
                // 기존데이터 clear() 해주고, 밑에 코드읽으면서 새로운 코드를 가져온다라는 과정

                for (dataModel in dataSnapshot.children) {
                    Log.d(TAG, dataModel.toString())

                    val item = dataModel.getValue(BoardModel::class.java)
                    boardDataList.add(item!!)
                    boardKeyList.add(dataModel.key.toString())
                }
                boardKeyList.reverse()
                boardDataList.reverse()                 // 받아온 데이터들을 뒤집겠다 (최신순)
                boardRVAdapter.notifyDataSetChanged()   //리스트 전체 업데이트

                Log.d(TAG, boardDataList.toString())
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
            }

        }

        FBRef.boardRef.addValueEventListener(postListener)

    }

}