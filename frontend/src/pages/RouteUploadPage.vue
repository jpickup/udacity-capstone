<template>
    <nav-bar/>
    <FullPage title="Route Upload">
    <RouteUpload/>
    </FullPage>
</template>

<script>
import RouteUpload from '@/components/route/RouteUpload';
import NavBar from '@/components/page/NavBar';
import FullPage from '@/components/page/FullPage';

export default {
    name: 'RouteUploadPage',
    components: {
        RouteUpload,
        NavBar,
        FullPage
    },
    data() {
        return {

        }
    }
}
</script>

<style scoped>
</style>