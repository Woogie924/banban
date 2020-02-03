<template>
  <div>
    <v-card>
      <v-card-title>주문 현황</v-card-title>
      <v-card-text v-for="value in list" :key="value.index">
        {{value.title}}
        <v-divider />
        {{value.credit}}원
        <v-divider />
        {{value.address}}
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "OrderList",
  methods: {},
  data: {},
  data() {
    return {
      list: [
        {
          index: "1",
          title: "김현우",
          credit: "19,900",
          address: "구미시 진평동"
        },
        {
          index: "2",
          title: "박교열",
          credit: "23,000",
          address: "구미시 인동"
        },
        {
          index: "3",
          title: "김정덕",
          credit: "19,900",
          address: "구미시 진평동"
        },
        {
          index: "4",
          title: "이응재",
          credit: "23,900",
          address: "구미시 인의"
        },
        {
          index: "5",
          title: "홍성욱",
          credit: "44,900",
          address: "구미시 진평동"
        },
        {
          index: "6",
          title: "XXXXXX",
          credit: "22,900",
          address: "구미시 진평동"
        }
      ]
    };
  }
};
</script>

<style>
</style>