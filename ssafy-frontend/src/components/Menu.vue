<template>
  <div>
    <v-card elevation="1">
      <v-tabs v-model="tab" background-color="teal lighten-1" dark>
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
import EventBus from "../EventBus";
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
        // console.log(store.state.lon + store.state.lat);
        var coords = new kakao.maps.LatLng(store.state.lat, store.state.lon);
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
        for (var i = 0; i < store.state.res.length; i++) {
          if (store.state.res[i].category === that.MenuList[0].food) {
            //위도경도 가져와서 하나씩 표시...
            var imageSrc =
              "https://image.flaticon.com/icons/svg/1046/1046751.svg";
            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(40, 50);
            storeId[i] = store.state.res[i].id;
            // 마커 이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
            var marker = new kakao.maps.Marker({
              map: map, // 마커를 표시할 지도
              position: new kakao.maps.LatLng(
                store.state.res[i].latitude,
                store.state.res[i].longitude
              ), // 마커를 표시할 위치
              title: store.state.res[i].name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              image: markerImage,
              clickable: true
            });
            marker.setMap(map);

            var iwContent =
              '<div class = "aa">' +
              '<div class = "info">' +
              '        <div class="title">' +
              store.state.res[i].name +
              "        </div>" +
              '        <div class="body">' +
              '            <div class="content">' +
              store.state.res[i].address +
              "           </div>" +
              "        </div>" +
              "        </div>" +
              "</div>";

            var infowindow = new kakao.maps.InfoWindow({
              content: iwContent
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
            EventBus.$emit("orderp", "치킨");
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
          var coords = new kakao.maps.LatLng(store.state.lat, store.state.lon);
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
          for (var i = 0; i < store.state.res.length; i++) {
            if (store.state.res[i].category === that.MenuList[idx].food) {
              var position = new kakao.maps.LatLng(
                store.state.res[i].latitude,
                store.state.res[i].longitude
              );
              storeId[i] = store.state.res[i].id;
              console.log(store.state.res[i].latitude);
              console.log(store.state.res[i].longitude);
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
                '<div class = "aa" style="">' +
                '<div class = "info">' +
                '        <div class="title">' +
                store.state.res[i].name +
                "        </div>" +
                '        <div class="body">' +
                '            <div class="content">' +
                store.state.res[i].address +
                "           </div>" +
                "           </div>" +
                "        </div>" +
                "        </div>" +
                "</div>";
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
      EventBus.$emit("orderp", this.MenuList[idx].food);
    }
  }
};
</script>
<style>
.aa * {
  padding: 0;
  margin: 0;
  background: white;
}
.info {
  /* width: 280px; */
  /* border-radius: 5px; */
  /* border-bottom: 6px solid white; */
  /* border-right: 1px solid white; */
  overflow: hidden;
  background: white;
  border-radius: 5px;
  box-shadow: 2px 2px 4px 0 rgba(0, 0, 0, 0.2),
    -2px -2px 4px 0 rgba(0, 0, 0, 0.2), 3px 3px 9px 0 rgba(0, 0, 0, 0.14),
    -3px -3px 9px 0 rgba(0, 0, 0, 0.14);
}
.title {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding: 0px 0px 0px 10px;
  height: 35px;
  background: white;
  /* border-bottom: 1px solid white; */
  font-size: 18px;
  font-weight: bold;
}
.content {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding: 5px 5px 5px 10px;
  height: 30px;
  /* border-bottom: 1px solid white; */
  background: white;
}
.body {
  position: relative;
  overflow: hidden;
  background: white;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
  background: white;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  background: white;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  /* margin-top: -2px; */
  background: white;
}
</style>