<template>
<div class="user">
    <div class="user-detail user-id">{{  user.id }}</div>
    <div class="user-detail user-name"><input type="text" v-model="userName"/></div>
    <div class="user-detail user-email"><input type="text" v-model="userEmail"/></div>
    <div class="user-detail user-role"><select v-model="userRole"><option>ADMIN</option><option>PUBLISHER</option><option>READ_ONLY</option></select></div>
    <div class="user-detail"><button @click="updateUser">Update</button></div>
    <div class="user-detail"><button @click="deleteUser">Delete</button></div>
</div>
</template>

<script>
import { post, sendDelete } from '@/modules/AuthorisedRequest.js';

export default {
    name: 'UserDetails',
    props: ['user'],
    data() {
        return {
            userName: this.user.name,
            userEmail: this.user.email,
            userRole: this.user.role
        }
    },
    methods: {
        updateUser() {
            if (confirm("Save changes?")) {
                console.log("Updating user " + this.user.id)
                const userData = {
                    id : this.user.id,
                    name: this.userName,
                    email: this.userEmail,
                    role: this.userRole
                }
                console.log("Updating user " + this.user.id + " with " + userData)
                post(window.config.VUE_APP_ROUTE_SERVER_URI + "/users/"+this.user.id, userData)

                this.$emit('change', this.user.id)
            }
        },
        deleteUser() {
            if (confirm("Delete user?")) {
                console.log("Deleting user " + this.user.id)

                sendDelete(window.config.VUE_APP_ROUTE_SERVER_URI + "/users/"+this.user.id)

                this.$emit('delete', this.user.id)
            }
        }
    },
    components: {
    }
}
</script>

<style scoped>
.user {
    display: flex;
    align-items: center;

}
.user-detail {
    text-align: left;
    height: 3rem;
    padding: 0.5rem;
    font-size: 1.2rem;
}
.user-id {
    width: 20rem;
}
.user-name {
    width: 30rem;
}
.user-email {
    width: 30rem;
}
.user-role {
    width: 15rem;
}
</style>