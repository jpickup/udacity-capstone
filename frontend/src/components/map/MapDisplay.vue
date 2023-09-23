<template>
  <div class="map-display">
    <mgl-map v-if="route"
      :mapStyle="mapTilerUrl"
      :bounds="[route.bounds.minLongitude, route.bounds.minLatitude, route.bounds.maxLongitude, route.bounds.maxLatitude]"
      :center="[route.centre.longitude, route.centre.latitude]"
    >
      <mgl-navigation-control position="top-left"/>
      <mgl-marker :coordinates="[route.startPoint.longitude, route.startPoint.latitude]" color="#42b842"></mgl-marker>
      <mgl-marker :coordinates="[route.endPoint.longitude, route.endPoint.latitude]" color="#b84242"></mgl-marker>
      <mgl-geo-json-source sourceId="geojson" :data="geoJson">
        <mgl-line-layer
            v-if="geoJson"
            layer-id="geojson"
            :layout="geoJsonDisplay.layout"
            :paint="geoJsonDisplay.paint"
          />
        </mgl-geo-json-source>
    </mgl-map>
  </div>
</template>
  
<script>
  import { MglMap, MglNavigationControl, MglMarker, MglGeoJsonSource, MglLineLayer } from 'vue-maplibre-gl'
  import { get } from '@/modules/AuthorisedRequest.js';
  
  export default {
    name: 'map-display',
    components: {
      MglMap,
      MglNavigationControl,
      MglMarker,
      MglGeoJsonSource,
      MglLineLayer
    },
    props: {
        mapId: String
    },
    data() {
      return {
        mapTilerKey: window.config.VUE_APP_MAPTILER_KEY,
        mapTilerUrl: "https://api.maptiler.com/maps/topo-v2/style.json?key="+window.config.VUE_APP_MAPTILER_KEY,
        centre: null,
        zoom: 2,
        route: null,
        geoJson: null,
        loaded: 0,
        error: null,
        geoJsonDisplay: {
          layout: {
                'line-join': 'round',
                'line-cap' : 'round'
              },
          paint : {
                'line-color': '#ff0000',
                'line-width': 3
              },    
        },
      };
    },
    created() {
      const routeUrl = window.config.VUE_APP_ROUTE_SERVER_URI + '/routes/'+this.mapId;
      const jsonUrl = window.config.VUE_APP_ROUTE_SERVER_URI + '/geojson/'+this.mapId;
      get(jsonUrl)
        .then((response) => { this.geoJson = response.data; });
      get(routeUrl)
        .then((response) => { this.route = response.data; console.log(this.route.centre); });
    },
  }
</script>
  
<style lang="scss">
  @import "~vue-maplibre-gl/src/css/maplibre.scss";

  .map-display {
    width: 100%;
    height: calc(100vh - 350px);
    overflow: visible;
    z-index: 0;
    position: sticky;
  }
</style>
