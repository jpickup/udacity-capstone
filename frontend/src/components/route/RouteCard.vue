<template>
  <div class="route-card">
    <div class="route-card-content">
      <router-link :to="`/route/${route.id}`">
        <h4 class="route-card-heading">{{ route.name }}</h4>
      </router-link>
      <p class="route-card-description">{{ route.description }}</p>
      <div class="route-card-map">
        <mini-map-display :mapId="route.id"/>
      </div>
      <div class="route-card-footer">
        <div class="route-card-stats">
          <div class="route-length">
            Length {{ Math.round(route.length/100)/10 }}km
          </div>
          <div v-if="route.totalAscent" class="route-ascent">
            Ascent {{ Math.round(route.totalAscent) }}m
          </div>
          <div v-if="route.totalDescent" class="route-descent">
            Descent {{ Math.round(route.totalDescent) }}m
          </div>
        </div>
        <div class="route-card-downloads">
          <gpx-download :route="route"/>
          <geo-json-download :route="route"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import GpxDownload from '@/components/download/GpxDownload';
import GeoJsonDownload from '@/components/download/GeoJsonDownload';
import MiniMapDisplay from '@/components/map/MiniMapDisplay';

export default {
    name: 'RouteCard',
    props: ['route'],
    components: {
      GpxDownload,
      GeoJsonDownload,
      MiniMapDisplay
    },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.route-card {
  width: 30rem;
  margin: 2rem;
  background-color: #a8a7ae;
  box-shadow: 0 3rem 6rem rgba(0,0,0,0.3);
  display: flex;
  height: 95%;
}

.route-card-content {
  color: #14ae69;
  padding: 1rem;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.route-card-heading {
  margin: 0;
  font-size: 2.5rem;
  font-weight: 300;
}

.route-card-description {
  font-size: 1.5rem;
  color: #3a3a3a;
}

.route-card-footer {
  display: flex;
}

.route-card-stats {
  font-size: 1.2rem;
  color: #3a3a3a;
  text-align: left;
  flex: 1 1;
}

.route-card-map {
  margin: 1.5rem;
}

a {
  color: #157047;
  text-decoration: none;
} 

.route-card-downloads {
  width: 80px;
}

</style>