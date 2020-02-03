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

Vue.use(Router)

const router = new Router({
	mode: 'history',
	base: process.env.BASE_URL,
	routes: [{
			path: '/read',
			name: 'Read',
			component: Read,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/create/',
			name: 'Create',
			component: Create,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/detail/:contentId',
			name: 'Detail',
			component: Detail,
			props: true,
			meta: {
				requireAuth: true
			}
		},
		{
			path: '/create/:contentId?',
			name: 'Create',
			component: Create,
			props: true,
			meta: {
				requireAuth: true
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
				requireAuth: true
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
		},
		{
			path: '/main',
			name: 'main',
			component: main
		}
	]
})

router.beforeEach((to, from, next) => {
	const loggedIn = localStorage.getItem('user')
	const userType = localStorage.getItem('userType')

	if (to.matched.some(record => record.meta.requireAuth)) {
		if (!loggedIn) {
			if (userType == 1) {
				next('mlogin')
			} else if (userType === 2) {
				next('StoreLogin')
			} else if (userType === 3) {
				next('/')
			} else {
				next('/')
			}
		} else {
			next()
		}
	} else {
		next()
	}
});

export default router