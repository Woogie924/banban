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
import firebase from 'firebase/app'
import 'firebase/messaging'

var firebaseConfig = {
	apiKey: "AIzaSyBDfQIXMxZdiJceuWb4kSAvQKi-xe7P1KM",
	authDomain: "push-91a2a.firebaseapp.com",
	databaseURL: "https://push-91a2a.firebaseio.com",
	projectId: "push-91a2a",
	storageBucket: "push-91a2a.appspot.com",
	messagingSenderId: "1039314123355",
	appId: "1:1039314123355:web:dd56826d06af953ec91d7e",
	measurementId: "G-K9C3LJHZ9V"
}

firebase.initializeApp(firebaseConfig)

const messaging = firebase.messaging()
messaging.usePublicVapidKey('BMX-Sqmz9DmeWaBzd0hp9Y9MskEj0_G3oHyOUlTl0wHIFOtGmEBTjnncO0Dudn5wDAEuasVbKT1MhhRIDObjnoA')

// TODO: Send token to server for send notification
// messaging.getToken()

// 작업
messaging.requestPermission()
	.then(function () {
		console.log('성공')
		return messaging.getToken()
	})
	.then(function (token) {
		console.log(token)
		localStorage.setItem('mToken', token)
	})
	.catch(function (err) {
		console.log('ee')
	})

// 작업끝
// Handle received push notification at foreground
messaging.onMessage(payload => {
	console.log(payload.data.status)
	alert(payload.data.status)
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