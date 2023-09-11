<template>
    <div class="star-rating">
        <!-- Star 1 -->
        <i class="star fa-solid fa-star" v-if="rating >= 1" @click="starClicked(1)"></i>
        <i class="star fa-solid fa-star-half-stroke" v-if="rating >= 0.5 && rating < 1" @click="starClicked(1)"></i>
        <i class="empty-star fa-regular fa-star" v-if="rating < 0.5" @click="starClicked(1)"></i>

        <!-- Star 2 -->
        <i class="star fa-solid fa-star" v-if="rating >= 2" @click="starClicked(2)"></i>
        <i class="star fa-solid fa-star-half-stroke" v-if="rating >= 1.5 && rating < 2" @click="starClicked(2)"></i>
        <i class="empty-star fa-regular fa-star" v-if="rating < 1.5" @click="starClicked(2)"></i>

        <!-- Star 3 -->
        <i class="star fa-solid fa-star" v-if="rating >= 3" @click="starClicked(3)"></i>
        <i class="star fa-solid fa-star-half-stroke" v-if="rating >= 2.5 && rating < 3" @click="starClicked(3)"></i>
        <i class="empty-star fa-regular fa-star" v-if="rating < 2.5" @click="starClicked(3)"></i>

        <!-- Star 4 -->
        <i class="star fa-solid fa-star" v-if="rating >= 4" @click="starClicked(4)"></i>
        <i class="star fa-solid fa-star-half-stroke" v-if="rating >= 3.5 && rating < 4" @click="starClicked(4)"></i>
        <i class="empty-star fa-regular fa-star" v-if="rating < 3.5" @click="starClicked(4)"></i>

        <!-- Star 5 -->
        <i class="star fa-solid fa-star" v-if="rating >= 5" @click="starClicked(5)"></i>
        <i class="star fa-solid fa-star-half-stroke" v-if="rating >= 4.5 && rating < 5" @click="starClicked(5)"></i>
        <i class="empty-star fa-regular fa-star" v-if="rating < 4.5" @click="starClicked(5)"></i>
    </div>
</template>

<script>
export default {
    name: 'StarRating',
    props: ['stars','readOnly'],
    methods: {
        starClicked(rating){
            if (!this.readOnly) {
                console.log("Rating is now " + rating)
                this.rating = rating;
                this.$emit('change', rating)
            }
        }
    },
    data() {
        return {
            rating: this.stars
        }
    }
}
</script>

<style scoped>
.star-rating {
    font-size: 1.2rem;
    width: 10rem;
}

.star {
    color: orange;
}

.empty-star {
    color: grey;
}

</style>