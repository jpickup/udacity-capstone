<template>
    <div v-if="userDataStore.isPublisher" class="route-upload-button">
        <i class="fa-solid fa-cloud-arrow-up" v-on:click="uploadRoute()"></i>
    </div>
</template>

<script>
import { useUserDataStore } from '@/stores/UserData.js';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router'

export default {
    name: 'RouteUploadButton',
    data() {
        return {
            userDataStore: useUserDataStore(),
            userData: storeToRefs(useUserDataStore())
        }
    },
    setup() {
        const router = useRouter();
        const uploadRoute = () => {
            router.replace({name: 'upload'});                
        }        
        return {
            uploadRoute
        }
    }
}
</script>

<style scoped>
.route-upload-button {
    font-size: 1.5rem;
    cursor: pointer;
    height: 2rem;
    width: 2rem;
}
</style>