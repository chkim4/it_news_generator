/**
 * 페이지네이션을 비동기로 처리하기 위한 함수 정의 (ex. 뉴스 영상 페이지 내 기사 목록)
 */

/**
 * 현재 페이지에 해당하는 페이지 세트를 화면에 출력
 * 각 파라미터 값은 NewsUtils.java의 getPaginationData 메소드 참고 
 */
 function getCurrentSet(firstPage, lastPage, defaultUrl, isNextSetExists, date){

    let tagStr = "";
    
    document.getElementById("pagination").innerHTML = "";
    
    // 이전 페이지 기호 '<<' 표시 여부 결정
    if (firstPage > 0) {
        
        let prevPage = firstPage-1;
        let previousPageURL = defaultUrl + "&page=" + prevPage;
          
        tagStr += '<li class="page-item">' + 
                      '<a class="page-link" onclick="getPageData(' + prevPage + ', \'' + date +'\')" aria-label="Previous">' + 
                           '<span aria-hidden="true">&laquo;</span> ' + 
                           '<span class="sr-only">Previous</span>' + 
                       '</a>' + 
                  '</li>';
    }
    
    // 현재 페이지 세트 설정
    for (let p = firstPage; p < lastPage; p++) {
        
       let currentPageURL = defaultUrl + "&page=" + p;
       
        tagStr += '<li class="page-item">' + 
                      '<a class="page-link" onclick="getPageData(' + p + ', \'' + date + '\')">' + 
                           (p+1) + 
                      '</a>' + 
                  '</li>';    
       
    }
    
    // 다음 페이지 기호 '>>' 표시 여부 결정
    if(isNextSetExists) {
        let nextPageURL = defaultUrl + "&page=" + lastPage;
      
        tagStr += '<li class="page-item">' + 
                  '<a class="page-link" onclick="getPageData(' + lastPage + ', \'' + date + '\')" aria-label="Next">' + 
                       '<span aria-hidden="true">&raquo;</span> ' + 
                       '<span class="sr-only">Next</span>' + 
                   '</a>' + 
                '</li>';
                        
    }             
     
    document.getElementById("pagination").innerHTML = tagStr;
}

/**
 * 특정 페이지의 데이터와 페이지네이션을 위한 정보를 비동기 방식으로 요청
 * 오늘 뉴스 페이지에서 기사 출처 조회 중 날짜의 변화로 인한 상황 방지
 * ex. 23:59:59에  오늘 뉴스 페이지의 출처 기사를 조회하며 서버에서 날짜를 계산할 경우, 그다음 날 기사를 반환할 수 있음
 * date가 필요없을 경우 null로 두어도 될 듯함
 * @param {Number} page - 호출할 페이지 번호
 * @param {String} date - 호출할 기사의 날짜
 */
function getPageData(page, date){

     axios({
            method: "GET",
            url: "/news-today-api",
            params: {
                page: page,
                date: date
            }
          })
          .then(function (response) {
              getTableData(JSON.parse(response.data.articles));
              getPageButtonData(response.data);
               
           });
    }

/**
 * 테이블에 출력할 태그 생성
 * @param {Object[]} articles - 화면에 출력할 기사들
 */
function getTableData(articles){

    let tagStr = "";
    for (let i = 0; i < articles.length; i++){

        tagStr += "<tr>";       
        
        tagStr +=   "<td>" + articles[i].ord + "</td>";
        tagStr +=   "<td>" + articles[i].summary + "</td>";
        tagStr +=   '<td><input type = "button" class = "btn" value = "기사 원본" ' + 
                        'onclick = "window.open(\'' + articles[i].url + '\');">' + 
                    '</td>';
        
        tagStr +=   '<td>';  
        
        if (articles[i].memberId == null) {
            
            tagStr += '<input type = "button" class = "btn" value = "스크랩" onclick="insertScrap(\'' + articles[i].articleId + '\', this)">'; 
        }
        else {
            
            tagStr += '<input type = "button" class = "btn delete-btn" value = "삭제" onclick="deleteScrap(\'' + articles[i].articleId + '\', this)">';
        }
        
        tagStr += '</td></tr>';  
    }
    
    document.getElementById("table-body").innerHTML = tagStr; 
}

/**
 * 화면 최하단에 출력할 페이지 이동 버튼 세트 생성
 * @param {Object} data - getPageDate 함수에서 서버에 요청하여 가져온 데이터
 */
function getPageButtonData(data){
   
    const firstPage = Number(data.firstPage);
    const lastPage = Number(data.lastPage);
    const defaultUrl = data.defaultUrl;
    const isNextSetExists = data.isNextSetExists;
    const date = data.date;
     
    getCurrentSet(firstPage, lastPage, defaultUrl, isNextSetExists, date); 

}
 