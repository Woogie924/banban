<template>
  <div>
    <v-container>
      <!-- 배달 정보 Component-->
      <!-- bind: 주문 번호, 주문자 정보 -->
      <v-layout class="my-5"></v-layout>
      <!-- 요청 사항 Component-->
      <!-- bind: 사장님께 -->
      <v-layout class="my-5"></v-layout>
      <!-- 결제 Component-->
      <!-- bind: 결제 번호, 결제 금액, 결제 방식 -->
      <v-layout class="my-5"></v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "UserPaymentPage",
  components: {
    deliveryInfo
  },
  data() {
    return {};
  }
};
</script>

<style>
</style>