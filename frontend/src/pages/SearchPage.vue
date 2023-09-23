<template>
    <full-page title="Search">
        <div v-if="routes" class="search-wrapper">
            <RouteCard v-for="route in routes" :key="route.id" :route="route"/>
        </div>
        <div v-if="!routes" class="search-wrapper">
            No routes found containing "{{ searchText }}"
        </div>
    </full-page>
</template>

<script>
import RouteCard from '@/components/route/RouteCard';
import FullPage from '@/components/page/FullPage';
import { post } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'SearchPage',
    props: ['searchText'],
    components: {
        RouteCard,
        FullPage,
    },
    data() {
        return {
            routes: null,
            responded: false
        }
    },
    created() {
        console.log("Querying " + window.config.VUE_APP_ROUTE_SERVER_URI + "/search/ with " + this.searchText);
        const routesUrl = window.config.VUE_APP_ROUTE_SERVER_URI + '/search/';
        let formData = new FormData();
        formData.append('searchText', this.searchText);
        post(routesUrl, formData)
            .then((response) => { this.routes = response.data; this.responded = true; });
    },
};
</script>

<style scoped>

.search-wrapper {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

</style>