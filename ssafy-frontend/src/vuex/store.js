import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from 'vuex-persistedstate'
import router from '@/router.js'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userName: null,
    token: null,
    userType: null,
    userAddr: null,
    res: [],
    lat: null,
    lon: null,
    mToken: null,
    order: 0,
    socket: null,
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      if (userData.token !== null) {
        state.userName = userData.data.id
        state.token = userData.token
        state.userType = userData.data.chk
        state.userAddr = userData.data.address
        state.lat = userData.data.latitude
        state.lon = userData.data.longitude
        axios.defaults.headers.common[
          'Authorization'
        ] = `Bearer ${store.state.token}`
      }
    },
    SET_STORE_DATA(state, storeData) {
      if (storeData.token !== null) {
        state.res = storeData
      }
    },
    SET_SOCIAL_DATA(state, userData) {
      if (userData.token !== null) {
        state.userName = userData.data.id
        state.token = userData.token
        state.userType = userData.data.chk
        state.userAddr = userData.data.address
        axios.defaults.headers.common['Authorization'] = `Bearer ${state.user}`
      }
    },
    getLocation(state, dist) {
      state.lat = dist.lat
      state.lon = dist.lon
      // navigator.geolocation.getCurrentPosition(function (pos) {
      //   state.lat = pos.coords.latitude
      //   state.lon = pos.coords.longitude
      //   alert(`위치가 갱신되었습니다.`)
      //   console.log(state.lat)
      //   console.log(state.lon)
      // }) //백으로 다시 보내서 계산...
    },
    CLEAR_USER_DATA(state) {
      localStorage.clear()
      location.reload()
      state.token = null
      state.userName = null
      state.userType = null
      state.userAddr = null
      state.socket = null
      state.lon = null
      state.lat = null
      state.order = 0
    },
    M_TOKEN_SAVE(state, mToken) {
      state.mToken = mToken
    },
    ORDER_PLUS(state) {
      state.order += 1
      return state.order
    },
    CLEAR_ORDER(state) {
      state.order = 0
    },
    SOCKET_CONNECTED(state) {
      state.socket = 1
    }
  },
  actions: {
    getLocation({
      commit
    }, credentials) {
      commit('getLocation', credentials)
    },
    resetaddr({
      commit
    }, credentials) {
      axios.defaults.headers.common[
        'Authorization'
      ] = `Bearer ${store.state.token}`
      return axios
        .post('http://192.168.100.92:8080/shopkeeper/near', credentials)
        .then(({
          data
        }) => {
          commit('SET_STORE_DATA', data)
        })
    },
    kakaologin({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/api/socialLogin', credentials)
        .then(({
          data //가입안되있으면 null 날라옴
        }) => {
          // console.log("t")
          // console.log(data)
          if (data.data != null) {
            console.log("ddd")
            commit('SET_USER_DATA', data)
            axios.defaults.headers.common[
              'Authorization'
            ] = `Bearer ${store.state.token}`
            axios({
                method: 'post',
                url: `http://192.168.100.92:8080/shopkeeper/near/`,
                data: {
                  lat: data.data.latitude,
                  lon: data.data.longitude
                }
              })
              .then(({
                data
              }) => {
                commit('SET_STORE_DATA', data)
              })
          }
          return data
        })
    },
    Mregister({
      commit
    }, credentials) {
      console.log(credentials + '! ')
      return axios.post('http://192.168.100.92:8080/api/user', credentials)
    },
    Sregister({
      commit
    }, credentials) {
      return axios.post(
        'http://192.168.100.92:8080/shopkeeper/store',
        credentials
      )
    },
    socialRegister({
      commit
    }, credentials) {
      return axios.post('http://192.168.100.92:8080/api/user', credentials)
    },
    Mlogin({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/api/loginCheck', credentials)
        .then(
          ({
            data //로그인한 사람의 모든 정보
          }) => {
            //위도, 경도만 추려내서 near로 전송
            commit('SET_USER_DATA', data)
            axios.defaults.headers.common[
              'Authorization'
            ] = `Bearer ${data.token}`
            axios({
                method: 'post',
                url: `http://192.168.100.92:8080/shopkeeper/near/`,
                data: {
                  lat: data.data.latitude,
                  lon: data.data.longitude
                }
              })
              .then(({
                data
              }) => {
                commit('SET_STORE_DATA', data)
              })

            // commit('SET_USER_DATA', data)
            // axios.post('http://192.168.100.92:8080/shopkeeper/near', data.data)
            //   .then(function (response) {
            //     //success(response.data);
            //     // alert(response)
            //     commit('SET_STORE_DATA', response)
            //     // return response;
            //   })
          }
        )
    },
    Slogin({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/shopkeeper/loginCheck', credentials)
        .then(({
          data
        }) => {
          console.log(data)
          commit('SET_USER_DATA', data)
        })
    },
    logout({
      commit
    }) {
      commit('CLEAR_USER_DATA')
    },
    modifyInfo({
      commit
    }, credentials) {
      return axios
        .put('http://192.168.100.92:8080/api/user', credentials)
        .then(router.push('/'))
        .then(commit('CLEAR_USER_DATA'))
    }
  },
  getters: {
    loggedIn(state) {
      return !!state.user
    }
  },
  plugins: [createPersistedState()]
})

export default store