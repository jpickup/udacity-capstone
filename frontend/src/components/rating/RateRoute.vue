<template>
    <div class="rate-route" v-if="userDataStore.isLoggedIn">
        <div class="rate-route-header">Submit Rating</div>
        <div class="rate-route-body">
            <div class="rate-route-stars">
                <StarRating :stars="0" :readOnly="false" @change="handleRatingChange($event)"/>
            </div>
            <div class="rate-route-comments">
                <p class="route-rating-comments" @input="handleCommentChange($event)" contenteditable></p>
                <!--textarea  @change="handleCommentChange($event)"/-->
            </div>
            <div class="rate-route-upload-button">
                <button v-on:click="submitRating()">Submit</button>
            </div>
        </div>
    </div>
    <div class="rate-route-login" v-if="!userDataStore.isLoggedIn">
        <i class="fa-solid fa-right-to-bracket"></i>
        Please login to rate routes
    </div>
</template>

<script>
import { useUserDataStore } from '@/stores/UserData.js';
import { storeToRefs } from 'pinia';
import { post } from '@/modules/AuthorisedRequest.js';
import StarRating from '@/components/rating/StarRating';

export default {
    name: 'RateRoute',
    props: ['route'],
    emits: ['ratingChanged'],
    data() {
        return {
            userDataStore: useUserDataStore(),
            userData: storeToRefs(useUserDataStore()),
            rating: 0,
            comments: '',
        }
    },
    methods: {
        handleRatingChange(newRating){
            this.rating = newRating;
            console.log("New rating " + this.rating);
        },        
        handleCommentChange(event){
            this.comments = event.target.innerText;
            console.log("New comment " + this.comments);
        },
        submitRating(){
            let formData = new FormData();
            formData.append('routeId', this.route.id);
            formData.append('rating', this.rating);
            formData.append('comments', this.comments);
            formData.append('userId', this.userDataStore.id);
            console.log(formData);
            post(window.config.VUE_APP_ROUTE_SERVER_URI + '/ratings',
              formData,
              {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
            ).then((response) => {
              console.log('SUCCESS!!');
              console.log(response);
              this.$emit('ratingChanged', this.rating)
            })
            .catch(function(){
              console.log('FAILURE!!');
            });
            },
    },
    components: {
      StarRating
    }
}
</script>

<style scoped>
.rate-route {
    font-size: 1.2rem;
    width: 50rem;
}

.rate-route-header {
    font-size: 2rem;
}

/* .rate-route-header::before {
    content: "";
    width: 4rem;
    height: 0.1rem;
    background-color: #51e3a1;
    position: absolute;
    top: 1rem;
    left: -4rem;
}

.rate-route-header::after {
    content: "";
    width: 4rem;
    height: 0.1rem;
    background-color: #51e3a1;
    position: absolute;
    top: 1rem;
    right: -4rem;
} */
.rate-route-body {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    align-items: center;
    margin: 0.5rem;
}

.route-rating-comments {
    padding: 12px 20px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    background-color: #f8f8f8;
}

.rate-route-upload-button {
    margin: 1.5rem;    
}
</style>