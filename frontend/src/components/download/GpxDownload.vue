<template>
    <img src="gpx.png" width="32" height="32" @click="onClick" title="GPX download" class="gpx-download"/>
</template>

<script>
import { get } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'gpx-download',
    props: ['route'],
    methods: {
        downloadItem ( url, label ) {
            console.log("getting " + url);
            get(url, { responseType: 'blob' })
            .then(response => {
                console.log(response.data);
                const blob = new Blob([response.data], { type: 'application/xml' })
                const link = document.createElement('a')
                link.href = URL.createObjectURL(blob)
                link.download = label
                link.click()
                URL.revokeObjectURL(link.href)
            }).catch(console.error)
        },  
        onClick() {
            console.log("Clicked " + this.route.id);
            this.downloadItem(this.baseUrl + this.route.id, this.route.name + ".gpx");
        }
    },
    data() {
        return {
            baseUrl: process.env.VUE_APP_ROUTE_SERVER_URI + "/gpx/"
        }
    }
}
</script>

<style scoped>
.gpx-download {
    cursor: pointer;
}
</style>