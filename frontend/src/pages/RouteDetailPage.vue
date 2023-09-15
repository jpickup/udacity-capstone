<template>
  <nav-bar/>
  <page-header title="Route Detail"/>
  <page-body>
    <LoadingSpinner v-if="!route">
      <p>Loading...</p>
    </LoadingSpinner>
    <RouteHeader v-if="route" :route="route" @ratingChanged="ratingChanged"/>
    <RouteFooter v-if="route" :route="route"/>
    <MapDisplay :mapId="routeId"/>
    <ElevationChart class="elevation-chart" :routeId="routeId"/>
    <RouteDeleteButton v-if="route" :route="route"/>
  </page-body>
</template>
  
<script>
  import LoadingSpinner from '@/components/LoadingSpinner';
  import RouteHeader from '@/components/route/RouteHeader';
  import RouteFooter from '@/components/route/RouteFooter';
  import RouteDeleteButton from '@/components/route/RouteDeleteButton';
  import MapDisplay from '@/components/map/MapDisplay';
  import ElevationChart from '@/components/elevation/ElevationChart.vue';
  import NavBar from '@/components/page/NavBar';
  import PageHeader from '@/components/page/PageHeader';
  import PageBody from '@/components/page/PageBody';
  import { get } from '@/modules/AuthorisedRequest.js';
  
  export default {
    name: 'RouteDetailPage',
    computed: {
      routeId() {
        console.log("ID:" + this.$route.params.id);
        return this.$route.params.id;
      }
    },
    components: {
      LoadingSpinner,
      RouteHeader,
      RouteFooter,
      RouteDeleteButton,
      MapDisplay,
      ElevationChart,
      NavBar,
      PageHeader,
      PageBody
    },
    data() {
        return {
            route: null
        }
    },
    methods: {
      loadRoute() {
        console.log("Querying " + process.env.VUE_APP_ROUTE_SERVER_URI + "/routemeta/" + this.routeId);
      const routesUrl = process.env.VUE_APP_ROUTE_SERVER_URI + '/routemeta/' + this.routeId;
      get(routesUrl)
        .then((response) => { this.route = response.data; });
      },
      ratingChanged() {
            console.log("Rating changed - time to reload")
            this.loadRoute()
      }
    },
    created() {
      this.loadRoute()
    }
  }
</script>
  
<style scoped>

.elevation-chart {
  height: 15rem;
}
</style>