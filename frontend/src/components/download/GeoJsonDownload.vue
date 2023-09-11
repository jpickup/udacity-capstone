<template>
    <img src="geojson.png" width="32" height="32" @click="onClick" title="GEO JSON download" class="geojson-download"/>
</template>

<script>
import { get } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'geo-json-download',
    props: ['route'],
    methods: {
        downloadItem ( url, label ) {
            console.log("getting " + url);
            get(url, { responseType: 'blob' })
            .then(response => {
                console.log(response.data);
                const blob = new Blob([response.data], { type: 'application/json' })
                const link = document.createElement('a')
                link.href = URL.createObjectURL(blob)
                link.download = label
                link.click()
                URL.revokeObjectURL(link.href)
            }).catch(console.error)
        },  
        onClick() {
            console.log("Clicked " + this.route.id);
            this.downloadItem(this.baseUrl + this.route.id, this.route.name + ".json");
        }
    },
    data() {
        return {
            baseUrl: process.env.VUE_APP_ROUTE_SERVER_URI + "/geojson/"
        }
    }

}
</script>

<style scoped>
.geojson-download {
    cursor: pointer;
}
</style>