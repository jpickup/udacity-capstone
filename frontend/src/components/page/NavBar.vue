<template>
    <div class="navbar">
        <input type="checkbox" class="navbar-checkbox" id="navbar-click" hidden v-model="menuIsOpen">
        <!-- sidebar -->
        <div class="sidebar">
            <label for="navbar-click">
                <div class="menu-icon">
                    <div class="line line-1"></div>
                    <div class="line line-2"></div>
                    <div class="line line-3"></div>
                </div>
            </label>
            <ul class="social-icons-list">
                <li>
                    <a href="https://www.strava.com/athletes/3348955" class="social-link"><i class="fa-brands fa-strava"></i></a>
                </li>
                <li>
                    <a href="https://www.instagram.com/surreyhillsrunning/" class="social-link"><i class="fa-brands fa-instagram"></i></a>
                </li>
            </ul>
            <div class="year">
                <p>&copy;2023 Udaroute</p>
            </div>
        </div>
        <!-- end of sidebar -->

        <!-- navigation -->
        <nav class="navigation">
            <div class="nagigation-header">
                <h1 class="navigation-heading">Udacity Routes</h1>
                <form class="navigation-search">
                    <input type="text" class="navigation-search-input" placeholder="Search..." v-on:change="handleSearchChange($event)">
                    <button class="navigation-search-btn" v-on:click="submitSearch()">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
            </div>
            <ul class="navigation-list">
                <li class="navigation-item">
                    <router-link to="/" class="navigation-link" @click="closeMenu">home</router-link>
                </li>
                <li class="navigation-item">
                    <router-link to="/routes" class="navigation-link">routes</router-link>
                </li>
                <li class="navigation-item">
                    <router-link to="/contact" class="navigation-link">contact</router-link>
                </li>
                <li class="navigation-item" v-if="userDataStore.isAdmin">
                    <router-link to="/user-admin" class="navigation-link">user admin</router-link>
                </li>
                <li class="navigation-item" v-if="userDataStore.isAdmin">
                    <router-link to="/rating-admin" class="navigation-link">rating admin</router-link>
                </li>
                <li class="navigation-item" v-if="userDataStore.isAdmin">
                    <router-link to="/route-admin" class="navigation-link">route admin</router-link>
                </li>
            </ul>
            <div class="nagigation-footer">

            </div>
        </nav>
        <!-- end of navigation -->
    </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { ref } from 'vue';
import { useUserDataStore } from '@/stores/UserData.js';
import { storeToRefs } from 'pinia';


export default {
    name: 'NavBar',
    setup() {
        const router = useRouter()

        const search = ref("");

        const submitSearch = () => {
            console.log("Search " + search.value);
            router.replace({
                name: 'search', params: {searchText: search.value} 
            })
        }

        const handleSearchChange = (event) => {
            search.value = event.target.value;
            //console.log("Search set to " + search.value);
        }
            
        return {
            handleSearchChange,
            submitSearch,            
        }
    },
    methods: {
        closeMenu() {
            this.menuIsOpen = false;      
        }
    },
    data() {
        return {
            menuIsOpen: false,
            userDataStore: useUserDataStore(),
            userData: storeToRefs(useUserDataStore())
        }
    },        
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.navigation {
    z-index: 10;
}

.navbar {
    z-index: 10;
}

/* sidebar */
.sidebar {
    width: 5rem;
    height: 100%;
    background-color: #eee;
    position: fixed;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    z-index: 20;
}

.menu-icon {
    margin-top: 3rem;
    cursor: pointer;
}

.menu-icon:hover .line {
    width: 2.5rem;
}

.line {
    height: 0.2rem;
    width: 2.5rem;
    background-color: #222;
    margin-bottom: 0.6rem;
}

.line-1, .line-3 {
    width: 1.8rem;
    transition: all 0.3s;
}

.social-icons-list {
    list-style: none;
    padding: 0;
    height: 15rem;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
}

.social-link {
    font-size: 2rem;
    color: #777;
    transition: color 0.3s;
}

.social-link:hover {
    color: #51bae3;
}

.year {
    margin-bottom: 1rem;
    font-size: 1rem;
    color: #555;
}
/* end of sidebar */


/* navigation */
.navigation {
    position: fixed;    
    left: -25rem;
    width: 18rem;
    height: 100%;
    background: linear-gradient(45deg, #333, #555);
    background-repeat: no-repeat;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    transition: left .8s cubic-bezier(1, 0, 0, 1);
}

.navigation-heading {
    font-size: 2.0rem;
    font-weight: 900;
    letter-spacing: 0.2rem;
    color: #517de3;
    text-align: center;
}

.navigation-search {
    position: relative;
}
.navigation-search-input {
    width: 18rem;
    padding: 1rem 2rem;
    background-color: rgba(0,0,0,0.3);
    font-size: 1.5rem;
    color: #517de3;
    letter-spacing: 0.1rem;
    outline: none;
    border: 0.1rem solid #888;
    border-radius: 10rem;
}

.navigation-search-input:focus {
    background-color: rgba(0,0,0,0.5); 
    transition: background-color 0.3s; 
}

.navigation-search-btn {
    border: none;
    background-color: transparent;
    color: #0e2459;
    font-size: 1.5rem;
    position: absolute;
    top: 1rem;
    right: 2.5rem;
    cursor: pointer;
}

.navigation-search-btn:focus {
    outline: none;
}

.navigation-list {
    list-style: none;
    padding: 0;
    height: 28rem;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
}

.navigation-item {
    height: 25rem;
    position: relative;
    display: flex;
}

.navigation-link {
    color: #517de3;
    font-size: 1.6rem; 
    font-weight: 400;
    letter-spacing: 0.1rem;
    text-transform: capitalize;
    text-decoration: none;
    transition: color 0.5s;
}

.navigation-link::before {
    content: "";
    width: 0rem;
    height: 0.1rem;
    background-color: #517de3;
    position: absolute;
    top: 1rem;
    left: -4rem;
    transition: width 0.5s cubic-bezier(1,0,0,1);
}

.navigation-link::after {
    content: "";
    width: 0rem;
    height: 0.1rem;
    background-color: #517de3;
    position: absolute;
    top: 1rem;
    right: -4rem;
    transition: width 0.5s cubic-bezier(1,0,0,1);
}

.navigation-link:hover {
    color: #517de3;
}

.navigation-link:hover::before {
    width: 3rem;
}

.navigation-link:hover::after {
    width: 3rem;
}

.navbar-checkbox:checked ~ .navigation {
    left: 5rem;
}

.navbar-checkbox:checked ~ .sidebar .line-1 {
    transform: rotate(-405deg) translate(-5px, 6px);
    width: 2.5rem;
}

.navbar-checkbox:checked ~ .sidebar .line-2 {
    opacity: 0;
}

.navbar-checkbox:checked ~ .sidebar .line-3 {
    transform: rotate(405deg) translate(-5px, -6px);
    width: 2.5rem;
}
/* end of navigation */

</style>