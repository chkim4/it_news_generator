/**
 * 스크랩 관련 공통 함수 정의
 */

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
 * @param {Number} articleId - 스크랩을 삭제할 기사 (cf. 사용자 정보는 Spring Security를 통해 식별)
 * @param {Object} element - 스크랩 추가 후 추가 버튼으로 바뀔 버튼 요소
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
 * 스크랩 추가 혹은 삭제 후 버튼 바꾸는 함수 (추가 <-> 삭제) 
 * @param {Number} articleId - 스크랩 상태를 변경할 기사 (cf. 사용자 정보는 Spring Security를 통해 식별)
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
       
        element.setAttribute("class", "btn delete-btn");              
        element.setAttribute("value", "삭제");              
        element.removeAttribute('onclick');            
        element.addEventListener("click", function(){deleteScrap(articleId, element)}, false);          
    }
}   
