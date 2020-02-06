import Vue from 'vue'
import Router from 'vue-router'
import Read from './components/Read'
import Create from './components/Create'
import Detail from './components/Detail'
import Mlogin from './views/Mlogin'
import visit from './views/VisitPage.vue'
import MsignUp from './views/MsignUp'
import KsignUp from './views/KsignUp'
import StoreMainPage from './views/StoreMainPage'
import UserMainPage from './views/UserMainPage'
import StoreLogin from './components/StoreLogin'
import SsignUp from './views/SsignUp'
import MenuManagement from './components/MenuManagement'
import store from '@/vuex/store.js'

Vue.use(Router)

const requireAuth = () => (to, from, next) => {
	if (store.state.accessToken !== '') {
		return next();
	}
	next('/login');
};


const router = new Router({
	mode: 'history',
	base: process.env.BASE_URL,
	routes: [{
			path: '/read',
			name: 'Read',
			component: Read,
			meta: {
				requireAuth: true,
			}
		},
		{
			path: '/create/',
			name: 'Create',
			component: Create,
			meta: {
				requireAuth: true,
			}
		},
		{
			path: '/detail/:contentId',
			name: 'Detail',
			component: Detail,
			props: true,
			meta: {
				requireAuth: true,
			}
		},
		{
			path: '/create/:contentId?',
			name: 'Create',
			component: Create,
			props: true,
			meta: {
				requireAuth: true,
			}
		},
		{
			path: '/Mlogin',
			name: 'Mlogin',
			component: Mlogin
		},
		{
			path: '/KsignUp',
			name: 'KsignUp',
			component: KsignUp
		},
		{
			path: '/StoreLogin',
			name: 'StoreLogin',
			component: StoreLogin
		},
		{
			path: '/',
			name: 'VisitPage',
			component: visit
		},
		{
			path: '/MsignUp',
			name: 'MsignUp',
			component: MsignUp
		},
		{
			path: '/SsignUp',
			name: 'SsignUp',
			component: SsignUp
		},
		{
			path: '/StoreMainPage',
			name: 'StoreMainPage',
			component: StoreMainPage,
			meta: {
				requireAuth: true,
			}
		},
		{
			path: '/UserMainPage',
			name: 'UserMainPage',
			component: UserMainPage,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/MenuManagement',
			name: 'MenuManagement',
			component: MenuManagement,
			meta: {
				requireAuth: true
			}
		}
	]
})

router.beforeEach((to, from, next) => {
	let loggedIn = store.state.token
	let userType = store.state.userType
	// 권한 체크가 필요할 때
	// 내 권한 타입
	// user info state :    0-> 비로그인, 1->일반 유저, 2->가게 유저
	console.log(to.matched.values(record => record.meta.requireAuth));
	if (to.matched.some(record => record.meta.requireAuth)) {
		// 로그인 되어있지 않을 때
		if (loggedIn === null) {
			alert('권한이 없습니다. 로그인 해주세요.')
			next('/')
			// 로그인 되어 있을 때
		} else {
			next()
		}
		// 권한 체크가 필요 없을 때
	} else {
		next()
	}
});
export default router