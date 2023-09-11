<template>
    <div class="rating-summary" @click="openRatingDetail">
        <StarRating :stars="averageRating" :readOnly="true"/>
        ({{ reviewCount }})
    </div>
    <div class="rating-popup" v-if="showPopup">
        <div class="rating-popup-content">
            <span class="rating-popup-close" @click="closeRatingDetail">&times;</span>
            <RatingsDetails v-if="route" :route="route"/>
            <RateRoute v-if="route" :route="route" @ratingChanged="ratingChanged"/>
        </div>
    </div>
</template>

<script>
import StarRating from '@/components/rating/StarRating';
import RatingsDetails from '@/components/rating/RatingsDetails';
import RateRoute from '@/components/rating/RateRoute';
export default {
    name: 'RatingSummary',
    props: ['route'],
    emits: ['ratingChanged'],
    components: {
      StarRating,
      RatingsDetails,
      RateRoute
    },
    methods: {
        openRatingDetail() {
            console.log("Open rating detail")
            this.showPopup = true
            
        },
        closeRatingDetail() {
            console.log("Open rating detail")
            this.showPopup = false            
        },
        ratingChanged() {
            this.$emit('ratingChanged')
            this.showPopup = false
        }
    },
    data() {
        return {
            averageRating: this.route.averageRating,
            reviewCount: this.route.ratings.length,
            showPopup: false 
        }
    }
}
</script>

<style scoped>
.rating-summary {
    font-size: 1.2rem;
    display: flex;
}

.rating-popup {
    display: block; 
    position: fixed;
    z-index: 100; 
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

.rating-popup-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

.rating-popup-close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.rating-popup-close:hover,
.rating-popup-close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>