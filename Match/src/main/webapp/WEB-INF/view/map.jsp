<%@page import="java.util.List"%>
<%@page import="map.model.Place"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
List<Place> list=(List<Place>)session.getAttribute("list");
System.out.println(list.get(0).getF_name()+":"+list.get(0).getF_addr());
System.out.println(session.getAttribute("authUser"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>주소로 장소 표시하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
</head>
<body>

<form method="post" action="reserve.do" >
<input type="hidden" id="placeName" name="placeName" value="">
</form>

<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9139ecf68b85217bc0cf45262df346ce&libraries=services"></script>

<script>


var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 9 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

function searchLocation(address, name) {
// 주소로 좌표를 검색합니다
geocoder.addressSearch(address, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords,    
            clickable:true
        });
        
     	// 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'click', function() {
             	var placeName =document.getElementById('placeName');
             	placeName.setAttribute('value',name);
             	$("form").submit();
             	
        });
     	
        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+name+'</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});  
}


<%
for(int j=0; j<list.size(); j++) {
%>
searchLocation('<%=list.get(j).getF_addr()%>', '<%=list.get(j).getF_name()%>');
<%
}
%>

</script>
</body>
</html>