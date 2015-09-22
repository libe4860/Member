/**
 * home.js
 * 
 * DOM (Document Object Model : 문서를 객체로 해석해서 처리하는 모델 / 태그기반은 다 DOM / ex)html,xml)
 * 	Tree형태의 구조로 되어있음
 * 
 * Selector 표현식 ==> DOM에서 객체(Element, Tag)를 찾는 표현식
 * 1. 태그이름으로 찾기 : <button></button> 			   ==> button  
 * 2. class로 찾기   : <button class="btn"></button> ==> .btn
 * 3. id로 찾기 	   : <button id="btn"></button>    ==> #btn
 * 
 * jQuery 함수 : jQuery()
 * jQuery() 함수가 리턴하는 Type ==> jQuery Wrapper 객체 
 * 							==> jQuery 객체 
 * 						    ==> jQuery 집합객체
 * 
 *  jQuery() as $()
 */
function xxx(event) {
//	alert("button click...");
	console.log("button click... event=" + event);
//	$('img').slideToggle(1000);
	$('img').fadeToggle(1000);
};

$(function(){
	$('span').click(xxx).draggable(); //메서드 체인 방식
	$('img').draggable();
});

