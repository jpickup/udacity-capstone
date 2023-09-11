<template>
<div class="rating">
    <div class="rating-detail rating-id">{{  rating.id }}</div>
    <div class="rating-detail rating-value">{{ ratingValue }}</div>
    <div class="rating-detail rating-comments">{{ ratingComments }}</div>
    <div class="rating-detail"><button @click="deleteRating">Delete</button></div>
</div>
</template>

<script>
import { sendDelete } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'RatingDetails',
    props: ['rating'],
    data() {
        return {
            ratingName: this.rating.name,
            ratingValue: this.rating.rating,
            ratingComments: this.rating.comments
        }
    },
    methods: {
        deleteRating() {
            if (confirm("Delete rating?")) {
                console.log("Deleting rating " + this.rating.id)

                sendDelete(process.env.VUE_APP_ROUTE_SERVER_URI + "/ratings/"+this.rating.id)

                this.$emit('delete', this.rating.id)
            }
        }
    },
    components: {
    }
}
</script>

<style scoped>
.rating {
    display: flex;
    align-items: center;

}
.rating-detail {
    text-align: left;
    height: 3rem;
    padding: 0.5rem;
    font-size: 1.2rem;
}
.rating-id {
    width: 20rem;
}
.rating-value {
    width: 10rem;
}
.rating-comments {
    width: 30rem;
}
</style>