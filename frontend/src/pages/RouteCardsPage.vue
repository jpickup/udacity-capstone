<template>
    <full-page title="Routes">
        <LoadingSpinner v-if="!routes">
            <p>Loading...</p>
        </LoadingSpinner>
        <div class="route-cards" v-if="routes"> 
            <div class="route-cards-search">
                <input type="text" class="route-search-input" placeholder="Search..." v-on:change="handleSearchChange($event)">
            </div>
            <div class="route-cards-wrapper">
                <RouteCardFilter v-for="route in routes" :key="route.id" :route="route" :search="search"/>
            </div>
        </div>
        <RouteUploadButton/>
    </full-page>
</template>

<script>
import LoadingSpinner from '@/components/LoadingSpinner';
import RouteCardFilter from '@/components/route/RouteCardFilter';
import RouteUploadButton from '@/components/route/RouteUploadButton';
import FullPage from '@/components/page/FullPage';
import { get } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'RouteCardsPage',
    components: {
        LoadingSpinner,
        RouteCardFilter,
        RouteUploadButton,
        FullPage,
    },
    data() {
        return {
            routes: null,
            search: null
        }
    },
    methods: {
        handleSearchChange(event) {
            this.search = event.target.value;
            console.log("Search set to " + this.search);
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

.route-cards {
    width: calc(100vw - 10rem);
}
.route-cards-search {
    text-align: right;
}

.route-search-input {
    width: 18rem;
    padding: 1rem 2rem;
    font-size: 1.5rem;
    color: #898989;
    letter-spacing: 0.1rem;
    outline: none;
    border: 0.1rem solid #888;
    border-radius: 10rem;
    margin: 5px;
}
.route-cards-wrapper {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
.route-cards-wait {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;
}

</style>