<template>
  <div class="route-upload">
    <div class="route-upload-name">
      <label class="route-upload-label">Name
        <input type="text" @change="handleNameChange($event)"/>
      </label>
    </div>
    <div class="route-upload-description">
      <label class="route-upload-label">Description
        <textarea @change="handleDescriptionChange($event)"/>
      </label>
    </div>
    <div class="route-upload-visibility">
      <label class="route-upload-label">Public
        <input type="radio" id="visiblility-public" name="visibility" value="PUBLIC" @change="handleVisibilityChange($event)" checked/>
      </label>
      <br/>
      <label class="route-upload-label">Private
        <input type="radio" id="visiblility-private" name="visibility" value="PRIVATE" @change="handleVisibilityChange($event)"/>
      </label>
    </div>
    <div class="route-upload-file">
      <label class="route-upload-label">File
        <input type="file" @change="handleFileUpload($event)"/>
      </label>
    </div>


    <div class="route-upload-button">
      <button v-on:click="submitFile()">Submit</button>
    </div>
  </div>
</template>

<script>
import { post } from '@/modules/AuthorisedRequest.js';
import { useRouter } from 'vue-router'

export default {
    name: 'RouteUpload',
    setup() {
        const router = useRouter();
        const showRoute = (routeID) => {
            router.replace({name: 'route', params: {id: routeID} });         
        }        
        return {
          showRoute
        }
    },
    methods: {
        handleNameChange(event){
            this.name = event.target.value;
        },
        handleDescriptionChange(event){
            this.description = event.target.value;
        },
        handleVisibilityChange(event){
            this.visibility = event.target.value;
        },
        handleFileUpload(event){
            this.file = event.target.files[0];
        },
        submitFile(){
            let formData = new FormData();
            formData.append('file', this.file);
            formData.append('name', this.name);
            formData.append('description', this.description);
            formData.append('visibility', this.visibility)
            console.log(formData);
            post( process.env.VUE_APP_ROUTE_SERVER_URI + '/gpx/upload',
              formData,
              {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
              }
            ).then((response) => {
              console.log('SUCCESS!!');
              console.log(response);
              console.log("New route ID " + response.data.id);
              // navigate to uploaded route
              this.showRoute(response.data.id)
            })
            .catch(function(){
              console.log('FAILURE!!');
            });
        },
    },
    data() {
        return {
            file: '',
            name: '',
            description: '',
            visibility: 'PUBLIC'
        }
    }
}
</script>

<style scoped>
.route-upload {
  margin: 5rem;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.route-upload-name {
  width: 500px;
  height: 3rem;
  text-align: left;
}

.route-upload-visibility {
  width: 500px;
  height: 5rem;
  text-align: left;
}

.route-upload-description {
  width: 500px;
  height: 8rem;
  text-align: left;
}

.route-upload-file {
  width: 500px;
  height: 3rem;
  text-align: left;
}
</style>