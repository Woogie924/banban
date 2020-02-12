// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/7.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.8.1/firebase-messaging.js');

var firebaseConfig = {
  apiKey: "AIzaSyBDfQIXMxZdiJceuWb4kSAvQKi-xe7P1KM",
  authDomain: "push-91a2a.firebaseapp.com",
  databaseURL: "https://push-91a2a.firebaseio.com",
  projectId: "push-91a2a",
  storageBucket: "push-91a2a.appspot.com",
  messagingSenderId: "1039314123355",
  appId: "1:1039314123355:web:dd56826d06af953ec91d7e",
  measurementId: "G-K9C3LJHZ9V"
};
firebase.initializeApp(firebaseConfig)

const messaging = firebase.messaging();
messaging.setBackgroundMessageHandler(function (payload) {
  const title = '주문 들어왔다!';
  const options = {
    body: payload.data.status
  };
  return self.registeration.showNotification(title, options);
})