<template>
  <div>
    <v-card elevation="24">
      <v-tabs v-model="tab">
        <v-tab
          v-for="item in MenuList"
          :key="item.index"
          @click="mapapi(item.index-1)"
        >{{item.food}}</v-tab>
      </v-tabs>
      <Map></Map>
      <v-row>
        <!-- <tbody>
          <tr v-for="(value, index) in this.$store.state.res.data" :key="index">
            <div v-if="value.category === MenuList[menuIdx].food">
              <td>{{value.name}}</td>
              <td>{{value.address}}</td>
            </div>
          </tr>
        </tbody>-->
      </v-row>
    </v-card>
  </div>
</template>

<script>
import axios from "axios";
import Map from "../components/Map";
import store from "@/vuex/store.js";
import router from "../router";
export default {
  mounted() {
    var StoreId = "";
    const that = this;
    var mapContainer = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
    var mapOptions = {
      //지도를 생성할 때 필요한 기본 옵션
      center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
      level: 5 //지도의 레벨(확대, 축소 정도)
    };
    var map = new kakao.maps.Map(mapContainer, mapOptions); //지도 생성 및 객체 리턴

    var geocoder = new kakao.maps.services.Geocoder();
    geocoder.addressSearch(this.$store.state.userAddr, function(
      result,
      status
    ) {
      // 정상적으로 검색이 완료됐으면
      if (status == kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        var imageSrc = "https://image.flaticon.com/icons/svg/944/944572.svg",
          imageSize = new kakao.maps.Size(52, 47), // 마커이미지의 크기입니다
          imageOption = { offset: new kakao.maps.Point(27, 69) };
        var markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        );
        // console.log(result[0].y + " " + result[0].x);
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: coords,
          image: markerImage
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
        var storeId = [];
        for (var i = 0; i < store.state.res.data.length; i++) {
          if (store.state.res.data[i].category === that.MenuList[0].food) {
            console.log(store.state.res.data[i].latitude);
            console.log(store.state.res.data[i].longitude);
            //위도경도 가져와서 하나씩 표시...
            var imageSrc =
              "https://image.flaticon.com/icons/svg/1046/1046751.svg";
            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(40, 50);
            storeId[i] = store.state.res.data[i].id;
            // 마커 이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
            var marker = new kakao.maps.Marker({
              map: map, // 마커를 표시할 지도
              position: new kakao.maps.LatLng(
                store.state.res.data[i].latitude,
                store.state.res.data[i].longitude
              ), // 마커를 표시할 위치
              title: store.state.res.data[i].name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              image: markerImage,
              clickable: true
            });
            marker.setMap(map);
            var iwContent = store.state.res.data[i].name;
            var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
            var infowindow = new kakao.maps.InfoWindow({
              content: iwContent,
              removable: iwRemoveable
            });
            kakao.maps.event.addListener(
              marker,
              "mouseover",
              makeOverListener(map, marker, infowindow)
            );

            kakao.maps.event.addListener(
              marker,
              "mouseout",
              makeOutListener(infowindow)
            );

            kakao.maps.event.addListener(
              marker,
              "click",
              makeClickListener(map, marker, i)
            );

            // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
            function makeOverListener(map, marker, infowindow) {
              return function() {
                infowindow.open(map, marker);
              };
            }

            // 인포윈도우를 닫는 클로저를 만드는 함수입니다
            function makeOutListener(infowindow) {
              return function() {
                infowindow.close();
              };
            }

            function makeClickListener(map, marker, i) {
              return function() {
                this.StoreId = storeId[i];
                router.push({
                  path: `StoreInfoPage/${this.StoreId}`
                });
              };
            }
          }
        }
      }
    });
  },
  name: "Menu",
  components: {
    Map
  },
  data() {
    return {
      tab: null,
      menuIdx: 0,
      MenuList: [
        { index: "1", food: "치킨" },
        { index: "2", food: "피자" },
        { index: "3", food: "한식" },
        { index: "4", food: "분식" },
        { index: "5", food: "디저트" },
        { index: "6", food: "일식" },
        { index: "7", food: "패스트푸드" },
        { index: "8", food: "야식" },
        { index: "9", food: "중국집" },
        { index: "10", food: "족발,보쌈" }
      ],
      StoreId: ""
    };
  },
  methods: {
    mapapi(idx) {
      const that = this;
      var mapContainer = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var mapOptions = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 5 //지도의 레벨(확대, 축소 정도)
      };
      var map = new kakao.maps.Map(mapContainer, mapOptions); //지도 생성 및 객체 리턴
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(this.$store.state.userAddr, function(
        result,
        status
      ) {
        // 정상적으로 검색이 완료됐으면
        if (status == kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          var imageSrc = "https://image.flaticon.com/icons/svg/944/944572.svg",
            imageSize = new kakao.maps.Size(52, 47), // 마커이미지의 크기입니다
            imageOption = { offset: new kakao.maps.Point(27, 69) };
          var markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
            imageOption
          );
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
            image: markerImage
          });
          map.setCenter(coords);
          var storeId = [];
          for (var i = 0; i < store.state.res.data.length; i++) {
            if (store.state.res.data[i].category === that.MenuList[idx].food) {
              var position = new kakao.maps.LatLng(
                store.state.res.data[i].latitude,
                store.state.res.data[i].longitude
              );
              storeId[i] = store.state.res.data[i].id;
              console.log(store.state.res.data[i].latitude);
              console.log(store.state.res.data[i].longitude);
              if (that.MenuList[idx].food === "치킨") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/1046/1046751.svg";
              } else if (that.MenuList[idx].food === "피자") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/1404/1404945.svg";
              } else if (that.MenuList[idx].food === "한식") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/1357/1357274.svg";
              } else if (that.MenuList[idx].food === "분식") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/2090/2090251.svg";
              } else if (that.MenuList[idx].food === "일식") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/2243/2243653.svg";
              } else if (that.MenuList[idx].food === "디저트") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/2234/2234886.svg";
              } else if (that.MenuList[idx].food === "패스트푸드") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/1365/1365577.svg";
              } else if (that.MenuList[idx].food === "야식") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/414/414840.svg";
              } else if (that.MenuList[idx].food === "중국집") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/678/678935.svg";
              } else if (that.MenuList[idx].food === "족발,보쌈") {
                var imageSrc =
                  "https://image.flaticon.com/icons/svg/1702/1702817.svg";
              }
              var imageSize = new kakao.maps.Size(40, 50);
              var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
              var marker = new kakao.maps.Marker({
                position: position, // 마커를 표시할 위치
                image: markerImage,
                clickable: true
              });
              marker.setMap(map);
              var iwContent =
                store.state.res.data[i].name + store.state.res.data[i].point;
              var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
              var infowindow = new kakao.maps.InfoWindow({
                content: iwContent
                // clickable: true
              });

              kakao.maps.event.addListener(
                marker,
                "mouseover",
                makeOverListener(map, marker, infowindow)
              );

              kakao.maps.event.addListener(
                marker,
                "mouseout",
                makeOutListener(infowindow)
              );

              kakao.maps.event.addListener(
                marker,
                "click",
                makeClickListener(map, marker, i)
              );

              // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
              function makeOverListener(map, marker, infowindow) {
                return function() {
                  infowindow.open(map, marker);
                };
              }

              // 인포윈도우를 닫는 클로저를 만드는 함수입니다
              function makeOutListener(infowindow) {
                return function() {
                  infowindow.close();
                };
              }

              function makeClickListener(map, marker, i) {
                return function() {
                  this.StoreId = storeId[i];
                  router.push({
                    path: `StoreInfoPage/${this.StoreId}`
                  });
                };
              }
            }
          }
        }
      });
      this.menuIdx = idx;
    }
  }
};
</script>
<style>
.wrap {
  /* position: absolute; */
  /* left: 0; */
  /* bottom: 40px; */
  /* width: 288px; */
  /* height: 132px; */
  /* margin-left: -144px; */
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>