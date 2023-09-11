import { defineStore } from "pinia"

export const useUserDataStore = defineStore('userData', {
    state: () => ({
        _id: null,
        _user: null,
        _role: null,
        _email: null,
        _credential: null,
        _loginTime: null
    }),
    actions: {
        login(id, user, credential, role, email) {
            this._id = id
            this._user = user
            this._credential = credential
            this._role = role
            this._email = email
            this._loginTime = Date.now()
        },
        logout() {
            this._id = null
            this._user = null
            this._credential = null
            this._role = null
            this._email = null
            this._loginTime = null
        }
    },
    getters: {
        id: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000))?state._id:null
        },
        user: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000))?state._user:null
        },
        credential: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000))?state._credential:null
        },
        role: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000))?state._role:null
        },
        email: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000))?state._email:null
        },
        isLoggedIn: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000))
        },
        isAdmin: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000)) && (state._role != null) && (state._role == "ADMIN")
        },
        isPublisher: (state) => {
            return ((state != null) && (state._loginTime != null) && ((Date.now() - state._loginTime) < 3600000)) && (state._role != null) && ((state._role == "ADMIN") || (state._role == "PUBLISHER"))
        }
    },
    persist: true
}
)
