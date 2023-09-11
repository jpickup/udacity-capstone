<template>
    <RouteCard v-if="searchMatches()" :route="route"/>
</template>

<script>
import RouteCard from '@/components/route/RouteCard';

export default {
    name: 'RouteCardFilter',
    props: ['route','search'],
    components: {
        RouteCard,
    },
    methods: {
      searchMatches() {
        if ((this.search == null) || (this.search == "")) {
          return true;
        }
        if (this.route == null) {
          return false;
        }
        if ((this.route.name!=null) && (this.route.name.toUpperCase().indexOf(this.search.toUpperCase())>=0))  {
          return true;
        }
        if ((this.route.description!=null) && (this.route.description.toUpperCase().indexOf(this.search.toUpperCase())>=0))  {
          return true;
        }
        return false;
      }
    }
}
</script>
