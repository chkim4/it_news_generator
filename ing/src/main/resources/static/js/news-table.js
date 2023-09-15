/**
 * 스크랩 추가
 * @param {Number} articleId - 스크랩을 추가할 기사 (cf. 사용자는 Spring Security를 통해 식별)
 * @param {Object} element - 스크랩 추가 후 삭제 버튼으로 바뀔 버튼 요소
 */
 function insertScrap(articleId, element){
    
    axios({
            method: "POST",
            url: "/scrap",
            params: {
                articleId: articleId,
            }
          })
          .then(function (response) {

            if(response.data){           
               changeButtonAttribute(articleId, element, "delete");             
            }
          })         
 }

/**
 * 스크랩 삭제
 * @param {Number} articleId - 스크랩을 추가할 기사 (cf. 사용자는 Spring Security를 통해 식별)
 * @param {Object} element - 스크랩 추가 후 삭제 버튼으로 바뀔 버튼 요소
 */
 function deleteScrap(articleId, element){
    
    axios({
            method: "DELETE",
            url: "/scrap",
            params: {
                articleId: articleId,
            }
          })
          .then(function (response) {
            if(response.data){
                changeButtonAttribute(articleId, element, "insert");
            }
            
          })         
 }

/**
 * 스크랩 버튼 변경 (스크랩 <-> 삭제). 스크랩 삭제와 추가 버튼 후 바뀌는 함수 간 중복이 많아서 별도 정의 
 * @param {Number} articleId - 스크랩 상태를 변경할 기사 (cf. 사용자는 Spring Security를 통해 식별)
 * @param {Object} element - 스크랩 상태를 변경할 버튼 요소
 * @param {String} buttonType - "insert": 스크랩 추가 버튼으로 변경 / "delete": 스크랩 제거 버튼으로 변경
 */ 
function changeButtonAttribute(articleId, element, buttonType){
    
    // 스크랩 추가 버튼으로 변경
    if(buttonType === "insert"){
        element.setAttribute("class", "btn");              
        element.setAttribute("value", "스크랩");              
        element.removeAttribute('onclick');            
        element.addEventListener("click", function(){insertScrap(articleId, element)}, false);   
    }
    // 스크랩 삭제 버튼으로 변경
    else{
        alert("button changed to delete");
        
        element.setAttribute("class", "btn delete-btn");              
        element.setAttribute("value", "삭제");              
        element.removeAttribute('onclick');            
        element.addEventListener("click", function(){deleteScrap(articleId, element)}, false);          
    }
}   
