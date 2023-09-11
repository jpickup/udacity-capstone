import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vue3GoogleLogin from 'vue3-google-login'
import { createPinia } from 'pinia';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate)
const app = createApp(App);
console.log("Server URI: " + process.env.VUE_APP_ROUTE_SERVER_URI)
console.log("Client ID: " + process.env.VUE_APP_GOOGLE_CLIENT_ID)
app.use(pinia)
app.use(vue3GoogleLogin, {
    clientId: process.env.VUE_APP_GOOGLE_CLIENT_ID
  }).use(router).mount('#app')
