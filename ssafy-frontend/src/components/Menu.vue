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
export default {
  mounted() {
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
        // console.log(result[0].y + " " + result[0].x);
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
          content:
            '<div style="width:150px;text-align:center;padding:6px 0;">배달 받을 곳</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
        for (var i = 0; i < store.state.res.data.length; i++) {
          if (store.state.res.data[i].category === that.MenuList[0].food) {
            console.log(store.state.res.data[i].latitude);
            console.log(store.state.res.data[i].longitude);
            //위도경도 가져와서 하나씩 표시...
            var marker = new kakao.maps.Marker({
              map: map, // 마커를 표시할 지도
              position: new kakao.maps.LatLng(
                store.state.res.data[i].latitude,
                store.state.res.data[i].longitude
              ), // 마커를 표시할 위치
              title: store.state.res.data[i].name // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            });
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
      ]
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
          // console.log(result[0].y + " " + result[0].x);
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords
          });
          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">배달 받을 곳</div>'
          });
          infowindow.open(map, marker);
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
          //마커표시 -> 전체 데이터중에서 현재클릭된 카테고리들 위도 경도 가져와서 표시
          // console.log(MenuList[idx].food);
          // console.log(that.MenuList[idx].food);
          for (var i = 0; i < store.state.res.data.length; i++) {
            if (store.state.res.data[i].category === that.MenuList[idx].food) {
              console.log(store.state.res.data[i].latitude);
              console.log(store.state.res.data[i].longitude);
              //위도경도 가져와서 하나씩 표시...
              // 마커 이미지의 이미지 크기 입니다
              // 마커 이미지를 생성합니다
              if (that.MenuList[idx].food === "치킨") {
              } else if (that.MenuList[idx].food === "피자") {
              } else if (that.MenuList[idx].food === "한식") {
              } else if (that.MenuList[idx].food === "분식") {
              } else if (that.MenuList[idx].food === "일식") {
              } else if (that.MenuList[idx].food === "디저트") {
              } else if (that.MenuList[idx].food === "패스트푸드") {
              } else if (that.MenuList[idx].food === "야식") {
              } else if (that.MenuList[idx].food === "중국집") {
              } else if (that.MenuList[idx].food === "족발,보쌈") {
              }

              // 마커를 생성합니다
              var marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: new kakao.maps.LatLng(
                  store.state.res.data[i].latitude,
                  store.state.res.data[i].longitude
                ), // 마커를 표시할 위치
                title: store.state.res.data[i].name // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              });
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
</style>