<template>
  <div>
    <v-layout my-5>
      <StoreNavBar></StoreNavBar>
    </v-layout>
    <v-container>
      <v-layout my-5>
        <v-expansion-panels focusable elevation="0">
          <v-expansion-panel v-for="(item) in panel_items" :key="item.idx">
            <v-expansion-panel-header>{{item.title}}</v-expansion-panel-header>
            <v-expansion-panel-content>{{item.words}}</v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import StoreNavBar from "../components/StoreNavBar";
export default {
  name: "FAQ",
  components: {
    StoreNavBar
  },
  data() {
    return {
      panel_items: [
        {
          idx: 1,
          title: "Q. [회원]반반한동네 회원 가입은 어떻게 하나요?",
          words:
            "A. '배달의민족 사장님사이트' 회원가입 절차를 통해 간편하게 배민상회 이용이 가능합니다"
        },
        {
          idx: 2,
          title: "Q. [회원] 탈퇴 후 재가입이 가능한가요?",
          words:
            "A. 회원 탈퇴 후 60일 이내에 동일한 명의로 재가입은 불가 합니다."
        },
        {
          idx: 3,
          title: "Q. [교환/반품/환불] 주문취소는 어떻게 하나요?",
          words:
            "A. -주문 취소는 주문접수, 입금확인, 결제완료 상태에서 가능합니다.  (추후 업데이트 예정)"
        }
      ]
    };
  },
  mounted() {},
  methods: {}
};
</script>

<style>
</style>