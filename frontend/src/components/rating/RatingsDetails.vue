<template>
    <div class="ratings-details" v-if="route.ratings.length > 0">
        <div v-for="rating in route.ratings" :key="rating.id"> 
            <RatingDetail :rating="rating"/>
        </div>
    </div>
</template>

<script>
import RatingDetail from '@/components/rating/RatingDetail';
import { useUserDataStore } from '@/stores/UserData.js';
import { storeToRefs } from 'pinia';

export default {
    name: 'RatingsDetails',
    props: ['route'],
    data() {
        return {
            userDataStore: useUserDataStore(),
            userData: storeToRefs(useUserDataStore())
        }
    },
    components: {
        RatingDetail
    }
}
</script>

<style scoped>
.ratings-details {
    font-size: 1.2rem;
    width: 50rem;
    display: flex;
    flex-direction: column;
}
</style>