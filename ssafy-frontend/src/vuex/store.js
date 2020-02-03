import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: null,
    userType: null,
    userName: null
  },
  mutations: {
    SET_USER_DATA(state, userData) {
      if (userData.token !== null) {
        state.user = userData
        state.userType = userData.data.chk
        state.userName = userData.data.id
        localStorage.setItem('userType', JSON.stringify(userData.data.chk))
        localStorage.setItem('userName', JSON.stringify(userData.data.userName))
        localStorage.setItem('user', JSON.stringify(userData.token))
        localStorage.setItem('userName', JSON.stringify(userData.data.id))
        axios.defaults.headers.common[
          'Authorization'
        ] = `Bearer ${userData.token}`
      }
    },
    SET_SOCIAL_DATA(state, userData) {
      if (userData.token !== null) {
        state.user = userData
        localStorage.setItem('user', JSON.stringify(userData.token))
        axios.defaults.headers.common[
          'Authorization'
        ] = `Bearer ${userData.token}`
      }
    },
    CLEAR_USER_DATA(state) {
      localStorage.clear()
      location.reload()
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
      return axios
        .post('http://192.168.100.92:8080/api/user', credentials)
        .then(({
          data
        }) => {
          commit('SET_USER_DATA', data)
        })
    },
    Sregister({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/shopkeeper/store', credentials)
        .then(({
          data
        }) => {
          commit('SET_USER_DATA', data)
        })
    },
    socialRegister({
      commit
    }, credentials) {
      return axios
        .post('http://192.168.100.92:8080/api/user', credentials)
        .then(({
          data
        }) => {
          commit('SET_SOCIAL_DATA', data)
        })
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
  }
})