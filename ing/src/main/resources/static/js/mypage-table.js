/**
 * 스크랩 관련 공통 모듈 정의
 */


/**
 * 스크랩 삭제 후 해당 태그 삭제
 * @param {Number} articleId - 스크랩을 추가할 기사 (cf. 사용자는 Spring Security를 통해 식별)
 * @param {String} elementId - 스크랩 삭제 후 기사 목록에서 제거할 <td> 요소의 Id 값  
 */
function deleteScrap(articleId, elementId){
    axios({
            method: "DELETE",
            url: "/scrap",
            params: {
                articleId: articleId,
            }
          })
          .then(function (response) {
            if(response.data){               
                document.getElementById(elementId).remove();
            }           
          })         
 }
