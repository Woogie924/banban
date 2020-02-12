import firebase from 'firebase/app'
import 'firebase/firestore'
import 'firebase/auth'
import 'firebase/messaging'

const POSTS = 'posts'
const PORTFOLIOS = 'portfolios'

// Setup Firebase
// Do change to your own firebase configuration
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

const firestore = firebase.firestore()

const messaging = firebase.messaging()

messaging.usePublicVapidKey('BMX-Sqmz9DmeWaBzd0hp9Y9MskEj0_G3oHyOUlTl0wHIFOtGmEBTjnncO0Dudn5wDAEuasVbKT1MhhRIDObjnoA')

// 알림 수신을 위한 사용자 권한 요청
Notification.requestPermission()
	.then((permission) => {
		console.log('permission ', permission)
		if (permission !== 'granted') {
			alert('알림을 허용해주세요')
		}
	})

// TODO: Send token to server for send notification
messaging.getToken()
	.then(console.log)

// Handle received push notification at foreground
messaging.onMessage(payload => {
	console.log(payload)
	alert(payload.data.message)
})

export default {
	getPosts() {
		const postsCollection = firestore.collection(POSTS)
		return postsCollection
			.orderBy('created_at', 'desc')
			.get()
			.then((docSnapshots) => {
				return docSnapshots.docs.map((doc) => {
					let data = doc.data()
					data.created_at = new Date(data.created_at.toDate())
					window.console.log(data)
					return data
				})
			})

	},
	postPost(title, body) {
		return firestore.collection(POSTS).add({
			title,
			body,
			created_at: firebase.firestore.FieldValue.serverTimestamp()
		})
	},
	getPortfolios() {
		const postsCollection = firestore.collection(PORTFOLIOS)
		return postsCollection
			.orderBy('created_at', 'desc')
			.get()
			.then((docSnapshots) => {
				return docSnapshots.docs.map((doc) => {
					let data = doc.data()
					data.created_at = new Date(data.created_at.toDate())
					return data
				})
			})
	},
	postPortfolio(title, body, img) {
		return firestore.collection(PORTFOLIOS).add({
			title,
			body,
			img,
			created_at: firebase.firestore.FieldValue.serverTimestamp()
		})
	},
	loginWithGoogle() {
		let provider = new firebase.auth.GoogleAuthProvider()
		return firebase.auth().signInWithPopup(provider).then(function (result) {
			let accessToken = result.credential.accessToken
			let user = result.user
			return result
		}).catch(function (error) {
			console.error('[Google Login Error]', error)
		})
	}
}