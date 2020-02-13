<template>
  <div>
    <button class="mdi mdi-bell-ring" @click="push"></button>
  </div>
</template>

<script>
import axios from "axios";
export default {
  methods: {
    push() {
      axios.defaults.headers.common[
        "Authorization"
      ] = `key=AAAAPeiU8nM:APA91bFw0DV3BkjxkmYqRFrZhcteOTmmc0MN1DeQ8CcpyNF2AVyZluRAKfVq7AXyz3ySkLd-5lsb48t6Ebh1ZUwy7pqutw4pBW1ff7rE-ll5GaJXpAFfjBVH1v26m1j0lnXE2nzQqX3o`;
      axios
        .post("https://fcm.googleapis.com/fcm/send", {
          to:
            "d6LC5DE40JlE8VCdMJpHrZ:APA91bHoPN0cSDeVeI_Qij1h9G-2tPaJN7SusgMJy8v4Jp_SHi15teTHyYYGqy8CyJw6MYNQZyzdb6l5B06x_KcnUQfTMh3UU9KAp_Ghd2sHt8JfanSp5G_20O17y1yGFiouvwulKdVM",
          data: {
            status: "주문 받아라!"
          }
        })
        .then(console.log(this.to));
    }
  }
};
</script>

<style>
</style>