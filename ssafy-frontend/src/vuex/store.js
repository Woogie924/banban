import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import createPersistedState from "vuex-persistedstate"

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    userName: null,
    token: null,
    userType: null,
    userAddr: null
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      if (userData.token !== null) {
        state.userName = userData.data.id
        state.userType = userData.data.chk
        state.userAddr = userData.data.address
        state.token = userData.token
        axios.defaults.headers.common[
          'Authorization'
        ] = `Bearer ${store.state.token}`
      }
    },
    SET_SOCIAL_DATA(state, userData) {
      if (userData.token !== null) {
        state.userName = userData.data.id
        state.userType = userData.data.chk
        state.token = userData.token
        axios.defaults.headers.common[
          'Authorization'
        ] = `Bearer ${state.user}`
      }
    },
    CLEAR_USER_DATA(state) {
      localStorage.clear()
      location.reload()
      state.token = null
      state.userName = null
      state.userType = null
    }
  },
  actions: {
    kakaologin({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/api/socialLogin', credentials)
        .then(({
          data
        }) => {
          return data;
        })
    },
    Mregister({
      commit
    }, credentials) {
      console.log(credentials + "! ");
      return axios
        .post('http://192.168.100.92:8080/api/user', credentials)
    },
    Sregister({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/shopkeeper/store', credentials)
    },
    socialRegister({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/api/user', credentials)
    },
    Mlogin({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/api/loginCheck', credentials)
        .then(({
          data
        }) => {
          console.log(data)
          commit('SET_USER_DATA', data)
        })
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