<template>
  <div class="mini-map-display">
    <mgl-map v-if="route"
      :mapStyle="mapTilerUrl"
      :bounds="[route.bounds.minLongitude, route.bounds.minLatitude, route.bounds.maxLongitude, route.bounds.maxLatitude]"
      :center="[route.centre.longitude, route.centre.latitude]"
      :scrollZoom="false"
      :boxZoom="false"
      :doubleClickZoom="false"
    >
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
  import { MglMap, MglGeoJsonSource, MglLineLayer } from 'vue-maplibre-gl'
  import { get } from '@/modules/AuthorisedRequest.js';
  
  export default {
    name: 'mini-map-display',
    components: {
      MglMap,
      MglGeoJsonSource,
      MglLineLayer
    },
    props: {
        mapId: String
    },
    data() {
      return {
        mapTilerKey: process.env.VUE_APP_MAPTILER_KEY,
        mapTilerUrl: "https://api.maptiler.com/maps/topo-v2/style.json?key="+process.env.VUE_APP_MAPTILER_KEY,
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
                'line-width': 2
              },    
        },
      };
    },
    created() {

      const routeUrl = process.env.VUE_APP_ROUTE_SERVER_URI + '/routes/'+this.mapId;
      const jsonUrl = process.env.VUE_APP_ROUTE_SERVER_URI + '/geojson/'+this.mapId;
      get(jsonUrl).then((response) => { this.geoJson = response.data; });
      get(routeUrl).then((response) => { this.route = response.data; console.log(this.route.centre); });
    },
  }
</script>
  
<style lang="scss">
  @import "~vue-maplibre-gl/src/css/maplibre.scss";

  .mini-map-display {
    width: 25rem;
    height: 25rem;
    z-index: 0;
  }
</style>
