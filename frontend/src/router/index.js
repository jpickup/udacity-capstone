import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import SearchPage from '../pages/SearchPage.vue'
import RouteAdminPage from '../pages/RouteAdminPage.vue'
import RouteCardsPage from '../pages/RouteCardsPage.vue'
import RouteDetailPage from '../pages/RouteDetailPage.vue'
import RouteUploadPage from '../pages/RouteUploadPage.vue'
import PrivacyPage from '../pages/PrivacyPage.vue'
import ContactPage from '../pages/ContactPage.vue'
import UserAdminPage from '../pages/UserAdminPage.vue'
import RatingAdminPage from '../pages/RatingAdminPage.vue'

const routes = [
    {
        path: '/',
        name: 'home',
        component: HomePage
    },
    {
        path: '/routes',
        name: 'routes',
        component: RouteCardsPage
    },
    {
        path: '/route/:id',
        name: 'route',
        component: RouteDetailPage
    },
    {
        path: '/search/:searchText',
        name: 'search',
        props: true,
        component: SearchPage
    },
    {
        path: '/upload',
        name: 'upload',
        component: RouteUploadPage
    },
    {
        path: '/privacy',
        name: 'privacy',
        component: PrivacyPage
    },
    {
        path: '/contact',
        name: 'contact',
        component: ContactPage
    },
    {
        path: '/user-admin',
        name: 'user-admin',
        component: UserAdminPage
    },
    {
        path: '/rating-admin',
        name: 'rating-admin',
        component: RatingAdminPage
    },
    {
        path: '/route-admin',
        name: 'route-admin',
        component: RouteAdminPage
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
