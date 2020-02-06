<template>
  <div>
    <v-tabs v-model="tab">
      <v-tab v-for="item in MenuList" :key="item.index" @click="mapapi(item.index-1)">{{item.data}}</v-tab>
    </v-tabs>
    <Map></Map>

    <tbody>
      <tr v-for="(value, index) in this.$store.state.res.data" :key="index">
        <div v-if="MenuList[menuIdx].data==value.category">
          <td>{{value.name}}</td>
          <td>{{value.tel}}</td>
          <td>{{value.address}}</td>
          <td>{{value.category}}</td>
        </div>
      </tr>
    </tbody>
    <!-- <template>
      <thead>
        <tr>
          <td>가게이름</td>
          <td>전화번호</td>
          <td>주소</td>
          <td>카테고리</td>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(value, index) in this.$store.state.res.data" :key="index">
          <td>{{value.name}}</td>
          <td>{{value.tel}}</td>
          <td>{{value.address}}</td>
          <td>{{value.category}}</td>
        </tr>
      </tbody>
    </template>-->
  </div>
</template>

<script>
import axios from "axios";
import Map from "../components/Map";
import store from "@/vuex/store.js";
export default {
  name: "Menu",
  components: {
    Map
  },
  data() {
    return {
      tab: true,
      menuIdx: { default: 0 },
      MenuList: [
        { index: "1", data: "치킨" },
        { index: "2", data: "피자" },
        { index: "3", data: "한식" },
        { index: "4", data: "분식" },
        { index: "5", data: "디저트" },
        { index: "6", data: "일식" },
        { index: "7", data: "패스트푸드" },
        { index: "8", data: "야식" },
        { index: "9", data: "중국집" },
        { index: "10", data: "족발,보쌈" }
      ]
    };
  },
  methods: {
    mapapi(idx) {
      // alert("zzz" + idx);
      console.log(this.$store.state.res);
      var mapContainer = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var mapOptions = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
        level: 6 //지도의 레벨(확대, 축소 정도)
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
        }
      });
      this.menuIdx = idx;
    }
  }
};
</script>

<style>
</style>