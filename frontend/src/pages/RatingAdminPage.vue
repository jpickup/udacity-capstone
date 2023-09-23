<template>
<full-page title="Rating Admin">
    <div v-if="ratings" class="rating-list">
        <RatingDetails  v-for="rating in ratings" :key="rating.id" :rating="rating"/>
    </div>
</full-page>
</template>

<script>

import FullPage from '@/components/page/FullPage';
import RatingDetails from '@/components/admin/RatingDetails';
import axios from 'axios';

export default {
    name: 'RatingAdminPage',
    components: {
        FullPage,
        RatingDetails
    },
    data() {
        return {
            ratings: null
        }
    },
    created() {
      console.log("Querying " + window.config.VUE_APP_ROUTE_SERVER_URI + "/ratings/");
      const ratingsUrl = window.config.VUE_APP_ROUTE_SERVER_URI + '/ratings/';
      axios.get(ratingsUrl)
        .then((response) => { this.ratings = response.data; });
    },
};
</script>

<style scoped>

p {
    text-align: left;
    margin: 10px;
    font-size: 1.5rem;
}

h2 {
    margin: 10px;
    text-align: left;
    font-size: 2.0rem;
}
</style>