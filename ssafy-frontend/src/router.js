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
import main from './views/main'
import store from '@/vuex/store.js'

Vue.use(Router)

const router = new Router({
	mode: 'history',
	base: process.env.BASE_URL,
	routes: [{
			path: '/read',
			name: 'Read',
			component: Read
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
				// requireAuth: true,
			}
		},
		{
			path: '/MenuManagement',
			name: 'MenuManagement',
			component: MenuManagement,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/ani',
			name: 'ani',
			componet: ani,
			meta: {

			}
		}
	]
})

router.beforeEach((to, from, next) => {
	const loggedIn = store.state.token
	const userType = store.state.user !== null ? store.state.user.chk : 98765;
	if (to.matched.some(record => record.meta.requireAuth)) {
		// 로그인 되어있지 않을 때
		if (loggedIn === null) {
			alert('권한이 없습니다. 로그인 해주세요.')
			next('/')
		} else {
			next()
		}
	} else {
		next()
	}
})
export default router