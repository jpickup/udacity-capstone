<template>
<full-page title="User Admin">
    <div v-if="users" class="user-list">
        <UserDetails  v-for="user in users" :key="user.id" :user="user"/>
    </div>
</full-page>
</template>

<script>

import FullPage from '@/components/page/FullPage';
import UserDetails from '@/components/admin/UserDetails';
import axios from 'axios';

export default {
    name: 'UserAdminPage',
    components: {
        FullPage,
        UserDetails
    },
    data() {
        return {
            users: null
        }
    },
    created() {
      console.log("Querying " + window.config.VUE_APP_ROUTE_SERVER_URI + "/users/");
      const usersUrl = window.config.VUE_APP_ROUTE_SERVER_URI + '/users/';
      axios.get(usersUrl)
        .then((response) => { this.users = response.data; });
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
    font-size: 2.0rem;
}
</style>