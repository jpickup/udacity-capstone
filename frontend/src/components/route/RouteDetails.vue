<template>
  <div class="route-details">
    <router-link :to="`/route/${route.id}`">
      <i class="fa-solid fa-eye"></i>
    </router-link>
    <div class="route-name" contenteditable @input="updateName($event)">
      {{ route.name }}
    </div>
    <div class="route-description" contenteditable @input="updateDescription($event)">
      {{ route.description }}
    </div>
    <div class="route-stats">
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
    <div class="route-downloads">
      <gpx-download :route="route"/>
      <geo-json-download :route="route"/>
    </div>
    <RouteDeleteButton v-if="route" :route="route"/>
    <RouteUpdateButton v-if="route" :routeId="routeId" :routeName="routeName" :routeDescription="routeDescription"/>
  </div>
</template>

<script>
import GpxDownload from '@/components/download/GpxDownload';
import GeoJsonDownload from '@/components/download/GeoJsonDownload';
import RouteDeleteButton from '@/components/route/RouteDeleteButton';
import RouteUpdateButton from '@/components/route/RouteUpdateButton';

export default {
    name: 'RouteDetails',
    props: ['route'],
    components: {
      GpxDownload,
      GeoJsonDownload,
      RouteDeleteButton,
      RouteUpdateButton
    },
    data() {
      return {
        routeId: this.route.id,
        routeName: this.route.name,
        routeDescription : this.route.description
      }
    },
    methods: {
      updateName(event) {
        this.routeName = event.target.innerText
      },
      updateDescription(event) {
        this.routeDescription = event.target.innerText
      }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.route-details {
  height: 100px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px black;
  font-size: 1.5rem;
}
.route-name {
  text-align: left;
  margin: 1rem;
  flex-grow: 0;
  flex-shrink: 0;
  font-weight: bold;
}

.route-description {
  margin: 1rem;
  word-wrap: normal;
  text-align: left;
  flex-grow: 1;
  flex-shrink: 1;
}

.route-downloads {
  width: 8rem;
  flex-grow: 0;
  flex-shrink: 0;
}

.route-stats {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  flex-grow: 0;
  flex-shrink: 0;
  width: 150px;
  font-size: 1.2rem;
}

.route-length {
  padding: 0;
  margin: 0;
}

.route-ascent {
  padding: 0;
  margin: 0;
}

.route-descent {
  padding: 0;
  margin: 0;
}

h2 {
  margin: 40px 0 0;
}
a {
  color: #157047;
}
</style>