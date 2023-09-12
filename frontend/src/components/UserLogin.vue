<template>
    <div class="google-login">
        <div v-if="!userDataStore.isLoggedIn" class="google-login-button">
            <!-- TODO put back when issues fixed -->
            <!--GoogleLogin :callback="callback"/-->
            <!-- <GoogleLogin :callback="callback" prompt auto-login/> -->
        </div>
        <div v-if="userDataStore.isLoggedIn" class="google-user">
            <img :src="userDataStore.user.picture" class="google-user-img" :alt="userDataStore.user.name" @click="logout">
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import { useUserDataStore } from '@/stores/UserData.js';
import { decodeCredential } from 'vue3-google-login';
import axios from 'axios';
import { googleLogout } from 'vue3-google-login';
import { storeToRefs } from 'pinia';

export default {
    name: 'UserLogin',
    data() {
        const ss = useUserDataStore()
        console.log("In UserLogin: " + ss._email)

        return {
            userDataStore: useUserDataStore(),
            userData: storeToRefs(useUserDataStore())
        }
    },   
    methods: {
        login(userCredential) {
            console.log("userCredential", userCredential)
            const decodedCredential = decodeCredential(userCredential)
            console.log("decodedCredential", decodedCredential)

            const loginCredentials = {
                user: decodedCredential,
                credential: userCredential,
                email: decodedCredential.email,
                role: null
            }
            const headers= {};
            headers.Authorization = 'Bearer ' + userCredential;
            axios.post(process.env.VUE_APP_ROUTE_SERVER_URI + '/login', loginCredentials, {headers})
            .then((response) => {
                console.log("Logged in as " + response.data.id + " with role " + response.data.role);
                this.userDataStore.login(response.data.id, decodedCredential, userCredential, response.data.role, decodedCredential.email)
            })
            .catch((error) => {
                console.log("Login error" + error);
                this.userDataStore.logout();
            })
        },
        logout() {
            console.log("Logging out");
            googleLogout();
            this.userDataStore.logout();
        },
        callback(response) {
            console.log("Google login callback");
            // decodeCredential will retrive the JWT payload from the credential
            this.login(response.credential)
        }
    }
}
</script>

<style scoped>
.google-login {
}

.google-login-button {
}

.google-user {
}

.google-user-img {
    height: 30px;
    border-radius: 15px;
    border: 0.1rem solid greenyellow;
}
</style>