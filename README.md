# management-pjt
경영정보 시스템   

# 개발 환경
- InteliJ   
- Java 8   
- SpringBoot 2.1.7   
- JPA   
- Lombok   
- Gradle      

# 구동 방법
[Windows 환경에서 구동]
1. C:\.....\management-pjt>gradlew build    //프로젝트를 Clone한 경로에서 빌드 수행
2. C:\.....\management-pjt/build/libs>java -jar hello-spring-0.0.1-SNAPSHOT.jar    //빌드하여 생성 된 jar 파일을 구동   

# 구현 기능

- 특정 경로의 파일을 매일 자정에 조회하여 정보를 추출하여 DB에 저장한다.   
  * application.properties 설정값으로 파일경로, 구분자를 관리하여 추후 변경 시 대응 (input.file, input.delimeter)   
  * Spring Batch 및 스케쥴러를 사용하여 일 배치 구성
   
- 1번 기능에 의해 저장된 정보를 조회, 등록, 수정, 삭제하는 api 구현 (Restful API)   
   * 스프링 AOP 및 Logback을 활용한 API 호출정보 로그파일에 기록

1. 정보 등록   
<pre><code>
요청 예시 : [POST] localhost:8080/api/v1/management   
{   
    "dateTime": "2021-07-25 16",
    "registUserCnt": "10",
    "deleteUserCnt": "4",
    "paidAmount": "2,100",
    "usedAmount": "53,300",
    "salesAmount": "225,000"
}   
응답 예시 : "2021-07-25 16"
</code></pre>
2. 정보 조회   
<pre><code>
요청 예시 : [GET] localhost:8080/api/v1/management/2021-07-25 16   
응답 예시 :   
{
    "id": 4,
    "dateTime": "2021-07-25 16",
    "registUserCnt": "10",
    "deleteUserCnt": "4",
    "paidAmount": "2,100",
    "usedAmount": "53,300",
    "salesAmount": "225,000"
}   
</code></pre>
3. 정보 수정  
<pre><code> 
요청 예시 : [PATCH]  localhost:8080/api/v1/management/2021-07-25 16   
{   
    "id": 4,   
    "dateTime": "2021-07-25 16",   
    "paidAmount": "9,100",   
    "usedAmount": "93,300",   
    "salesAmount": "925,000"   
}    
응답 예시 : "2021-07-25 16"
</code></pre>   
4. 정보 삭제
<pre><code> 
요청 예시 : [DELETE] localhost:8080/api/v1/management/2021-07-25 16   
응답 예시 : "2021-07-25 16"   
</code></pre> 
