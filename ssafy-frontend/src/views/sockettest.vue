<template>
  <div>
    <button @click="connect">연결</button>
    <button @click="disconnect">연결끊기</button>
    {{ status }}
    <br />
    <br />
    <div v-if="connected">
      <form @submit.prevent="send" action="#">
        <input v-model="send_message" />
        <button type="submit">메세지 전송</button>
      </form>
      <ul>
        <li :v-for="log in received_messages">{{ log }}</li>
      </ul>
    </div>
  </div>
</template>
<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"></script>
<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  data() {
    return {
      message: "",
      logs: [],
      status: "disconnected",
      received_messages: [],
      send_message: null,
      connected: false,
      solo_received_messages: [],
      solo_send_message: null,
      soloconnected: false
    };
  },
  methods: {
    connect() {
      this.socket = new SockJS("http://localhost:8082/order");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          console.log("연결요");
          this.status = "connected";
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/push/god", tick => {
            console.log(JSON.parse(tick.body));
            this.received_messages.push(JSON.parse(tick.body));
          });
        },
        error => {
          console.log("에러요");
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
      this.status = "disconnected";
      this.received_messages = [];
    },
    send() {
      console.log("Send message:" + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = {
          message: this.send_message,
          sender: "이응재",
          receiver: "god",
          data: null
        };
        console.log(JSON.stringify(msg));
        this.stompClient.send("/push", JSON.stringify(msg), {});
      }
    }
  }
};
</script>

<style>
</style>