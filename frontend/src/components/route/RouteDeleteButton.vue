<template>
    <div v-if="userDataStore.isAdmin" class="route-delete-button">
        <i class="fa-solid fa-trash" v-on:click="confirmDeleteRoute()"></i>
    </div>
</template>

<script>
import { useUserDataStore } from '@/stores/UserData.js';
import { storeToRefs } from 'pinia';
import { sendDelete } from '@/modules/AuthorisedRequest.js';
import { useRouter } from 'vue-router'

export default {
    name: 'RouteDeleteButton',
    props: ['route'],
    data() {
        return {
            userDataStore: useUserDataStore(),
            userData: storeToRefs(useUserDataStore())
        }
    },
    setup() {
        const router = useRouter();
        const deleteRoute = (routeID) => {
            sendDelete( window.config.VUE_APP_ROUTE_SERVER_URI + '/routes/' + routeID)
            .then(router.replace({name: 'routes'}));                
        }        
        return {
            deleteRoute
        }
    },
    methods: {
        confirmDeleteRoute() {
            if (confirm("Delete route " + this.route.name + "?")) {
                this.deleteRoute(this.route.id)
            }
        }
    }
}
</script>

<style scoped>
.route-delete-button {
    font-size: 1.5rem;
    cursor: pointer;
    height: 2rem;
    width: 2rem;
}
</style>