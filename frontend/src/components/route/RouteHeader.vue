<template>
  <div class="route-header">
    <div class="route-name">
      {{ route.name }}
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
    <div class="route-rating">
      <RatingSummary :route="route" @ratingChanged="ratingChanged"/>
    </div>
  </div>
</template>

<script>
import RatingSummary from '@/components/rating/RatingSummary';

export default {
    name: 'RouteHeader',
    props: ['route'],
    components: {
      RatingSummary
    },
    methods: {
      ratingChanged() {
            console.log('RouteHeader got rating change')
            this.$emit('ratingChanged')
        }      
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.route-header {
  height: 60px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.route-name {
  font-weight: bold;
  text-align: left;
  font-size: large;
  margin: 10px;
  flex-grow: 1;
  flex-shrink: 1;
}

.route-stats {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-start;
  flex-grow: 0;
  flex-shrink: 0;
  width: 100px;
}


.route-rating {
  flex-grow: 0;
  flex-shrink: 0;
  width: 100px;
}

</style>