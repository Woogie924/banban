import Vue from 'vue'
import Router from 'vue-router'
import Read from './components/Read'
import Orderl from './components/orderl'
import Create from './views/Create'
import Detail from './views/Detail'
import Mlogin from './views/Mlogin'
import visit from './views/VisitPage.vue'
import MsignUp from './views/MsignUp'
import KsignUp from './views/KsignUp'
import StoreMainPage from './views/StoreMainPage'
import UserMainPage from './views/UserMainPage'
import StoreLogin from './views/StoreLogin'
import SsignUp from './views/SsignUp'
import MenuManagement from './components/MenuManagement'
import store from '@/vuex/store.js'
import index from './views/index'
import SelectedMenuPage from './views/SelectedMenuPage'
import StoreInfoPage from './views/StoreInfoPage'
import MyCartPage from './views/MyCartPage'
import profile from './views/profile'
import MyPaymentPage from './views/MyPaymentPage'
import StoreMenuPage from './views/StoreMenuPage'
import nothing from './views/nothing'
import push from './views/push'
import kakaoPaySuccess from '@/views/kakaoPaySuccess.vue'
import ManagePage from './views/ManagePage'
import CompletePayPage from './views/CompletePayPage'
import storeProfile from './views/storeProfile'
import FAQ from './views/FAQ'
import userFAQ from './views/userFAQ'

Vue.use(Router)

const router = new Router({
	base: process.env.BASE_URL,
	routes: [{
			path: '/read',
			name: 'Read',
			component: Read,
		},
		{
			path: '/orderl',
			name: 'Orderl',
			component: Orderl,
		},
		{
			path: '/detail/:contentId',
			name: 'Detail',
			component: Detail,
			props: true,
		},
		{
			path: '/create/:contentId?',
			name: 'Create',
			component: Create,
			props: true,
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
			path: '/visit',
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
		},
		{
			path: '/UserMainPage',
			name: 'UserMainPage',
			component: UserMainPage,
		},
		{
			path: '/MenuManagement',
			name: 'MenuManagement',
			component: MenuManagement,
		}, {
			path: '/',
			name: 'index',
			component: index
		},
		{
			path: '/SelectedMenuPage',
			name: 'SelectedMenuPage',
			component: SelectedMenuPage,
		},
		{
			path: '/StoreInfoPage/:StoreId',
			name: 'StoreInfoPage',
			component: StoreInfoPage,
			props: true,
		},
		{
			path: '/MyCartPage',
			name: 'MyCartPage',
			component: MyCartPage,
		},
		{
			path: '/nothing',
			name: 'nothing',
			component: nothing
		},
		{
			path: '/StoreMenuPage',
			name: 'StoreMenuPage',
			component: StoreMenuPage,
		},
		{
			path: '/push',
			name: 'push',
			component: push
		},
		{
			path: '/ManagePage',
			name: 'Managepage',
			component: ManagePage,
		},
		{
			path: '/profile',
			name: 'profile',
			component: profile,
		},
		{
			path: '/storeProfile',
			name: 'storeProfile',
			component: storeProfile,
		},
		{
			path: '/MyPaymentPage',
			name: 'MyPaymentPage',
			component: MyPaymentPage,
		},
		{
			path: '/CompletePayPage',
			name: 'CompletePayPage',
			component: CompletePayPage
		},
		{
			path: '/kakaoPaySuccess',
			name: 'kakaoPaySuccess',
			component: kakaoPaySuccess
		},
		{
			path: '/FAQ',
			name: 'FAQ',
			component: FAQ
		},
		{
			path: '/userFAQ',
			name: 'FAQ',
			component: userFAQ
		}
	]
})

router.beforeEach((to, from, next) => {
	const loggedIn = store.state.token
	// 권한 체크가 필요할 때
	if (to.matched.some(record => record.meta.requireAuth)) {
		// 로그인 되어있지 않을 때
		if (loggedIn === null) {
			alert('권한이 없습니다. 로그인 해주세요.')
			next('/visit')
		} else {
			// 로그인이 되어 있을 때
			next()
		}
		// 권한 체크가 필요 없을 때
	} else {
		next()
	}
})
export default router