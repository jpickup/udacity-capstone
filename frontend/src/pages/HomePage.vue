<template>
    <full-page title="Udacity Routes">
    <div class="home-page">
        <div class="home-page-text">
        <p>
        Welcome to Udacity Routes, a repository of routes for hiking, running and cycling. 
        </p>
        <p>
        All routes are freely available. Please create an account if you would like to make comments on any of the routes or give them a rating. Let us know if you'd like to contribute new routes.
        </p>
        <!--p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p>
        <p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p>
        <p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p>
        <p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p>
        <p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p>
        <p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p>
        <p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p>
        <p>lots more text needed... lots more text needed... lots more text needed... lots more text needed... lots more text needed... </p-->
        </div>        
    </div>
    </full-page>
</template>

<script>

import FullPage from '@/components/page/FullPage';

export default {
    name: 'HomePage',
    components: {
        FullPage
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
    font-size: 2.5rem;
}

.home-page {

}

.home-page-text {
    position: relative;
}
.home-page-images {
    /* flex: 1 1; */
    position: relative;
    height: 800px;
}

.image {
    width: 36rem;
    position: absolute;
    top: 0%;
    left: 50%;
    box-shadow: 0 3rem 6rem rgba(0, 0, 0, 0.4);
    border-radius: 1rem;
    border: 0.1rem solid gray;
}

.image-1 {
    transform: translate(-20%, 0%);
    z-index: 0;
}

.image-2 {
    transform: translate(-70%, 60%);
    z-index: 1;
}

.image-3 {
    transform: translate(0%, 120%);
    z-index: 0;
}

.image-4 {
    transform: translate(-40%, 180%);
    z-index: 1;
}
</style>