<template>
  <div class="route-footer">
    <div class="route-description">
      {{ route.description }}
    </div>
    <div class="route-downloads">
      <gpx-download :route="route"/>
      <geo-json-download :route="route"/>
    </div>

  </div>
</template>

<script>
import GpxDownload from '@/components/download/GpxDownload';
import GeoJsonDownload from '@/components/download/GeoJsonDownload';

export default {
    name: 'RouteFooter',
    props: ['route'],
    components: {
      GpxDownload,
      GeoJsonDownload
    },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.route-footer {
  height: 6rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 1.2rem;
}

.route-description {
  margin: 1rem;
  word-wrap: normal;
  text-align: left;
  flex-grow: 1;
  flex-shrink: 1;
}

.route-downloads {
  margin: 10px;
  height: 40px;
  width: 80px;
  flex-grow: 0;
  flex-shrink: 0;
}

</style>