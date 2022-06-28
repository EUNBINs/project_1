# :pushpin: 자취생 커뮤니티 앱
> 자취생을 위한 정보 공유 및 커뮤니티 앱 [클론코딩]


</br>

## 1. 제작 기간 & 참여 인원
- 2022년 6월 3일 ~ 6월 24일
- 개인 프로젝트

</br>

## 2. 사용 기술
#### `anroid`
  - Kotlin
  - androidStudio
  - Firebase
  

</br>

## 3. 핵심 기능
> Firebase의 Auth를 이용한 로그인, 로그아웃 기능
>
> Firebase의 Database와 Storage 를 사용해 게시글 및 이미지를 생성, 읽기, 수정, 삭제 기능을 구현
>
> Webview를 활용해 앱을 통해 웹상에 있는 컨텐츠와 연결을 도와주고, 북마크하는 기능


<br>
이 앱은 로그인을 통해 앱을 접속하고, 자취생을 위한 정보를 웹상의 컨텐츠와 연결을 돕고, 이용자들끼리 게시판을 통해

게시글 업로드를 통해 정보를 공유하며, 댓글기능으로 의사소통을 가능하게 합니다.

또한, 북마크 기능으로 유용한 웹컨텐츠는 저장을 통하여 편리하게 볼 수 있도록 도와줍니다.






<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1. 메인 화면 & 로그인 화면
  
![스플래쉬화면](https://user-images.githubusercontent.com/103995985/175888653-7179700e-df5e-476c-ad8d-164c73c24bc9.png)![메인화면](https://user-images.githubusercontent.com/103995985/175973297-a86adac7-b20f-4eee-a178-16ff35a2aa36.png)


- ** Auth 로그인 기능 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/master/app/src/main/java/com/eunbin/mysolelife/auth/IntroActivity.kt)

파이어베이스에서 FirebaseAuth 객체의 공유 인스턴스를 가져와 익명로그인을 가능하게 하고, binding을 사용하여 각 버튼을 누를 시, 

연결된 액티비티(로그인이나 회원가입)로 화면이 전환됩니다.

로그인이 성공하게 되면, intent.flag를 통해 메인액티비티로 연결합니다.

- ** 앱 기본 화면 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/fragments/HomeFragment.kt)

navigation 구현을 통해 하단 imageview 바 마다 각각의 fragment로 연결시켜줍니다.

앱의 기본 메인화면이기 때문에 모든 카테고리와, 카테고리의 데이터, 게시판데이터들을 가져옵니다.

카테고리의 데이터는 layoutManager = GridLayoutManager(requireContext(), 2) 를 선언하여 2줄로 나열해 공간을 활용하여 보여주도록 합니다.


### 4.2. 북마크 체크인
![북마크화면](https://user-images.githubusercontent.com/103995985/175892726-69a23423-f00f-4f05-8774-a0eae8e79677.png) ![북마크저장화면](https://user-images.githubusercontent.com/103995985/175892759-74b9240d-52a9-42d4-b9db-cd87329b2909.png)

- **각각의 카테고리에 콘텐츠데이터 넣어주기 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/contentsList/ContentListActivity.kt)   
  
Contents 데이터모델을 생성하고 Firebase database에 IF문을 활용하여 카테고리를 분류하여 database.getReference를 선언해 데이터를 넣어줍니다
  
recyclerview를 사용해 컨텐츠들을 Grid를 사용해 2열로 나열하여 보여주고,

여기서 IF문을 활용해 북마크 체크표시를 하게되면, firebase database에 데이터를 저장합니다

  
 
- ** 북마크 카테고리에 북마크목록 저장 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/fragments/BookmarkFragment.kt)

북마크 체크표시와 함께 저장된 컨텐츠들은 Activity에 정렬되어 보여집니다.
  
Adapter로 연결해주고, intent.putExtra를 사용해 북마크 액티비티에서 컨텐츠 클릭시 해당 컨텐츠의 webUrl로 연결해준다.

WebUrl은 또 다른 Activity를 생성해 ContentsShow 액티비티에서 보여줍니다.
  
### 4.4. 웹컨텐츠로 연결
![카테고리화면](https://user-images.githubusercontent.com/103995985/175893463-f1fcca32-5122-486b-b2bc-b1a9cdede6ae.png) ![웹뷰화면](https://user-images.githubusercontent.com/103995985/175893493-944f207a-7d5d-41c9-8c21-01bead041687.png)

- ** 스토어 이미지바 클릭시 웹뷰로 이동 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/fragments/StoreFragment.kt)
  
스토어 액티비티에서는 webView.loadUrl 사용해 ImageView 클릭시 해당Url로 연결됩니다. 따로 스토어주소가 준비되어 있지않아 네이버주소로 설정되었습니다.
 


### 4.5. 게시판 생성, 삭제, 수정
  ![게시판](https://user-images.githubusercontent.com/103995985/175895032-983abde5-2ab1-451d-8d9a-63cad7b5331b.png) ![내가쓴게시글](https://user-images.githubusercontent.com/103995985/175895070-4c93fc71-82e6-4cc7-94a4-79c74cc220f2.png)
  
  
- ** 게시판 목록 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/fragments/TalkFragment.kt)

board데이터모델을 형성하고, Key값으로 구분하여 생성,삭제,수정한 게시물데이터는 Firebase database에 저장합니다.

ListView와 Adapter를 이용해 생성된 게시물들을 리스트형태로 Fragment에 보여주고, 

FBAuth의 uid 를 활용해 본인의 게시물은 주황색으로 표시되도록 보여줍니다.

  
![게시글](https://user-images.githubusercontent.com/103995985/175895390-3aa7bb3b-d15e-4cf1-b489-a6f85a2a8f15.png) ![게시글수정삭제](https://user-images.githubusercontent.com/103995985/175895407-32d71d99-0b1b-4228-a873-b59321a2cdac.png)

  
- ** 타인이 쓴 게시물과 내가 쓴 게시물 분리 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/board/BoardInsideActivity.kt)

게시판의 데이터리스트 중에서 한 개의 게시물 클릭시, Firebase에서 데이터id를 기반으로 저장된 database를 가져와 보여주고,

visibility="invisible" 를 이용해 내가 쓴 게시물에서만 Dialog가 실현될 수 있도록 설정해줍니다.

Dialog가 실현되면 내가 작성한 게시물의 수정,삭제가 가능합니다.

- ** 게시물 이미지 업로드 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/board/BoardWriteActivity.kt)

Firebas의 storage를 이용해 이미지를 업로드하고 불러올 수 있도록 해줍니다

이미지를 key값으로 저장하여 불러올 때 보다 쉽게 불러오도록 이름을 부여하여 Storage에 저장합니다.



### 4.6. 댓글 기능
![댓글1](https://user-images.githubusercontent.com/103995985/175895593-1042eeba-1939-4dfc-bd5c-259a7b79218f.png) ![댓글2](https://user-images.githubusercontent.com/103995985/175895614-c41b5e53-7fdd-4e4f-a0d2-3bd250810543.png)


- ** 게시물에 댓글 기능 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/comment/CommentLVAdatper.kt)

마찬가지로 Firebase의 database에 comment레퍼런스를 생성하고, 댓글 입력시 값을 저장하여, 

Adapter를 이용해 database로부터 게시물로 comment 데이터를 불러옵니다.



</div>
</details>

</br>

## 4. 겪었던 오류들 
### 4.1 레이아웃에서 컬러적용이 되지 않았을 때
- res>values>themes에서 "Theme.AppCompat.Light" 로 바꿔주기

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">


~~~java
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Theme.MySoleLife" parent="Theme.AppCompat.Light">
~~~


</div>
</details>


### 4.2 게시물을 수정했을 때, Firebase에 동일하게 2번 값이 저장되는 상황 발생
- 파이어베이스의 특성상, 데이터가 변경되면 데이터가 두 번 생성된다고 하였습니다.
  때문에 데이터리스트에 clear()를 선언하여, 기존데이터를 clear 해주고, 변경된 데이터와 함께 새로운 코드로 리셋해줍니다.
<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

~~~java

commentDataList.clear()

~~~

</div>
</details>

### 4.3. 게시물 작성 후 게시물 리스트순서가 뒤죽박죽 되는 상황 발생
- view를 재활용하면서 생기게 된 이슈라고 합니다. 그래서 null을 주석처리 하였습니다.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">


~~~java 
       var view = convertView
  //          if(view == null) { // view 를 재활용하면서 생기는 이슈
        view = LayoutInflater.from(parent?.context).inflate(R.layout.board_list_item, parent,false)
  //      }

~~~


</div>
</details>

</br> 

  
## 5. 기억에 남았던 코드들
 


<details>
<summary> 해당Activity에서 binding 할때에는 반드시 해당 xml 전체를 layout으로 감싸줘야 한다 </summary>
<div markdown="1">
 
  
</div>
</details>

<details>
<summary>Calendar.getInstance() 을 활용</summary>
<div markdown="1">
  
 -SimpleDateFormat을 활용해 한국 시간과 시간표시패턴을 표현할 수 있었다
 -Locale 이란 사용자 언어 국가 및 장소 설정
 

</div>
</details>

<details>
<summary> 리스트를 최신순으로 정렬하고 싶다면 reverse() 선언 </summary>
<div markdown="1">
  
-boardKeyList.reverse()
 boardDataList.reverse()

  
</div>
</details>
    
<details>
<summary> 안드로이드폰에서 이미지를 업로드하고 싶다면 manifest에서 external_storage권한 추가해줘야 한다 </summary>
<div markdown="1">
  
-
   
</div>
</details>    

<details>
<summary>Activity를 호출하다 보면 중복문제가 발생할 수 있기때문에 startactivity(intent)보다 intent.flags를 활용하면
 중복을 방지하거나 흐름제어에 더 유용할 수 있다.</summary>
<div markdown="1">
  
 -
</div>
</details>    

<details>
  
<summary>리스트를 업데이트할 수 있는 5가지 방법</summary>
 - 리스트들의 삭제, 변경이 이루어지는 경우 업데이트를 해야한다 게시글리스트들을 reverse() 하였기에 전체업데이트를 실행
  
- 전체 업데이트 : notifyDataSetChanged
 
- 변경 : notifyItemChanged / notifyItemRangeChanged
 
- 추가 : notifyItemInserted / notifyItemRangeInserted
 
- 삭제 : notifyItemRemoved / notifyItemRangeRemoved
 
- 이동 : notifyItemMoved

<div markdown="1>
               
               
</div>
               
</details>
    
</br>

## 6. 회고 / 느낀점
자바를 공부하고, 안드로이드스튜디오를 사용해 작은 앱들을 만들어보면서 활용법을 익힌 후 처음으로 만든 프로젝트였던 만큼 설레는 출발이었고, 공을 많이 들였던 것 같습니다.
Adapter나 recyclerView 는 아직까지도 사용할 때마다 헷갈리고, 익숙하지 못하지만 반복학습을 통해 빠르게 손에 익히고 싶은 마음이 컸습니다.              
클래스의 새로운 인스턴스들을 접할때마 신기해하면서 코드를 작성한 순간이 몇몇 있는데, 더더욱 다양한 기술스택을 쌓아가고 싶습니다. 첫 발걸음이다 보니 오류가 났던 순간들을
메모하지 못한게 2,3가지 있었는데 다행히 큰 문제는 아니었는지 구글링과 stackoveflow를 통해 많은 도움을 얻었고, 한가지 적어내지 못한 오류가 있다면, 위의 프로젝트 배포코드를
비교하기 위하여 프로젝트를 오픈한적이 있는데, Gradle문제로 열리지 않았던 적이 있습니다. 초반에 검색을 통해 잘 나오지않아서 스페인유튜브를 보면서 번역하고, 오류를 고치려고           시도했던 기억이 남습니다. 
               
마지막엔 스택오버플로우의 도움으로 허무하게 해결되었지만 아직까지도 이 프로젝트 뿐만이 아니라 오류를 해결하기 위해 머리를 싸매는 순간이 많고,             
해결안되는 시간이 길어지다보니 이대로 프로젝트를 엎어야하는건 아닌가, 라는 부담감과 압박감을 느끼게 되는데 이때 잠시 리프레쉬시간을 가지면서 산책이나 반려견과 데이트하고나서         다시 시도해보면 해결되는 순간도 있었던 것 같습니다. 압박감에서 벗어나서 나무가 아닌 숲을보는 시야를 기르는 것도 중요하다고 생각합니다.
다음 프로젝트는 서버를 연동한 앱을 공부할 예정입니다.             

Ended on June 28, 2022



