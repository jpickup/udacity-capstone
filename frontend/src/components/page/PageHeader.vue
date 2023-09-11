<template>
    <div class="page-header">
        <div class="page-header-padding"></div>
        <div class="page-header-title"><h1>{{ title }}</h1></div> 
        <UserLogin/>
        <div class="page-header-logo"><img src="Udacity.png" height="48"></div>
    </div>
</template>

<script>
import UserLogin from '@/components/UserLogin';

export default {
    name: 'PageHeader',
    props: ['title'],
    components: {
        UserLogin
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.page-header {
    margin-left: 6rem;
    margin-right: 1rem;
    position: fixed;
    z-index: 1;
    width: 100%;
    background-color: white;
    display: flex;
    justify-content: space-between;
    align-items: center;
    align-content: space-evenly;
}
.page-header-padding {
    width: 23px;
    flex-grow: 0;
}
.page-header-title {
    flex-grow: 1;
    text-align: center;
}

.page-header-logo {
    margin-right: 6rem;
    flex-grow: 0;
    width: 50px;
}

h1 {
    margin: 1rem;
    font-size: 2.0rem;
    font-weight: 800;
}
</style>