// Give the service worker access to Firebase Messaging.
// Note that you can only use Firebase Messaging here, other Firebase libraries
// are not available in the service worker.
importScripts('https://www.gstatic.com/firebasejs/7.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/7.8.1/firebase-messaging.js');

const firebaseConfig = {
  apiKey: "AIzaSyBs1shcJNlZqmCO9_negDiPoXZ8Vlahip8",
  authDomain: "sub-pjt-1-1ce19.firebaseapp.com",
  databaseURL: "https://sub-pjt-1-1ce19.firebaseio.com",
  projectId: "sub-pjt-1-1ce19",
  storageBucket: "sub-pjt-1-1ce19.appspot.com",
  messagingSenderId: "265895080563",
  appId: "1:265895080563:web:d996de3b6e41c9a755f006",
  measurementId: "G-99SVHW1MMX"
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