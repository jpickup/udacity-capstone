<template>
    <div class="rating-detail">
        <StarRating :stars="rating.rating" :readOnly="true"/>
        <div class="rating-comment">
        {{ rating.comments }}
        </div>
    </div>
</template>

<script>
import StarRating from '@/components/rating/StarRating';

export default {
    name: 'RatingDetail',
    props: ['rating'],
    components: {
      StarRating
    }
}
</script>

<style scoped>
.rating-detail {
    font-size: 1.2rem;
    width: 50rem;
    display: flex;
}

.rating-comment {
    text-align: left;
}

</style>
