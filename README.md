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

 
<br>
- ** Auth 로그인 기능 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/auth/IntroActivity.kt)
<br>
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
  
Contents 데이터모델을 생성하고 Firebase database에 IF문을 활용하여 카테고리를 분류하여 database.getReference를 선언해 데이터를 넣어준다
  
recyclerview를 사용해 컨텐츠들을 Grid를 사용해 2열로 나열하여 보여준다. 여기서 IF문을 활용해 북마크 체크표시를 하게되면, firebase database에 데이터를 저장한다  

  
 
- ** 북마크 카테고리에 북마크목록 저장 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/fragments/BookmarkFragment.kt)
북마크체크로 저장된 컨텐츠들은 북마크 액티비티에 저장되어 보여진다
  
콘텐츠Adapter 액티비티를 통해 intent.putExtra를 사용해 북마크 액티비티에서 컨텐츠 클릭시 해당 컨텐츠의 webUrl로 연결해준다.WebUrl은 ContentsShow 액티비티에서 보여준다
  
### 4.4. 웹컨텐츠로 연결
![카테고리화면](https://user-images.githubusercontent.com/103995985/175893463-f1fcca32-5122-486b-b2bc-b1a9cdede6ae.png) ![웹뷰화면](https://user-images.githubusercontent.com/103995985/175893493-944f207a-7d5d-41c9-8c21-01bead041687.png)

- ** 스토어 이미지바 클릭시 웹뷰로 이동 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/fragments/StoreFragment.kt)
  
스토어 액티비티에서는 webView.loadUrl 사용해 ImageView 클릭시 해당Url로 연결된다. 따로 스토어주소가 준비되어 있지않아 네이버주소로 연결된다
 


### 4.5. 게시판 생성, 삭제, 수정
  ![게시판](https://user-images.githubusercontent.com/103995985/175895032-983abde5-2ab1-451d-8d9a-63cad7b5331b.png) ![내가쓴게시글](https://user-images.githubusercontent.com/103995985/175895070-4c93fc71-82e6-4cc7-94a4-79c74cc220f2.png)

  
  
- ** 게시판 목록 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/fragments/TalkFragment.kt)
  
![게시글](https://user-images.githubusercontent.com/103995985/175895390-3aa7bb3b-d15e-4cf1-b489-a6f85a2a8f15.png) ![게시글수정삭제](https://user-images.githubusercontent.com/103995985/175895407-32d71d99-0b1b-4228-a873-b59321a2cdac.png)

  
- ** 타인이 쓴 게시물과 내가 쓴 게시물 분리 ** :pushpin: [코드 확인](https://github.com/EUNBINs/project_1/blob/0bb6ce92a040bf58e9b2b0db1d7115badff368dc/app/src/main/java/com/eunbin/mysolelife/board/BoardInsideActivity.kt)

### 4.6. 댓글 기능
![댓글1](https://user-images.githubusercontent.com/103995985/175895593-1042eeba-1939-4dfc-bd5c-259a7b79218f.png) ![댓글2](https://user-images.githubusercontent.com/103995985/175895614-c41b5e53-7fdd-4e4f-a0d2-3bd250810543.png)


- ** 게시물에 댓글 기능 ** :pushpin: [코드 확인]()
  



</div>
</details>

</br>

## 5. 핵심 트러블 슈팅
### 5.1. 컨텐츠 필터와 페이징 처리 문제
- 저는 이 서비스가 페이스북이나 인스타그램 처럼 가볍게, 자주 사용되길 바라는 마음으로 개발했습니다.  
때문에 페이징 처리도 무한 스크롤을 적용했습니다.

- 하지만 [무한스크롤, 페이징 혹은 “더보기” 버튼? 어떤 걸 써야할까](https://cyberx.tistory.com/82) 라는 글을 읽고 무한 스크롤의 단점들을 알게 되었고,  
다양한 기준(카테고리, 사용자, 등록일, 인기도)의 게시물 필터 기능을 넣어서 이를 보완하고자 했습니다.

- 그런데 게시물이 필터링 된 상태에서 무한 스크롤이 동작하면,  
필터링 된 게시물들만 DB에 요청해야 하기 때문에 아래의 **기존 코드** 처럼 각 필터별로 다른 Query를 날려야 했습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 Top10 (기준: 댓글 수 + 좋아요 수)
 * @return 인기순 상위 10개 게시물
 */
public Page<PostResponseDto> listTopTen() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "rankPoint", "likeCnt");
    return postRepository.findAll(pageRequest).map(PostResponseDto::new);
}

/**
 * 게시물 필터 (Tag Name)
 * @param tagName 게시물 박스에서 클릭한 태그 이름
 * @param pageable 페이징 처리를 위한 객체
 * @return 해당 태그가 포함된 게시물 목록
 */
public Page<PostResponseDto> listFilteredByTagName(String tagName, Pageable pageable) {

    return postRepository.findAllByTagName(tagName, pageable).map(PostResponseDto::new);
}

// ... 게시물 필터 (Member) 생략 

/**
 * 게시물 필터 (Date)
 * @param createdDate 게시물 박스에서 클릭한 날짜
 * @return 해당 날짜에 등록된 게시물 목록
 */
public List<PostResponseDto> listFilteredByDate(String createdDate) {

    // 등록일 00시부터 24시까지
    LocalDateTime start = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MIN);
    LocalDateTime end = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MAX);

    return postRepository
                    .findAllByCreatedAtBetween(start, end)
                    .stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
    }
~~~

</div>
</details>

- 이 때 카테고리(tag)로 게시물을 필터링 하는 경우,  
각 게시물은 최대 3개까지의 카테고리(tag)를 가질 수 있어 해당 카테고리를 포함하는 모든 게시물을 질의해야 했기 때문에  
- 아래 **개선된 코드**와 같이 QueryDSL을 사용하여 다소 복잡한 Query를 작성하면서도 페이징 처리를 할 수 있었습니다.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">

~~~java
/**
 * 게시물 필터 (Tag Name)
 */
@Override
public Page<Post> findAllByTagName(String tagName, Pageable pageable) {

    QueryResults<Post> results = queryFactory
            .selectFrom(post)
            .innerJoin(postTag)
                .on(post.idx.eq(postTag.post.idx))
            .innerJoin(tag)
                .on(tag.idx.eq(postTag.tag.idx))
            .where(tag.name.eq(tagName))
            .orderBy(post.idx.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
            .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
}
~~~

</div>
</details>

</br>

## 6. 그 외 트러블 슈팅
<details>
<summary>npm run dev 실행 오류</summary>
<div markdown="1">

- Webpack-dev-server 버전을 3.0.0으로 다운그레이드로 해결
- `$ npm install —save-dev webpack-dev-server@3.0.0`

</div>
</details>

<details>
<summary>vue-devtools 크롬익스텐션 인식 오류 문제</summary>
<div markdown="1">
  
  - main.js 파일에 `Vue.config.devtools = true` 추가로 해결
  - [https://github.com/vuejs/vue-devtools/issues/190](https://github.com/vuejs/vue-devtools/issues/190)
  
</div>
</details>

<details>
<summary>ElementUI input 박스에서 `v-on:keyup.enter="메소드명"`이 정상 작동 안하는 문제</summary>
<div markdown="1">
  
  - `v-on:keyup.enter.native=""` 와 같이 .native 추가로 해결
  
</div>
</details>

<details>
<summary> Post 목록 출력시에 Member 객체 출력 에러 </summary>
<div markdown="1">
  
  - 에러 메세지(500에러)
    - No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.SerializationFeature.FAIL_ON_EMPTY_BEANS)
  - 해결
    - Post 엔티티에 @ManyToOne 연관관계 매핑을 LAZY 옵션에서 기본(EAGER)옵션으로 수정
  
</div>
</details>
    
<details>
<summary> 프로젝트를 git init으로 생성 후 발생하는 npm run dev/build 오류 문제 </summary>
<div markdown="1">
  
  ```jsx
    $ npm run dev
    npm ERR! path C:\Users\integer\IdeaProjects\pilot\package.json
    npm ERR! code ENOENT
    npm ERR! errno -4058
    npm ERR! syscall open
    npm ERR! enoent ENOENT: no such file or directory, open 'C:\Users\integer\IdeaProjects\pilot\package.json'
    npm ERR! enoent This is related to npm not being able to find a file.
    npm ERR! enoent

    npm ERR! A complete log of this run can be found in:
    npm ERR!     C:\Users\integer\AppData\Roaming\npm-cache\_logs\2019-02-25T01_23_19_131Z-debug.log
  ```
  
  - 단순히 npm run dev/build 명령을 입력한 경로가 문제였다.
   
</div>
</details>    

<details>
<summary> 태그 선택후 등록하기 누를 때 `object references an unsaved transient instance - save the transient instance before flushing` 오류</summary>
<div markdown="1">
  
  - Post 엔티티의 @ManyToMany에 영속성 전이(cascade=CascadeType.ALL) 추가
    - JPA에서 Entity를 저장할 때 연관된 모든 Entity는 영속상태여야 한다.
    - CascadeType.PERSIST 옵션으로 부모와 자식 Enitity를 한 번에 영속화할 수 있다.
    - 참고
        - [https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218](https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218)
   
</div>
</details>    

<details>
<summary> JSON: Infinite recursion (StackOverflowError)</summary>
<div markdown="1">
  
  - @JsonIgnoreProperties 사용으로 해결
    - 참고
        - [http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html](http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html)
        - [https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue](https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue)
        
</div>
</details>  
    
<details>
<summary> H2 접속문제</summary>
<div markdown="1">
  
  - H2의 JDBC URL이 jdbc:h2:~/test 으로 되어있으면 jdbc:h2:mem:testdb 으로 변경해서 접속해야 한다.
        
</div>
</details> 
    
<details>
<summary> 컨텐츠수정 모달창에서 태그 셀렉트박스 드랍다운이 뒤쪽에 보이는 문제</summary>
<div markdown="1">
  
   - ElementUI의 Global Config에 옵션 추가하면 해결
     - main.js 파일에 `Vue.us(ElementUI, { zIndex: 9999 });` 옵션 추가(9999 이하면 안됌)
   - 참고
     - [https://element.eleme.io/#/en-US/component/quickstart#global-config](https://element.eleme.io/#/en-US/component/quickstart#global-config)
        
</div>
</details> 

<details>
<summary> HTTP delete Request시 개발자도구의 XHR(XMLHttpRequest )에서 delete요청이 2번씩 찍히는 이유</summary>
<div markdown="1">
  
  - When you try to send a XMLHttpRequest to a different domain than the page is hosted, you are violating the same-origin policy. However, this situation became somewhat common, many technics are introduced. CORS is one of them.

        In short, server that you are sending the DELETE request allows cross domain requests. In the process, there should be a **preflight** call and that is the **HTTP OPTION** call.

        So, you are having two responses for the **OPTION** and **DELETE** call.

        see [MDN page for CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).

    - 출처 : [https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o](https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o)
        
</div>
</details> 

<details>
<summary> 이미지 파싱 시 og:image 경로가 달라서 제대로 파싱이 안되는 경우</summary>
<div markdown="1">
  
  - UserAgent 설정으로 해결
        - [https://www.javacodeexamples.com/jsoup-set-user-agent-example/760](https://www.javacodeexamples.com/jsoup-set-user-agent-example/760)
        - [http://www.useragentstring.com/](http://www.useragentstring.com/)
        
</div>
</details> 
    
<details>
<summary> 구글 로그인으로 로그인한 사용자의 정보를 가져오는 방법이 스프링 2.0대 버전에서 달라진 것</summary>
<div markdown="1">
  
  - 1.5대 버전에서는 Controller의 인자로 Principal을 넘기면 principal.getName(0에서 바로 꺼내서 쓸 수 있었는데, 2.0대 버전에서는 principal.getName()의 경우 principal 객체.toString()을 반환한다.
    - 1.5대 버전에서 principal을 사용하는 경우
    - 아래와 같이 사용했다면,

    ```jsx
    @RequestMapping("/sso/user")
    @SuppressWarnings("unchecked")
    public Map<String, String> user(Principal principal) {
        if (principal != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
            logger.info("details = " + details);  // id, email, name, link etc.
            Map<String, String> map = new LinkedHashMap<>();
            map.put("email", details.get("email"));
            return map;
        }
        return null;
    }
    ```

    - 2.0대 버전에서는
    - 아래와 같이 principal 객체의 내용을 꺼내 쓸 수 있다.

    ```jsx
    UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder
                            .getContext().getAuthentication();
            Map<String, Object> map = (Map<String, Object>) token.getPrincipal();

            String email = String.valueOf(map.get("email"));
            post.setMember(memberRepository.findByEmail(email));
    ```
        
</div>
</details> 
    
<details>
<summary> 랭킹 동점자 처리 문제</summary>
<div markdown="1">
  
  - PageRequest의 Sort부분에서 properties를 "rankPoint"를 주고 "likeCnt"를 줘서 댓글수보다 좋아요수가 우선순위 갖도록 설정.
  - 좋아요 수도 똑같다면..........
        
</div>
</details> 
    
</br>

## 6. 회고 / 느낀점
>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/
