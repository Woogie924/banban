import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/dist/vuetify.min.css'
import VueSimplemde from 'vue-simplemde'
import 'simplemde/dist/simplemde.min.css'
import 'font-awesome/css/font-awesome.min.css'
import VueResizeText from 'vue-resize-text'
import App from './App.vue'
import router from './router'
import store from './vuex/store'
import './registerServiceWorker'
import VueCookies from 'vue-cookies'
import firebase, {
	firestore
} from 'firebase/app'
import 'firebase/messaging'
import Vuelidate from 'vuelidate'

Vue.use(Vuelidate)

var firebaseConfig = {
	apiKey: "AIzaSyBs1shcJNlZqmCO9_negDiPoXZ8Vlahip8",
	authDomain: "sub-pjt-1-1ce19.firebaseapp.com",
	databaseURL: "https://sub-pjt-1-1ce19.firebaseio.com",
	projectId: "sub-pjt-1-1ce19",
	storageBucket: "sub-pjt-1-1ce19.appspot.com",
	messagingSenderId: "265895080563",
	appId: "1:265895080563:web:fc6e4195a9d3f0d155f006",
	measurementId: "G-890ZWRM6V7"
};
firebase.initializeApp(firebaseConfig)

const messaging = firebase.messaging()
messaging.usePublicVapidKey('BEiZZil17WeZ_LFy8GW-IbRlTK7ppeBXlH_1eAB_n8IN8fe0FbDy3F_qXmTU-ShK-0csbmq4PbbwvyQlWrE5vDU')

// TODO: Send token to server for send notification
// messaging.getToken()

// 작업
Notification.requestPermission().then((permission) => {
	if (permission === 'granted') {
		console.log('Notification permission granted.');
		// TODO(developer): Retrieve an Instance ID token for use with FCM.
		// ...
	} else {
		console.log('Unable to get permission to notify.');
	}
})
messaging.getToken().then((currentToken) => {
	console.log(currentToken)
	if (currentToken) {
		firebase.firestore().collection('tokens').doc('nothing').set({
			token: currentToken
		}).then(localStorage.setItem('mToken', currentToken))
	} else {
		// Show permission request.
		console.log('No Instance ID token available. Request permission to generate one.');
	}
}).catch((err) => {
	console.log('An error occurred while retrieving token. ', err);
});

// Callback fired if Instance ID token is updated.
messaging.onTokenRefresh(() => {
	messaging.getToken().then((refreshedToken) => {
		console.log('Token refreshed.');
		if (refreshedToken) {
			firebase.firestore().collection('tokens').doc('nothing').set({
				token: refreshedToken
			}).then(localStorage.setItem('mToken', refreshedToken))
		} else {
			// Show permission request.
			console.log('No Instance ID token available. Request permission to generate one.');
		}
	}).catch((err) => {
		console.log('Unable to retrieve refreshed token ', err);
		showToken('Unable to retrieve refreshed token ', err);
	});
});

// 작업끝
// Handle received push notification at foreground
messaging.onMessage(payload => {
	console.log(payload.data.status)
	alert(payload.data.status)
	store.commit('ORDER_PLUS')
})

Vue.config.productionTip = false

Vue.use(VueSimplemde)
Vue.use(Vuetify)
Vue.use(VueResizeText)
Vue.use(VueCookies)

new Vue({
	router,
	store,
	vuetify: new Vuetify({
		iconfont: 'fa',
		theme: {
			primary: '#3f51b5',
			secondary: '#b0bec5',
			accent: '#8c9eff',
			error: '#b71c1c'
		}
	}),
	render: h => h(App)
}).$mount('#app')