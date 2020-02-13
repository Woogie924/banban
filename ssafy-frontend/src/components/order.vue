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
      ] = `key=AAAA8fvy-ls:APA91bGWO0UCXWPWot0DNUz0qBzNZdm1p8OVwscXSPm5EqZOse_tlMSuPAQw8D3ZEBkAq_a7Vd1BuzBHteUydouwhsWAKeycbxYjmnL9i_cXc9rEubsJqhksV3mX3fUqxWhG0SRDjsao`;
      axios
        .post("https://fcm.googleapis.com/fcm/send", {
          to:
            "cEIxNriJbqn4iZXT-ApJ8Y:APA91bFMlIi3OOryi3sCbuH6Yk_KkSST0Ch8EazaUtqBFwULFzoKcRYmqex-nctXq08D6JD4l_8Tl6-TirO5t4gpOH87oW4f6LpOlWX3Xoecsqtq-CHXTR4WK8CAmKO1DZkr-CIoT1EE",
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