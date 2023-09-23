<template>
    <div v-if="userDataStore.isAdmin" class="route-save-button">
        <i class="fa-solid fa-circle-check" v-on:click="doUpdateRoute()"></i>
    </div>
</template>

<script>
import { useUserDataStore } from '@/stores/UserData.js';
import { storeToRefs } from 'pinia';
import { put } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'RouteUpdateButton',
    props: ['routeId', 'routeName','routeDescription'],
    data() {
        return {
            userDataStore: useUserDataStore(),
            userData: storeToRefs(useUserDataStore())
        }
    },
    setup() {
        const updateRoute = (routeId, routeName, routeDescription) => {
            console.log(window.config.VUE_APP_ROUTE_SERVER_URI + '/routemeta/' + routeId)
            put( window.config.VUE_APP_ROUTE_SERVER_URI + '/routemeta/' + routeId, {
                "id": routeId,
                "name": routeName,
                "description": routeDescription
            })
        }        
        return {
            updateRoute
        }
    },
    methods: {
        doUpdateRoute() {
            this.updateRoute(this.routeId, this.routeName, this.routeDescription)
        }
    }
}
</script>

<style scoped>
.route-save-button {
    font-size: 1.5rem;
    cursor: pointer;
    height: 2rem;
    width: 2rem;
}
</style>