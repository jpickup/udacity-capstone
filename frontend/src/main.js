import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vue3GoogleLogin from 'vue3-google-login'
import { createPinia } from 'pinia';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const pinia = createPinia();
pinia.use(piniaPluginPersistedstate)
const app = createApp(App);
app.use(pinia)

fetch(process.env.BASE_URL + "config.json")
  .then((response) => response.json())
  .then((config) => {
    // either use window.config
    window.config = config
    // deliberate error
    const unused = 1
    // or use [Vue Global Config][1]
    app.config.globalProperties.config = config
    console.log("Server URI: " + window.config.VUE_APP_ROUTE_SERVER_URI)
    console.log("Client ID: " + window.config.VUE_APP_GOOGLE_CLIENT_ID)
    // FINALLY, mount the app
    app.use(vue3GoogleLogin, {
      clientId: window.config.VUE_APP_GOOGLE_CLIENT_ID
    }).use(router).mount('#app')
  })
