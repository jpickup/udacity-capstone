<template>
    <full-page title="Route Admin">
        <RouteUploadButton/>
        <div v-if="routes" class="route-list">
            <RouteDetails  v-for="route in routes" :key="route.id" :route="route"/>
        </div>
    </full-page>
</template>

<script>
import RouteDetails from '@/components/route/RouteDetails';
import RouteUploadButton from '@/components/route/RouteUploadButton';
import FullPage from '@/components/page/FullPage';
import { get } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'RouteAdminPage',
    components: {
        RouteDetails,
        RouteUploadButton,
        FullPage,
    },
    data() {
        return {
            routes: null
        }
    },
    created() {
      console.log("Querying " + process.env.VUE_APP_ROUTE_SERVER_URI + "/routemeta/");
      const routesUrl = process.env.VUE_APP_ROUTE_SERVER_URI + '/routemeta/';
      get(routesUrl)
        .then((response) => { this.routes = response.data; });
    },
};
</script>

<style scoped>

</style>